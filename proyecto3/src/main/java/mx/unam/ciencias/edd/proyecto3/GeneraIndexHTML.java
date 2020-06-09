package mx.unam.ciencias.edd.proyecto3;
import mx.unam.ciencias.edd.*;
import mx.unam.ciencias.edd.proyecto3.*;
import mx.unam.ciencias.edd.proyecto3.estructuras_svg.*;
import mx.unam.ciencias.edd.proyecto3.graficas.ManejaPalabras;
import mx.unam.ciencias.edd.proyecto3.graficas.ConteoPalabras;
import java.io.IOException;
import java.io.*;
/**
* Clase que analiza archivo por archivo y genera el cuerpo html de todo el proyecto
*/
public class GeneraIndexHTML{
  /* Variable que tiene el directorio donde se guardará todo el cuerpo del proyecto */
  private File directorioF;
  /* Lista con todos los archivos a analizar */
  private Lista<String> archivos;
  /* Archivos con su información */
  private Archivo[] listaArchivos;
  /**
  * Constructor de la clase GeneraIndexHTML
  * @param Lista<Strin lista con los archivos a analizar
  * @param String directorio
  */
  public GeneraIndexHTML(Lista<String> archivos, String dir){
    this.archivos = archivos;
    this.directorioF = new File(dir);
  }
  /* Método main del proyecto que se encarga de analizar todo */
  public void generaAnalisis(){
    // Si no existe el directorio, creamos el directorio
    if(!directorioF.exists())
      directorioF.mkdir();
    Diccionario<String, Integer> diccionario;
    File file;
    int i = 0;
    listaArchivos = new Archivo[archivos.getLongitud()];
    for(String archivo : archivos){
      file = new File(archivo);
      if(file.exists()){
        diccionario = ConteoPalabras.contarApariciones(archivo);
        if(diccionario != null){
          ManejaPalabras ap = new ManejaPalabras(diccionario, 20);
          try{
            FileWriter mw = new FileWriter(new File(directorioF, "archivo"+i+".html"));
            mw.write(ap.generaHTML());
            mw.close();
          }catch(IOException e){
            System.out.println("No se ha podido escribir el archivo: "+archivo);
          }
          listaArchivos[i] = new Archivo("archivo"+i+".html", diccionario, archivo);
          i++;
        }
      }else
          System.out.println("No existe el archivo: "+archivo);
    }
    escribeIndexHTML();
  }
  /**
  * Método que escribe el cuerpo del Index.html
  */
  public void escribeIndexHTML(){
    try{
      //LectorEntrada.escribirArchivo("archivo"+i+".html", ap.generaHTML());
      FileWriter mw = new FileWriter(new File(directorioF, "index.html"));
      mw.write(generaIndexHTML());
      mw.close();
    }catch(IOException e){
      System.out.println("No se ha podido escribir el archivo: index.html");
    }
  }
  /**
  * Método que genera el index html
  * @return representación en string del html del index.html
  */
  public String generaIndexHTML(){
    String cadena = "<html>\n";
    String link = "";
    for(Archivo arch : listaArchivos){
      if(arch != null){
        link = "<a "+" href= "+arch.toString()+">"+ arch.getNombre()+" </a>";
        cadena+="\n<h1>"+link+" tiene "+arch.getTotalPalabras()+"</h1>\n";
      }
    }
    cadena+=generaGrafica();
    return cadena+="</html>";
  }
  /**
  * Método para generar la gŕafica de los archivos (aquellos que tienen intersección de palabras)
  * @return String representación en SVG de la gráfica
  */
  public String generaGrafica(){
    DibujaGrafica<String> db = new DibujaGrafica<>();
    for(int i = 0; i < listaArchivos.length; i++)
      if(listaArchivos[i] != null) db.agrega(listaArchivos[i].getNombre());
    for(int i = 0; i < listaArchivos.length - 1; i++){
      for(int j = i + 1; j < listaArchivos.length; j++){
        if(listaArchivos[i] != null && listaArchivos[j] != null && listaArchivos[i].comparaArchivo(listaArchivos[j]))
          db.conecta(listaArchivos[i].getNombre(), listaArchivos[j].getNombre());
      }
    }
    return db.dibujaGrafica();
  }
}

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
  private String directorio;
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
    this.directorio = dir;
    this.archivos = archivos;
  }
  /* Método main del proyecto que se encarga de analizar todo */
  public void generaAnalisis(){
    // Veriticar si el directorio existe, de lo contrario, crearlo

    // ME FALTA VER SI EXISTE EL DIRECTORIO O NO, Y ESCRIBIR !!!EN!!!! EL DIRECTORIO


    Diccionario<String, Integer> diccionario;
    File file;
    int i = 0;
    listaArchivos = new Archivo[archivos.getLongitud()];
    for(String archivo : archivos){
      file = new File(archivo);
      if(file.exists()){
        diccionario = ConteoPalabras.contarApariciones(archivo);
        ManejaPalabras ap = new ManejaPalabras(diccionario, 25);
        try{
          System.out.println(diccionario.toString());

          // AQUÍ TENEMOS QUE ESCRIBIR EL ARCHIVO DENTRO DEL DIRECTORIO!
          LectorEntrada.escribirArchivo("archivo"+i+".html", ap.generaHTML());


        }catch(IOException e){
          System.out.println("No se ha podido escribir el archivo: "+archivo);
        }
        listaArchivos[i] = new Archivo("archivo"+i+".html", diccionario);
        i++;
      }
    }
  }
  /**
  * Método que genera el cuerpo del Index.html
  */
  public void generaIndexHtml(){

  }

  /**
  * Método para generar la gŕafica de los archivos (aquellos que tienen intersección de palabras)
  * @return String representación en SVG de la gráfica
  */
  public String generaGrafica(){
    DibujaGrafica<String> db = new DibujaGrafica<>();
    for(int i = 0; i < listaArchivos.length; i++)
      db.agrega(listaArchivos[i].toString());
    for(int i = 0; i < listaArchivos.length - 1; i++){
      for(int j = i + 1; j < listaArchivos.length; j++){
        if(listaArchivos[i].comparaArchivo(listaArchivos[j]))
          db.conecta(listaArchivos[i].toString(), listaArchivos[j].toString());
      }
    }
    return db.dibujaGrafica();
  }
}

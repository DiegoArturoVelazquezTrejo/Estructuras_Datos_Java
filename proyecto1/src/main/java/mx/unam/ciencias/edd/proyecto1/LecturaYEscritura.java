package mx.unam.ciencias.edd.proyecto1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
/**
* Clase para manejar los archivos de texto
* Operaciones: leer, escribir.
*/
public class LecturaYEscritura{

  /* Constructor privado para evitar que se intancie un objeto de esta clase */
  private LecturaYEscritura(){}

  /** Método para abrir y leer un archivo de texto
  * @param Lista de nombres de los archivos en donde se realizará la lectura
  * @return Lista de oraciones de contenido de los archivos
  */
  public static Lista<Oracion> leerArchivos(Lista<Oracion> archivos)throws IOException{
    Lista<Oracion> lista = new Lista<Oracion>();
    /* Realizando la lectura por cada archivo que se ingrese */
    for(Oracion oracion : archivos)
      leerArchivo(oracion.toString(), lista);
    return lista;
  }

  /**
  * Método para leer un archivo e insertarle a la lista las oraciones
  * @param String nombre del archivo
  * @param Lista de oraciones que se van acumulando
  */
  private static void leerArchivo(String nombre, Lista<Oracion> lista) throws IOException{
    FileInputStream fileIn = new FileInputStream(nombre);
    InputStreamReader isIn = new InputStreamReader(fileIn);
    BufferedReader in = new BufferedReader(isIn);
    String l = in.readLine();
    while(l != null){
      lista.agregaFinal(new Oracion(l));
      l = in.readLine();
    }
  }

  /** Método para escribir sobre un archivo de texto
  * @param Nombre del archivo donde se guardará el resultado del programa
  * @param Lista con la ordenación resultante del programa
  */
  public static void escribirArchivo(String documento, Lista<Oracion> ordenacion) throws IOException{
    FileOutputStream fileOut = new FileOutputStream(documento);
    OutputStreamWriter osOut = new OutputStreamWriter(fileOut);
    BufferedWriter out = new BufferedWriter(osOut);
    for(Oracion s : ordenacion){
      out.write(s.toString() + "\n");
    }
    out.close();
  }

  /** Método para leer de la consola
  * @return Lista con las oraciones leídas por la consola
  */
  public static Lista<Oracion> leerConsola(){
    Lista<Oracion> lista = new Lista<Oracion>();
    try(BufferedReader br = new BufferedReader(new InputStreamReader((System.in), "UTF-8"))){
      String linea = br.readLine();
      while(linea != null){
        lista.agregaFinal(new Oracion(linea));
        linea = br.readLine();
      }
    }
    catch(IOException e){
      System.out.print("Sucedió un error en la entrada estandar");
      System.exit(1);
    }
    System.out.print("\n");
    return lista;
  }

  /**
   * Imprimirá todos los elementos de una lista en consola.
   * @param lista: la lista que imprimirá.
   */
   public static <Oracion> void imprimeConsola(Lista<Oracion> lista){
     for(Oracion r : lista){
       System.out.print(r.toString() + "\n");
     }
   }

}

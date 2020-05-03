package mx.unam.ciencias.edd.proyecto3;
import mx.unam.ciencias.edd.Lista;
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
* Clase para leer la entrada del programa ya sea por consola o por archivo de texto
*/
public class LectorEntrada{

  /**
  * Método para leer un archivo que se pase como entrada
  * @param String ruta del archivo
  * @return String
  */
  public static String leerArchivo(String nombre) throws IOException{
    String contenido = "";
    FileInputStream fileIn = new FileInputStream(nombre);
    InputStreamReader isIn = new InputStreamReader(fileIn);
    BufferedReader in = new BufferedReader(isIn);
    String l = in.readLine();
    String[] trim;
    while(l != null){
      trim = l.trim().split("#");
      if(!trim.equals("")){
        contenido+=trim[0];
        contenido+=" ";
      }
      l = in.readLine();
    }
    return contenido;
  }
  /**
  * Método para leer los datos de la entrada estándar
  * @return String
  */
    public static String leerConsola(){
    String contenido = "";
    try(BufferedReader br = new BufferedReader(new InputStreamReader((System.in), "UTF-8"))){
      String[] trim;
      String linea = br.readLine();
      while(linea != null){
        trim = linea.trim().split("#");
        if(!trim.equals("")){
          contenido+=trim[0];
          contenido+=" ";
        }
        linea = br.readLine();
      }
    }
    catch(IOException e){
      System.out.print("Sucedió un error en la entrada estandar");
      System.exit(1);
    }
    System.out.print("\n");
    return contenido;
  }
  /** Método para escribir sobre un archivo de texto
  * @param Nombre del archivo donde se guardará el resultado del programa
  * @param String resultado del programa
  */
  public static void escribirArchivo(String documento, String oracion) throws IOException{
    FileOutputStream fileOut = new FileOutputStream(documento);
    OutputStreamWriter osOut = new OutputStreamWriter(fileOut);
    BufferedWriter out = new BufferedWriter(osOut);
    out.write(oracion + "\n");
    out.close();
  }

}

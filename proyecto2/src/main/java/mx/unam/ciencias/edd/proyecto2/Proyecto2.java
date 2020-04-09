package mx.unam.ciencias.edd.proyecto2;
import mx.unam.ciencias.edd.*;
import java.io.IOException;
/**
* Clase principal del proyecto2
*/
public class Proyecto2{
  /* Método main */
  public static void main(String[] args){
    /* String contendio de la lectura */
    String contenido = "";
    /* En caso de ser mayor, existe un archivo de texto que se ingresó como parámetro de entrada */
    if(args.length > 0 ){
      try{
          contenido = LectorEntrada.leerArchivo(args[0]);
      } catch(IOException e){
          System.out.println(e.toString());
      }
    }
    /*  El usuario ingresará la información por la entrada estándar */
    else
      contenido = LectorEntrada.leerConsola();
    /* Creamos el objeto con el que analizaremos la entrada */
    AnalizaEntrada a = new AnalizaEntrada(contenido);
    /* Mandamos a imprimir la estructura que el usuario haya escogido */
    System.out.println(a.imprimeEstructura());
  }
}

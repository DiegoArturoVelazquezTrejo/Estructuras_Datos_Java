package mx.unam.ciencias.edd.proyecto1;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
* Clase main para ejecutar el ordenador lexicográfico
*/
public class Main{

  /* Método main */
  public static void main(String[] args){
    try{
      Bandera bandera = new Bandera(args);
      /* Lista con el contenido de los archivos de texto */
      Lista<Oracion> contenido = new Lista<Oracion>();
      if(bandera.getArgumentos().getLongitud() == 0)
        contenido = LecturaYEscritura.leerConsola();
      else{
        /* Obteniendo los nombres de los archivos */
        Lista<Oracion> nombresArchivos = bandera.getArgumentos();
        /* Ahora pasamos a leer los archivos que se ingresaron */
        try{
          contenido = LecturaYEscritura.leerArchivos(nombresArchivos);
        }catch(IOException e){
          System.out.println("No se ha podido leer el archivo. ");
        }
      }
      /* Realiza la ordenación del conjunto de oraciones */
      contenido = contenido.mergeSort((a, b) -> a.compareTo(b));
      /* Verificando si el usuario especificó que se tenía que guardar el resultado */
      if(bandera.getGuarda()){
        try{
          if(bandera.getReversa())
            LecturaYEscritura.escribirArchivo(bandera.getDocumentoGuarda(), contenido.reversa());
          else
            LecturaYEscritura.escribirArchivo(bandera.getDocumentoGuarda(), contenido);
        }catch(IOException e){
          System.out.println("Se ha producido un error al momento de escribir");
        }
      }
      /* Imprimiendo en la consola con base en la bandera de reversa  */
      if(bandera.getReversa())
        LecturaYEscritura.imprimeConsola(contenido.reversa());
      else
        LecturaYEscritura.imprimeConsola(contenido);
    }catch(NoSuchElementException e){
      System.out.println("Se ha producido un error, verifique su entrada. ");
    }

  }
}

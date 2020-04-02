package mx.unam.ciencias.edd.proyecto1;
import java.util.NoSuchElementException;
/**
* Clase para analizar las banderas de entrada del programa
* Operaciones: analizar, getters y setters.
*/
public class Bandera{
  /* Arreglo con los parámetros del programa */
  private Lista<Oracion> argumentos;
  /* Variable reversa que indica si la bandera -r se encuentra en los argumentos. */
  private boolean reversa;
  /* Variable guarda que indica si se guardará el resultado del programa en un archivo */
  private boolean guarda;
  /* En caso que el usuario quiera guardar el resultado en un archivo, esta variable contiene el nombre de dicho archivo. */
  private String documentoGuarda;

  /**
  * OBSERVACIÓN:
  * Si al momento de obtener los argumentos, la lista es vacía, significa que se leerá de la consola.
  */

  /* Constructor por omisión de la clase*/
  public Bandera(String[] args){
    /* Metemos los argumentos en una lista de string */
    argumentos = new Lista<Oracion>();
    for(int i = 0; i < args.length; i++)
      argumentos.agregaFinal(new Oracion(args[i]));
    /* Analizaremos los argumentos que se le pasaron a la clase Bandera. */
    analiza();
  }
  /**
  * @return lista de argumentos
  */
  public Lista<Oracion> getArgumentos(){
    return this.argumentos;
  }
  /**
  * @return variable que indica si se busca reversa o no
  * <t> true si el usuario ingresó la bandera -r </t>
  * <t> false de lo contrario </t>
  */
  public boolean getReversa(){
    return this.reversa;
  }
  /**
  * @return variable que indica si se quiere guardar el resultado del programa
  * <t> true si el usuario ingresó la bandera -o </t>
  * <t> false de lo contrario </t>
  */
  public boolean getGuarda(){
    return this.guarda;
  }
  /**
  * @return nombre del archivo donde se guardará el resultado del programa.
  */
  public String getDocumentoGuarda(){
    return this.documentoGuarda;
  }
  /* Método que analiza los argumentos del programa */
  public void analiza(){
    Oracion rev = new Oracion("-r");
    Oracion guard = new Oracion("-o");
    /* Corrobora que exista la bandera de reversa en los argumentos */
    if(argumentos.busquedaLineal(rev, (a,b) -> a.compareTo(b))){
      this.reversa = true;
      argumentos.elimina(rev);
      while(argumentos.contiene(rev))
        argumentos.elimina(rev);
    }
    /* Corrobora que exista la bandera de guarda y el nombre del archivo para guardar */
    if(argumentos.busquedaLineal(guard, (a,b) -> a.compareTo(b))){
      this.guarda = true;
      int indiceDocumento = argumentos.indiceDe(guard);
      if(indiceDocumento+1 < argumentos.getLongitud()){
        documentoGuarda = argumentos.get(indiceDocumento+1).toString();
        argumentos.elimina(new Oracion(documentoGuarda));
      }else {
        // De no haber encontrado el nombre del archivo después de la bandera -o, lanza excepción
        throw new NoSuchElementException(" No has ingresado el nombre donde se guardará el archivo ");
      }
      argumentos.elimina(guard);
    }
  }
}

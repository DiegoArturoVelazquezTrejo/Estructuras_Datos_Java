package mx.unam.ciencias.edd.proyecto3;
import mx.unam.ciencias.edd.*;
import mx.unam.ciencias.edd.proyecto3.*;
import java.util.Iterator;

/**
* Clase que tratará a cada archivo como entidad para
* poder generar la gráfica y ver la intersección entre cada archivo
*/
public class Archivo{
  /* Conjunto de palabras para cada archivo */
  private Conjunto<String> palabras;
  /* Total de palabras que tiene cada archivo */
  private int totalPalabras;
  /* Nombre del archivo (referencia html)*/
  private String nombre;
  /**
  * Constructor de la clase Archivo
  * @param String nombre
  * @param Diccionario<String, Integer> palabras
  */
  public Archivo(String nombre, Diccionario<String, Integer> diccionario){
    this.nombre = nombre;
    this.totalPalabras = diccionario.getElementos();
    this.palabras = new Conjunto<String>();
    // Tenemos que llenar al conjunto con los elementos del diccionario
    Iterator<String> iteradorLlaves = diccionario.iteradorLlaves();
    while(iteradorLlaves.hasNext()){
      String llave = iteradorLlaves.next();
      palabras.agrega(llave);
    }
  }
  /**
  * Método para imprimir la representación en cadena del Archivo
  * @return String
  */
  @Override public String toString(){
    return this.nombre;
  }
  /**
  * Método que compara dos archivos y te dice si tienen en común palabras con más de 7 dígitos
  * @param Archivo
  * @return boolean true si tienen al menos una palabra en común con más de 7 dígitos, false de lo contrario
  */
  public boolean comparaArchivo(Archivo arch){
    Conjunto<String> interseccion = this.palabras.interseccion(arch.palabras);
    if(interseccion.getElementos() == 0) return false;
    for(String elemento : interseccion)
      if(elemento.length() >= 7) return true;
    return false;  
  }
}

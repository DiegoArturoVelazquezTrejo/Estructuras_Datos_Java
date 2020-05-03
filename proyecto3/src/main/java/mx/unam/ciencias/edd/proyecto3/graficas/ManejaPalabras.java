package mx.unam.ciencias.edd.proyecto3.graficas;
import mx.unam.ciencias.edd.*;
/**
* Clase para manejar las palabras que se ingresen y de ahí mandarlas a graficar en barras o en pastel
*/
public class ManejaPalabras{
  /* Lista con las palabras */
  private Lista<Palabra> palabras;
  /* total de palabras (equivalente al tamaño del diccionario )*/
  private int total_palabras;
  /* total de apariciones de todas las palabras */
  private int apariciones_total;
  /** Constructor de la clase que recibe datos para graficarlos
  *  @param Lista<String> palabras del archivo
  *  @param Lista<Integer> apariciones de cada palabra
  */
  // Vamos a cambiar ambas listas por un diccionario en donde las palabras son la clave y las apariciones son los datos
  public ManejaPalabras(Lista<String> pal, Lista<Integer> apariciones /* Aquí iría el diccionario*/){
    palabras = new Lista<>();
    /* Vamos a obtener las claves del diccionario e iterar sobre ellas para agregar palabras a la lista
      total = diccionario.getLongitud();
      for(String clave : diccionario.llaves()){
        apariciones_total+=diccionario.get(palabra);
        palabras.agrega(new Palabra(clave, diccionario.get(palabra)));
      }
    */
    total_palabras = pal.getLongitud();
    int i = 0;
    for(String palabra : pal)
      palabras.agrega(new Palabra(palabra, apariciones.get(i++)));
    /* Ordenamos la lista de palabras de acuerdo a las apariciones que tienen para facilitar su gráfica */
    palabras = palabras.mergeSort((a, b) -> b.compareTo(a));
  }
  /** Método que imprime la gráfica de pastel
  * @return Representación en cadena (svg) de la gráfica de pastel
  **/
  public String imprimePastel(){
    GraficaPastel gp = new GraficaPastel(palabras, apariciones_total);
    return gp.pastel();
  }
  /**
  * @return Representación en cadena (svg) de la gráfica de barras
  **/
  public String imprimeBarras(){
    // Similarmente al método de imprimePastel
    return "";
  }
}

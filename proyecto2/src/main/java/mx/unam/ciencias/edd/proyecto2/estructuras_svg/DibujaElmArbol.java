package mx.unam.ciencias.edd.proyecto2.estructuras_svg;
import mx.unam.ciencias.edd.*;
/**
* Clase para dibujar todo tipo de árbol binario
* Árboles Binarios Ordenados, Árboles Binarios Completos.
*/
public class DibujaElmArbol{

  /**
  * Método para dibujar una arista de un vértice a su vértice padre
  * @param int coordenada1 en x
  * @param int coordenada1 en y
  * @param int coordenada2 en x
  * @param int coordenada2 en y
  * @return Representación en cadena del arista
  */
  public  static  String dibujaArista(int x1, int y1, int x2, int y2){
    return "<line x1="+x1+" y1="+y1+" x2= "+x2+" y2=" + y2 +" style='stroke:#f00; stroke-width:3'></line>";
  }
  /**
  * Método para dibujar en vértice del árbol binario
  * @param int coordenada en x
  * @param int coordenada en y
  * @param VerticeArbolBinario vértice que se representará en cadena
  * @param Color color del vértice
  * @return Representación en cadena del arista
  */
  public static String dibujaNodo(int x, int y, VerticeArbolBinario vertice, Color c){
    String color = "";
    switch(c){
      case ROJO:
        color = "Red";
        break;
      case NEGRO:
        color = "Black";
        break;
      case NINGUNO:
        color = "Gray";
        break;
    }
    return "<circle cx= "+x+" cy= "+y+" r='25' fill="+color+"  /> <text x= "+x+" y= "+y+
    " text-anchor='middle' fill='white' font-size='10px' font-family='Arial' dy='.3em'>"+
    vertice.get()+"</text>";
  }
  /**
  * Método que genera las coordenadas X de todos los nodos que se graficarán
  * @param int número de nodos del arbol binario
  * @return int[] con las coordenadas en X
  */
  public static  int[] generaCoordenadasX(int numero){
    // Genera un arreglo con la coordenada en x para cada elemento del árbol binario
    int [] coordenadas = new int[numero];
    for(int i = 0;  i < numero; i++)
      coordenadas[i] = i*50;
    return coordenadas;
  }
  /**
  * Método que calcula la mitad de un número por nosotros
  * @param int numero
  */
  public static int calculaMitad(int x1, int x2){
    return (int)Math.floor((x1 + x2)/2);
  }
  /**
  * Método que nos dice si el vértice es una hoja o no
  * @param VerticeArbolBinario vertice
  * @return true si es hoja, false de lo contrario
  */
  public static  boolean esHoja(VerticeArbolBinario vertice){
    return !vertice.hayIzquierdo() && !vertice.hayDerecho();
  }
  /**
  * Método exclusivo para árboles binarios AVL
  * @param
  */
  public static String dibujaEtiqueta(int x, int y, String etiqueta){
    return "<text x= "+x+" y= "+y+" text-anchor='middle' fill='white' font-size='8px' font-family='Arial' dy='.3em'>"+
    etiqueta+"</text>";
  }
}

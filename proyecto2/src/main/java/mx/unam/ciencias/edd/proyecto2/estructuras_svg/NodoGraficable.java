package mx.unam.ciencias.edd.proyecto2.estructuras_svg;
/**
* Clase para determinar la forma en la que los nodos se van a imprimir.
* Estructuras de datos que implementarán de esta interfaz:
* Lista, Cola, Pila
*/
public class NodoGraficable{
  /**
  * Método que dibuja el cuadrado del nodo correspondiente
  * @param int coordenada en x del cuadrado
  * @param int coordenada en y del cuadrado
  * @param T elemento que irá dentro del cuadrado
  * @return Representación en cadena del cuadrado
  */
  public static String dibujaCuadrado     (int x1, int y1, T elemento){

  }
  /**
  * Método que dibuja la flecha correspondiente hacia la derecha (para colas)
  * @param int coordenada en x de la flecha
  * @param int coordenada en y de la flecha
  * @return Representación en cadena de la flecha
  */
  public static String dibujaFlechaDerecha(int x1, int y1){

  }
  /**
  * Método que dibuja la flecha correspondiente hacia ambas direcciones (para listas)
  * @param int coordenada en x de la flecha
  * @param int coordenada en y de la flecha
  * @return Representación en cadena de la doble flecha
  */
  public static String dibujaDobleFlecha  (int x1, int y1){

  }
}

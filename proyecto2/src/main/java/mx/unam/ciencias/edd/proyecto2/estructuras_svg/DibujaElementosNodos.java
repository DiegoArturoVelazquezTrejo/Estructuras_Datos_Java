package mx.unam.ciencias.edd.proyecto2.estructuras_svg;
/**
* Clase para determinar la forma en la que los nodos se van a imprimir.
* Estructuras de datos que implementarán de esta interfaz:
* Lista, Cola, Pila
*/
public class DibujaElementosNodos{
  /**
  * Método que dibuja el cuadrado del nodo correspondiente
  * @param int coordenada en x del cuadrado
  * @param int coordenada en y del cuadrado
  * @param String elemento que irá dentro del cuadrado
  * @return Representación en cadena del cuadrado
  */
  public static <T> String dibujaCuadrado(int x1, int y1, String elemento){
    int x = x1 + 35;
    int y = y1 + 25;
    return "<rect x= '"+ x1 +"' y= '"+ y1 +"' width='80' height='60' style='fill:white;stroke:black;' />\n"+
           "<text x= '"+x+"' y= '"+y+"' text-anchor='middle' fill='black' font-size='20px' font-family='Arial' dy='.3em'>"+elemento+"</text>\n";
  }
  /**
  * Método que dibuja la flecha correspondiente hacia la derecha (para colas)
  * @param int coordenada en x de la flecha
  * @param int coordenada en y de la flecha
  * @return Representación en cadena de la flecha
  */
  public static String dibujaFlechaDerecha(int x1, int y1){
    return "<svg x = '"+x1+"' y ='"+y1+"' width='25' height='20' xmlns='http://www.w3.org/2000/svg' fill-rule='evenodd' clip-rule='evenodd'>\n<path d='M21.883 12l-7.527 6.235.644.765 9-7.521-9-7.479-.645.764 7.529 6.236h-21.884v1h21.883z'/></svg>\n";
  }
  /**
  * Método que dibuja la flecha correspondiente hacia ambas direcciones (para listas)
  * @param int coordenada en x de la flecha
  * @param int coordenada en y de la flecha
  * @return Representación en cadena de la doble flecha
  */
  public static String dibujaDobleFlecha  (int x1, int y1){
    return "<svg x = '"+x1+"' y ='"+y1+"' width='200' height='50' xmlns='http://www.w3.org/2000/svg' fill-rule='evenodd' clip-rule='evenodd'>\n<path d='M21.883 12l-7.527 6.235.644.765 9-7.521-9-7.479-.645.764 7.529 6.236h-21.884v1h21.883z'/></svg>\n"+
           "<svg x = '"+x1+"' y ='"+y1+"' width='200' height='50' xmlns='http://www.w3.org/2000/svg' fill-rule='evenodd' clip-rule='evenodd'>\n<path d='M2.117 12l7.527 6.235-.644.765-9-7.521 9-7.479.645.764-7.529 6.236h21.884v1h-21.883z'/></svg>\n";
  }
  /**
  * Método que dibuja el cuadrado del nodo junto con flechas
  * @param int coordenada en x del cuadrado
  * @param int coordenada en y del cuadrado
    * @param String elemento que irá dentro del cuadrado
  * @return Representación en cadena del cuadrado
  */
  public static String dibujaNodo(int x1, int y1, String elemento, boolean dobleFlecha){
    String s = "";
    if(dobleFlecha)
      s+=dibujaDobleFlecha(x1+88, y1+15);
    else
      s+=dibujaFlechaDerecha(x1+88, y1+15);
    return s+=dibujaCuadrado(x1, y1, elemento);
  }

}

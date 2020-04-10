package mx.unam.ciencias.edd.proyecto2.estructuras_svg;
import mx.unam.ciencias.edd.*;
/**
* Clase para dibujar las estructuras de datos con nodos
*/
public class ListaColaPilaDibujable<T extends Comparable<T>>{
  /* Lista con los elementos que se agregarán a la estructura de datos*/
  private Lista<T> elementos;
  /*Cadena que contendrá la representación en SVG del árbol */
  private String estructuraSVG = "<?xml   version = \'1.0\' encoding = \'utf-8\' ?>\n";
  /**
  * Método que establece las dimensiones del svg en donde se presentará la estructura
  * @param int ancho
  * @param int alto
  */
  public void estableceDimensiones(int ancho,int alto){
      this.estructuraSVG+= "<svg width="+ancho+"  height= " + alto+ ">";
  }
  /**
  * Constructor de la clase ListaColaPilaDibujable
  * @param Lista<Integer> lista de ele mentos
  */
  public ListaColaPilaDibujable(Lista<T> l){
    this.elementos = l;
  }
  /**
  * Método que devuelve la representación en string svg de la colección que se pase
  * @param EstructuraDatos estructura mediante la cual los datos se representarán
  * @return String representación en string svg de los datos
  */
  public String dibujaEstructuraDatos(EstructuraDatos estructura){
    /* En caso de no tener elementos, no se dibuja nada */
    if(elementos.getLongitud() == 0)
      return "";
    String res = "";
    /* Checaremos de qué tipo de estructura se trata */
    switch(estructura){
      /* En caso de ser una lista, se concatena su representación en svg */
      case LISTA:
        res+=dibujaListaCola(true);
        break;
      /* En caso de ser una pila , se concatena su representación en svg */
      case PILA:
        res+=dibujaPila();
        break;
      /* En caso de ser una cola, se concatena su representación en svg */
      case COLA:
        res+=dibujaListaCola(false);
        break;
      case NINGUNO:
        res+="";
        break;
    }
    /* Concatenamos la etiqueta de cierre */
    return estructuraSVG+res;
  }
  /**
  * Método que representa en SVG a una lista o a una cola
  * @param boolean lista true si es una lista, false si es una cola
  */
  public String dibujaListaCola(boolean lista){
    String res = "";
    estableceDimensiones(elementos.getLongitud()*115, 300);
    int coordx = 10;
    int coordY = 50;
    while(elementos.getLongitud() > 1){
      /* Eliminamos el primer elemento, lo guardamos y lo representamos en svg*/
      T elemento = elementos.eliminaPrimero();
      res+= DibujaElementosNodos.dibujaNodo(coordx, coordY, elemento.toString(), lista);
      coordx+=85;
    }
    /* Agregamos el último elemento de la lista*/
    res+=DibujaElementosNodos.dibujaCuadrado(coordx, coordY, elementos.eliminaPrimero().toString());
    return res+"</svg>";
  }
  /**
  * Método que representa a una pila en SVG
  */
  public String dibujaPila(){
    String res = "";
    estableceDimensiones(80,elementos.getLongitud()*40);
    Lista<T> reversa = elementos.reversa();
    int coordY = 40;
    while(reversa.getLongitud() > 0){
      T elemento = reversa.eliminaPrimero();
      res+=DibujaElementosNodos.dibujaCuadrado(10, coordY, elemento.toString());
      coordY+=30;
    }
    return res+"</svg>";
  }

}

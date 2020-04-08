package mx.unam.ciencias.edd.proyecto2.estructuras_svg;
import mx.unam.ciencias.edd.*;
/**
* Clase que dibuja árboles utilizando herramientas de la clase
* DibujaElementosArbolBinario
* Implementaremos el método toSVG que será el método mediante el cual el arbol se imprima.
*/
public class DibujaArbol{
  /*Cadena que contendrá la representación en SVG del árbol */
  String arbolSVG = "<?xml version = \'1.0\' encoding = \'utf-8\' ?>\n";
  /*Lista que contendrá los elementos del árbol binario */
  Lista<Integer> elementos;

  /**
  * Método que establece las dimensiones del svg en donde se presentará el árbol
  */
  public void estableceDimensiones(){
    if(this.elementos.getLongitud() > 0)
      this.arbolSVG+= "<svg width="+elementos.getUltimo() +"  height= " + elementos.getLongitud()*80+ ">";
  }
  /**
  * Constructor de la clase DibujaArbol
  * @param Lista<Integer> lista de ele mentos
  */
  public DibujaArbol(Lista<Integer> l){
    this.elementos = l;
    estableceDimensiones();
  }

  public <T> String dibujaArbol(EstructuraDatos estructura){
    // Checaremos de qué tipo de arbol se trata
    // Si es Ordenado, creamos el arbol ordenado
    // Si es Completo, creamos el arbol completo
    // Si es ALV,  creamos el arbol AVL
    // si es Rojinegro, creamos el arbol rojinegro

    return "";
  }

  /**
  * Método que dibuja un arbol binario ordenado
  */
  public String dibujaArbolOrdenado(){
      ArbolBinarioOrdenado<Integer> arbol = new ArbolBinarioOrdenado<>(this.elementos);
      VerticeArbolBinario raiz = arbol.raiz();
      int    mitad = DibujaElmArbol.calculaMitad(this.elementos.getLongitud());
      int    coordY = 30;
      int [] coordsX = DibujaElmArbol.generaCoordenadasX(this.elementos.getLongitud());
      return DibujaElmArbol.dibujaNodo(coordsX[mitad], coordY, raiz, Color.NINGUNO)+
        dibujaArbol(coordsX, coordY, mitad, raiz.izquierdo(), 0, mitad)+
        dibujaArbol(coordsX, coordY, mitad, raiz.derecho(), mitad, this.elementos.getLongitud());
  }

  /**
  * Método que dibuja un arbol binario completo
  */
  public String dibujaArbolCompleto(){
      ArbolBinarioCompleto<Integer> arbol = new ArbolBinarioCompleto<>(this.elementos);
      VerticeArbolBinario raiz = arbol.raiz();
      int    mitad = DibujaElmArbol.calculaMitad(this.elementos.getLongitud());
      int    coordY = 30;
      int [] coordsX = DibujaElmArbol.generaCoordenadasX(this.elementos.getLongitud());
      return DibujaElmArbol.dibujaNodo(coordsX[mitad], coordY, raiz, Color.NINGUNO)+
        dibujaArbol(coordsX, coordY, mitad, raiz.izquierdo(), 0, mitad)+
        dibujaArbol(coordsX, coordY, mitad, raiz.derecho(), mitad, this.elementos.getLongitud());
  }

  /**
  * Método que dibuja un arbol binario rojinegro
  */
  public String dibujaArbolRojinegro(){
      ArbolRojinegro<Integer> arbol = new ArbolRojinegro<>(this.elementos);
      VerticeArbolBinario raiz = arbol.raiz();
      int    mitad = DibujaElmArbol.calculaMitad(this.elementos.getLongitud());
      int    coordY = 30;
      int [] coordsX = DibujaElmArbol.generaCoordenadasX(this.elementos.getLongitud());
      // Color color = (v.toString().substring(0,1).equals("R")) ? ROJO : NEGRO;
      return DibujaElmArbol.dibujaNodo(coordsX[mitad], coordY, raiz, Color.NEGRO)+
        dibujaArbol(coordsX, coordY, mitad, raiz.izquierdo(), 0, mitad)+
        dibujaArbol(coordsX, coordY, mitad, raiz.derecho(), mitad, this.elementos.getLongitud());
  }

  public String dibujaArbol(int[] coordsX, int coordY, int mitad, VerticeArbolBinario v, int i, int j){
    // Primero dibujamos el arista del vértice pasado al nuemo vértice
    // Caso base que es cuando el vértice es hoja
    // Si no es el caso base ya regresamos la representación del nodo etc etc 
    return "";
  }
}

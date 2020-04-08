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
  /* Variable que indica si el arbol que se quiere imprimir es AVL */
  boolean esArbolAVL = false;

  /**
  * Método que establece las dimensiones del svg en donde se presentará el árbol
  */
  public void estableceDimensiones(){
    if(this.elementos.getLongitud() > 0)
      this.arbolSVG+= "<svg width="+elementos.getUltimo()*70+"  height= " + elementos.getLongitud()*80+ ">";
  }
  /**
  * Constructor de la clase DibujaArbol
  * @param Lista<Integer> lista de ele mentos
  */
  public DibujaArbol(Lista<Integer> l){
    this.elementos = l;
    estableceDimensiones();
  }

  public String dibujaArbol(EstructuraDatos estructura){
    // Checaremos de qué tipo de arbol se trata
    switch(estructura){
      // En caso de ser un arbol ordenado, se concatena su representación en svg
      case ARBOLORDENADO:
        arbolSVG+=dibujaArbolOrdenado();
        break;
      // En caso de ser un arbol completo, se concatena su representación en svg
      case ARBOLCOMPLETO:
        arbolSVG+=dibujaArbolCompleto();
        break;
      // En caso de ser un arbol rojinegro, se concatena su representación en svg
      case ARBOLROJINEGRO:
        arbolSVG+=dibujaArbolRojinegro();
        break;
      // En caso de ser un arbol avl, se concatena su representación en svg
      case ARBOLAVL:
        esArbolAVL = true;
        arbolSVG+=dibujaARbolAVL();
        break;
    }
    // Concatenamos la etiqueta de cierre
    arbolSVG+="</svg>";
    return arbolSVG;
  }

  /**
  * Método que dibuja un arbol binario ordenado
  */
  public String dibujaArbolOrdenado(){
      ArbolBinarioOrdenado<Integer> arbol = new ArbolBinarioOrdenado<>(this.elementos);
      VerticeArbolBinario raiz = arbol.raiz();
      int    mitad = DibujaElmArbol.calculaMitad(0, this.elementos.getLongitud());
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
      int    mitad = DibujaElmArbol.calculaMitad(0, this.elementos.getLongitud());
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
      System.out.println(arbol.toString());
      int    mitad = DibujaElmArbol.calculaMitad(0, this.elementos.getLongitud());
      int    coordY = 30;
      int [] coordsX = DibujaElmArbol.generaCoordenadasX(this.elementos.getLongitud());
      return DibujaElmArbol.dibujaNodo(coordsX[mitad], coordY, raiz, Color.NEGRO)+
        dibujaArbol(coordsX, coordY, mitad, raiz.izquierdo(), 0, mitad)+
        dibujaArbol(coordsX, coordY, mitad, raiz.derecho(), mitad, this.elementos.getLongitud());
  }
  /**
  * Método que dibuja un arbol binario avl
  */
  public String dibujaARbolAVL(){
      if(elementos.getLongitud() == 0) return "";
      ArbolAVL<Integer> arbol = new ArbolAVL<>(this.elementos);
      VerticeArbolBinario raiz = arbol.raiz();
      int    mitad = DibujaElmArbol.calculaMitad(0, this.elementos.getLongitud());
      int    coordY = 30;
      int [] coordsX = DibujaElmArbol.generaCoordenadasX(this.elementos.getLongitud());
      // aquí necesitamos mandar a llamar la etiqueta del arbol avl
      String balance_altura = raiz.toString().substring(raiz.toString().length()-3, raiz.toString().length());
      String etiqueta = DibujaElmArbol.dibujaEtiqueta(coordsX[mitad], coordY, balance_altura);
      return etiqueta + DibujaElmArbol.dibujaNodo(coordsX[mitad], coordY, raiz, Color.NINGUNO)+
        dibujaArbol(coordsX, coordY, mitad, raiz.izquierdo(), 0, mitad)+
        dibujaArbol(coordsX, coordY, mitad, raiz.derecho(), mitad, this.elementos.getLongitud());
  }
  /**
  * Método para dibujar un árbol binario
  * @param int[] coordenadas en x
  * @param int coordY coordenada del nodo pasado, por lo que la nueva coordenada debe ser coordY+incremento
  * @param int mitad Posición en donde se encuentra la coordenada en X pasada (se usará para dibujar el arista)
  * @param VerticeArbolBinario vértice actual que se representará en svg (se tiene que ver si es o no hoja)
  * @param int i corresponde al límite inferior del arreglo
  * @param int j corresponde al límite superior del arreglo
  * i, j nos ayudarán a poder ir calculando las mitades de cada subarreglo para encontrar la coordenada en x para cada nodo
  */
  public String dibujaArbol(int[] coordsX, int coordY, int mitad, VerticeArbolBinario v, int i, int j){
    // Recalculamos la mitad
    int mit = DibujaElmArbol. calculaMitad(i, j);
    // Recalculamos la nueva coordenada en Y
    int coordYNueva = coordY + 30;
    // Primero dibujamos el arista del vértice pasado al nuemo vértice
    String arista = DibujaElmArbol.dibujaArista(coordsX[mitad], coordY, coordsX[mit], coordYNueva+10);
    // Tenemos que meternos en el lío de los colores
    Color color;
    if(v.toString().substring(0,1).equals("R"))
      color = Color.ROJO;
    else if(v.toString().substring(0,1).equals("N"))
      color = Color.NEGRO;
    else
      color = Color.NINGUNO;
    // Caso en donde el vértice no existe (Caso base 1)
    if(v == null)
      return "";
    String etiqueta = "";
    String balance_altura = "";
    if(esArbolAVL){
      // Obtenemos la subcadena del nodo que representa su altura/balance
      balance_altura = v.toString().substring(v.toString().length()-3, v.toString().length());
      // Creamos la etiqueta txt de svg
      etiqueta = DibujaElmArbol.dibujaEtiqueta(coordsX[mit]+8, coordYNueva-8, balance_altura);
    }
    // Caso base que es cuando el vértice es hoja (Caso base 2)
    if(DibujaElmArbol.esHoja(v))
      return etiqueta + arista + DibujaElmArbol.dibujaNodo(coordsX[mit],coordYNueva,v,color);
    // Si no es el caso base ya regresamos la representación del nodo etc etc
    // Vamos a ver si tiene hijo izquierdo
    if(!v.hayIzquierdo())
      return  etiqueta + arista + DibujaElmArbol.dibujaNodo(coordsX[mit],coordYNueva,v,color)+
        dibujaArbol(coordsX, coordYNueva, mit, v.derecho(), mit, j);
    // Vamos a ver si tiene hijo derecho
    if(!v.hayDerecho())
      return  etiqueta + arista + DibujaElmArbol.dibujaNodo(coordsX[mit],coordYNueva,v,color)+
        dibujaArbol(coordsX, coordYNueva, mit, v.izquierdo(), mit, j);
    // Tiene ambos hijos
    else
      return  etiqueta + arista + DibujaElmArbol.dibujaNodo(coordsX[mit],coordYNueva,v,color)+
        dibujaArbol(coordsX, coordYNueva, mit, v.izquierdo(), i, mit)+
        dibujaArbol(coordsX, coordYNueva, mit, v.derecho(), mit, j);
  }
  /**
  * Vamos a probar que se imprima correctamente el arbol binario
  * PRUEBA ÚNICAMENTE, SE TENDRÁ QUE ELIMINAR ESTE MÉTODO DESPUÉS
  */
  public static void main(String[] args){
    Lista<Integer> lista = new Lista<>();
    for(int i = 0; i < 12; i++)
      lista.agregaFinal(i);
    DibujaArbol d = new DibujaArbol(lista);
    String arbolR = d.dibujaArbol(EstructuraDatos.ARBOLAVL);
    System.out.println("A Continación se imprime el árbol rojinegro");
    System.out.println(arbolR);
  }
}

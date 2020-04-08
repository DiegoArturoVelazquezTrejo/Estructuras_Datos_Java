package mx.unam.ciencias.edd.proyecto2.estructuras_svg;
import mx.unam.ciencias.edd.*;
/**
* Clase que dibuja árboles utilizando herramientas de la clase
* DibujaElementosArbolBinario
* Implementaremos el método toSVG que será el método mediante el cual el arbol se imprima.
*/
public class DibujaArbol<T extends Comparable<T>>{
  /*Cadena que contendrá la representación en SVG del árbol */
  String arbolSVG = "<?xml version = \'1.0\' encoding = \'utf-8\' ?>\n";
  /*Lista que contendrá los elementos del árbol binario */
  Lista<T> elementos;
  /* Variable que indica si el arbol que se quiere imprimir es AVL */
  boolean esArbolAVL = false;
  /* Arreglo de las coordenadas en X */
  int [] coordsX;

  /**
  * Método que establece las dimensiones del svg en donde se presentará el árbol
  */
  public void estableceDimensiones(){
    if(this.elementos.getLongitud() > 0)
      this.arbolSVG+= "<svg width="+coordsX[coordsX.length-1]+10+"  height= " + elementos.getLongitud()*80+ ">";
  }
  /**
  * Constructor de la clase DibujaArbol
  * @param Lista<Integer> lista de ele mentos
  */
  public DibujaArbol(Lista<T> l){
    this.elementos = l;
    this.coordsX = DibujaElmArbol.generaCoordenadasX(this.elementos.getLongitud());
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
  public  String dibujaArbolOrdenado(){
      ArbolBinarioOrdenado<T> arbol = new ArbolBinarioOrdenado<>(this.elementos);
      VerticeArbolBinario raiz = arbol.raiz();
      int    mitad = DibujaElmArbol.calculaMitad(0, this.elementos.getLongitud());
      int    coordY = 30;
      String izq = "";
      String der = "";
      if(raiz.hayIzquierdo())
        izq = dibujaArbol(coordsX, coordY, mitad, raiz.izquierdo(), 0, mitad);
      if(raiz.hayDerecho())
        der = dibujaArbol(coordsX, coordY, mitad, raiz.derecho(), mitad, this.elementos.getLongitud());
      return DibujaElmArbol.dibujaNodo(coordsX[mitad], coordY, raiz, Color.NINGUNO)+ der + izq;
  }

  /**
  * Método que dibuja un arbol binario completo
  */
  public String dibujaArbolCompleto(){
      ArbolBinarioCompleto<T> arbol = new ArbolBinarioCompleto<>(this.elementos);
      VerticeArbolBinario raiz = arbol.raiz();
      int    mitad = DibujaElmArbol.calculaMitad(0, this.elementos.getLongitud());
      int    coordY = 30;
      String izq = "";
      String der = "";
      if(raiz.hayIzquierdo())
        izq = dibujaArbol(coordsX, coordY, mitad, raiz.izquierdo(), 0, mitad);
      if(raiz.hayDerecho())
        der = dibujaArbol(coordsX, coordY, mitad, raiz.derecho(), mitad, this.elementos.getLongitud());
      return DibujaElmArbol.dibujaNodo(coordsX[mitad], coordY, raiz, Color.NINGUNO)+ der + izq;
  }

  /**
  * Método que dibuja un arbol binario rojinegro
  */
  public String dibujaArbolRojinegro(){
      ArbolRojinegro<T> arbol = new ArbolRojinegro<>(this.elementos);
      VerticeArbolBinario raiz = arbol.raiz();
      int    mitad = DibujaElmArbol.calculaMitad(0, this.elementos.getLongitud());
      int    coordY = 30;
      String izq = "";
      String der = "";
      if(raiz.hayIzquierdo())
        izq = dibujaArbol(coordsX, coordY, mitad, raiz.izquierdo(), 0, mitad);
      if(raiz.hayDerecho())
        der = dibujaArbol(coordsX, coordY, mitad, raiz.derecho(), mitad, this.elementos.getLongitud());
      return DibujaElmArbol.dibujaNodo(coordsX[mitad], coordY, raiz, Color.NEGRO)+ der + izq;
  }
  /**
  * Método que dibuja un arbol binario avl
  */
  public String dibujaARbolAVL(){
      if(elementos.getLongitud() == 0) return "";
      ArbolAVL<T> arbol = new ArbolAVL<>(this.elementos);
      VerticeArbolBinario raiz = arbol.raiz();
      int    mitad = DibujaElmArbol.calculaMitad(0, this.elementos.getLongitud());
      int    coordY = 30;
      // aquí necesitamos mandar a llamar la etiqueta del arbol avl
      String balance_altura = raiz.toString().substring(raiz.toString().length()-4, raiz.toString().length());
      String etiqueta = DibujaElmArbol.dibujaEtiqueta(coordsX[mitad]+3, coordY-9, balance_altura);
      String izq = "";
      String der = "";
      if(raiz.hayIzquierdo())
        izq = dibujaArbol(coordsX, coordY, mitad, raiz.izquierdo(), 0, mitad);
      if(raiz.hayDerecho())
        der = dibujaArbol(coordsX, coordY, mitad, raiz.derecho(), mitad, this.elementos.getLongitud());
      return etiqueta + DibujaElmArbol.dibujaNodo(coordsX[mitad], coordY, raiz, Color.NINGUNO)+ der + izq;
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
    String arista = DibujaElmArbol.dibujaArista(coordsX[mitad], coordY, coordsX[mit], coordYNueva);
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
      balance_altura = v.toString().substring(v.toString().length()-4, v.toString().length());
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
    for(int i = 0; i < 8; i++)
      lista.agregaFinal(i);
    DibujaArbol d = new DibujaArbol(lista);
    String arbolR = d.dibujaArbol(EstructuraDatos.ARBOLROJINEGRO);
    System.out.println("A Continación se imprime el árbol rojinegro");
    System.out.println(arbolR);
  }
  /**
  ----------------------PRUEBA PARA IMRPIMIR NODOS EN DFS SVG
  int alturaX = 0;
  int xglobal = 0;
  public void dfsInOrder(Vertice vertice, int altura){
    if(vertice == null) return;
    dfsInOrder(vertice.izquierdo, alturaX++);
    System.out.println(vertice.get()+" ( "+(xglobal+=50) +", "+((alturaX--)*10)+" )");
    dfsInOrder(vertice.derecho, alturaX++ );
  }
  public void dfsI(){
    dfsInOrder(this.raiz, alturaX);
  }
  ----------------------PRUEBA PARA IMRPIMIR NODOS EN DFS SVG
  **/
}

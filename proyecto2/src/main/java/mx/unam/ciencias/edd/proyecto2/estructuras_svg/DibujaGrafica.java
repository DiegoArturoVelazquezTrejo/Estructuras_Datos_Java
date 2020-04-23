package mx.unam.ciencias.edd.proyecto2.estructuras_svg;
import mx.unam.ciencias.edd.*;
/**
* Clase para dibujar las estructuras de datos con nodos
* A pesar que se puede imprimir la gráfica analizando únicamente los datos de entrada,
* vamos a hacer uso de una estructura gráfica, principalmente para poder usar el método de son vecinos y ver como éste funciona.
* Cabe aclarar que no es la manera más óptima, sin embargo pretendo utilizar distintos métodos que la clase gráfica me puede ofrecer.
*/
public class DibujaGrafica<T extends Comparable<T>>{
  /* Clase interna que nos permitirá representar cada vértice con una coordenada x, y y su respectivo elemento */
  private class Punto{
    /* Elemento del punto */
    public T elemento;
    /* Coordenada en x*/
    public double x;
    /* Coordenada en y*/
    public double y;
    /**
    * Constructor de la clase Punto
    * @param int coordenada en x
    * @param int coordenada en y
    * @param T elemento
    * @param Color color
    */
    public Punto(double x,double y, T elemento ){
      this.x = x;
      this.y = y;
      this.elemento = elemento;
    }
  }
  /* Lista con los elementos que se agregarán a la estructura de datos*/
  private Lista<T> elementos;
  /* Gráfica que almacenará los elementos */
  public Grafica<T> graf = new Grafica<>();
  /*Cadena que contendrá la representación en SVG del árbol */
  private String estructuraSVG = "";
  /**
  * Constructor de la clase DibujaGrafica
  * @param Lista<T> datos
  */
  public DibujaGrafica(Lista<T> lista){
    for(T elemento : lista)
      try{
        graf.agrega(elemento);
      }catch(Exception e){
        System.out.println("No se pueden ingresar elementos repetidos");
      }
  }
  /**
  * Método que establece las dimensiones del svg en donde se presentará la estructura
  * @param int ancho
  * @param int alto
  */
  public void estableceDimensiones(int ancho,int alto){
      this.estructuraSVG+= "<svg width='"+ancho+"'  height= '" + alto+ "' >\n";
  }
  /**
  * Método que genera Puntos de acuerdo a los elementos de la gráfica (vértices)
  * @return Lista<Punto> lista con los vértices de la gráfica representados con Puntos
  */
  public Lista<Punto> generaPuntos(){
    Lista<Punto> listaPuntos = new Lista<>();
    for(T elemento : graf){
      listaPuntos.agregaFinal(new Punto(0, 0, elemento)); // X va a tener un incremento, Y va a ser producto de una función circular
    }
    return listaPuntos;
  }
  /**
  * Método que genera la cadena de los puntos
  * @param Lista<Puntos> lista con los puntos que se graficarán
  * @return String con los puntos representados en svg
  */
  public String dibujaPuntos(Lista<Punto> listaPuntos){
    String vertices = "";
    for(Punto p : listaPuntos)
      vertices+=dibujaVertice(p);
    return vertices;
  }
  /**
  * Método para dibujar cada Punto
  * @param Punto que se dibujará
  * @return String representación en svg del putno
  */
  public String dibujaVertice(Punto p){
      return dibujaNodo(p.x, p.y, p.elemento);
  }
  /**
  * Método que dibuja cada nodo de la gráfica
  * @param int coordenada en x
  * @param int coordenada en y
  * @param T elemento
  * @return Representación en svg del nodo
  */
  public String dibujaNodo(double x, double y, T elemento){
    double y1 = y+2;
    String colorletra = "Black";
    return "<circle cx= '"+x+"' cy= '"+y+"' r='10' stroke='black' fill='white'  />\n <text x= '"+x+"' y= '"+y1+
    "' text-anchor='middle' fill='"+ colorletra+"' font-size='8px' font-family='Arial' dy='.1em'>"+
    elemento.toString()+"</text>\n";
  }
  /**
  * Método para agregar la representación en svg de los aristas
  * @param Lista<Puntos> puntos que se conectarán
  * @return String representación de los aristas en svg
  */
  public String dibujaAristas(Lista<Punto> listaPuntos){
    String aristas = "";
    for(Punto p : listaPuntos){
      for(Punto q : listaPuntos){
        if(graf.sonVecinos(p.elemento, q.elemento))
          aristas+=conectaVertices(p, q);
      }
    }
    return aristas;
  }
  /**
  * Método para conectar dos Puntos
  * @param Punto p1
  * @param Punto p2
  * @return String representación del arista
  */
  public String conectaVertices(Punto p1, Punto p2){
    return dibujaArista(p1.x,p1.y,p2.x,p2.y);
  }
  /**
  * Método para dibujar una arista de un vértice a su vértice padre
  * @param double coordenada1 en x
  * @param double coordenada1 en y
  * @param double coordenada2 en x
  * @param double coordenada2 en y
  * @return Representación en cadena del arista
  */
  public String dibujaArista(double x1, double y1, double x2, double y2){
    double y1N, y2N;
    y1N = (y1 < 200) ? y1+8 : y1 -8;
    y2N = (y2 < 200) ? y2+8 : y2 -8;
    return "<line x1='"+x1+"' y1='"+y1N+"' x2='"+x2+"' y2='" + y2N+"' style='stroke:black; stroke-width:1'></line>\n";
  }
  /**
  * Método que asigna las coordenadas en x, y a cada punto
  * @param Lista<Punto> listaPuntos
  */
  public void asignaCoordenadas(Lista<Punto> listaPuntos){
    if(listaPuntos.getLongitud() == 1){
      listaPuntos.get(0).x = 250;
      listaPuntos.get(0).y = 200;
      return;
    }
    int mitad = listaPuntos.getLongitud()/2;
    Lista<Punto> lista2 = new Lista<>();
    for(int i = 0; i < mitad; i++)
      lista2.agregaFinal(listaPuntos.eliminaPrimero());
    /* Vamos a ir imprimiendo la mitad de los nodos por la parte superior de la circunferencia y la otra mitad por la parte inferior*/
    double xSuperior, xInferior, ySuperior, yInferior;
    xSuperior = 250;
    xInferior = 650;
    ySuperior = yInferior = 200;
    double deltaX1 = 400/listaPuntos.getLongitud();
    double deltaX2 = 400/lista2.getLongitud();
    /* Asignamos coordenadas a la mitad de los vértices en la media circunferencia superior */
    for(Punto p : listaPuntos){
      p.x = xSuperior;
      p.y = ySuperior;
      xSuperior = xSuperior+deltaX1;
      ySuperior = funcionCircular(xSuperior, 1);
    }
    /* Asignamos coordenadas a la mitad de los vértices en la media circunferencia inferior */
    for(Punto p : lista2){
      p.x = xInferior;
      p.y = yInferior;
      xInferior = xInferior-deltaX2;
      yInferior = funcionCircular(xInferior, -1);
    }
    for(Punto p : lista2)
      listaPuntos.agregaFinal(p);
  }
  /**
  * Ecuación de la circunferencia
  * @param Int x
  * @param Int signo (negativo si queremos la parte inferior de la circunferencia, positivo si queremos la parte superior )
  * @return Double y
  */
  public double funcionCircular(double x, int signo){
    return (signo * Math.sqrt(Math.pow(200,2)-Math.pow((x-450),2))) + 210;
  }
  /**
  * Método para dibujar la gráfica
  * @return String representación en svg de la gráfica
  */
  public String dibujaGrafica(){
    estableceDimensiones(700, 420);
    Lista<Punto> listaPuntos = generaPuntos();
    if(listaPuntos.getLongitud() == 0) return "Gráfica Vacía";
    asignaCoordenadas(listaPuntos);
    String svgPuntos = dibujaPuntos(listaPuntos);
    String svgAristas = dibujaAristas(listaPuntos);
    return estructuraSVG+svgPuntos+svgAristas+"</svg>";
  }
}

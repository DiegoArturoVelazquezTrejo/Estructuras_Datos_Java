package mx.unam.ciencias.edd.proyecto3.graficas;
import mx.unam.ciencias.edd.*;
import mx.unam.ciencias.edd.proyecto3.*;
import java.io.IOException;
/**
* Clase para dibujar las gráficas de pastel
* Dibujará una gráfica de pastel en svg con base en los datos que se le pasen.
*/
public class GraficaPastel{
  /* Clase privada de punto para modelar los puntos en el svg */
  private class Punto{
    /* Coordenada en X*/
    double x;
    /* Coordenada en Y */
    double y;
    /* Elemento que se graficará*/
    String dato;
    /* Porcentaje que ocupa sobre el total */
    double porcentaje;
    /*Coordenada en x para la etiqueta */
    double x_etiqueta;
    /* Coordenada en y para la etiqueta */
    double y_etiqueta;
    /* Constructor de la clase Punto */
    public Punto(double x, double y, String dato, double porcentaje, double xe, double ye){
      this.x = x;
      this.y = y;
      this.dato = dato;
      this.porcentaje = porcentaje;
      this.x_etiqueta = xe;
      this.y_etiqueta = ye;
    }
    public String toString(){
      return dato;
    }
  }
  /* Lista con los puntos que se graficarán */
  private Lista<Punto> puntos;
  /* Centro trasladado en X */
  private int nuevoCentroX = 270;
  /* Centro trasladado en Y */
  private int nuevoCentroY = 270;
  /* Radio de la Circunferencia */
  private int radio = 200;
  /* String con la representación de la gráfica en SVG*/
  private String cadena = "<?xml    version = \'1.0\' encoding = \'utf-8\' ?>\n<svg width = 590 height = 560 >\n";

  /* Método que construye los puntos en la gráfica de acuerdo a los datos  */
  public GraficaPastel(Lista<Palabra> palabras, int totalApariciones){
    puntos = new Lista<>();
    double x, y, angulo, angulo_pasado, angulo_etiqueta, xe, ye;
    float porcentaje;
    angulo = 0;
    angulo_pasado = 0;
    for(Palabra palabra : palabras){
      angulo = angulo + ((double)(palabra.getApariciones() * 360)/(double)totalApariciones);
      x = radio * Math.cos(Math.toRadians(angulo)) + nuevoCentroX;
      y = radio * Math.sin(Math.toRadians(angulo)) + nuevoCentroY;
      angulo_etiqueta = angulo_pasado+((angulo - angulo_pasado)/2);
      xe = radio * Math.cos(Math.toRadians(angulo_etiqueta)) + nuevoCentroX;
      ye = radio * Math.sin(Math.toRadians(angulo_etiqueta)) + nuevoCentroY;
      porcentaje = ((float)palabra.getApariciones()/(float)totalApariciones) * 100;
      puntos.agrega(new Punto(x, y, palabra.getPalabra(), Math.floor(porcentaje), xe, ye));
      angulo_pasado = angulo;
    }
  }
  /* Con el método de grafica pastel, solo tendremos que graficar cada punto con una línea hasta el centro de
  *la Circunferencia
  */
  /**
  * Método que regresa la representación en svg de la gráfica de pastel
  * @return String representación en svg de la gráfica de pastel
  */
  public String pastel(){
    String linea, etiqueta;
    cadena+=dibujaCirculo();
    for(Punto punto : puntos){
      linea= dibujaLinea(punto.x, punto.y);
      etiqueta = dibujaEtiqueta(punto.dato, punto.porcentaje, punto.x_etiqueta, punto.y_etiqueta);
      cadena+=linea+etiqueta;
    }
    cadena+="</svg>";
    return cadena;
  }
  /**
  * Método para dibujar una línea en svg
  * @param double x1
  * @param double y1
  * @return Representación en SVG de una línea
  */
  public String dibujaLinea(double x1, double y1){
    return "<line x1='"+x1+"' y1='"+y1+"' x2= '"+nuevoCentroX+"' y2='" + nuevoCentroY +"' style='stroke:black; stroke-width:1'></line>\n";
  }
  /**
  * Método para dibujar un cículo
  * @return Círculo en Svg
  */
  public String dibujaCirculo(){
    return "<circle cx="+ nuevoCentroX +" cy="+ nuevoCentroY +" r="+ radio +" stroke='red' stroke-width='3' fill='transparent' />\n";
  }
  /**
  * Método para dibujar la etiqueta del dato
  * @param String dato
  * @param double porcentaje
  * @param double x1
  * @param double y1
  * @return representación en svg de la etiqueta del dato
  */
  public String dibujaEtiqueta(String dato, double porcentaje, double x1, double y1){
    String[] colores = {"black", "blue", "orange", "red", "pruple", "brown", "gray", "green", "pink"};
    String color = colores[(int)(Math.random()*colores.length)];
    return "<text x= '"+x1+"' y= '"+y1+"' text-anchor='middle' fill='"+ color +"' font-size='20px' font-family='Arial' dy='.3em'>"+
    dato+ " "+porcentaje+"% "+"</text>\n";
  }

  /**
  * Método exclusivamente para pruebas
  */
  public static void main(String[] args){
    int limite = Integer.valueOf(args[0]);
    String[] pal = {"informacion", "data", "covid", "automata", "criptografia"};
    int val[] = {24, 20, 27, 9, 17};
    Lista<Palabra> palabras = new Lista<>();
    int repeticiones = 0;
    int rep = 0;
    for(int i = 0; i < pal.length; i++){
      rep = (int)(Math.random()*limite);
      palabras.agrega(new Palabra(pal[i], rep));
      repeticiones+=rep;
    }
    GraficaPastel gp = new GraficaPastel(palabras, repeticiones);
    try{
      LectorEntrada.escribirArchivo("res.html",gp.pastel());
    }catch(IOException e){}
  }
}

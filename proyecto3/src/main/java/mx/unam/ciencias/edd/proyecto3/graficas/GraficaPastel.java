package mx.unam.ciencias.edd.proyecto3.graficas;
import mx.unam.ciencias.edd.*;
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
    /* Constructor de la clase Punto */
    public Punto(double x, double y, String dato, double porcentaje){
      this.x = x;
      this.y = y;
      this.dato = dato;
      this.porcentaje = porcentaje;
    }
  }
  /* Lista con los puntos que se graficarán */
  private Lista<Punto> puntos;
  /* Centro trasladado en X */
  private int nuevoCentroX = 250;
  /* Centro trasladado en Y */
  private int nuevoCentroY = 250;
  /* Radio de la Circunferencia */
  private int radio = 200;
  /* String con la representación de la gráfica en SVG*/
  private String cadena = "<?xml    version = \'1.0\' encoding = \'utf-8\' ?>\n<svg width = 520 height = 520>\n";

  /* Método que construye los puntos en la gráfica de acuerdo a los datos  */
  public GraficaPastel(Lista<Palabra> palabras, int totalApariciones){
    puntos = new Lista<>();
    double x, y, angulo;
    float porcentaje;
    angulo = 0;
    for(Palabra palabra : palabras){
      // Vamos a checar la asignación del ángulo que al parecer está causando problemas 
      angulo = angulo + ((palabra.getApariciones() * 360)/totalApariciones);
      x = radio * Math.cos(angulo) + nuevoCentroX;
      y = radio * Math.sin(angulo) + nuevoCentroY;
      porcentaje = ((float)palabra.getApariciones()/(float)totalApariciones) * 100;
      puntos.agrega(new Punto(x, y, palabra.getPalabra(), Math.floor(porcentaje)));
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
      etiqueta = dibujaEtiqueta(punto.dato, punto.porcentaje, punto.x, punto.y);
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
    return "<circle cx="+ nuevoCentroX +" cy="+ nuevoCentroY +" r="+ radio +" stroke='black' stroke-width='3' fill='transparent' />\n";
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
    double x = 0;
    if(x1 <= 200 && y1 <= 200) x = x1 +20;
    if(x1 <= 200 && y1 >= 200) x = x1 +20;
    if(x1 >= 200 && y1 >= 200) x = x1 -20;
    if(x1 >= 200 && y1 <= 200) x = x1 -20;
    return "<text x= '"+x+"' y= '"+y1+"' text-anchor='middle' fill='red' font-size='20px' font-family='Arial' dy='.3em'>"+
    dato+ " "+porcentaje+"% "+"</text>\n";
  }

  /**
  * Método exclusivamente para pruebas
  */
  public static void main(String[] args){
    String[] pal = {"informacion", "data", "covid", "automata", "criptografia"};
    int[] repet = {20, 20,20, 20, 20};
    Lista<Palabra> palabras = new Lista<>();
    int repeticiones = 0;
    int rep = 0;
    for(int i = 0; i < pal.length; i++){
      rep = repet[i];
      palabras.agrega(new Palabra(pal[i], rep));
      repeticiones+=rep;
    }
    GraficaPastel gp = new GraficaPastel(palabras, repeticiones);
    System.out.println(gp.pastel());
  }
}

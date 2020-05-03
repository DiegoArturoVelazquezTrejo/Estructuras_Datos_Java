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
  private int nuevoCentroX = 210;
  /* Centro trasladado en Y */
  private int nuevoCentroY = 210;
  /* Radio de la Circunferencia */
  private int radio = 200;

  /* Método que construye los puntos en la gráfica de acuerdo a los datos  */
  public GraficaPastel(Lista<Palabra> palabras, int totalApariciones){
    puntos = new Lista<>();
    double x, y, angulo;
    angulo = 0;
    for(Palabra palabra : palabras){
      angulo = angulo + (palabra.getApariciones() * 360)/totalApariciones;
      x = radio * Math.cos(angulo) + nuevoCentroX;
      y = radio * Math.sin(angulo) + nuevoCentroY;
      puntos.agrega(new Punto(x, y, palabra.getPalabra(), palabra.getApariciones()/totalApariciones));
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
    return "";
  }

  /*

  Necesito un método para hacer una línea desde las coordenadas x, y hasta los dos centros (nuevoCentroX, nuevoCentroY)
  Necesito un método para hacer una única circunferencia con base en los dos centros trasladados y su radio
  Necesito un método para hacer la etiqueta que tenga el porcentaje y la palabra
  Necesito un método para ajustar la width y height de la gráfica
  Unirlos todos en el método de pastel en donde iterará por punto y utilizará todos los métodos anteriores para graficar

  */
}

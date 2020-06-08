package mx.unam.ciencias.edd.proyecto3.graficas;
import mx.unam.ciencias.edd.*;
import mx.unam.ciencias.edd.proyecto3.*;
import mx.unam.ciencias.edd.proyecto3.estructuras_svg.*;
import java.io.IOException;
import java.util.Iterator;
/**
* Clase para manejar las palabras que se ingresen y de ahí mandarlas a graficar en barras o en pastel
*/
public class ManejaPalabras{
  /* Lista con las palabras */
  private Lista<Palabra> palabras;
  /* total de palabras (equivalente al tamaño del diccionario )*/
  private int total_palabras;
  /* total de apariciones de todas las palabras */
  private int apariciones_total;
  /* Variable que nos indica el porcentaje de palabras que se tomará sobre el total para graficar */
  int porcentajeDePalabras;
  /** Constructor de la clase que recibe datos para graficarlos
  *  @param Diccionario<String, Integer> diccionario con las palabras
  *  @param int porcentaje de palabras que se graficará
  */
  public ManejaPalabras(Diccionario<String, Integer> diccionario, int porcentaje){
    porcentajeDePalabras = porcentaje;
    Lista<Palabra> palabrasTotales = new Lista<>();
    palabras  = new Lista<>();
    // Iteramos sobre las llaves del diccionario para obtener las apariciones por palabra
    Iterator<String> iteradorLlaves = diccionario.iteradorLlaves();
    while (iteradorLlaves.hasNext()) {
        String s = iteradorLlaves.next();
        apariciones_total+=diccionario.get(s);  // PORCENTAJE ABSOLUTO ALL 100% DE LAS APARICIONES
        palabrasTotales.agrega(new Palabra(s, diccionario.get(s)));
    }
    total_palabras = diccionario.getElementos();
    /* Ordenamos la lista de palabras de acuerdo a las apariciones que tienen para facilitar su gráfica */
    palabrasTotales = palabrasTotales.mergeSort((a, b) -> b.compareTo(a));
    // Aquí tengo que determinar un corte (N% de las palabras que utilizaremos)
    int corte_palabras = (diccionario.getElementos() > 20) ? (int)Math.ceil((diccionario.getElementos() * porcentajeDePalabras) / 100) : diccionario.getElementos();
    Iterator<Palabra> iterador = palabrasTotales.iterator();
    int i = 0;
    while(iterador.hasNext() && i < corte_palabras){
      Palabra pal = iterador.next();
      //apariciones_total+=pal.getApariciones(); // PORCENTAJE RELATIVO AL 15% DE LAS APARICIONES
      palabras.agrega(pal);
      i++;
    }
  }
  /** Método que imprime la gráfica de pastel
  * @return Representación en cadena (svg) de la gráfica de pastel
  **/
  public String imprimePastel(){
    // Chance aquí tenga que hacer un corte de las palabras (a ver )
    GraficaPastel gp = new GraficaPastel(palabras, apariciones_total);
    return gp.pastel();
  }
  /**
  * @return Representación en cadena (svg) de la gráfica de barras
  **/
  public String imprimeBarras(){
    GraficaBarras gb = new GraficaBarras(palabras, apariciones_total);
    return gb.barras();
  }
  /**
  * Método que genera la representación en cadena de un arbol rojinegro
  * @param Lista de datos
  * @return String representación en svg del arbol rojinegro
  */
  public String obtieneRojinegro(){
    if(palabras == null || palabras.getLongitud() == 0) return "";
    // Tengo que generar la lista de mayores apariciones
    Lista<Integer> mayoresApariciones = new Lista<>();
    Iterator<Palabra> iterador = palabras.iterator();
    int i = 0;
    while(iterador.hasNext() && i < 15){
      Palabra pal = iterador.next();
      mayoresApariciones.agrega(pal.getApariciones());
      i++;
    }
    DibujaArbol ar = new DibujaArbol(mayoresApariciones);
    return ar.dibujaArbol(EstructuraDatos.ARBOLROJINEGRO);
  }
  /**
  * Método que genera la representación en cadena de un arbol avl
  * @param Lista de datos
  * @return String representación en svg del arbol avl
  */
  public String obtieneAVL(){
    if(palabras == null || palabras.getLongitud() == 0) return "";
    // Tengo que generar la lista de mayores apariciones
    Lista<Integer> mayoresApariciones = new Lista<>();
    Iterator<Palabra> iterador = palabras.iterator();
    int i = 0;
    while(iterador.hasNext() && i < 15){
      Palabra pal = iterador.next();
      mayoresApariciones.agrega(pal.getApariciones());
      i++;
    }
    DibujaArbol ab = new DibujaArbol(mayoresApariciones);
    return ab.dibujaArbol(EstructuraDatos.ARBOLAVL);
  }
  /**
  * Método que genera el archivo html en cadena String
  * @return String representación en cadena del reporte en html
  */
  public String generaHTML(){
    String a1 = "<html>\n<body>\n<br><h1 style='align = center; margin-left: 20px; font-family: Courier; '>Informe de Palabras en el Documento 1</h1>\n";
    String ap = "<div style='margin-left: 20px; margin-right: 20px; border: 3px solid #73AD21; font-family: Garamond'> Lista de palabras: "+ palabras.toString()+"</div>\n<br><div style = 'display: table-cell'>\n";
    String a2 = "</div>\n<div style = 'display: table-cell'>";
    String a3 = "</div>\n<div style = 'display: flex; flex-wrap: nowrap;'>\n<div>\n<h3 style = 'background-color: #91D1FF;  padding: 6px; font-family: Garamond; margin-left: 10px; margin-right: 50em; display: table-cell'>Árbol AVL de las palabras con mayores apariciones</h3>";
    String a4 = "</div>\n<div>\n<h3 style = 'background-color: #91D1FF; padding: 6px; font-family: Garamond; margin-left: 10px; margin-right: 50em; display: table-cell'>Árbol Rojinegro de las palabras con mayores apariciones</h3>";
    String a5 = "</div>\n</div>\n</body>\n</html>";
    return a1 +ap+ imprimePastel() + a2 + imprimeBarras() + a3 + obtieneAVL() + a4 + obtieneRojinegro() + a5;
  }
}

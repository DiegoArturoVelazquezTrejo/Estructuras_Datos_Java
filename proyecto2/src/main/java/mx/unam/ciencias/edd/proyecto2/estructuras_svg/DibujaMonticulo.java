package mx.unam.ciencias.edd.proyecto2.estructuras_svg;
import mx.unam.ciencias.edd.*;

/**
* Clase para dibujar monticulos en SVG
*/

public class DibujaMonticulo{
  /* Lista con los elementos iniciales */
  private Lista<Integer> elementos;

  public DibujaMonticulo(Lista<Integer> lista){
    this.elementos = lista;
  }
  /**
  * Método que dibuja un montículo
  * @return String representación en svg del montículo
  */
  public String dibujaMonticulo(){

    Lista<ValorIndexable<Integer>> l1 = new Lista<>();
    for(Integer elemento : elementos){
      double valor = elemento;
      l1.agregaFinal(new ValorIndexable<Integer>(elemento, valor));
    }
    MonticuloMinimo<ValorIndexable<Integer>> minHeap = new MonticuloMinimo<>(l1);
    Lista<Integer> lista2 = new Lista<>();
    for(int i = 0; i < minHeap.getElementos(); i++)
      lista2.agregaFinal(minHeap.get(i).getElemento());
    DibujaArbol<Integer> dibAr = new DibujaArbol<>(lista2);
    System.out.println(minHeap.toString()); 
    return dibAr.dibujaArbol(EstructuraDatos.ARBOLCOMPLETO);
  }
}

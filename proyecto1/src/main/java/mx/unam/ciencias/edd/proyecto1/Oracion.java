package mx.unam.ciencias.edd.proyecto1;
/* Biblioteca para poder comparar oraciones de texto*/
import java.text.Collator;

/**
* Clase para almacenar la información de una oración.
* Operaciones: compareTo
*/
public class Oracion implements Comparable<Oracion>{
  /* Oración */
  private String oracion;
  /**
  * @param String oracion
  */
  public Oracion(String oracion){
    this.oracion = oracion;
  }
  /**
  * @return Oracion
  */
  @Override public String toString(){
    return this.oracion;
  }
  /**
  * Método equals para el objeto Oración
  * @param Objeto a comaprar
  * @return <t>true</t> si son iguales
            <t>false</t> de lo contrario 
  */
  @Override public boolean equals(Object o ){
    Oracion or = (Oracion) o;
    return or.toString().equals(this.oracion);
  }
  /**
  * Método para comparar oraciones
  * @param Oracion a comparar
  * @return <t>menor a cero implica que la oración es mayor </t>
            <t>mayor a cero implica que la oración es menor </t>
            <t>igual a cero implica que son iguales </t>
  */
  @Override public int compareTo(Oracion c){
      Collator collator = Collator.getInstance();
      collator.setStrength(Collator.PRIMARY);
      return collator.compare(oracion.replaceAll("\\P{L}+",""), c.toString().replaceAll("\\P{L}+",""));
  }
}

package mx.unam.ciencias.edd.proyecto2;
import mx.unam.ciencias.edd.*;
import mx.unam.ciencias.edd.proyecto2.estructuras_svg.*;
/**
* Método para analizar la entrada del usuario
*/
public class AnalizaEntrada{
  /* String con el contenido del archvo o de la entrada estándar. */
  private String contenido = "";
  /* Tipo de estructura que el usuario haya escgido */
  private EstructuraDatos estructura = EstructuraDatos.NINGUNO;
  /* Lista que contendrá los datos que se pasarán a la estructura*/
  private Lista<Integer> datos = new Lista<>();
  /**
  * Constructor de la clase AnalizaEntrada
  * @param Lista<String> lectura;
  */
  public AnalizaEntrada(String contenido ){
    this.contenido = contenido;
  }

  /**
  * Método que obtiene los datos de la entrada que proporcionó el usuario
  */
  public void obtieneDatosEntrada(){
      String estructura = "";
      String numero = "";
      for(int i = 0; i < contenido.length(); i++){
        if(contenido.charAt(i) != ' '){
          // Caso en el que sea una letra (Aumentamos la cadena de estructura )
          if(Character.isLetter(contenido.charAt(i))) estructura+=contenido.charAt(i);
          // Caso en que sea un número (Aumentamos la cadena de numero)
          if(esNumero(Character.toString(contenido.charAt(i)))) numero+=contenido.charAt(i);
        }else if(contenido.charAt(i)== ' '){
          // Hacemos el cast del numero a int y lo agregamos a datos
          try{
            int data = Integer.parseInt(numero);
            datos.agregaFinal(data);
          }catch(Exception e){}
          //datos.agregaFinal(data);
          numero = "";
        }
      }
      determinaEstructura(estructura.toLowerCase());
  }
  /**
  * Método que asigna la estructura ingresada
  */
  public void determinaEstructura(String e){
    switch(e){
      case "arbolrojinegro":
        this.estructura = EstructuraDatos.ARBOLROJINEGRO;
        break;
      case "arbolavl":
        this.estructura = EstructuraDatos.ARBOLAVL;
        break;
      case "arbolcompleto":
        this.estructura = EstructuraDatos.ARBOLCOMPLETO;
        break;
      case "arbolordenado":
        this.estructura = EstructuraDatos.ARBOLORDENADO;
        break;
      case "cola":
        this.estructura = EstructuraDatos.COLA;
        break;
      case "lista":
        this.estructura = EstructuraDatos.LISTA;
        break;
      case "pila":
        this.estructura = EstructuraDatos.PILA;
        break;
      }
  }
  /**
  * Método que verifica si un string es un número o no
  * @param String cadena a verificar
  * @return true si es numero, false de lo contrario
  */
  public static boolean esNumero(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return str.chars().allMatch(Character::isDigit);
    }
  /**
  * Método que obtiene la representación en cadena en svg de la estructura que se haya escgido
  * @return Representación en cadena svg de los datos
  */
  public String imprimeEstructura(){
    obtieneDatosEntrada();
    String resultado = "";
    if(estructura.equals(EstructuraDatos.LISTA) || estructura.equals(EstructuraDatos.PILA) || estructura.equals(EstructuraDatos.COLA)){
      ListaColaPilaDibujable<Integer> l = new ListaColaPilaDibujable<>(datos);
      resultado = l.dibujaEstructuraDatos(estructura);
    }else if(estructura.equals(EstructuraDatos.GRAFICA))
      resultado = ""; // Cuando agregue la gŕafica al proyecto, ésta irá aquí
    else{
      DibujaArbol<Integer> dibAr = new DibujaArbol<>(datos);
      resultado = dibAr.dibujaArbol(estructura);
    }
    /* En este caso no se identificó la estructura que se ingresaba y no se pudo asignar correctamente
    o también se ingresaron caracteres adicionales por lo que se produjo un error. */
    if(estructura == EstructuraDatos.NINGUNO)
      return "Se ha producido un error en tu entrada";
    return resultado;
  }
}

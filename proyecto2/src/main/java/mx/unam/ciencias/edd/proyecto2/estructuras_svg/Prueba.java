package mx.unam.ciencias.edd.proyecto2.estructuras_svg;
import mx.unam.ciencias.edd.*;

public class Prueba{
  public static void main(String[] agrs){

    Lista<Integer> lista = new Lista<>();
    for(int i = 0; i < 100; i++)
      lista.agregaFinal((int)(Math.random()*50+1));


    DibujaArbol<Integer> dibAr = new DibujaArbol<>(lista.copia());
    DibujaArbol<Integer> dibAr1 = new DibujaArbol<>(lista.copia());
    DibujaArbol<Integer> dibAr2 = new DibujaArbol<>(lista.copia());
    DibujaArbol<Integer> dibAr3 = new DibujaArbol<>(lista.copia());
    ListaColaPilaDibujable<Integer> dibLCP = new ListaColaPilaDibujable<>(lista.copia());
    ListaColaPilaDibujable<Integer> dibLCP1 = new ListaColaPilaDibujable<>(lista.copia());
    ListaColaPilaDibujable<Integer> dibLCP2 = new ListaColaPilaDibujable<>(lista.copia());
    // Comenzamos a dibujar
    // Arbol rojinegro
    System.out.println("Arbol Rojinegro: ");
    System.out.println(dibAr.dibujaArbol(EstructuraDatos.ARBOLROJINEGRO));
    // Arbol AVL
    System.out.println("Arbol AVL: ");
    System.out.println(dibAr1.dibujaArbol(EstructuraDatos.ARBOLAVL));
    // Arbol ordenado
    System.out.println("Arbol ORDENADO: ");
    System.out.println(dibAr2.dibujaArbol(EstructuraDatos.ARBOLORDENADO));
    // Arbol Completo
    System.out.println("Arbol COMPLETO: ");
    System.out.println(dibAr3.dibujaArbol(EstructuraDatos.ARBOLCOMPLETO));
    // Dibuja Lista
    System.out.println("LISTA: ");
    System.out.println(dibLCP.dibujaEstructuraDatos(EstructuraDatos.LISTA));
    // Dibuja cola
    System.out.println("COLA: ");
    System.out.println(dibLCP1.dibujaEstructuraDatos(EstructuraDatos.COLA));
    // Dibuja Pila
    System.out.println("PILA: ");
    System.out.println(dibLCP2.dibujaEstructuraDatos(EstructuraDatos.PILA));
  }
}

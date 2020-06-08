package mx.unam.ciencias.edd.proyecto3;
import mx.unam.ciencias.edd.*;
import mx.unam.ciencias.edd.proyecto3.*;
import mx.unam.ciencias.edd.proyecto3.graficas.ManejaPalabras;
import mx.unam.ciencias.edd.proyecto3.graficas.ConteoPalabras;
import java.io.IOException;
/**
* Clase principal del proyecto2
*/
public class Proyecto3{
  public static void main(String[] args){
    if(args.length == 0){
      System.err.println("Uso: java -jar proyecto3.jar NombreArchivo");
      System.exit(1);
    }
    Diccionario<String, Integer> dic1 = ConteoPalabras.contarApariciones(args[0]);
    System.out.println(dic1.toString());
    ManejaPalabras gp = new ManejaPalabras(dic1, Integer.valueOf(args[1]));
    try{
      LectorEntrada.escribirArchivo("res.html",gp.generaHTML());
    }catch(IOException e){}
  }
}

//Lo que necesito ahora hacer es que por cada archivo de texto me genere el html 

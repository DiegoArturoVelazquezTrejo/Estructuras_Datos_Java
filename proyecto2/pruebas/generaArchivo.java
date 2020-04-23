import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
/**
* Clase que genera un archivo de pruena
*/
public class GeneraArchivo{

  public static void main(String args[]){
    String res = "";
    if(args.length != 0 && !args[0].equals("grafica")){
      res+=args[0]+" ";
      res+=GeneraArchivo.generaNumerosAleatorios();
    }
    else{
      res+=args[0]+" ";
      res+=GeneraArchivo.generaGrafica();
    }
    try{
      GeneraArchivo.escribirArchivo("resultado.txt", res);
    }catch(IOException e){}
  }

  public static String generaNumerosAleatorios(){
    String s = "";
    int limite = (int)(Math.random()*40+1);
    for(int i = 0; i < limite; i++){
      int a = (int)(Math.random()*999+1);
      s+=Integer.toString(a)+" ";
    }
    return s;
  }

  public static String generaGrafica(){
    String string = "";
    int limite = 40; //(int)(Math.random()*40+1);
    int[] numeros = new int[limite];
    for(int i = 0; i < limite; i++){
      for(int j = 0; j < limite; j++){
        string+=Integer.toString(i) + " "+ Integer.toString(j);
      }
    }
    /*
    for(int i = 0; i < limite; i++)
      numeros[i] = (int)(Math.random()*40+1);
    for(int i = 0; i < limite/2; i++){
      int numero1 = (int)(Math.random()*limite);
      int numero2 = (int)(Math.random()*limite);
      string+=Integer.toString(numeros[numero1]) + " "+ Integer.toString(numeros[numero2]);
    }
    */
    return string;
  }
  /** Método para escribir sobre un archivo de texto
  * @param Nombre del archivo donde se guardará el resultado del programa
  * @param Lista con la ordenación resultante del programa
  */
  public static void escribirArchivo(String documento, String oracion) throws IOException{
    FileOutputStream fileOut = new FileOutputStream(documento);
    OutputStreamWriter osOut = new OutputStreamWriter(fileOut);
    BufferedWriter out = new BufferedWriter(osOut);
    out.write(oracion + "\n");
    out.close();
  }

}

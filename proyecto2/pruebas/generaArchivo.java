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
    if(args.length != 0 && !args[0].toLowerCase().equals("grafica")){
      res+=args[0]+" ";
      res+=GeneraArchivo.generaNumerosAleatorios();
    }else if(args.length != 0 && args[0].toLowerCase().equals("grafica")){
      ArrayList<Integer> lista = new ArrayList<>();
      int limite = (int)(Math.random()*100+1);
      res+=args[0]+" ";
      for(int i = 0; i < limite; i++){
        lista.add(i);
        res+=Integer.toString(i)+" ";
      }
      res+="  :";
      for(int i = 0; i < lista.size(); i++){
        int aleatorio = (int)(Math.random()*limite);
        res+=lista.get(i).toString()+"-"+lista.get(aleatorio).toString()+",";
      }
    }
    try{
      GeneraArchivo.escribirArchivo("resultado.txt", res);
    }catch(IOException e){}
  }

  public static String generaNumerosAleatorios(){
    String s = "";
    int limite = (int)(Math.random()*100+1);
    for(int i = 0; i < limite; i++){
      int a = (int)(Math.random()*10+1);
      s+=Integer.toString(a)+" ";
    }
    return s;
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

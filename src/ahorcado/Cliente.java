package ahorcado;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Slayer
 */
public class Cliente{
    public static void main (String args[]) throws IOException{
        //pruebas para el socket   
        try {
        Socket cl = new Socket("localhost", 7777);
        BufferedReader br1 = new BufferedReader(new InputStreamReader(cl.getInputStream()));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c =0;
        while ( c++<4)
         System.out.println(br1.readLine());
         PrintWriter outtext = new PrintWriter (new OutputStreamWriter(cl.getOutputStream() ));  
         // colocar la entrada de la opcion
         int opcion = Integer.parseInt(br.readLine());
         outtext.println(opcion); // escibe con numero todo hasta las letras en el server haré cast (char)
         outtext.flush();

         //predicciones culereas
        //palabra a adivinar
        System.out.println("Adivina la que podria entrar en los espacios letra a letra palabra:\n"+ br1.readLine());
      
        // empieza a mandar las letras para adivinar para acabar mandare un cero para que puedas terminar el while
       boolean flag = true;
       //String a= "Mode Berserker bro como el shiji ikxxxxxxxri"; //de prueba pa ganar
       int cont=0; // de prueba pa ganar
       while (flag ){
       outtext.println(""+br.readLine());
       //outtext.println(""+a.charAt(cont++)); // de prueba
       outtext.flush();
       
       
       
       String    aux = br1.readLine(); 
       flag=!aux.equals("0");
       System.out.println(aux);
        }
       System.out.println("Resultados:\n"+br1.readLine());

        }catch (IOException e ){
        System.out.print("Error: "+e.toString());
        }
    }
}

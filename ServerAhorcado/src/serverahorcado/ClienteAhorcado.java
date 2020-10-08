/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverahorcado;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author Slayer
 */
public class ClienteAhorcado {
    public static void main (String args[]) throws IOException{
        //pruebas para el socket   
        try {
        Socket cl = new Socket("localhost", 3000);
        BufferedReader br1 = new BufferedReader(new InputStreamReader(cl.getInputStream()));
        int c =0;
        while ( c++<4)
            System.out.println(br1.readLine());
         OutputStreamWriter outtext = new OutputStreamWriter(cl.getOutputStream() );  
         // colocar la entrada de la opcion
         outtext.write(1); // escibe con numero todo hasta las letras en el server haré cast (char)
         outtext.flush();

         //predicciones culereas
        //palabra a adivinar
        System.out.println("hola "+ br1.readLine());
       
        
        }catch (IOException e ){
        System.out.print("Error: "+e.toString());
        }
    }
}

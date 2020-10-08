/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverahorcado;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.IntStream;

/**
 *
 * @author Slayer
 */
public class ServerAhorcado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.print("Hello my first program with sockets is a game");
        String facil[] = {"Hello","Bro","Oso","Perro"};
        String medio[] = {"Hola bro","en corto","ta feo el socket","una frase XD"};
        String dificil[] = {"Mode Berserker bro como el shiji ikari","Ya me sueño programando sockets en java"};

        
        try {
        //inicializacion del socket
            ServerSocket serverSocket =new ServerSocket(3000);
            System.out.print("init serve");
            while(true){
            // el Cliente se conecta y comienza el game
            Socket s = serverSocket.accept();
            System.out.print("Conectado esperando seleccion de peronaje");
            PrintWriter outtext = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
            BufferedReader  inputtext =new  BufferedReader(new InputStreamReader(s.getInputStream()));
            outtext.write("Elige una opcion\n1-Facil\n2.-Medio\n3.-Dificil\n");
           outtext.flush();
            int a = Integer.parseInt(""+inputtext.read());
            System.out.print("La opcion elegida es: "+ a);
            String palabra="";
            switch (a){
                case 1: 
                   palabra=facil[(int) Math.random()*(facil.length -1 )]; 
                break;
                case 2:
                    palabra=medio[(int) Math.random()*(medio.length -1 )]; 
                    break;  
                case 3:
                    palabra=dificil[(int) Math.random()*(dificil.length -1 )]; 
                    break ; 
                default : break ; 
            }
            //algoritmo para encontrar la palabra
            String Adivinada="";
            for (int i =0 ; i< palabra.length() ;i++ ){
                 if (palabra.charAt(i)!=' '){
                 Adivinada+='_';
                 }else Adivinada+=' ';
            }
            
            outtext.println(Adivinada);
            outtext.flush();
            
            }
            
        }catch (IOException e){
           System.out.print(e.toString());
        }
        
    }
    private int encontrar (){
        
       return 0; }
}

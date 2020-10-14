package ahorcado;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class Servidor {
  static String Adivinada="";
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
            ServerSocket serverSocket =new ServerSocket(7777);
            System.out.print("init serve");
            while(true){
            // el Cliente se conecta y comienza THE GAME
            Socket s = serverSocket.accept();
            System.out.print("Conectado esperando seleccion de peronaje");
            PrintWriter outtext = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
            BufferedReader  inputtext =new  BufferedReader(new InputStreamReader(s.getInputStream()));
            outtext.print("Elige una opcion\n1-Facil\n2.-Medio\n3.-Dificil\n");
           outtext.flush();
           Adivinada=""; 
           int a = Integer.parseInt(""+(char)inputtext.readLine().toCharArray()[0]);
            System.out.println("La opcion elegida es: "+ a);
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
            System.out.println(palabra); 
            //algoritmo para encontrar la palabra
          
            for (int i =0 ; i< palabra.length() ;i++ ){
                 if (palabra.charAt(i)!=' '){
                 Adivinada+='_';
                 }else Adivinada+=' ';
            }
            int intentos = 5;
            
            outtext.println(Adivinada+"intentos"+5);
            outtext.flush();
            while (intentos>0){
                if (!Adivinada.contains("_"))
                    break;
                String aux= inputtext.readLine();
                System.out.println(aux);
                if (encontrar(palabra,aux.charAt(0))==0){
                        intentos--;}
                outtext.println(Adivinada+" intentos "+intentos);
                outtext.flush();
                
         
                
            }
            //flag de fin del juego
                outtext.println("0");
                outtext.flush();
                //final
                outtext.println("Game over: "+(intentos==0?"Perdiste":"ganaste"));
                outtext.flush();
                System.out.println("final");
            }
            
        }catch (IOException e){
           System.out.print(e.toString());
        }
        
    }
    private static int encontrar (String palabra,char letra){
       int flag =0; //cuenta la cantidad de letras que coinciden 
       char temp[]= Adivinada.toCharArray();
       for (int i =0 ; i< palabra.length();i++){
        if (Character.toLowerCase(letra)==Character.toLowerCase(palabra.charAt(i))){
             temp[i]=palabra.charAt(i);
             flag ++; 
        }
        Adivinada=new String(temp);
       }
       return flag; }
}

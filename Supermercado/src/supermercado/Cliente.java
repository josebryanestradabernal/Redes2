package supermercado;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.swing.JFileChooser;

public class Cliente {
    public static void main(String[] args){
        
        try {
            ArrayList<Producto> productos = new ArrayList<>();
            for (;;) {
                Socket cl = new Socket("localhost", 8000);
                BufferedReader br1 = new BufferedReader(new InputStreamReader(cl.getInputStream()));
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                ObjectInputStream ois = new ObjectInputStream(cl.getInputStream());
                try{
                    productos  = (ArrayList<Producto>) ois.readObject();
                }catch(ClassNotFoundException e){
                    System.out.println("El servidor no regresó el catálogo.");
                    e.printStackTrace();
                }
                
                
                System.out.println("Objeto recibido desde " + cl.getInetAddress() + ":" + cl.getPort() + " con los datos:");
                for(int i=0;i<productos.size();i++){
                    System.out.println("Nombre:"+productos.get(i).getNombre());
                    System.out.println("Id:"+productos.get(i).getId());
                    System.out.println("Descripción:"+productos.get(i).getDescripcion());
                    System.out.println("Existencias:"+productos.get(i).getExistencias());
                    System.out.println("Promoción:"+productos.get(i).getPromocion());
                    System.out.println("Vigencia:"+productos.get(i).getVigencia().toString());
                    System.out.println("Precio: $"+productos.get(i).getPrecio()+"\n");
                }
                
                for (int i = 0; i < 5; i++) { // For para que imprima todas las líneas.
                    System.out.println(br1.readLine());
                }
                PrintWriter outtext = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
                int opcion = Integer.parseInt(br.readLine());
                outtext.println(opcion);
                outtext.flush();
                switch (opcion) {
                    case 1:
                        cl.close();
                        break;
                    case 2:
                        
                        cl.close();

                        break;
                    case 3:
                        
                        break;
                    case 4:
                        cl.close();
                        System.exit(0);
                    default:
                        cl.close();
                        break;
                }
            }

        } catch (IOException e) {
            System.out.print("Error: " + e.toString());
        }
    }
}
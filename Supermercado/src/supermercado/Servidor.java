package supermercado;

import java.net.*;
import java.io.*;
import java.util.Date;

import java.util.ArrayList;

public class Servidor {

    public static void main(String[] args) {
        
        try {
            
            
            int pto = 8000;
            ServerSocket s = new ServerSocket(pto);
            s.setReuseAddress(true);
            System.out.println("Servidor iniciado.");
            File f = new File("");
            String ruta = f.getAbsolutePath();
            String carpeta = "productos";
            String ruta_archivos = ruta + "\\" + carpeta + "\\";
            System.out.println("ruta:" + ruta_archivos);
            File f2 = new File(ruta_archivos);
            f2.mkdirs();
            f2.setWritable(true);

            for (;;) {
                ArrayList<Producto> catalogo=crearProductos();
                System.out.println("Esperando cliente...");
                Socket cl = s.accept();
                System.out.println("Cliente conectado desde " + cl.getInetAddress() + ":" + cl.getPort());
                PrintWriter outtext = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
                BufferedReader inputtext = new BufferedReader(new InputStreamReader(cl.getInputStream()));
                ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream());
                oos.writeObject(catalogo);
                oos.flush();
                
                /*outtext.print("Elige una opcion\n1-Ver Archivos\n2.-Subir archivos\n3.-Descargar Archivo\n4.-Salir.\n");
                outtext.flush();

                switch (Integer.parseInt("" + (char) inputtext.readLine().toCharArray()[0])) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }*/
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static ArrayList<Producto> crearProductos(){
        ArrayList<Producto> catalogo=new ArrayList<>();
        String[] imagenes = new String[2];
        
        imagenes[0]="refresco1.jpg";
        imagenes[1]="refresco2.jpg";
        catalogo.add(new Producto("Refresco","1","bebida 2LT",10,(float)25.49,2,
                new Date(2020,10,25),imagenes));
        
        imagenes[0]="pan1.jpg";
        imagenes[1]="pan2.jpg";
        catalogo.add(new Producto("Pan","2","Alimento 675g",20,(float)34.00,2,
                new Date(2020,10,28),imagenes));
        
        imagenes[0]="jabon1.jpg";
        imagenes[1]="jabon2.jpg";
        catalogo.add(new Producto("Jabon de manos","3","Jabon 600g",50,(float)12.00,3,
                new Date(2020,10,26),imagenes));
        
        imagenes[0]="sopa1.jpg";
        imagenes[1]="sopa2.jpg";
        catalogo.add(new Producto("Sopas","4","Paquete de sopa 300g",10,(float)7.50,3,
                new Date(2020,10,23),imagenes));
 
        return catalogo;
    }
}

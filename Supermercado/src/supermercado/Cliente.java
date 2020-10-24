package supermercado;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.swing.JFileChooser;

public class Cliente {
    public static void main(String[] args){
        
        try {
            ArrayList<Producto> productos = new ArrayList<>();
            ArrayList<ItemCarrito> carrito = new ArrayList<>();
            float monto=0;
            for (;;) {
                Socket cl = new Socket("localhost", 8000);
                BufferedReader br1 = new BufferedReader(new InputStreamReader(cl.getInputStream()));
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
                
                for (int i = 0; i < 5; i++) { // For para que imprima todas las líneas.
                    System.out.println(br1.readLine());
                }
                PrintWriter outtext = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
                int opcion = Integer.parseInt(br.readLine());
                outtext.println(opcion);
                outtext.flush();
                switch (opcion) {
                    case 1:
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
                        System.out.println("Teclea el ID del producto a elegir:");
                        String opc = br.readLine();
                        Producto item=null;
                        for(int i=0;i<productos.size();i++){
                            if(productos.get(i).getId().equals(opc)){
                               item=productos.get(i);
                            }
                        }
                        if(item==null){
                            System.out.println("Producto no disponible.");
                            outtext.println("0");
                            outtext.flush();
                            cl.close();
                            break;
                        }
                        outtext.println(opc);
                        outtext.flush();
                        System.out.println("Producto elegido:"+item.getNombre());
                        System.out.println("1.- Ver Imágenes\n2.- Agregar al carrito.");
                        switch(br.readLine()){
                            case "1":
                                outtext.print("1");
                                outtext.flush();
                                break;
                            case "2":
                                outtext.println("2");
                                outtext.flush();
                                if(item.getExistencias()>0){
                                    System.out.println("Escribe la cantidad:");
                                    int cantidad = Integer.parseInt(br.readLine());
                                    if(item.getExistencias()-cantidad>=0){
                                        outtext.println(Integer.toString(cantidad));
                                        outtext.flush();
                                        ItemCarrito nuevo = new ItemCarrito(item.getNombre(),item.getPrecio(),cantidad);
                                        monto=monto+nuevo.getMonto();
                                        System.out.println("Item Agregado al carrito! :)\n");
                                        cl.close();
                                        break;
                                    }
                                    else{
                                        outtext.println("0");
                                        outtext.flush();
                                        System.out.println("Producto no disponible...");
                                        cl.close();
                                        break;
                                    }
                                }
                                break;
                            default:
                                outtext.print("0");
                                outtext.flush();
                                break;
                        }
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
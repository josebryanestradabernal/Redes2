package drive;

import java.net.*;
import java.io.*;

public class Servidor {

    public static void main(String[] args) {
        try {
            int pto = 8000;
            ServerSocket s = new ServerSocket(pto);
            s.setReuseAddress(true);
            System.out.println("Servidor iniciado.");
            File f = new File("");
            String ruta = f.getAbsolutePath();
            String carpeta = "archivos";
            String ruta_archivos = ruta + "\\" + carpeta + "\\";
            System.out.println("ruta:" + ruta_archivos);
            File f2 = new File(ruta_archivos);
            f2.mkdirs();
            f2.setWritable(true);

            for (;;) {
                System.out.println("Esperando cliente...");
                Socket cl = s.accept();
                System.out.println("Cliente conectado desde " + cl.getInetAddress() + ":" + cl.getPort());
                PrintWriter outtext = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
                BufferedReader inputtext = new BufferedReader(new InputStreamReader(cl.getInputStream()));
                outtext.print("Elige una opcion\n1-Ver Archivos\n2.-Subir archivos\n3.-\n4.-Salir.\n");
                outtext.flush();

                switch (Integer.parseInt("" + (char) inputtext.readLine().toCharArray()[0])) {
                    case 1:
                        System.out.println("Mostrando directorio al Cliente...\n ");
                        String[] files = f2.list();
                        String directorio = "";
                        outtext.println("" + files.length);
                        outtext.flush();
                        for (int i = 0; i < files.length; i++) {
                            directorio = directorio + "-" + files[i] + "\n";
                        }
                        outtext.println(directorio);
                        outtext.flush();
                        break;
                    case 2:
                        int narchivos = Integer.parseInt("" + (char) inputtext.readLine().toCharArray()[0]);
                        for(int i=0;i<narchivos;i++){
                            cl = s.accept();
                            DataInputStream dis = new DataInputStream(cl.getInputStream());
                            String nombre = dis.readUTF();
                            long tam = dis.readLong();
                            System.out.println("Comienza descarga del archivo " + nombre + " de " + tam + " bytes\n\n");
                            DataOutputStream dos = new DataOutputStream(new FileOutputStream(ruta_archivos + nombre));
                            long recibidos = 0;
                            int l = 0, porcentaje = 0;
                            while (recibidos < tam) {
                                byte[] b = new byte[1500];
                                l = dis.read(b);
                                System.out.print("leidos: " + l);
                                dos.write(b, 0, l);
                                dos.flush();
                                recibidos = recibidos + l;
                                porcentaje = (int) ((recibidos * 100) / tam);
                                System.out.println(" Recibido el " + porcentaje + " % del archivo");
                            } // while
                            System.out.println("Archivo recibido...\n");
                            dos.close();
                            dis.close();
                        }
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

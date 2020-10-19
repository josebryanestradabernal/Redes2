package drive;

import java.io.*;
import java.net.*;
import javax.swing.JFileChooser;

public class Cliente {
    public static void main(String[] args) {
        try {
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
                        // n representa al número de elementos en el directorio.
                        int n = Integer.parseInt(br1.readLine());
                        System.out.println("\nDirectorio:");
                        for (int i = 0; i < n; i++) {
                            System.out.println(br1.readLine());
                        }
                        System.out.println("\n");
                        cl.close();
                        break;
                    case 2:
                        JFileChooser jf = new JFileChooser();
                        jf.setMultiSelectionEnabled(true);
                        int r = jf.showOpenDialog(null);
                        if (r == JFileChooser.APPROVE_OPTION) {
                            File[] files = jf.getSelectedFiles();
                            outtext.println("" + files.length);
                            outtext.flush();
                            for (int i = 0; i < files.length; i++) {
                                cl = new Socket("localhost", 8000);
                                String nombre = files[i].getName();
                                String path = files[i].getAbsolutePath();
                                long tam = files[i].length();
                                System.out.println(
                                        "Preparandose pare enviar archivo " + path + " de " + tam + " bytes\n");
                                DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
                                DataInputStream dis = new DataInputStream(new FileInputStream(path));
                                dos.writeUTF(nombre);
                                dos.flush();
                                dos.writeLong(tam);
                                dos.flush();
                                long enviados = 0;
                                int l = 0, porcentaje = 0;
                                while (enviados < tam) {
                                    byte[] b = new byte[1500];
                                    l = dis.read(b);
                                    System.out.println("enviados: " + l);
                                    dos.write(b, 0, l);
                                    dos.flush();
                                    enviados = enviados + l;
                                    porcentaje = (int) ((enviados * 100) / tam);
                                    System.out.print("\rEnviado el " + porcentaje + " % del archivo");
                                } // while
                                System.out.println("\nArchivo enviado...\n");
                            }
                        }
                        cl.close();

                        break;
                    case 3:
                        
                        System.out.println("Descargar Archivo: ");
                        String file = br.readLine();
                        outtext.println(file);
                        outtext.flush();
                        String a = br1.readLine();
                        System.out.println(a);
                        if (a.equals("true")){
                              jf = new JFileChooser ();
                             int jfValue = jf.showOpenDialog(null);
                             String ruta_archivos = jf.getSelectedFile().getParent();
                             
                             DataInputStream dis = new DataInputStream(cl.getInputStream());
                            String nombre = dis.readUTF();
                            long tam = dis.readLong();
                            System.out.println("Comienza descarga del archivo " + nombre + " de " + tam + " bytes\n\n");
                            DataOutputStream dos = new DataOutputStream(new FileOutputStream(ruta_archivos +"\\"+ nombre));
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
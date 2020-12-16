package poolhttp;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class Processor implements Runnable{
    private URL url;
    private File directorio;
    private ArrayList<URLs> listaurls;
    
    public Processor(URL  url,ArrayList<URLs> listaurls){
        this.url=url;
        this.listaurls=listaurls;
        String urlaux = url.toString();
        int i = urlaux.indexOf("/")+2;
        urlaux=urlaux.substring(i);
        String array[]=urlaux.split("/");
        String dir="";
        for(int x=0;x<array.length-1;x++){
            dir=dir+array[x]+"/";
        }
        dir=dir+array[array.length-1]+".html";
        this.directorio=new File(dir);
    }
    
    @Override
    public void run() {
        try {
            
            //File fout = new File(archivo+id+".html");
            if(directorio.getParentFile()!=null){
                directorio.getParentFile().mkdirs();
                directorio.createNewFile();
            }
            else{
                String branch = directorio.toString();
                branch=branch.substring(0,branch.indexOf(".html"));
                branch=branch+"/"+branch+".html";
                //System.out.println(branch);
                directorio = new File(branch);
                directorio.getParentFile().mkdirs();
                directorio.createNewFile();
            }
            listaurls.add(new URLs(url,directorio.getPath()));
            FileOutputStream fos = new FileOutputStream(directorio);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                                    connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null){
                bw.write(inputLine+"\n");
            }
            in.close();
        } catch (MalformedURLException ex) {
            //Logger.getLogger(Panel1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(Panel1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

public class PoolHTTP extends JFrame implements Runnable,ActionListener{
private final JLabel JLabel1 = new JLabel("Introduce una URL");
    private final JTextField URLField = new JTextField();
    private final JButton Boton1 = new JButton("Descarga!");
    private final ExecutorService pool = Executors.newFixedThreadPool(500);
    protected Thread       runningThread= null;
    private URL url;
    private ArrayList<URLs> urls = new ArrayList<>();
    
    public PoolHTTP(){
        setTitle("Hilos");
        setPreferredSize(new Dimension(600,500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JLabel1.setBounds(50,50,150,25);
        URLField.setBounds(50, 100, 500, 25);
        Boton1.setBounds(100, 200, 100, 25);
        Boton1.addActionListener(this);
        
        add(URLField);
        add(JLabel1);
        add(Boton1);
        setLocationRelativeTo(null);
        setLayout(null);
        pack();
        setVisible(true);
    }
    public static void main(String[] args) {
        new PoolHTTP();
    }

    @Override
    public void run() {
        synchronized(this){
            this.runningThread = Thread.currentThread();
        }
        try{
            url = new URL(URLField.getText());
            this.pool.execute(new Processor(url,urls));
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                                    connection.getInputStream()));
            String inputLine;
            String img;
            while ((inputLine = in.readLine()) != null){
                int x = inputLine.indexOf("<a ");
                if(x!=-1){
                    int y = inputLine.indexOf("href",x)+6;
                    int z = inputLine.indexOf("\"",y);
                    if(y!=-1){
                        img = inputLine.substring(y,z);
                        if(img.indexOf("http")!=-1){
                            System.out.println(img);
                            int flag=0;
                            for(int i=0;i<urls.size();i++){
                                if(img.equals(urls.get(i).getUrl().toString())){
                                    flag=1;
                                    break;
                                }
                            }
                            if(flag==0){
                                this.pool.execute(new Processor(url,urls));
                                URL url2 = new URL(img);
                                URLConnection connection2 = url2.openConnection();
                                BufferedReader in2 = new BufferedReader(new InputStreamReader(
                                    connection2.getInputStream()));
                                String inputLine2;
                                String img2;
                                while ((inputLine2 = in2.readLine()) != null){
                                    int n = inputLine2.indexOf("<a ");
                                    if(n!=-1){
                                        int m = inputLine2.indexOf("href",n)+6;
                                        int g = inputLine2.indexOf("\"",m);
                                        if(m!=-1){
                                            img2 = inputLine2.substring(m,g);
                                            if(img2.indexOf("http")!=-1){
                                                System.out.println(img2);
                                                int flag2=0;
                                                for(int i=0;i<urls.size();i++){
                                                    if(img2.equals(urls.get(i).getUrl().toString())){
                                                        flag2=1;
                                                        break;
                                                    }
                                                }
                                                if(flag2==0){
                                                    this.pool.execute(new Processor(new URL(img2),urls));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            in.close();
        }catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(null,"URL Inválido...");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"URL Inválido...");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Thread(this).start();
    }
}

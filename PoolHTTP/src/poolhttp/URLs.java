package poolhttp;

import java.net.URL;

public class URLs {
    private URL url;
    private String directorio;
    
    public URLs(URL url,String directorio){
        this.url=url;
        this.directorio=directorio;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getDirectorio() {
        return directorio;
    }

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }
    
    
}

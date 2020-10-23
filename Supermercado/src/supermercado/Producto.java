package supermercado;

import java.io.Serializable;
import java.util.Date;

public class Producto implements Serializable{
    private String id;
    private String nombre;
    private String descripcion;
    private float precio;
    private int promocion;
    private Date vigencia;
    private String[] imagenes;
    private int existencias;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getPromocion() {
        return promocion;
    }

    public void setPromocion(int promocion) {
        this.promocion = promocion;
    }

    public Date getVigencia() {
        return vigencia;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }

    public String[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(String[] imagenes) {
        this.imagenes = imagenes;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }
    
    public Producto(String nombre,String id, String descripcion,int existencias,
                    float precio,int promocion,Date vigencia,String[] imagenes){
        this.nombre=nombre;
        this.id=id;
        this.descripcion=descripcion;
        this.existencias=existencias;
        this.vigencia=vigencia;
        this.precio=precio;
        this.promocion=promocion;
        this.imagenes=imagenes;
    }
    
}

package supermercado;

import java.io.Serializable;

public class ItemCarrito implements Serializable{
    private String Producto;
    private float Precio;
    private int Cantidad;
    
    public ItemCarrito(String Producto,float Precio,int Cantidad){
        this.Producto=Producto;
        this.Precio=Precio;
        this.Cantidad=Cantidad;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String Producto) {
        this.Producto = Producto;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float Precio) {
        this.Precio = Precio;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public float getMonto() {
        return Precio*Cantidad;
    }
    
}

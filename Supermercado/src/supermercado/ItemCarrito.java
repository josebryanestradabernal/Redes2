package supermercado;

public class ItemCarrito {
    private String Producto;
    private float Precio;
    private int Cantidad;
    private float Monto=0;
    
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
        return Monto*Cantidad;
    }

    public void setMonto(float Monto) {
        this.Monto = Monto;
    }
    
    
}

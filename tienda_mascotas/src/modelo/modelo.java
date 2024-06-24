package modelo;

public class modelo {
    private String objeto;
    private int IDs;
    private int precio;
    private int cantidad;

    public modelo() {
    }

    public modelo(String objeto, int IDs, int precio, int cantidad) {
        this.objeto = objeto;
        this.IDs = IDs;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getObjeto() {
        return this.objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public int getIDs() {
        return this.IDs;
    }

    public void setIDs(int IDs) {
        this.IDs = IDs;
    }

    public int getPrecio() {
        return this.precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String toString() {
        return "modelo{objeto='" + this.objeto + "', IDs=" + this.IDs + ", precio=" + this.precio + ", cantidad=" + this.cantidad + "}";
    }
}


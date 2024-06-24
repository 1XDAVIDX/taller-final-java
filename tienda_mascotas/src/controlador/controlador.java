package controlador;

import modelo.almacen;
import vista.vista;

public class controlador {
    private vista vista;
    private almacen almacen;

    public controlador() {
        this.almacen = new almacen();
        this.vista = new vista();
    }

    public void control() {
        boolean t = true;

        while(t) {
            int opt = vista.menu();
            switch (opt) {
                case 1:
                    String nom = vista.obtenerNombre();
                    int id = vista.obtenerId();
                    int precio = vista.obtenerPrecio();
                    int cantidad = vista.obtenerCantidad();
                    this.vista.imprimir(almacen.insertar(nom, id, precio, cantidad));
                    break;
                case 2:
                    this.vista.imprimir(almacen.ver());
                    break;
                case 3:
                    String nom1 = vista.obtenerNombre();
                    int id1 = vista.obtenerId();
                    int precio1 = vista.obtenerPrecio();
                    int cantidad1 = vista.obtenerCantidad();
                    this.vista.imprimir(almacen.modificar(nom1, id1, precio1, cantidad1));
                    break;
                case 4:
                    int id2 = vista.obtenerId();
                    this.vista.imprimir(almacen.eliminar(id2));
                    break;
                case 5:
                    int id3 = vista.obtenerId();
                    this.vista.imprimir(almacen.buscar(id3));
                    break;
                case 0:
                    t = false;
                    break;
                default:
                    this.vista.imprimir("Opción no válida.");
            }
        }

    }
}

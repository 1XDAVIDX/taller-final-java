package vista;

import controlador.controlador;

import javax.swing.*;

public class vista {


    public void conectar() {
        controlador c = new controlador();
        c.control();
    }

    public int menu() {
        int opcion = -1;
        while (opcion < 0 || opcion > 5) {
            String menu = "1) Agregar\n"
                    + "2) Inventario\n"
                    + "3) Modificar\n"
                    + "4) Eliminar\n"
                    + "5) Buscar\n"
                    + "0) Salir\n"
                    + "Seleccione una opción: ";
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
        }
        return opcion;
    }

    public String obtenerNombre() {
        return JOptionPane.showInputDialog("Nombre del objeto:");
    }

    public int obtenerId() {
        return Integer.parseInt(JOptionPane.showInputDialog("ID del objeto:"));
    }

    public int obtenerPrecio() {
        return Integer.parseInt(JOptionPane.showInputDialog("Precio del objeto:"));
    }

    public int obtenerCantidad() {
        return Integer.parseInt(JOptionPane.showInputDialog("Cantidad del objeto:"));
    }

    public void imprimir(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}

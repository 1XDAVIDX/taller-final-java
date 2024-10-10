package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import vista.vista;

public class almacen {
    Conexion cnn = new Conexion();
    Connection conexion = cnn.Conecta();
    PreparedStatement ps = null;
    Statement st = null;
    ResultSet res = null;
    HashMap<Integer, modelo> has = new HashMap<>();
    private vista vista = new vista();

    public almacen() {
        // Cargar los datos desde MySQL al HashMap al inicio
        this.cargarDatosDesdeBaseDatos();
        if (this.has.isEmpty()) {
            // Si está vacío, se puede agregar un valor inicial
            this.has.put(1, new modelo("casa", 1, 1000, 5));
        }
    }

    // Método para cargar datos desde la base de datos al HashMap al inicio
    private void cargarDatosDesdeBaseDatos() {
        try {
            String sql = "SELECT * FROM modelo";
            st = conexion.createStatement();
            res = st.executeQuery(sql);

            while (res.next()) {
                int id = res.getInt("IDs");
                String objeto = res.getString("objeto");
                int precio = res.getInt("precio");
                int cantidad = res.getInt("cantidad");
                has.put(id, new modelo(objeto, id, precio, cantidad));
            }
            vista.imprimir("Datos cargados correctamente desde la base de datos.");
        } catch (Exception e) {
            vista.imprimir("Error al cargar datos desde la base de datos: " + e.getMessage());
        }
    }

    // Método para sincronizar el HashMap con la base de datos
    private void sincronizarConBaseDatos() {
        try {
            // Primero, limpiar la tabla en MySQL (opcional, depende de la necesidad)
            String sqlTruncate = "TRUNCATE TABLE modelo";
            st = conexion.createStatement();
            st.executeUpdate(sqlTruncate);

            // Insertar todos los valores del HashMap en MySQL
            for (modelo obj : has.values()) {
                String sqlInsert = "INSERT INTO modelo (objeto, IDs, precio, cantidad) VALUES (?, ?, ?, ?)";
                ps = conexion.prepareStatement(sqlInsert);
                ps.setString(1, obj.getObjeto());
                ps.setInt(2, obj.getIDs());
                ps.setInt(3, obj.getPrecio());
                ps.setInt(4, obj.getCantidad());
                ps.executeUpdate();
            }

            vista.imprimir("Datos sincronizados correctamente con la base de datos.");
        } catch (Exception e) {
            vista.imprimir("Error al sincronizar datos con la base de datos: " + e.getMessage());
        }
    }

    // Método para insertar en el HashMap y luego en la base de datos
    public String insertar(String nombre, int id, int precio, int cantidad) {
        String mensaje;
        if (has.containsKey(id)) {
            mensaje = "Ya existe en el HashMap.";
        } else {
            // Insertar en el HashMap
            has.put(id, new modelo(nombre, id, precio, cantidad));
            mensaje = "¡Agregado al HashMap!";
            // Sincronizar el HashMap con la base de datos
            sincronizarConBaseDatos();
        }
        return mensaje;
    }

    // Método para ver los datos del HashMap
    public String ver() {
        StringBuilder resultado = new StringBuilder();
        for (Integer key : has.keySet()) {
            modelo obj = has.get(key);
            resultado.append("ID: ").append(obj.getIDs())
                    .append(", Objeto: ").append(obj.getObjeto())
                    .append(", Precio: ").append(obj.getPrecio())
                    .append(", Cantidad: ").append(obj.getCantidad())
                    .append("\n");
        }
        return resultado.toString();
    }

    // Método para modificar en el HashMap y luego sincronizar con la base de datos
    public String modificar(String nombre, int id, int precio, int cantidad) {
        String mensaje;
        if (has.containsKey(id)) {
            // Modificar en el HashMap
            has.put(id, new modelo(nombre, id, precio, cantidad));
            mensaje = "¡Modificado en el HashMap!";
            // Sincronizar el HashMap con la base de datos
            sincronizarConBaseDatos();
        } else {
            mensaje = "No existe en el HashMap.";
        }
        return mensaje;
    }

    // Método para eliminar en el HashMap y luego sincronizar con la base de datos
    public String eliminar(int id) {
        String mensaje;
        if (has.containsKey(id)) {
            // Eliminar del HashMap
            has.remove(id);
            mensaje = "¡Eliminado del HashMap!";
            // Sincronizar el HashMap con la base de datos
            sincronizarConBaseDatos();
        } else {
            mensaje = "No existe en el HashMap.";
        }
        return mensaje;
    }

    // Método para buscar en el HashMap
    public String buscar(int id) {
        modelo obj = has.get(id);
        return (obj != null) ? obj.toString() : "No existe en el HashMap.";
    }
}


package modelo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import vista.vista;

public class almacen {
    HashMap<Integer, modelo> has = new HashMap();
    private vista vista = new vista();

    public almacen() {
        this.cargarDatosDesdeArchivo();
        if (this.has.isEmpty()) {
            this.has.put(1, new modelo("casa", 1, 1000, 5));
            this.guardarDatosEnArchivo();
        }

    }

    private void cargarDatosDesdeArchivo() {
        try (FileReader fileReader = new FileReader("data.json")) {
            Gson gson = new Gson();
            Type tipo = new TypeToken<HashMap<Integer, modelo>>() {}.getType();
            has = gson.fromJson(fileReader, tipo);
            //vista.imprimir("Datos cargados correctamente desde el archivo JSON.");
        } catch (IOException e) {
            vista.imprimir("Archivo no encontrado o error al leer.");
        } catch (Exception e) {
            vista.imprimir("Error al cargar datos: " + e.getMessage());
        }
    }

    private void guardarDatosEnArchivo() {
        try (FileWriter fileWriter = new FileWriter("data.json")) {
            Gson gson = new Gson();
            String json = gson.toJson(has);
            fileWriter.write(json);
            //vista.imprimir("Datos guardados en el archivo JSON correctamente.");
        } catch (IOException e) {
            vista.imprimir("Error al guardar datos: " + e.getMessage());
        }
    }

    public String insertar(String nombre, int id, int precio, int cantidad) {
        this.cargarDatosDesdeArchivo();
        String mensaje;
        if (has.containsKey(id)) {
            mensaje = "Ya está";
        } else {
            has.put(id, new modelo(nombre, id, precio, cantidad));
            guardarDatosEnArchivo();
            mensaje = "¡AGREGADO!";
        }

        return mensaje;
    }

    public String ver() {
        cargarDatosDesdeArchivo();
        Gson gson = new Gson();
        return gson.toJson(has);
    }

    public String modificar(String nombre, int id, int precio, int cantidad) {
        this.cargarDatosDesdeArchivo();
        String mensaje;
        if (has.containsKey(id)) {
            has.put(id, new modelo(nombre, id, precio, cantidad));
            guardarDatosEnArchivo();
            mensaje = "¡Modificado!";
        } else {
            mensaje = "No existe";
        }

        return mensaje;
    }

    public String eliminar(int id) {
        this.cargarDatosDesdeArchivo();
        String mensaje;
        if (has.containsKey(id)) {
            has.remove(id);
            mensaje = "¡Eliminado!";
            guardarDatosEnArchivo();
        } else {
            mensaje = "No existe";
        }

        return mensaje;
    }

    public String buscar(int id) {
        cargarDatosDesdeArchivo();
        return String.valueOf(has.get(id));
    }
}


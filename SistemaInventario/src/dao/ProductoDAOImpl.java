package dao;

import entidad.Producto;
import java.io.*;
import java.util.*;

public class ProductoDAOImpl implements ProductoDAO {

    private final String archivo = "data/productos.txt";

    @Override
    public void guardar(Producto producto) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(producto.toFileString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
public List<Producto> listar() {
    List<Producto> lista = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
        String linea;
        while ((linea = br.readLine()) != null) {

            // 🔥 Ignorar líneas vacías
            if (linea.trim().isEmpty()) continue;

            String[] datos = linea.split(",");

            // 🔥 Validar que tenga todos los datos
            if (datos.length < 4) continue;

            try {
                int id = Integer.parseInt(datos[0]);
                int cantidad = Integer.parseInt(datos[2]);
                double precio = Double.parseDouble(datos[3]);

                Producto p = new Producto(id, datos[1], cantidad, precio);
                lista.add(p);

            } catch (NumberFormatException e) {
                System.out.println("Línea inválida ignorada: " + linea);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return lista;
}

    @Override
    public Producto buscarPorId(int id) {
        return listar().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void actualizar(Producto producto) {
        List<Producto> lista = listar();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Producto p : lista) {
                if (p.getId() == producto.getId()) {
                    bw.write(producto.toFileString());
                } else {
                    bw.write(p.toFileString());
                }
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        List<Producto> lista = listar();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Producto p : lista) {
                if (p.getId() != id) {
                    bw.write(p.toFileString());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
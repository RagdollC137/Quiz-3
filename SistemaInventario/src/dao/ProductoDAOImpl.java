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
                String[] datos = linea.split(",");
                Producto p = new Producto(
                        Integer.parseInt(datos[0]),
                        datos[1],
                        Integer.parseInt(datos[2]),
                        Double.parseDouble(datos[3])
                );
                lista.add(p);
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
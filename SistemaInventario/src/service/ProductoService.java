package service;

import dao.ProductoDAO;
import dao.ProductoDAOImpl;
import entidad.Producto;
import java.util.List;

public class ProductoService {

    private ProductoDAO dao = new ProductoDAOImpl();

    public void registrar(Producto producto) {
        if (dao.buscarPorId(producto.getId()) != null) {
            System.out.println("El producto ya existe.");
            return;
        }
        dao.guardar(producto);
    }

    public List<Producto> listar() {
        return dao.listar();
    }

    public Producto buscar(int id) {
        return dao.buscarPorId(id);
    }

    public void actualizar(Producto producto) {
        dao.actualizar(producto);
    }

    public void eliminar(int id) {
        dao.eliminar(id);
    }
}
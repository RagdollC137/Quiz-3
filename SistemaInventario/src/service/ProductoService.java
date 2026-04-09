package service;

import dao.ProductoDAO;
import dao.ProductoDAOImpl;
import entidad.Producto;
import java.util.List;

public class ProductoService {

    private ProductoDAO dao = new ProductoDAOImpl();

    public void registrar(Producto producto) {

    // 🔴 Validar ID repetido
    if (dao.buscarPorId(producto.getId()) != null) {
        System.out.println("⚠️ Error: Ya existe un producto con ese ID.");
        return;
    }

    // 🔴 Validar nombre repetido
    for (Producto p : dao.listar()) {
        if (p.getNombre().equalsIgnoreCase(producto.getNombre())) {
            System.out.println("⚠️ Error: Ya existe un producto con ese nombre.");
            return;
        }
    }

    // 🔴 Validaciones extra (PRO)
    if (producto.getNombre().trim().isEmpty()) {
        System.out.println("⚠️ Error: El nombre no puede estar vacío.");
        return;
    }

    if (producto.getCantidad() < 0) {
        System.out.println("⚠️ Error: La cantidad no puede ser negativa.");
        return;
    }

    if (producto.getPrecio() < 0) {
        System.out.println("⚠️ Error: El precio no puede ser negativo.");
        return;
    }

    // ✅ Si todo está bien
    dao.guardar(producto);
    System.out.println("✅ Producto registrado correctamente.");
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
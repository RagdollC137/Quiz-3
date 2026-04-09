package ui;

import service.ProductoService;
import entidad.Producto;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductoService service = new ProductoService();

        int opcion;

        do {
            System.out.println("\n=== INVENTARIO ===");
            System.out.println("1. Registrar");
            System.out.println("2. Listar");
            System.out.println("3. Buscar");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("0. Salir");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Cantidad: ");
                    int cantidad = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();

                    service.registrar(new Producto(id, nombre, cantidad, precio));
                    break;

                case 2:
                    service.listar().forEach(p ->
                            System.out.println(p.getId() + " - " + p.getNombre())
                    );
                    break;

                case 3:
                    System.out.print("ID: ");
                    Producto p = service.buscar(sc.nextInt());
                    System.out.println(p != null ? p.getNombre() : "No encontrado");
                    break;

                case 4:
                    System.out.print("ID a actualizar: ");
                    int idAct = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = sc.nextLine();

                    System.out.print("Nueva cantidad: ");
                    int nuevaCant = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nuevo precio: ");
                    double nuevoPrecio = sc.nextDouble();

                    service.actualizar(new Producto(idAct, nuevoNombre, nuevaCant, nuevoPrecio));
                    break;

                case 5:
                    System.out.print("ID a eliminar: ");
                    service.eliminar(sc.nextInt());
                    sc.nextLine();
                    break;
            }

        } while (opcion != 0);
    }
}
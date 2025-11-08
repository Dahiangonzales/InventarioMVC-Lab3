package vista;

import modelo.Producto;

import java.util.List;
import java.util.Scanner;

public class InventarioVista {
    private Scanner scanner;

    public InventarioVista() {
        this.scanner = new Scanner(System.in);
    }

    // Imprime menú y devuelve la opción seleccionada (1-5)
    public int mostrarMenu() {
        System.out.println("\n--- Menú Inventario ---");
        System.out.println("1. Agregar nuevo producto");
        System.out.println("2. Buscar producto por SKU");
        System.out.println("3. Mostrar todos los productos");
        System.out.println("4. Eliminar producto por SKU");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción (1-5): ");

        int opcion = -1;
        try {
            String line = scanner.nextLine();
            opcion = Integer.parseInt(line.trim());
        } catch (Exception e) {
            opcion = -1;
        }
        return opcion;
    }

    // Pide al usuario datos y retorna un Producto (o null si entrada inválida)
    public Producto pedirDatosUsuario() {
        try {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine().trim();

            System.out.print("SKU: ");
            String sku = scanner.nextLine().trim();

            System.out.print("Cantidad (entero): ");
            int cantidad = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Precio unitario (decimal): ");
            double precio = Double.parseDouble(scanner.nextLine().trim());

            return new Producto(nombre, sku, cantidad, precio);
        } catch (Exception e) {
            mostrarMensaje("Entrada inválida. Operación cancelada.");
            return null;
        }
    }

    // Pide un SKU y lo retorna
    public String pedirSku() {
        System.out.print("Ingrese SKU: ");
        return scanner.nextLine().trim();
    }

    // Muestra un producto o mensaje si es nulo
    public void mostrarProducto(Producto p) {
        if (p != null) {
            System.out.println(p.toString());
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    // Muestra lista de productos o mensaje si está vacía
    public void mostrarProductos(List<Producto> productos) {
        if (productos == null || productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            System.out.println("\n--- Productos registrados ---");
            for (Producto p : productos) {
                System.out.println(p.toString());
            }
        }
    }

    // Mensaje genérico (éxito/error/info)
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    // Cerrar scanner (opcional)
    public void cerrar() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
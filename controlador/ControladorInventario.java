package controlador;

import modelo.BaseDeDatos;
import modelo.Producto;
import vista.InventarioVista;

import java.util.List;

public class ControladorInventario {
    private BaseDeDatos baseDatos;
    private InventarioVista vista;

    public ControladorInventario(BaseDeDatos baseDatos, InventarioVista vista) {
        this.baseDatos = baseDatos;
        this.vista = vista;
    }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            int opcion = vista.mostrarMenu();
            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    buscarProducto();
                    break;
                case 3:
                    mostrarTodos();
                    break;
                case 4:
                    eliminarProducto();
                    break;
                case 5:
                    salir = true;
                    vista.mostrarMensaje("Saliendo... ¡Hasta luego!");
                    break;
                default:
                    vista.mostrarMensaje("Opción inválida. Intente de nuevo.");
            }
        }
        vista.cerrar();
    }

    private void agregarProducto() {
        Producto nuevo = vista.pedirDatosUsuario();
        if (nuevo != null) {
            
            Producto existente = baseDatos.buscarProductoSku(nuevo.getSku());
            if (existente != null) {
                vista.mostrarMensaje("Ya existe un producto con ese SKU. Operación cancelada.");
                return;
            }

            baseDatos.agregarProducto(nuevo);
            vista.mostrarMensaje("Producto agregado correctamente.");
        }
    }

    private void buscarProducto() {
        String sku = vista.pedirSku();
        Producto p = baseDatos.buscarProductoSku(sku);
        vista.mostrarProducto(p);
    }

    private void mostrarTodos() {
        List<Producto> lista = baseDatos.buscarTodos();
        vista.mostrarProductos(lista);
    }

    private void eliminarProducto() {
        String sku = vista.pedirSku();
        boolean ok = baseDatos.eliminarProducto(sku);
        if (ok) {
            vista.mostrarMensaje("Producto eliminado correctamente.");
        } else {
            vista.mostrarMensaje("No se encontró el producto con ese SKU.");
        }
    }
}
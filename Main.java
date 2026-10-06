public class Main {

    public static void main(String[] args) {

        Carrito<Producto> carrito = new Carrito<>();

        carrito.agregarProducto(new Producto("Celular", 1200000));
        carrito.agregarProducto(new Producto("Audífonos", 250000));
        carrito.agregarProducto(new Producto("Teclado", 180000));
        carrito.agregarProducto(new Producto("Monitor", 900000));

        System.out.println("Producto más caro:");
        System.out.println(carrito.obtenerMayorPrecio());

        System.out.println();

        System.out.println("Precio total:");
        System.out.println("$" + carrito.calcularTotal());
    }
} 

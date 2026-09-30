import java.util.Random;

public class BenchmarkPlataformaTaxis {

private static final int[] TAMANOS = {100, 1_000, 10_000, 100_000};
private static final Random RANDOM = new Random(42); // semilla fija -> resultados reproducibles

public static void main(String[] args) {
    System.out.println("FASE 4 - Escenario: Plataforma de Taxis");
    System.out.println("Estructura: HashMap<Integer,Solicitud> + Queue<Integer> (LinkedList)");
    System.out.println();
    System.out.printf("%-10s %-16s %-16s %-16s %-18s %-16s%n",
            "N", "Insertar(ms)", "Buscar(ms)", "Atender(ms)", "Mem.antes(KB)", "Mem.despues(KB)");
    System.out.println("-".repeat(96));

    for (int n : TAMANOS) {
        medir(n);
    }
}

private static void medir(int n) {
    // Se limpia memoria antes de medir para que el "antes" sea representativo
    System.gc();
    sleepBreve();
    long memAntes = memoriaUsadaKB();

    PlataformaTaxis sistema = new PlataformaTaxis();

    // --- Registrar N solicitudes ---
    long t0 = System.nanoTime();

    for (int i = 0; i < n; i++) {
        int id = i + 1;
        String usuario = "Usuario" + id;
        String destino = "Destino" + id;

        sistema.registrarSolicitud(
                new Solicitud(id, usuario, destino)
        );
    }

    long t1 = System.nanoTime();
    double msInsertar = (t1 - t0) / 1_000_000.0;

    // --- Buscar N/10 solicitudes al azar por ID ---
    int busquedas = Math.max(1, n / 10);

    t0 = System.nanoTime();

    for (int i = 0; i < busquedas; i++) {
        int id = 1 + RANDOM.nextInt(n);
        sistema.buscarSolicitud(id);
    }

    t1 = System.nanoTime();
    double msBuscar = (t1 - t0) / 1_000_000.0;

    // --- Atender todas las solicitudes en orden FIFO ---
    t0 = System.nanoTime();

    while (sistema.cantidadSolicitudes() > 0) {
        sistema.atenderSolicitud();
    }

    t1 = System.nanoTime();
    double msAtender = (t1 - t0) / 1_000_000.0;

    long memDespues = memoriaUsadaKB();

    System.out.printf("%-10d %-16.3f %-16.3f %-16.3f %-18d %-16d%n",
            n, msInsertar, msBuscar, msAtender, memAntes, memDespues);
}

private static long memoriaUsadaKB() {
    Runtime rt = Runtime.getRuntime();
    long usados = rt.totalMemory() - rt.freeMemory();
    return usados / 1024;
}

private static void sleepBreve() {
    try {
        Thread.sleep(50);
    } catch (InterruptedException ignored) {
    }
}

}

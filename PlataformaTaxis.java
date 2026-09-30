import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class PlataformaTaxis {
// HashMap para buscar rápidamente las solicitudes por ID
private final Map<Integer, Solicitud> solicitudes = new HashMap<>();

// Queue para mantener el orden de llegada de las solicitudes
private final Queue<Integer> cola = new LinkedList<>();

/**
 * Registra una solicitud si el ID no existe todavía.
 *
 * @return true si se registró, false si ya existía.
 */
public boolean registrarSolicitud(Solicitud solicitud) {

    if (solicitudes.containsKey(solicitud.getId())) {
        return false;
    }

    // Guardamos la solicitud en el HashMap
    solicitudes.put(solicitud.getId(), solicitud);

    // Guardamos el ID en la cola
    cola.offer(solicitud.getId());

    return true;
}

/**
 * Busca una solicitud por su ID.
 * O(1) promedio.
 */
public Solicitud buscarSolicitud(int id) {
    return solicitudes.get(id);
}

/**
 * Verifica si existe una solicitud con determinado ID.
 * O(1) promedio.
 */
public boolean existeSolicitud(int id) {
    return solicitudes.containsKey(id);
}

/**
 * Atiende la solicitud más antigua respetando el orden FIFO.
 * O(1) promedio.
 */
public Solicitud atenderSolicitud() {

    // Sacamos el primer ID de la cola
    Integer id = cola.poll();

    if (id == null) {
        return null;
    }

    // Eliminamos la solicitud del HashMap
    return solicitudes.remove(id);
}

/**
 * Cancela una solicitud específica.
 * O(n) debido a la búsqueda del ID dentro de la cola.
 */
public boolean cancelarSolicitud(int id) {

    if (!solicitudes.containsKey(id)) {
        return false;
    }

    // Eliminamos del HashMap
    solicitudes.remove(id);

    // Eliminamos el ID de la cola
    cola.remove(id);

    return true;
}

/**
 * Retorna la cantidad de solicitudes pendientes.
 */
public int cantidadSolicitudes() {
    return solicitudes.size();
}

public static void main(String[] args) {
}
}

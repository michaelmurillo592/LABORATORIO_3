public class Solicitud {
    private final int id;
    private final String usuario;
    private final String destino;

    public Solicitud(int id, String usuario, String destino) {
        this.id = id;
        this.usuario = usuario;
        this.destino = destino;
    }

    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getDestino() {
        return destino;
    }

    @Override
    public String toString() {
        return "Solicitud{id=" + id +
                ", usuario='" + usuario + '\'' +
                ", destino='" + destino + '\'' +
                '}';
    }
}

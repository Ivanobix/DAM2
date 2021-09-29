package ejercicio2;

public class Pelicula {
    private String titulo;
    private String estilo;
    private int duracion;
    private String ruta;

    public Pelicula(String titulo, String estilo, int duracion, String ruta) {
        this.titulo = titulo;
        this.estilo = estilo;
        this.duracion = duracion;
        this.ruta = ruta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return titulo + " " + duracion;
    }
}

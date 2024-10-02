package rec;

public class Curso {
    private int id;
    private int duracion;
    private int dificultad;

    public Curso(int id, int duracion, int dificultad) {
        this.id = id;
        this.duracion = duracion;
        this.dificultad = dificultad;
    }

    public int getId() { return id; }
    public int getDuracion() { return duracion; }
    public int getDificultad() { return dificultad; }
}

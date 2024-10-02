package rec;

public class Curso {
	//Attributes
    private int id;
    private int duracion;
    private int dificultad;
    
    //Constructor
    public Curso(int id, int duracion, int dificultad) {
        this.id = id;
        this.duracion = duracion;
        this.dificultad = dificultad;
    }
    
    //Getters
    public int getId() { return id; }
    public int getDuracion() { return duracion; }
    public int getDificultad() { return dificultad; }
}

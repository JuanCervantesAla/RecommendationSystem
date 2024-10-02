package rec;

public class AlumnoCurso {
	//Attribute
    private int idAlumno;
    private int idCurso;
    private double calificacion;

    //Constructor
    public AlumnoCurso(int idAlumno, int idCurso, double calificacion) {
        this.idAlumno = idAlumno;
        this.idCurso = idCurso;
        this.calificacion = calificacion;
    }

    //getter
    public int getIdAlumno() { return idAlumno; }
    public int getIdCurso() { return idCurso; }
    public double getCalificacion() { return calificacion; }
}

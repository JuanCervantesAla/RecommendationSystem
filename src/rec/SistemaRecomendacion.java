package rec;

import java.util.*;

public class SistemaRecomendacion {
    private List<Curso> cursos;
    private Map<Integer, List<AlumnoCurso>> historialCalificaciones;

    //Constructor 
    public SistemaRecomendacion(List<Curso> cursos, Map<Integer, List<AlumnoCurso>> historialCalificaciones) {
        this.cursos = cursos;
        this.historialCalificaciones = historialCalificaciones;
    }

    public List<Curso> recomendarCursos(int idAlumno) {
        List<Curso> recomendaciones = new ArrayList<>();
        List<AlumnoCurso> calificacionesAlumno = historialCalificaciones.get(idAlumno);
        
        Set<Integer> cursosYaTomados = new HashSet<>();
        Set<Integer> cursosRecomendados = new HashSet<>();
        Set<Integer> cursosNoRecomendados = new HashSet<>();

        // Obtener los cursos tomados por el alumno
        for (AlumnoCurso ac : calificacionesAlumno) {
            cursosYaTomados.add(ac.getIdCurso());
        }

        // Calcular similitud con otros cursos
        for (AlumnoCurso ac : calificacionesAlumno) {
            Curso cursoTomado = buscarCursoPorId(ac.getIdCurso());
            
            for (Curso curso : cursos) {
                // Verificamos que el curso no haya sido tomado y no esté en las recomendaciones
                if (!cursosYaTomados.contains(curso.getId()) && !cursosRecomendados.contains(curso.getId()) && !cursosNoRecomendados.contains(curso.getId())) {
                    double similitud = SimilitudCoseno.calcularSimilitud(cursoTomado, curso, ac.getCalificacion());
                    
                    System.out.println(curso.getId() + " " + similitud);
                    if (similitud > 0.8) { // Ajusta el umbral
                        recomendaciones.add(curso);
                        cursosRecomendados.add(curso.getId()); // Agregamos el ID al Set de recomendaciones
                    } else {
                    	cursosNoRecomendados.add(curso.getId());//Evita repeticiones de cursos
                    }
                }
            }
            
        }

        return recomendaciones;
    }


    private Curso buscarCursoPorId(int idCurso) {
        for (Curso curso : cursos) {
            if (curso.getId() == idCurso) {
                return curso;
            }
        }
        return null; // Si no se encuentra
    }
}


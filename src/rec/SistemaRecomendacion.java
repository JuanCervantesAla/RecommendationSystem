package rec;

import java.util.*;

public class SistemaRecomendacion {
	
	//Attributes
    private List<Curso> listaCursosTotales;//Todos los cursos
    private Map<Integer, List<AlumnoCurso>> mapHistorialCalificaciones;

    //Constructor 
    public SistemaRecomendacion(List<Curso> listaCursosTotales, Map<Integer, List<AlumnoCurso>> mapHistorialCalificaciones) {
        this.listaCursosTotales = listaCursosTotales;
        this.mapHistorialCalificaciones = mapHistorialCalificaciones;
    }
    
    //Methods
    
    //Recommendation returns a recommendationList based on studentId
    public List<Curso> recomendarCursos(int idAlumno) {
    	
  
        List<Curso> listaCursosRecomendados = new ArrayList<>();//Creacion de la lista a devolver
        
        
        List<AlumnoCurso> listaCalificacionesAlumno = mapHistorialCalificaciones.get(idAlumno);//Obtiene una lista a partir del map del idAlumno
        
        //Creacion de los sets(facilitan la verificacion de existencia de un "objeto"
        Set<Integer> setCursosYaTomados = new HashSet<>();//Cursos ya tomados por el alumno
        Set<Integer> setCursosRecomendados = new HashSet<>();//Cursos que ya han sido recomendados
        Set<Integer> setCursosNoRecomendados = new HashSet<>();//Cursos ya han sido rechazados

        // Obtener los cursos tomados por el alumno
        for (AlumnoCurso registroCalificacion : listaCalificacionesAlumno) {
            setCursosYaTomados.add(registroCalificacion.getIdCurso());//Si esta en la lista, agregas al set de ya tomados
        }

        for (AlumnoCurso registroCalificacion : listaCalificacionesAlumno) {//Iterar por cada curso que ya haya tomado el alumno
            Curso cursoTomado = buscarCursoPorId(registroCalificacion.getIdCurso());//Tomamos ese curso que se va a comparar a los no tomados
            
            for (Curso curso : listaCursosTotales) {//Iterar una lista de todos los cursos
            	
                /*Verificamos 3 cosas: Comparamos un curso ya tomado con un curso que cumpla 3 condiciones
                 * Que no este en el set de ya tomados
                 * Que no este en el set de ya ha sido recomendado
                 * Que no este en el set de que ya haya sido rechazado
                 * */
                if (!setCursosYaTomados.contains(curso.getId()) && !setCursosRecomendados.contains(curso.getId()) && !setCursosNoRecomendados.contains(curso.getId())) {
                	
                    double similitud = SimilitudCoseno.calcularSimilitud(cursoTomado, curso, registroCalificacion.getCalificacion());//Calculamos la similitud entre mas cercano a 1 es mas recomendable
                    
                    if (similitud > 0.8) {//Si la similitud se acerca a 1 significa que es muy recomendable
                    	listaCursosRecomendados.add(curso);//Anadimos el curso a la lista de regreso
                    	setCursosRecomendados.add(curso.getId()); // Agregamos el ID al Set de recomendaciones para evitar repetir
                    } else {
                    	setCursosNoRecomendados.add(curso.getId());//Y si no es recomendable igual anadimos a la lista para que no vuelva a repetir
                    }
                }
            }
            
        }

        return listaCursosRecomendados;//Regresa las recomendaciones
    }


    private Curso buscarCursoPorId(int idCurso) {
        for (Curso curso : listaCursosTotales) {
            if (curso.getId() == idCurso) {
                return curso; //Si encuentra un curso que coincida
            }
        }
        return null; // Si no se encuentra
    }
}


package rec;

public class SimilitudCoseno {
    public static double calcularSimilitud(Curso curso1, Curso curso2, double calificacion) {
    	
    	//Tenemos los dos vectores a comparar, A y B
        double[] vector1 = {curso1.getDuracion(), curso1.getDificultad(), calificacion};
        double[] vector2 = {curso2.getDuracion(), curso2.getDificultad(), 0.0};

        //Inicializamos tanto como el producto punto como las magnitudes
        double productoPunto = 0.0;
        double magnitud1 = 0.0;
        double magnitud2 = 0.0;

        for (int i = 0; i < vector1.length; i++) {//Vamos a realizar el recorrido de "Sumatoria" de los vectores
            productoPunto += vector1[i] * vector2[i];//Calculamos el producto punto sumando en cada iteracion
            magnitud1 += Math.pow(vector1[i], 2);//Calculamos la magnitud
            magnitud2 += Math.pow(vector2[i], 2);
        }
        
        if (magnitud1 == 0 || magnitud2 == 0) {//Si la magnitud es 0 no podemos dividir entre el producto punto
            return 0.0;
        }

        return productoPunto / (Math.sqrt(magnitud1) * Math.sqrt(magnitud2));//Dividimos el producto punto entre la raiz de las magnitudes
    }
}


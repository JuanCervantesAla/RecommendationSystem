package rec;

public class SimilitudCoseno {
    public static double calcularSimilitud(Curso curso1, Curso curso2, double califacion) {
        double[] vector1 = {curso1.getDuracion(), curso1.getDificultad(), califacion};
        double[] vector2 = {curso2.getDuracion(), curso2.getDificultad(), 0.0};

        double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;

        for (int i = 0; i < vector1.length; i++) {
            dotProduct += vector1[i] * vector2[i];
            magnitude1 += Math.pow(vector1[i], 2);
            magnitude2 += Math.pow(vector2[i], 2);
        }
        
        if (magnitude1 == 0 || magnitude2 == 0) {
            return 0.0;
        }

        return dotProduct / (Math.sqrt(magnitude1) * Math.sqrt(magnitude2));
    }
}


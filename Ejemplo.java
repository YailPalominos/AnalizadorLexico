public class Ejemplo {
    /**
     * @author Braulio Yail Palominos Patiño
     */
    public static void main(String[] args) {
        IngresarDatos(1, 1, -12);
    }

    public static void IngresarDatos(int nA, int nB, int nC) {

        float nResultadoSuperior = (nB * nB) - (4 * nA * nC);

        if (nResultadoSuperior >= 0) {
            double x1 = (-nB + Math.sqrt(nResultadoSuperior)) / (2 * nA);
            double x2 = (-nB - Math.sqrt(nResultadoSuperior)) / (2 * nA);
            System.out.println("\nx1: " + x1 + "\nx2: " + x2);
        } else {
            System.out.println("Su solucion no existe o es erronea");
            double x1 = (-nB + Math.sqrt(nResultadoSuperior)) / (2 * nA);
            double x2 = (-nB - Math.sqrt(nResultadoSuperior)) / (2 * nA);
            System.out.println("\nx1: " + x1 + "\nx2: " + x2);
        }

    }

}

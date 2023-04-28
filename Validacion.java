/**
 * @author Braulio Yail Palominos Patiño
 */

public class Validacion {

    // Contructor
    public Validacion() {
    }

    public void PrepararDatos(String sCadenaValida) {
        // Toma los datos de la cadena valida para poder dividirlos y enviarlos a
        // Ejecucion

        // Objeto figuras donde se guadara los datos
        Figuras oFiguras = new Figuras();
        // Separa en secciones la cadena aceptada
        // Ejemplo = Posiciones=(3,2)(3,5) Grosor(2) Color(255,255,255)
        String[] sSecciones = sCadenaValida.split("-");

        System.out.println(sSecciones[0] + sSecciones[1] + sSecciones[2]);
        // Separar la cadena con el uso de Split y convertirlo en enteros

        // Identificacion de posiciones
        if (sSecciones[0] != null) {
            String sCadenaPosiciones = sSecciones[0];
            sCadenaPosiciones = sCadenaPosiciones.replace("(", "");
            sCadenaPosiciones = sCadenaPosiciones.replace(")", "-");
            String[] sPosiciones = sCadenaPosiciones.split("-");

            for (int x = 0; x < sPosiciones.length; x++) {
                String[] sPuntos = sPosiciones[x].split(",");

                if (sPuntos[0] != null && sPuntos[1] != null) {
                    oFiguras.nPosX = agregar(oFiguras.nPosX, Integer.parseInt(sPuntos[0]));
                    oFiguras.nPosY = agregar(oFiguras.nPosY, Integer.parseInt(sPuntos[1]));
                }
            }
        }

        // Identificacion de grosores
        if (sSecciones[1] != null) {
            String sCadenaGrosor = sSecciones[1];
            sCadenaGrosor = sCadenaGrosor.replace("(", "");
            sCadenaGrosor = sCadenaGrosor.replace(")", "");
            oFiguras.nGr = Integer.parseInt(sCadenaGrosor);
        }

        // Identificacion de colores
        if (sSecciones[2] != null) {
            String sCadenaColor = sSecciones[2];
            sCadenaColor = sCadenaColor.replace("(", "");
            sCadenaColor = sCadenaColor.replace(")", "");

            String[] sRangoColores = sCadenaColor.split(",");
            if (sRangoColores[0] != null && sRangoColores[1] != null && sRangoColores[2] != null) {
                oFiguras.nR = Integer.parseInt(sRangoColores[0]);
                oFiguras.nG = Integer.parseInt(sRangoColores[1]);
                oFiguras.nB = Integer.parseInt(sRangoColores[2]);
            }
        }

        Evaluar oEjecucion = new Evaluar();
        oEjecucion.Comprobar(oFiguras);

    }

    // Metodo auxiliar

    public int[] agregar(int[] aArreglo, int nNuevoDato) {

        // Redimenciona el arreglo para añadir datos

        int[] aNuevoArreglo = aArreglo;
        aNuevoArreglo = new int[aNuevoArreglo.length + 1];
        System.arraycopy(aArreglo, 0, aNuevoArreglo, 0, aArreglo.length);
        aNuevoArreglo[aArreglo.length - 1] = nNuevoDato;

        return aNuevoArreglo;
    }

}
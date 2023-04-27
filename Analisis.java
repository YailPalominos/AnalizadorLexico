import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analisis {

    /**
     * @author Braulio Yail Palominos Patiño
     */

    /*
     * Ojo
     * Tomar en cuenta que las llaves alteran la posicion de los grupos del marcher
     * En caso de agregar mas grupos
     */

    /*
     * Estas son las expreciones regulares que utiliza generalmente el analizador
     * lexico
     *
     * Palabras reservadas
     * (class|for|if|float|int|boolean|static|new|static|void|int|string|import|
     * public|else|programa|binario)|"
     * + "Identificadores ([a-zA-Z]+)|"
     * + "Relacionales ([<|>]+)|"
     * + "Aritmeticas ([+]|[-])|"
     * + "Asignacion([=]+)|"
     * + "Parentesis ([(|)]+)|"
     * + "LLaves ([{|}]+)|"
     * + "Punto y coma(;)|"
     * + "Bineario ([0-1]b)|"
     * + "Ocatal ([0-8]o)|"
     * + "Hexadecimal (^[0-9A-F]+$)|"
     * + "Espacios (^(\s)*)";
     */

    String sCodigoFuente = ""; // Codigo fuente resibido
    Simbolo[] aSimbolo = new Simbolo[0]; // Simbolos que se obtener en la etapa de analisis
    String[] aFiguras = new String[0]; // Cadena de las figuras a analizar
    Resultado oResultado = new Resultado(); // Clase para imprimir los resultados
    Validacion oDesglosar = new Validacion(); // Clase para verificar la cadena de figura
    int nPosLectura = 0; // posicion de lectura con respecto al codigo fuente
    int nLinea = 0; // Linea en la que va la posicion de lectura con respecto al codigo fuente

    public Analisis(String sCodigoFuente) {
        this.sCodigoFuente = sCodigoFuente;
    }

    // Genera las clases con los simbolos
    public void Generar() {

        // Establecemos una cadena de coincidencias Esto es una exprecion regular
        String coincidencias = "(Rectangulo|Triangulo|Cuadrado|Circulo|Linea)|"
                + "(((\\(\\d{1,2},\\d{1,2}\\)){2,4})+-+\\([1-3]\\)+-+\\((25[0-5]|2[0-4]\\d|1\\d{1,2}|\\d{1,2}),(25[0-5]|2[0-4]\\d|1\\d{1,2}|\\d{1,2}),(25[0-5]|2[0-4]\\d|1\\d{1,2}|\\d{1,2})\\))|"
                + "(((\s)*))";
        // Define un patron de busquedas dentro de nuestra cadena de coincidencias
        Pattern pPatron = Pattern.compile(coincidencias);
        // ralizara la búsqueda de nuestra coincidencias
        Matcher mMatcher = pPatron.matcher(sCodigoFuente);

        // Buscamos las coincidencias con el ciclo While
        while (mMatcher.find()) {

            String tokenPalabrasrReservadas = mMatcher.group(1);
            String tokenFiguras = mMatcher.group(2);
            String tokenEspacios = mMatcher.group(8);

            nLinea = ObtenerLinea(sCodigoFuente, mMatcher.start());
            if (tokenPalabrasrReservadas != null) {
                nPosLectura += tokenPalabrasrReservadas.length();
                int nPosInicioLexema = nPosLectura - tokenPalabrasrReservadas.length();
                AgregarSimbolo("Palabras reservadas:", tokenPalabrasrReservadas, nLinea, nPosInicioLexema,
                        nPosLectura);
            }
            if (tokenEspacios != null) {
                if (tokenEspacios.length() > 0) {
                    nPosLectura += tokenEspacios.length();
                }
            }

            if (tokenFiguras != null) {
                nPosLectura += tokenFiguras.length();
                int nPosInicioLexema = nPosLectura - tokenFiguras.length();
                AgregarSimbolo("Figura:", tokenFiguras, nLinea, nPosInicioLexema,
                        nPosLectura);
            }
        }

        oResultado.Imprimir(aSimbolo);

        for (int x = 0; x < this.aFiguras.length; x++) {
            oDesglosar.PrepararDatos(this.aFiguras[x]);

        }

    }

    public void AgregarSimbolo(String sToken, String sLexema, int nLinea, int nPosInicioLexema, int nPosFinalLexema) {

        Simbolo[] aSimboloNuevo = this.aSimbolo;
        aSimboloNuevo = new Simbolo[this.aSimbolo.length + 1];
        System.arraycopy(this.aSimbolo, 0, aSimboloNuevo, 0, this.aSimbolo.length);

        Simbolo oSimbolo = new Simbolo();
        oSimbolo.Token = sToken;
        oSimbolo.Lexema = sLexema;
        oSimbolo.Linea = nLinea;
        oSimbolo.PosInicioLexema = nPosInicioLexema;
        oSimbolo.PosFinalLexema = nPosFinalLexema;

        if (oSimbolo.Token.equals("Figura:")) {
            RedimencionarArreglo(oSimbolo.Lexema);
        }

        aSimboloNuevo[aSimboloNuevo.length - 1] = oSimbolo;

        this.aSimbolo = aSimboloNuevo;

    }

    public void RedimencionarArreglo(String sCadena) {
        String[] aFigurasNuevo = new String[0];
        aFigurasNuevo = new String[this.aFiguras.length + 1];
        System.arraycopy(this.aFiguras, 0, aFigurasNuevo, 0, this.aFiguras.length);
        aFigurasNuevo[aFigurasNuevo.length - 1] = sCadena;
        this.aFiguras = aFigurasNuevo;
    }

    int ObtenerLinea(String sCodigoFuente, int nInicio) {
        int nLinea = 0;
        Pattern pPatron = Pattern.compile("\n");
        Matcher mMatcher = pPatron.matcher(sCodigoFuente);
        mMatcher.region(0, nInicio);

        while (mMatcher.find()) {
            nLinea++;
        }

        if (this.nLinea != nLinea) {
            nPosLectura = 0;
        }

        return (nLinea);
    }

}
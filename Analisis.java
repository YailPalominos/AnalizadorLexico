import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analisis {

    /**
     * @author Braulio Yail Palominos Patiño
     * @co Author Jose Adrian Terrones Perez
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
     * + "Binario ([0-1]b)|"
     * + "Octal ([0-8]o)|"
     * + "Hexadecimal (^[0-9A-F]+$)|"
     * + "Espacios (^(\s)*)";
     */

    String sCodigoFuente = ""; // Codigo fuente resibido
    String sCodigoFuenteErrores = ""; // Codigo fuente resibido
    // de analisis
    String[] aFiguras = new String[0]; // Cadena de las figuras a analizar
    Resultado oResultado = new Resultado(); // Clase para imprimir los resultados
    Validacion oDesglosar = new Validacion(); // Clase para verificar la cadena de figura
    int nPosLectura = 0; // posicion de lectura con respecto al codigo fuente
    int nLinea = 0; // Linea en la que va la posicion de lectura con respecto al codigo fuente

    TblaTokens[] TaToken = new TblaTokens[0];
    TblaSimbolos[] TaSimbolos = new TblaSimbolos[0];
    TblaReservadas[] TaReserv = new TblaReservadas[0];
    TblaErrores[] TaErrors = new TblaErrores[0];

    public Analisis(String sCodigoFuente) {
        this.sCodigoFuente = sCodigoFuente;
        this.sCodigoFuenteErrores = sCodigoFuente;
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
                AgregarTablaReservadas("Palabras reservadas:", tokenPalabrasrReservadas, nLinea, nPosInicioLexema,
                        nPosLectura);

                AgregarTablaToken("Palabras reservadas:", tokenPalabrasrReservadas, nLinea, nPosInicioLexema,
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

                AgregarTablaToken("Figura:", tokenFiguras, nLinea, nPosInicioLexema,
                        nPosLectura);

                AgregarTablaSimbolos("Figura:", tokenFiguras, nLinea, nPosInicioLexema,
                        nPosLectura);
            }

        }

        // Codigo fuente a chart para leer parte por parte
        var cLetras = this.sCodigoFuenteErrores.toCharArray();
        String sPalabra = "";
        Pattern pPatronError = Pattern.compile(
                "(((\\(\\d{1,2},\\d{1,2}\\)){2,4})+-+\\([1-3]\\)+-+\\((25[0-5]|2[0-4]\\d|1\\d{1,2}|\\d{1,2}),(25[0-5]|2[0-4]\\d|1\\d{1,2}|\\d{1,2}),(25[0-5]|2[0-4]\\d|1\\d{1,2}|\\d{1,2})\\))");
        this.nPosLectura = 0;
        // Recorre palabra por palabra encontrada
        for (int y = 0; y < cLetras.length; y++) {

            nLinea = ObtenerLinea(sCodigoFuenteErrores, y);
            sPalabra += cLetras[y];
            this.nPosLectura++;

            if (sPalabra.split("\\s").length > 0) {
                this.nPosLectura -= 1;
                sPalabra = sPalabra.trim();

                char cLeta = cLetras[y];
                var x = ((cLeta + "").replace("", " ").trim());

                // System.out.println(">" + sPalabra);

                if (sPalabra.equals("Rectangulo")) {
                    sPalabra = sPalabra.replaceAll("Rectangulo", "");
                    nPosLectura += "Rectangulo".length();
                }

                if (sPalabra.equals("Triangulo")) {
                    sPalabra = sPalabra.replaceAll("Triangulo", "");
                    nPosLectura += "Triangulo".length();
                }

                if (sPalabra.equals("Cuadrado")) {
                    sPalabra = sPalabra.replaceAll("Cuadrado", "");
                    nPosLectura += "Cuadrado".length();
                }
                if (sPalabra.equals("Circulo")) {
                    sPalabra = sPalabra.replaceAll("Circulo", "");
                    nPosLectura += "Circulo".length();
                }
                if (sPalabra.equals("Linea")) {
                    sPalabra = sPalabra.replaceAll("Linea", "");
                    nPosLectura += "Linea".length();
                }
                // System.out.println(">" + nPosLectura);

                if (x.length() == 0) {
                    Matcher mMatcherError = pPatronError.matcher(sPalabra);

                    if (mMatcherError.find()) {
                        sPalabra = "";
                    } else {
                        this.nPosLectura += sPalabra.length();
                        int nPosInicioLexema = this.nPosLectura - sPalabra.length();
                        AgregarTablaErrores("Error:", sPalabra, nLinea, nPosInicioLexema,
                                nPosLectura);
                        sPalabra = "";
                    }
                }
            }
        }

        oResultado.ImprimirTblaTokens(TaToken);
        System.out.println();
        oResultado.ImprimirTblaPlRes(TaReserv);
        System.out.println();
        oResultado.ImprimirTblaSimb(TaSimbolos);
        System.out.println();
        oResultado.ImprimirTblaError(TaErrors);
        System.out.println();

        if (this.aFiguras.length > 0)

        {
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%70s", "Resultados de las figuras");
            System.out.println();
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------");
        }
        for (int x = 0; x < this.aFiguras.length; x++) {
            oDesglosar.PrepararDatos(this.aFiguras[x]);
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------");
        }

    }

    public void AgregarTablaToken(String sToken, String sLexema, int nLinea, int nPosInicioLexema,
            int nPosFinalLexema) {

        TblaTokens[] aTblaTokensNueva = this.TaToken;
        aTblaTokensNueva = new TblaTokens[this.TaToken.length + 1];
        System.arraycopy(this.TaToken, 0, aTblaTokensNueva, 0, this.TaToken.length);

        TblaTokens oTblaSimbolo = new TblaTokens();
        oTblaSimbolo.Token = sToken;
        oTblaSimbolo.Lexema = sLexema;
        oTblaSimbolo.Linea = nLinea;
        oTblaSimbolo.PosInicioLexema = nPosInicioLexema;
        oTblaSimbolo.PosFinalLexema = nPosFinalLexema;

        if (oTblaSimbolo.Token.contains("Figura:")) {
            AgregarFigura(oTblaSimbolo.Lexema);
        }

        aTblaTokensNueva[aTblaTokensNueva.length - 1] = oTblaSimbolo;
        this.TaToken = aTblaTokensNueva;

    }

    /**
     * Metodo que agrega los simbolos a la tabla de Errores
     * 
     * @param sError
     * @param sLexema
     * @param sLinea
     * @param sPosInicioLexema
     * @param sPosFinalLexema
     */
    public void AgregarTablaErrores(String sError, String sLexema, int sLinea, int sPosInicioLexema,
            int sPosFinalLexema) {

        TblaErrores[] aErrorNuevo = this.TaErrors;
        aErrorNuevo = new TblaErrores[this.TaErrors.length + 1];
        System.arraycopy(this.TaErrors, 0, aErrorNuevo, 0, this.TaErrors.length);

        TblaErrores oError = new TblaErrores();
        oError.setError(sError);
        oError.setLexema(sLexema);
        oError.setLinea(sLinea);
        oError.setInicioPos(sPosInicioLexema);
        oError.setFinalPos(sPosFinalLexema);

        aErrorNuevo[aErrorNuevo.length - 1] = oError;

        this.TaErrors = aErrorNuevo;
    }// Fin del metodo AgregarTablaErrores

    /**
     * Metodo que agrega los simbolos a la tabla Palabras Reservadas
     * 
     * @param sReservada
     * @param sLexema
     * @param sLinea
     * @param sPosInicioLexema
     * @param sPosFinalLexema
     */
    public void AgregarTablaReservadas(String sReservada, String sLexema, int sLinea, int sPosInicioLexema,
            int sPosFinalLexema) {

        // Se verifica si la Palabra reservada no haya sido agregada con anterioridad

        TblaReservadas[] aReservNuevo = this.TaReserv;
        aReservNuevo = new TblaReservadas[this.TaReserv.length + 1];
        System.arraycopy(this.TaReserv, 0, aReservNuevo, 0, this.TaReserv.length);

        TblaReservadas oReserv = new TblaReservadas();
        oReserv.setReservada(sReservada);
        oReserv.setLexema(sLexema);
        oReserv.setLinea(sLinea);
        oReserv.setPosInicioReserv(sPosInicioLexema);
        oReserv.setPosFinalReserv(sPosFinalLexema);

        aReservNuevo[aReservNuevo.length - 1] = oReserv;

        this.TaReserv = aReservNuevo;
    }// Fin del metodo AgregarTablaReservadas

    /**
     * Metodo para agregar un simbolo a la Tabla de simbolos
     * 
     * @param sSimbolo
     * @param sLexema
     * @param sLinea
     * @param sPosInicioLexema
     * @param sPosFinalLexema
     */
    public void AgregarTablaSimbolos(String sSimbolo, String sLexema, int sLinea, int sPosInicioLexema,
            int sPosFinalLexema) {

        TblaSimbolos[] aSimboloNuevo = this.TaSimbolos;
        aSimboloNuevo = new TblaSimbolos[this.TaSimbolos.length + 1];
        System.arraycopy(this.TaSimbolos, 0, aSimboloNuevo, 0, this.TaSimbolos.length);

        TblaSimbolos oSimbolo = new TblaSimbolos();
        oSimbolo.setSimbolo(sSimbolo);
        oSimbolo.setLexema(sLexema);
        oSimbolo.setLinea(sLinea);
        oSimbolo.setFinalPosSimbolo(sPosInicioLexema);
        oSimbolo.setFinalPosSimbolo(sPosFinalLexema);

        aSimboloNuevo[aSimboloNuevo.length - 1] = oSimbolo;

        this.TaSimbolos = aSimboloNuevo;
    }// Fin del metodo AgregarTablaSimbolos

    public void AgregarFigura(String sFigura) {

        String[] aFigurasNueva = this.aFiguras;
        aFigurasNueva = new String[this.aFiguras.length + 1];
        System.arraycopy(this.aFiguras, 0, aFigurasNueva, 0, this.aFiguras.length);

        aFigurasNueva[aFigurasNueva.length - 1] = sFigura;
        this.aFiguras = aFigurasNueva;
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
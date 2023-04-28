import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Analisis {

    String sCodigoFuente = "";
    TblaTokens[] TaToken = new TblaTokens[0];
    TblaSimbolos[] TaSimbolos = new TblaSimbolos[0];
    TblaReservadas[] TaReserv = new TblaReservadas[0];
    TblaErrores[] TaErrors = new TblaErrores[0];

    Resultado oResultado = new Resultado();
    int nPosLectura = 0;
    int nLinea = 0;
    Set<String> simbolosAgregados = new HashSet<>();
    Set<String> PRAgregados = new HashSet<>();

    public Analisis(String sCodigoFuente) {
        this.sCodigoFuente = sCodigoFuente;
        // System.out.println(sCodigoFuente);
    }

    public void Generar() {

        // Establecemos una cadena de coincidencias Esto es una expresion regular
        String coincidencias = "(class|for|if|float|int|boolean|static|void|new|static|int|string|import|public|else|programa|binario|"
                + "octal|hexadecimal|leer|escribir|finprograma|linea|circulo|triangulo|cuadrado|rectangulo)|"
                + "([a-zA-Z]+)|"
                + "([<|>]+)|"
                + "([+]|[-])|"
                + "([=]+)|"
                + "([++]{2}|"
                + "[--]{2})|"
                + "([(|)]+)|"
                + "([{|}]+)|"
                + "([\\[|\\]])|"
                + "(;)|"
                + "(\\.)|"
                + "([0-9]+,[0-9]+)|"
                + "([0-3]+)|"
                + "([0-1]b)|"
                + "([0-8]o)|"
                + "(^[0-9A-F]+$)|"
                + "(\\s+)|"
                + "(//.*)|(/\\*.*?\\*/)|"
                + "(^(\s)*)";

        // Define un patron de busquedas dentro de nuestra cadena de coincidencias
        Pattern pPatron = Pattern.compile(coincidencias);
        // ralizara la búsqueda de nuestra coincidencias
        Matcher mMatcher = pPatron.matcher(sCodigoFuente);

        // Buscamos las coincidencias con el ciclo While
        while (mMatcher.find()) {
            
            String tokenPalabrasrReservadas = mMatcher.group(1);
            String tokenIdentificadores = mMatcher.group(2);
            String tokenORelacionales = mMatcher.group(3);
            String tokenOAritmeticas = mMatcher.group(4);
            String tokenOAsignacion = mMatcher.group(5);
            String tokenOID = mMatcher.group(6);
            String tokenParentesis = mMatcher.group(7);
            String tokenLlaves = mMatcher.group(8);
            String tokenCorchetes = mMatcher.group(9);
            String tokenPuntoYComa = mMatcher.group(10);
            String tokenPunto = mMatcher.group(11);
            String tokenBinarios = mMatcher.group(12);
            String tokenOctales = mMatcher.group(13);
            String tokenHexadecimales = mMatcher.group(14);
            String tokenEspacios = mMatcher.group(15);
            String tokenComentarioUnaLinea = mMatcher.group(16);

            int nPosInicioLexema = 0;
            nLinea = ObtenerLinea(sCodigoFuente, mMatcher.start());

            if (tokenPalabrasrReservadas != null) {
                
                nPosLectura += tokenPalabrasrReservadas.length();
                nPosInicioLexema = nPosLectura - tokenPalabrasrReservadas.length();
                AgregarTablaToken("Palabras reservadas:", tokenPalabrasrReservadas, nLinea, nPosInicioLexema,
                        nPosLectura);
            if (!PRAgregados.contains(tokenPalabrasrReservadas))
                AgregarTablaReservadas("Palabra Reservada", tokenPalabrasrReservadas, nLinea, nPosInicioLexema, nPosLectura);
                PRAgregados.add(tokenPalabrasrReservadas);
            }

            if (tokenIdentificadores != null) {
                nPosLectura += tokenIdentificadores.length();
                nPosInicioLexema = nPosLectura - tokenIdentificadores.length();
                AgregarTablaToken("Identificadores:", tokenIdentificadores, nLinea, nPosInicioLexema, nPosLectura);
            }

            if (tokenORelacionales != null) {
                nPosLectura += tokenORelacionales.length();
                nPosInicioLexema = nPosLectura - tokenORelacionales.length();
                AgregarTablaToken("Operadores relacionales:", tokenORelacionales, nLinea, nPosInicioLexema,
                        nPosLectura);
            }
            if (!simbolosAgregados.contains(tokenORelacionales)){
                AgregarTablaSimbolos("Operadores relacionales:", tokenORelacionales, nLinea, nPosInicioLexema, nPosLectura);
                simbolosAgregados.add(tokenORelacionales);
            }

            if (tokenOAritmeticas != null) {
                nPosLectura += tokenOAritmeticas.length();
                nPosInicioLexema = nPosLectura - tokenOAritmeticas.length();
                AgregarTablaToken("Operadores de aritméticos:", tokenOAritmeticas, nLinea, nPosInicioLexema,
                        nPosLectura);
            }
            if (!simbolosAgregados.contains(tokenOAritmeticas)){
                AgregarTablaSimbolos("Operadores de aritméticos:", tokenOAritmeticas, nLinea, nPosInicioLexema, nPosLectura);
                simbolosAgregados.add(tokenOAritmeticas);
            }
            if (tokenOAsignacion != null) {
                nPosLectura += tokenOAsignacion.length();
                AgregarTablaToken("Operadores de asignación:", tokenOAsignacion, nLinea, nPosInicioLexema,
                        nPosLectura);
            }
            if (!simbolosAgregados.contains(tokenOAsignacion)){
                AgregarTablaSimbolos("Operadores de asignación:", tokenOAsignacion, nLinea, nPosInicioLexema, nPosLectura);
                simbolosAgregados.add(tokenOAsignacion);
            }

            if (tokenOID != null) {
                nPosLectura += tokenOID.length();
                nPosInicioLexema = nPosLectura - tokenOID.length();
                AgregarTablaToken("Operadores de incremento/decremento: ", tokenOID, nLinea, nPosInicioLexema, nPosLectura);
            }
            if (!simbolosAgregados.contains(tokenOID)){
                AgregarTablaSimbolos("Operadores de incremento/decremento: ", tokenOID, nLinea, nPosInicioLexema, nPosLectura);
                simbolosAgregados.add(tokenOID);
            }
            
            if (tokenParentesis != null) {
                nPosLectura += tokenParentesis.length();
                nPosInicioLexema = nPosLectura - tokenParentesis.length();
                AgregarTablaToken("Parentesis:", tokenParentesis, nLinea, nPosInicioLexema, nPosLectura);
            }
            if(!simbolosAgregados.contains(tokenParentesis)){
                AgregarTablaSimbolos("Parentesis:", tokenParentesis, nLinea, nPosInicioLexema, nPosLectura);
                simbolosAgregados.add(tokenParentesis);
            }

            if (tokenLlaves != null) {
                nPosLectura += tokenLlaves.length();
                nPosInicioLexema = nPosLectura - tokenLlaves.length();
                AgregarTablaToken("Llaves: ", tokenLlaves, nLinea, nPosInicioLexema, nPosLectura);
            }
            if(!simbolosAgregados.contains(tokenLlaves)){
                AgregarTablaSimbolos("Llaves: ", tokenLlaves, nLinea, nPosInicioLexema, nPosLectura);
                simbolosAgregados.add(tokenLlaves);
            }


            if (tokenCorchetes != null) {
                nPosLectura += tokenCorchetes.length();
                nPosInicioLexema = nPosLectura - tokenCorchetes.length();
                AgregarTablaToken("Corchetes", tokenCorchetes, nLinea, nPosInicioLexema, nPosLectura);
            }
            if (!simbolosAgregados.contains(tokenCorchetes)){
                AgregarTablaSimbolos("Corchetes", tokenCorchetes, nLinea, nPosInicioLexema, nPosLectura);
                simbolosAgregados.add(tokenCorchetes);
            }

            if (tokenPuntoYComa != null) {
                nPosLectura += tokenPuntoYComa.length();
                nPosInicioLexema = nPosLectura - tokenPuntoYComa.length();
                AgregarTablaToken("Punto y coma", tokenPuntoYComa, nLinea, nPosInicioLexema, nPosLectura);
            }
            if (!simbolosAgregados.contains(tokenPuntoYComa)){
                AgregarTablaSimbolos("Punto y coma", tokenPuntoYComa, nLinea, nPosInicioLexema, nPosLectura);
                simbolosAgregados.add(tokenPuntoYComa);
            }

            if (tokenPunto != null) {
                nPosLectura += tokenPunto.length();
                nPosInicioLexema = nPosLectura - tokenPunto.length();
                AgregarTablaToken("Punto", tokenPunto, nLinea, nPosInicioLexema, nPosLectura);
            }
            if (!simbolosAgregados.contains(tokenPunto)){
                AgregarTablaSimbolos("Punto", tokenPunto, nLinea, nPosInicioLexema, nPosLectura);
                simbolosAgregados.add(tokenPunto);
            }
            
            if (tokenBinarios != null) {
                nPosLectura += tokenBinarios.length();
                nPosInicioLexema = nPosLectura - tokenBinarios.length();
                AgregarTablaToken("Numeros binarios", tokenBinarios, nLinea, nPosInicioLexema, nPosLectura);

            }
            if (tokenOctales != null) {
                nPosLectura += tokenOctales.length();
                nPosInicioLexema = nPosLectura - tokenOctales.length();
                AgregarTablaToken("Numeros octales", tokenOctales, nLinea, nPosInicioLexema, nPosLectura);
            }
            if (tokenHexadecimales != null) {
                nPosLectura += tokenHexadecimales.length();
                nPosInicioLexema = nPosLectura - tokenHexadecimales.length();
                AgregarTablaToken("Numeros hexadecimales", tokenHexadecimales, nLinea, nPosInicioLexema, nPosLectura);
            }
            if (tokenEspacios != null) {
                nPosLectura += tokenEspacios.length();
                nPosInicioLexema = nPosLectura - tokenEspacios.length();
            }
            if(tokenComentarioUnaLinea != null){
                nPosLectura += tokenComentarioUnaLinea.length();
                AgregarTablaToken("Comentario de una línea:", tokenComentarioUnaLinea, nLinea, nPosLectura - tokenComentarioUnaLinea.length(), nPosLectura);
                //AgregarTablaErrores("Error en el Lexema", tokenComentarioUnaLinea, nPosInicioLexema, nPosInicioLexema, nPosInicioLexema);
                //AgregarTablaReservadas("Palabra Reservada", tokenComentarioUnaLinea, nPosInicioLexema, nPosInicioLexema, nPosInicioLexema);
                //AgregarTablaSimbolos("Simbolo", tokenComentarioUnaLinea, nPosInicioLexema, nPosInicioLexema, nPosInicioLexema);
            }
            if (tokenPalabrasrReservadas == null &&
                tokenIdentificadores == null &&
                tokenORelacionales == null &&
                tokenOAritmeticas == null &&
                tokenOAsignacion == null &&
                tokenOID == null &&
                tokenParentesis == null &&
                tokenLlaves == null &&
                tokenCorchetes == null &&
                tokenPuntoYComa == null &&
                tokenPunto == null &&
                tokenBinarios == null &&
                tokenOctales == null &&
                tokenHexadecimales == null &&
                tokenEspacios == null &&
                tokenComentarioUnaLinea == null) {
        
                String tokenDesconocido = sCodigoFuente.substring(nPosLectura, mMatcher.end());
                nPosLectura += tokenDesconocido.length();
                nPosInicioLexema = nPosLectura - tokenDesconocido.length();
                AgregarTablaErrores("Símbolo no reconocido:", tokenDesconocido, nLinea, nPosInicioLexema, nPosLectura);
}
            
        }

        oResultado.ImprimirTblaTokens(TaToken);
        oResultado.ImprimirTblaPlRes(TaReserv);
        oResultado.ImprimirTblaSimb(TaSimbolos);
        oResultado.ImprimirTblaError(TaErrors);
        

    }

    /**
     * Metodo que agrega los simbolos a la tabla de tokens
     * @param sToken
     * @param sLexema
     * @param nLinea
     * @param nPosInicioLexema
     * @param nPosFinalLexema
     */
    public void AgregarTablaToken(String sToken, String sLexema, int nLinea, int nPosInicioLexema, int nPosFinalLexema) {

        TblaTokens[] aSimboloNuevo = this.TaToken;
        aSimboloNuevo = new TblaTokens[this.TaToken.length + 1];
        System.arraycopy(this.TaToken, 0, aSimboloNuevo, 0, this.TaToken.length);

        TblaTokens oSimbolo = new TblaTokens();
        oSimbolo.Token = sToken;
        oSimbolo.Lexema = sLexema;
        oSimbolo.Linea = nLinea;
        oSimbolo.PosInicioLexema = nPosInicioLexema;
        oSimbolo.PosFinalLexema = nPosFinalLexema;

        aSimboloNuevo[aSimboloNuevo.length - 1] = oSimbolo;

        this.TaToken = aSimboloNuevo;

    }//Fin del metodo AgregarTablaToken


    /**
     * Metodo que agrega los simbolos a la tabla de Errores
     * @param sError
     * @param sLexema
     * @param sLinea
     * @param sPosInicioLexema
     * @param sPosFinalLexema
     */
    public void AgregarTablaErrores( String sError, String sLexema, int sLinea, int sPosInicioLexema, int sPosFinalLexema){

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
    }//Fin del metodo AgregarTablaErrores

    /**
     * Metodo que agrega los simbolos a la tabla Palabras Reservadas
     * @param sReservada
     * @param sLexema
     * @param sLinea
     * @param sPosInicioLexema
     * @param sPosFinalLexema
     */
    public void AgregarTablaReservadas( String sReservada, String sLexema, int sLinea, int sPosInicioLexema, int sPosFinalLexema){

               //Se verifica si la Palabra reservada no haya sido agregada con anterioridad


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
    }//Fin del metodo AgregarTablaReservadas

    /**
     * Metodo para agregar un simbolo a la Tabla de simbolos
     * @param sSimbolo
     * @param sLexema
     * @param sLinea
     * @param sPosInicioLexema
     * @param sPosFinalLexema
     */
    public void AgregarTablaSimbolos( String sSimbolo, String sLexema, int sLinea, int sPosInicioLexema, int sPosFinalLexema){

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
    }//Fin del metodo AgregarTablaSimbolos

    int ObtenerLinea(String sCodigoFuente, int nInicio) {
        int nLinea = 1;
        Pattern pPatron = Pattern.compile("\n");
        Matcher mMatcher = pPatron.matcher(sCodigoFuente);
        mMatcher.region(0, nInicio);
        
        while (mMatcher.find()) {
            nLinea++;
        }
        return (nLinea);
    }

}
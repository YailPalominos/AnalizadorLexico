import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analisis {

    String sCodigoFuente = "";
    Simbolo[] aSimbolo = new Simbolo[0];
    Resultado oResultado = new Resultado();
    int nPosLectura = 0;
    int nLinea = 0;

    public Analisis(String sCodigoFuente) {
        this.sCodigoFuente = sCodigoFuente;
        // System.out.println(sCodigoFuente);
    }

    public void Generar() {

        // Establecemos una cadena de coincidencias Esto es una exprecion regular
        String coincidencias = "(class|for|if|float|int|boolean|static|new|static|void|int|string|import|public|else|programa|binario|"
                + "octal|hexadecimal|leer|escribir|finprograma)|"
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
                + "([0-1]b)|"
                + "([0-8]o)|"
                + "(^[0-9A-F]+$)|"
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

            int nPosInicioLexema = 0;
            nLinea = ObtenerLina(sCodigoFuente, mMatcher.start());

            if (tokenPalabrasrReservadas != null) {
                nPosLectura += tokenPalabrasrReservadas.length();
                nPosInicioLexema = nPosLectura - tokenPalabrasrReservadas.length();
                AgregarSimbolo("Palabras reservadas:", tokenPalabrasrReservadas, nLinea, nPosInicioLexema,
                        nPosLectura);

            }
            if (tokenIdentificadores != null) {
                nPosLectura += tokenIdentificadores.length();
                nPosInicioLexema = nPosLectura - tokenIdentificadores.length();
                AgregarSimbolo("Identificadores:", tokenIdentificadores, nLinea, nPosInicioLexema, nPosLectura);

            }

            if (tokenORelacionales != null) {
                nPosLectura += tokenORelacionales.length();
                nPosInicioLexema = nPosLectura - tokenORelacionales.length();
                AgregarSimbolo("Operadores relacionales:", tokenORelacionales, nLinea, nPosInicioLexema,
                        nPosLectura);

            }
            if (tokenOAritmeticas != null) {
                nPosLectura += tokenOAritmeticas.length();
                nPosInicioLexema = nPosLectura - tokenOAritmeticas.length();
                AgregarSimbolo("Operadores de aritméticos:", tokenOAritmeticas, nLinea, nPosInicioLexema,
                        nPosLectura);

            }
            if (tokenOAsignacion != null) {
                nPosLectura += tokenOAsignacion.length();
                AgregarSimbolo("Operadores de asignación:", tokenOAsignacion, nLinea, nPosInicioLexema,
                        nPosLectura);
            }
            if (tokenOID != null) {
                nPosLectura += tokenOID.length();
                nPosInicioLexema = nPosLectura - tokenOID.length();
                AgregarSimbolo("Operadores de incremento/decremento: ", tokenOID, nLinea, nPosInicioLexema,
                        nPosLectura);

            }
            if (tokenParentesis != null) {
                nPosLectura += tokenParentesis.length();
                nPosInicioLexema = nPosLectura - tokenParentesis.length();
                AgregarSimbolo("Parentesis:", tokenParentesis, nLinea, nPosInicioLexema, nPosLectura);

            }
            if (tokenLlaves != null) {
                nPosLectura += tokenLlaves.length();
                nPosInicioLexema = nPosLectura - tokenLlaves.length();
                AgregarSimbolo("Llaves: ", tokenLlaves, nLinea, nPosInicioLexema, nPosLectura);

            }
            if (tokenCorchetes != null) {
                nPosLectura += tokenCorchetes.length();
                nPosInicioLexema = nPosLectura - tokenCorchetes.length();
                AgregarSimbolo("Corchetes", tokenCorchetes, nLinea, nPosInicioLexema, nPosLectura);

            }
            if (tokenPuntoYComa != null) {
                nPosLectura += tokenPuntoYComa.length();
                nPosInicioLexema = nPosLectura - tokenPuntoYComa.length();
                AgregarSimbolo("Punto y coma", tokenPuntoYComa, nLinea, nPosInicioLexema, nPosLectura);

            }
            if (tokenPunto != null) {
                nPosLectura += tokenPunto.length();
                nPosInicioLexema = nPosLectura - tokenPunto.length();
                AgregarSimbolo("Punto", tokenPunto, nLinea, nPosInicioLexema, nPosLectura);

            }
            if (tokenBinarios != null) {
                nPosLectura += tokenBinarios.length();
                nPosInicioLexema = nPosLectura - tokenBinarios.length();
                AgregarSimbolo("Numeros binarios", tokenBinarios, nLinea, nPosInicioLexema, nPosLectura);

            }
            if (tokenOctales != null) {
                nPosLectura += tokenOctales.length();
                nPosInicioLexema = nPosLectura - tokenOctales.length();
                AgregarSimbolo("Numeros octales", tokenOctales, nLinea, nPosInicioLexema, nPosLectura);
            }
            if (tokenHexadecimales != null) {
                nPosLectura += tokenHexadecimales.length();
                nPosInicioLexema = nPosLectura - tokenHexadecimales.length();
                AgregarSimbolo("Numeros hexadecimales", tokenHexadecimales, nLinea, nPosInicioLexema, nPosLectura);
            }

            if (tokenEspacios != null) {

                // System.out.println(tokenEspacios);
                // if (tokenEspacios.length() > 1)
                // nPosLectura -= tokenEspacios.length();

            }

        }

        oResultado.Imprimir(aSimbolo);

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

        aSimboloNuevo[aSimboloNuevo.length - 1] = oSimbolo;

        this.aSimbolo = aSimboloNuevo;

    }

    int ObtenerLina(String sCodigoFuente, int nInicio) {
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
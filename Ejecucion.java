
//Biblotecas importadas
import java.io.IOException; //Bibloteca Control de excepción para localizar el archivo
import java.io.UnsupportedEncodingException; //Bibloteca Control de excepción para convertir bytes a string
import java.nio.file.Files; //Bibloteca verificacion de existencia el archivo
import java.nio.file.Path; //Bibloteca de la ruta
import java.nio.file.Paths; //Bibloteca del instanciador de la ruta

public class Ejecucion {
    // Ruta estatica relativa del programa java
    static String sRutaEstaticaRelativa = ("C:\\Users\\yailp\\OneDrive\\Documentos\\GitHub\\AnalizadorLexico\\ProgramaEjemploFacil.java");

    public static void main(String[] args) throws IOException {

        // Envia el codigo fuente obtenido el un archivo tipo java a la estapa de
        // analisis
        Analisis cAnalizador = new Analisis(ObtenerCodigoFuente(sRutaEstaticaRelativa));
        cAnalizador.Generar();
    }

    public static String ObtenerCodigoFuente(String sRutaEstatica) throws IOException {

        Path pRuta = Paths.get(sRutaEstatica);
        // Finaliza el programa y lanza un mensage, en caso de no existir el directorio
        if (!Files.exists(pRuta)) {
            System.err.println("No existe el directorio");
            return "";
        }
        // Almacena los bytes encontrados en la ruta del archivo
        byte[] bBytes = Files.readAllBytes(pRuta);

        // Regresa una cadena tipo String de los bytes del archivo ingresando a una
        // función retorno
        return ConvertirBytesAString(bBytes);
    }

    private static String ConvertirBytesAString(byte[] byteValue) throws UnsupportedEncodingException {
        String stringValue = (new String(byteValue, "US-ASCII"));
        return (stringValue);
    }

}
public class Simbolo {
    /**
     * @author Braulio Yail Palominos Patiño
     */
    public String Token;
    public String Lexema;
    public int Linea;
    public int PosInicioLexema;
    public int PosFinalLexema;

    public Simbolo() {
        this.Token = "";
        this.Lexema = "";
        this.Linea = 0;
        this.PosInicioLexema = 0;
        this.PosFinalLexema = 0;
    }
}

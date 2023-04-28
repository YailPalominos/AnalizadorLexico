public class TblaSimbolos {

    private String simbolo;
    private String lexema;
    private int linea;
    private int posInicioSimbolo;
    private int posFinalSimbolo;

    public TblaSimbolos() {

    }

    public TblaSimbolos(String simbolo, String lexema, int linea, int posInicioSimbolo, int posFinalSimbolo) {
        this.simbolo = simbolo;
        this.lexema = lexema;
        this.linea = linea;
        this.posInicioSimbolo = posInicioSimbolo;
        this.posFinalSimbolo = posFinalSimbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public void setInicioPosSimbolo(int posInicioSimbolo) {
        this.posInicioSimbolo = posInicioSimbolo;
    }

    public void setFinalPosSimbolo(int posFinalSimbolo) {
        this.posFinalSimbolo = posFinalSimbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public String getLexema() {
        return lexema;
    }

    public int getLinea() {
        return linea;
    }

    public int getPosInicioSimbolo() {
        return posInicioSimbolo;
    }

    public int getPosFinalSimbolo() {
        return posFinalSimbolo;
    }
}
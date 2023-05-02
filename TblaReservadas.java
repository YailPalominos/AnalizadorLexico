public class TblaReservadas {

    private String reservada;
    private String lexema;
    private int linea;
    private int posInicioReserv;
    private int posFinalReserv;

    public TblaReservadas() {

    }

    public TblaReservadas(String reservada, String lexema, int linea, int posInicioError, int posFinalError) {
        this.reservada = reservada;
        this.lexema = lexema;
        this.linea = linea;
        this.posInicioReserv = posInicioError;
        this.posFinalReserv = posFinalError;
    }

    public void setReservada(String reservada) {
        this.reservada = reservada;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public void setPosInicioReserv(int posInicioError) {
        this.posInicioReserv = posInicioError;
    }

    public void setPosFinalReserv(int posFinalError) {
        this.posFinalReserv = posFinalError;
    }

    public String getReservada() {
        return reservada;
    }

    public String getLexema() {
        return lexema;
    }

    public int getLinea() {
        return linea;
    }

    public int getPosInicioReserv() {
        return posInicioReserv;
    }

    public int getPosFinalReserv() {
        return posFinalReserv;
    }

}
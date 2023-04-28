public class TblaErrores {
    private String error;
    private String lexema;
    private int linea;
    private int posInicioError;
    private int posFinalError;

    public TblaErrores(){

    }

    public TblaErrores(String error, String lexema, int linea, int posInicioError, int posFinalError) {
        this.error = error;
        this.lexema = lexema;
        this.linea = linea;
        this.posInicioError = posInicioError;
        this.posFinalError = posFinalError;
    }

public void setError( String error ){
    this.error=error;
}

public void setLexema( String lexema ){
    this.lexema=lexema;
}

public void setLinea( int linea ){
    this.linea=linea;
}

public void setInicioPos( int posInicioError ){
    this.posInicioError=posInicioError;
}

public void setFinalPos( int posFinalError ){
    this.posFinalError=posFinalError;
}

    public String getError() {
        return error;
    }

    public String getLexema(){
        return lexema;
    }

    public int getLinea() {
        return linea;
    }

    public int getPosInicioError() {
        return posInicioError;
    }

    public int getPosFinalError() {
        return posFinalError;
    }
}

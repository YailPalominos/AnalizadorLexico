public class Resultado {
<<<<<<< HEAD

    public Resultado() {

    }

    public void ImprimirTblaTokens(TblaTokens[] aSimbolo) {
        System.out.println();
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%70s", "Tabla de Tokens");
        System.out.println();
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%25s %25s %25s %10s %10s", "Token", "Lexema", "Linea", "Inicio", "Final");
        System.out.println();
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------");

        for (int x = 0; x < aSimbolo.length; x++) {
            var Simbolo = aSimbolo[x];

            System.out.format("%25s %25s %25s %10s %10s", Simbolo.Token, Simbolo.Lexema, Simbolo.Linea,
                    Simbolo.PosInicioLexema, Simbolo.PosFinalLexema);
            System.out.println();

        }
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------");

    }

    public void ImprimirTblaError(TblaErrores[] aErrores) {
        System.out.println();
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%70s", "Tabla de Errores");
        System.out.println();
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%25s %25s %25s %10s %10s", "Token Error", "Lexema", "Linea", "Inicio", "Final");
        System.out.println();
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------");

        for (int x = 0; x < aErrores.length; x++) {
            var Error = aErrores[x];

            System.out.format("%25s %25s %25s %10s %10s", Error.getError(), Error.getLexema(), Error.getLinea(), 
                                                                Error.getPosInicioError(), Error.getPosFinalError());
            System.out.println();

        }
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------");

    }

    /**
     * Metodo que Imprime la Tabla de las palabras Reservadas
     * @param aReservadas
     */
    public void ImprimirTblaPlRes(TblaReservadas[] aReservadas) {
        System.out.println();
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%70s", "Tabla de Plabras Reservadas");
        System.out.println();
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%25s %25s %25s %10s %10s", "Token Palabra Reservada", "Lexema", "Linea", "Inicio", "Final");
        System.out.println();
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------");

        for (int x = 0; x < aReservadas.length; x++) {
            var reserv = aReservadas[x];

            System.out.format("%25s %25s %25s %10s %10s", reserv.getReservada(), reserv.getLexema(), reserv.getLinea(), 
                                                                reserv.getPosInicioReserv(), reserv.getPosFinalReserv());
            System.out.println();

        }
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------");

    }//Fin del metodo ImprimirTblaPlRes

    public void ImprimirTblaSimb(TblaSimbolos[] aSimbolos) {
        System.out.println();
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%70s", "Tabla de Simbolos");
        System.out.println();
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%25s %25s %25s %10s %10s", "Token Simbolo", "Lexema", "Linea", "Inicio", "Final");
        System.out.println();
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------");

        for (int x = 0; x < aSimbolos.length; x++) {
            var aSimbolo = aSimbolos[x];

            System.out.format("%25s %25s %25s %10s %10s", aSimbolo.getSimbolo(), aSimbolo.getLexema(), aSimbolo.getLinea(), 
                                                                aSimbolo.getPosInicioSimbolo(), aSimbolo.getPosFinalSimbolo());
            System.out.println();

        }
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------");

    }

}
=======
        /**
         * @author Braulio Yail Palominos Patiño
         */
        public Resultado() {

        }

        public void Imprimir(Simbolo[] aSimbolo) {

                System.out.println(
                                "---------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("%25s %25s %25s %10s %10s", "Token", "Lexema", "Linea", "Inicio", "Final");
                System.out.println();
                System.out.println(
                                "---------------------------------------------------------------------------------------------------------------------------------");

                for (int x = 0; x < aSimbolo.length; x++) {
                        var Simbolo = aSimbolo[x];

                        System.out.format("%25s %25s %25s %10s %10s", Simbolo.Token, Simbolo.Lexema, Simbolo.Linea,
                                        Simbolo.PosInicioLexema, Simbolo.PosFinalLexema);
                        System.out.println();

                }
                System.out.println(
                                "---------------------------------------------------------------------------------------------------------------------------------");

        }

}
>>>>>>> 36b3db99d3d93ba926f1055db9f23e55fc42b65e

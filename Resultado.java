public class Resultado {

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

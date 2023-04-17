// public class AnalisisP {

// // Estado actual.
// private int estado = 0;
// // Posición actual con respecto al editor.
// private int posicion = 0;
// // Representa el texto fuente del análisis.
// public String fuente = "";
// // Se analizará carácter por carácter el String fuente. 'caracter'
// // representará el simbolo.
// private char caracter;
// // Es el carácter o conjunto de caracteres que representan una
// // acción, atributo, etc. en el lenguaje.
// private String lexema = "";
// // Lista completa de lexemas encontradas en el String fuente.
// private ArrayList<String> listaLexema = new ArrayList();
// // Indica que tipo de lexema se encuentra en la lista lexema.
// // los atributos estan ordenados:
// // listaLexema(i) es de tipo listaToken(i)
// private ArrayList<String> listaToken = new ArrayList();

// public AnalizadorPrimitivo(){

// }

// public void Analizar() {

// caracter = fuente.charAt(posicion);

// switch (estado) {
// case 0: {

// if (caracter == ';') {
// lexema += Character.toString(caracter);
// addList(lexema, "punto y coma");
// lexema = "";
// } else if (caracter == '+') {
// lexema += Character.toString(caracter);
// addList(lexema, "mas");
// lexema = "";
// } else if (caracter == '=') {
// lexema += Character.toString(caracter);
// addList(lexema, "igual");
// lexema = "";
// } else if (Character.isDigit(caracter)) {
// estado = 5;
// lexema += Character.toString(caracter);
// } else if (Character.isLetter(caracter)) {
// estado = 1;
// lexema += Character.toString(caracter);
// } else if (esEspacio(caracter)) {
// } else {
// error();
// }
// break;
// }
// case 1: {
// /**
// * Estado 1, inicia cuando se encuentra una letra.
// */
// if (caracter == ';') {
// addList(lexema, "identificador");
// addList(";", "punto y coma");
// estado = 0;
// lexema = "";
// } else if (caracter == '=') {
// addList(lexema, "identificador");
// addList("=", "igual");
// estado = 0;
// lexema = "";
// } else if (caracter == '+') {
// addList(lexema, "identificador");
// addList("+", "mas");
// estado = 0;
// lexema = "";
// } else if (esEspacio(caracter)) {
// addList(lexema, "identificador");
// estado = 0;
// lexema = "";
// } else if (Character.isDigit(caracter) || Character.isLetter(caracter)) {
// lexema += Character.toString(caracter);
// } else {
// error();
// }
// imprimir();
// break;
// }
// case 5: {
// /**
// * Estado 5, inicia cuando se encuentra un digito.
// */
// if (caracter == ';') {
// addList(lexema, "numero");
// addList(";", "punto y coma");
// lexema = "";
// estado = 0;
// } else if (caracter == '=') {
// addList(lexema, "numero");
// addList("=", "igual");
// lexema = "";
// estado = 0;
// } else if (caracter == '+') {
// addList(lexema, "numero");
// addList("+", "mas");
// lexema = "";
// estado = 0;
// } else if (esEspacio(caracter)) {
// addList(lexema, "numero");
// lexema = "";
// estado = 0;
// } else if (Character.isDigit(caracter)) {
// lexema += Character.toString(caracter);
// } else {
// error();
// }
// break;
// }
// default:
// break;
// }

// posicion++;
// imprimir();
// if (posicion >= fuente.length()) {
// if (estado == 1) {
// addList(lexema, "identificador");
// } else if (estado == 5) {
// addList(lexema, "numero");
// }
// } else {
// Analizar();
// }
// }

// private void error() {
// lexema += Character.toString(caracter);
// posicion++;
// if (posicion >= fuente.length()) {
// estado = 0;
// addList(lexema, "error");
// } else {
// caracter = fuente.charAt(posicion);
// if (caracter == '=') {
// addList(lexema, "error");
// addList("=", "igual");
// estado = 0;
// lexema = "";
// } else if (caracter == '+') {
// addList(lexema, "error");
// addList("+", "suma");
// estado = 0;
// lexema = "";
// } else if (caracter == ';') {
// addList(lexema, "error");
// addList(";", "punto y coma");
// estado = 0;
// lexema = "";
// } else if (esEspacio(caracter)) {
// addList(lexema, "error");
// estado = 0;
// lexema = "";
// } else {
// error();
// }
// }
// }

// private boolean esEspacio(char c) {
// return c == '\n' || c == '\t' || c == ' ';
// }

// public void imprimirLista() {
// String auxiliar = "Token ------- Lexema\n";
// for (int i = 0; i < listaLexema.size(); i++) {
// auxiliar += listaToken.get(i) + " ------- " + listaLexema.get(i) + "\n";
// }
// System.out.println(auxiliar);
// }

// private void imprimir() {
// System.out.println("estado:" + estado + " caracter:" + caracter + " lexema:"
// + lexema + " posicion:" + posicion);
// }

// private void addList(String lex, String token) {
// listaLexema.add(lex);
// listaToken.add(token);
// }

// }

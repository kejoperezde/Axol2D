import compilerTools.Token;

%%
%class Lexer
%type Token
%line
%column
%{
    private Token token(String lexeme, String lexicalComp, int line, int column){
        return new Token(lexeme, lexicalComp, line+1, column+1);
    }
%}

/* Variables básicas de comentarios y espacios */

TerminadorDeLinea = \r|\n|\r\n
EntradaDeCaracter = [^\r\n]
EspacioEnBlanco = {TerminadorDeLinea} | [ \t\f]
ComentarioTradicional = "/*" [^*] ~"*/" | "/*" "*"+ "/"
FinDeLineaComentario = "//" {EntradaDeCaracter}* {TerminadorDeLinea}?
ContenidoComentario = ( [^*] | \*+ [^/*] )*
ComentarioDeDocumentacion = "/**" {ContenidoComentario} "*"+ "/"

/* Comentario */
Comentario = {ComentarioTradicional} | {FinDeLineaComentario} | {ComentarioDeDocumentacion}

/* Identificador */
Letra = [A-Za-zÑñ_ÁÉÍÓÚáéíóúÜü]
Digito = [0-9]
Identificador = {Letra}({Letra}|{Digito})*

%%

/* Comentarios o espacios en blanco */
{Comentario}|{EspacioEnBlanco} { /*Ignorar*/ }

/* tipos de dato */
boolean | byte | int | char |
stack | queue | list { return token(yytext(), "TIPO_DATO", yyline, yycolumn); }

/* byte */
(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?) { return token(yytext(), "BYTE", yyline, yycolumn); }

/* int */
(6553[0-4]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5]?[0-9]{1,4})
{ return token(yytext(), "INT", yyline, yycolumn); }

/* boolean */
true |
false { return token(yytext(), "BOOLEAN", yyline, yycolumn); }

/* char */
[A-Za-z] { return token(yytext(), "CHAR", yyline, yycolumn); }

/* ESTTRUCTURAS DE CONTROL */

/* if else */
if | else { return token(yytext(), "ES_IF_ELSE", yyline, yycolumn); }

/* switch */
switch | case | break { return token(yytext(), "ES_SWITCH", yyline, yycolumn); }

/* for */
for { return token(yytext(), "ES_FOR", yyline, yycolumn); }

/* whilie */
while { return token(yytext(), "ES_WHILE", yyline, yycolumn); }

/* do whilie */
(do while) { return token(yytext(), "ES_DO_WHILE", yyline, yycolumn); }

/* try catch */
try | catch { return token(yytext(), "ES_TRY_CATCH", yyline, yycolumn); }


/* PALABRAS RESERVADAS */

/* metodos y funciones */
method | return | start | show | print | rotate |
pop | push | read_tec | read_bin | read_mp3 | read_mg |
save_bin | getPosition | add | set | random | begin |
finish { return token(yytext(), "MET_FUNC", yyline, yycolumn); }

/* elementos interfaz gráfica */
screen | print_con | level | dimensions | background | platform |
backElement | obstacles | player | Enemies | music | axol2D |
positionY | positionX { return token(yytext(), "INTERF_GRAFICA", yyline, yycolumn); }

/* importacion creacion*/
import | class | from | new { return token(yytext(), "IMPORT_CREACION", yyline, yycolumn); }

/* controladores */
Controllers | up | down | left |
right { return token(yytext(), "CONTROLADORES", yyline, yycolumn); }

/* constantes y valores especiales */
constant | this | null | image | size | lifes |
enemies { return token(yytext(), "VALOR_ESPECIAL", yyline, yycolumn); }


/* IDENTIFICADORES */

/* Identificador */
{Identificador} { return token(yytext(), "IDENTIFICADOR", yyline, yycolumn); }


/* OPERADORES */

/* Operadores aritméticos */
"+" | "-" | "*" | "/" | "%" | "^" { return token(yytext(), "OP_ARITMETICO", yyline, yycolumn); }

/* Operador de asignación */
"=" | "+-" | "-=" | "*=" | "/=" { return token (yytext(), "OP_ASIG", yyline, yycolumn); }

/* Operadores comparación */
"==" | "!=" | ">" | "<" | ">=" | "<=" { return token(yytext(), "OP_COMPARACION", yyline, yycolumn); }

/* Operadores lógicos */
"&" | "|" | "!" { return token(yytext(), "OP_LOGICO", yyline, yycolumn); }

/* Operadores incremento */
"++" { return token(yytext(), "OP_INCREMENTO", yyline, yycolumn); }

/* Operadores decremento */
"--" { return token(yytext(), "OP_DECREMENTO", yyline, yycolumn); }


/* DELIMINTADORES */

/* Operadores de agrupación */
"(" { return token(yytext(), "DEL_PAR_A", yyline, yycolumn); }
")" { return token(yytext(), "DEL_PAR_C", yyline, yycolumn); }
"{" { return token(yytext(), "DEL_LLAVE_A", yyline, yycolumn); }
"}" { return token(yytext(), "DEL_LLAVE_C", yyline, yycolumn); }

/* Signos de puntuación */
"," { return token(yytext(), "COMA", yyline, yycolumn); }
";" { return token(yytext(), "PUNTO_COMA", yyline, yycolumn); }

/* ERRORES */


. { return token(yytext(), "ERROR", yyline, yycolumn); }
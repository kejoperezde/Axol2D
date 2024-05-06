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
ComentarioTradicional = "/°" [^*] ~"°/" | "/°" "°"+ "/"
FinDeLineaComentario = "//" {EntradaDeCaracter}* {TerminadorDeLinea}?
ContenidoComentario = ( [^*] | \*+ [^/*] )*
ComentarioDeDocumentacion = "/**" {ContenidoComentario} "*"+ "/"

/* Comentario */
Comentario = {ComentarioTradicional} | {FinDeLineaComentario} | {ComentarioDeDocumentacion}



/* Identificador */
Letra = [A-Za-zÑñ_ÁÉÍÓÚáéíóúÜü]
Digito = [0-9]
Identificador = {Letra}({Letra}|{Digito}){1,31}?
Puntuacion= [^ \r,\n,"/",";","*","+","-","%","^","=","!","<",">","&","|","(",")","[","]","{","}",","]*
Puntuacion2= [^ \r,\n,"/",";","*","+","-","%","^","=","!","<",">","&","|","(",")","[","]","{","}",",","."]*
Operador = ["*","+","-","%","^","=","!","<",">","&","|"]*

/* Cadena */
SimbolosEnCadena = ["_","-","."]
Cadena = ({Letra}|{Digito}|{EspacioEnBlanco}|{SimbolosEnCadena})+
%%


/*Error comentario no cerrado*/
"/°" { return token(yytext(), "ERROR_LEXICO_8", yyline, yycolumn); }


/*Error comentario no cerrado*/
"°/" { return token(yytext(), "ERROR_LEXICO_9", yyline, yycolumn); }

/* Comentarios o espacios en blanco */
{Comentario}|{EspacioEnBlanco} { /*Ignorar*/ }

/* tipos de dato */
boolean | byte | int | char |
stack | queue | list { return token(yytext(), "PALABRA_RES", yyline, yycolumn); }

/* byte */
(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?) { return token(yytext(), "NUMERO", yyline, yycolumn); }

/* int */
(6553[0-5]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5]?[0-9]{1,4})
{ return token(yytext(), "NUMERO", yyline, yycolumn); }

/* boolean */
true |
false { return token(yytext(), "BOOLEANO", yyline, yycolumn); }

/* char */
\'({Letra}|{Digito}|{EspacioEnBlanco})\' { return token(yytext(), "CADENA", yyline, yycolumn); }

/* string */
\"{Cadena}\" { return token(yytext(), "CADENA", yyline, yycolumn); }

/* ESTTRUCTURAS DE CONTROL */

/* if else */
if | else { return token(yytext(), "PALABRA_RES", yyline, yycolumn); }

/* switch */
switch | case | break { return token(yytext(), "PALABRA_RES", yyline, yycolumn); }

/* for */
for { return token(yytext(), "PALABRA_RES", yyline, yycolumn); }

/* whilie */
while { return token(yytext(), "PALABRA_RES", yyline, yycolumn); }

/* do whilie */
(do while) { return token(yytext(), "PALABRA_RES", yyline, yycolumn); }

/* try catch */
try | catch { return token(yytext(), "PALABRA_RES", yyline, yycolumn); }


/* PALABRAS RESERVADAS */

/* metodos y funciones */
method | return | start | show | print | rotate |
pop | push | read_tec | read_bin | read_mp3 | read_mg |
save_bin | getPosition | add | set | random | begin |
finish | play { return token(yytext(), "PALABRA_RES", yyline, yycolumn); }

/* elementos interfaz gráfica */
screen | print_con | level | dimensions | background | platform |
backElement | obstacles | player | Enemies | music | axol2D |
positionY | positionX { return token(yytext(), "PALABRA_RES", yyline, yycolumn); }

/* importacion creacion*/
import | class | from | new { return token(yytext(), "PALABRA_RES", yyline, yycolumn); }

/* controladores */
Controllers | up | down | left |
right { return token(yytext(), "PALABRA_RES", yyline, yycolumn); }

/* constantes y valores especiales */
constant | this | null | image | size | lifes |
enemies { return token(yytext(), "PALABRA_RES", yyline, yycolumn); }

/* IDENTIFICADORES */

/* Identificador */
{Identificador} { return token(yytext(), "IDENTIFICADOR", yyline, yycolumn); }

/* Signos de puntuación */
"." { return token(yytext(), "SEPARADOR", yyline, yycolumn); }
"," { return token(yytext(), "SEPARADOR", yyline, yycolumn); }
";" { return token(yytext(), "SEPARADOR", yyline, yycolumn); }

/* OPERADORES */

/* Operadores aritméticos */
"+" | "-" | "*" | "/" | "%" | "^" { return token(yytext(), "OP_ARITMETICO", yyline, yycolumn); }

/* Operador de asignación */
"=" | "+=" | "-=" | "*=" | "/=" { return token (yytext(), "OP_ASIG", yyline, yycolumn); }

/* Operadores comparación */
"==" | "!=" | ">" | "<" | ">=" | "<=" { return token(yytext(), "OP_COMPARA", yyline, yycolumn); }

/* Operadores lógicos */
"&" | "|" | "!" { return token(yytext(), "OP_LOGICO", yyline, yycolumn); }

/* Operadores incremento */
"++" { return token(yytext(), "OP_INCREMENTO", yyline, yycolumn); }

/* Operadores decremento */
"--" { return token(yytext(), "OP_DECREMENTO", yyline, yycolumn); }


/* DELIMINTADORES */

/* Operadores de agrupación */
"(" { return token(yytext(), "DELIMITADOR", yyline, yycolumn); }
")" { return token(yytext(), "DELIMITADOR", yyline, yycolumn); }
"{" { return token(yytext(), "DELIMITADOR", yyline, yycolumn); }
"}" { return token(yytext(), "DELIMITADOR", yyline, yycolumn); }
"[" { return token(yytext(), "DELIMITADOR", yyline, yycolumn); }
"]" { return token(yytext(), "DELIMITADOR", yyline, yycolumn); }

/* ERRORES */

/* Error tamaño */
(6553[0-5]{Digito}|655[0-2][0-9]|65[0-4][0-9]{2}{Digito}|6[0-4][0-9]{3}{Digito}|[1-5]?[0-9]{1,4}{Digito})
{ return token(yytext(), "ERROR_LEXICO_10", yyline, yycolumn);} 

/* Error simbolo invalido en números */
{Digito}{Puntuacion} { return token(yytext(), "ERROR_LEXICO_3", yyline, yycolumn); }

/* Error de identificador largo */
{Letra}({Letra}|{Digito})* { return token(yytext(), "ERROR_LEXICO_2", yyline, yycolumn); }

/* Error de identificador con caracter invalido */
{Letra}{Puntuacion2} { return token(yytext(), "ERROR_LEXICO_4", yyline, yycolumn); }

/* Error Operador Invalido */
{Operador} { return token(yytext(), "ERROR_LEXICO_5", yyline, yycolumn); }

/* Error Char no cerrado */
\'({Letra}|{Digito}|{EspacioEnBlanco}) { return token(yytext(), "ERROR_LEXICO_6", yyline, yycolumn); }

/* Error cadena no cerrada*/
\"({Letra}|{Digito}|{EspacioEnBlanco})* { return token(yytext(), "ERROR_LEXICO_7", yyline, yycolumn); }

/*CARACTER INVALIDO*/
{Puntuacion2} { return token(yytext(), "ERROR_LEXICO_1", yyline, yycolumn); }

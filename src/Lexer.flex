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

/* Número */
Numero = 0 | [1-9][0-9]*
%%

/* Comentarios o espacios en blanco */
{Comentario}|{EspacioEnBlanco} { /*Ignorar*/ }

/* Tipos de dato */
boolean | 
byte | 
int | 
char { return token(yytext(), "TIPO_DATO", yyline, yycolumn); }

/* boolean */
true |
false { return token(yytext(), "BOOLEAN", yyline, yycolumn); }

/* byte */
(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?) { return token(yytext(), "BYTE", yyline, yycolumn); }

/* int */
(6553[0-4]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5]?[0-9]{1,4})
{ return token(yytext(), "INT", yyline, yycolumn); }

/* char */
[A-Za-z] { return token(yytext(), "CHAR", yyline, yycolumn); }

/* if else */
if | else { return token(yytext(), "ES_IF_ELSE", yyline, yycolumn); }

/* switch */
switch | case | break { return token(yytext(), "ES_SWITCH", yyline, yycolumn); }

/* for */
for { return token(yytext(), "ES_FOR", yyline, yycolumn); }

/* whilie */
while { return token(yytext(), "ES_WHILE", yyline, yycolumn); }

/* try catch */
try | catch { return token(yytext(), "ES_TRY_CATCH", yyline, yycolumn); }

/* Identificador */
{Identificador} { return token(yytext(), "IDENTIFICADOR", yyline, yycolumn); }

/* Operadores lógicos */
"&" | "|" | "!" { return token(yytext(), "OP_LOGICO", yyline, yycolumn); }

/* Signos de puntuación */
"," { return token(yytext(), "COMA", yyline, yycolumn); }
";" { return token(yytext(), "PUNTO_COMA", yyline, yycolumn); }

/* Operador de asignación */
"=" { return token (yytext(), "OP_ASIG", yyline, yycolumn); }

/* Operadores de agrupación */
"(" { return token(yytext(), "PARENTESIS_A", yyline, yycolumn); }
")" { return token(yytext(), "PARENTESIS_C", yyline, yycolumn); }
"{" { return token(yytext(), "LLAVE_A", yyline, yycolumn); }
"}" { return token(yytext(), "LLAVE_C", yyline, yycolumn); }

. { return token(yytext(), "ERROR", yyline, yycolumn); }
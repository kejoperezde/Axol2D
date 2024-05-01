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

/* Identificador */
{Identificador} { return token(yytext(), "IDENTIFICADOR", yyline, yycolumn); }

/* Tipos de dato */
boolean | byte | int | char | string
{ return token(yytext(), "TIPO_DATO", yyline, yycolumn); }

/* boolean */
(true|false) { return token(yytext(), "BOOLEAN", yyline, yycolumn); }

/* byte */
(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?) { return token(yytext(), "BYTE", yyline, yycolumn); }

/* int */
(6553[0-4]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5]?[0-9]{1,4})
{ return token(yytext(), "INT", yyline, yycolumn); }

/* char */
[A-Za-z] { return token(yytext(), "CHAR", yyline, yycolumn); }

/* Número */
{Numero} { return token(yytext(), "NUMERO", yyline, yycolumn); }

/* Colores */
#[{Letra}{Digito}]{6} { return token(yytext(), "COLOR", yyline, yycolumn); }

/* Operadores de agrupación */
"(" { return token(yytext(), "PARENTESIS_A", yyline, yycolumn); }
")" { return token(yytext(), "PARENTESIS_C", yyline, yycolumn); }
"{" { return token(yytext(), "LLAVE_A", yyline, yycolumn); }
"}" { return token(yytext(), "LLAVE_C", yyline, yycolumn); }

/* Signos de puntuación */
"," { return token(yytext(), "COMA", yyline, yycolumn); }
";" { return token(yytext(), "PUNTO_COMA", yyline, yycolumn); }

/* Operador de asignación */
"=" { return token (yytext(), "OP_ASIG", yyline, yycolumn); }

/* if else */
if | else { return token(yytext(), "ESTRUCTURA_IF_ELSE", yyline, yycolumn); }

/* switch */
switch | case | break { return token(yytext(), "ESTRUCTURA_SWITCH", yyline, yycolumn); }

/* for */
for { return token(yytext(), "ESTRUCTURA_FOR", yyline, yycolumn);

/* whilie */
while { return token(yytext(), "ESTRUCTURA_WHILE", yyline, yycolumn); } }

/* try catch */
try | catch { return token(yytext(), "ESTRUCUTRA_TRY_CATCH", yyline, yycolumn); }

/* Operadores lógicos */
"&" | "|" | "!" { return token(yytext(), "OP_LOGICO", yyline, yycolumn); }

/* Final */
final { return token(yytext(), "FINAL", yyline, yycolumn); }


/* ERRORES */

// Número erróneo
// 0 {Numero}+ { return token(yytext(), "ERROR_1", yyline, yycolumn); }

// Identificador sin $
// {Identificador} { return token(yytext(), "ERROR_2", yyline, yycolumn); }
//. { return token(yytext(), "ERROR", yyline, yycolumn); }
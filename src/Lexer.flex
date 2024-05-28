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
boolean { return token(yytext(), "boolean", yyline, yycolumn); }
byte { return token(yytext(), "byte", yyline, yycolumn); }
int { return token(yytext(), "int", yyline, yycolumn); }
char { return token(yytext(), "char", yyline, yycolumn); }
stack { return token(yytext(), "stack", yyline, yycolumn); }
queue { return token(yytext(), "queue", yyline, yycolumn); }
list { return token(yytext(), "list", yyline, yycolumn); }

/* byte */
(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?) { return token(yytext(), "numerobyte", yyline, yycolumn); }

/* int */
(6553[0-5]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5]?[0-9]{1,4})
{ return token(yytext(), "numeroint", yyline, yycolumn); }

/* boolean */
true { return token(yytext(), "true", yyline, yycolumn); }
false { return token(yytext(), "false", yyline, yycolumn); }

/* char */
\'({Letra}|{Digito}|{EspacioEnBlanco})\' { return token(yytext(), "char", yyline, yycolumn); }

/* string */
\"{Cadena}\" { return token(yytext(), "string", yyline, yycolumn); }

/* ESTTRUCTURAS DE CONTROL */

/* if else */
if { return token(yytext(), "if", yyline, yycolumn); }
else { return token(yytext(), "else", yyline, yycolumn); }

/* switch */
switch { return token(yytext(), "switch", yyline, yycolumn); }
case { return token(yytext(), "case", yyline, yycolumn); }
break { return token(yytext(), "break", yyline, yycolumn); }

/* for */
for { return token(yytext(), "for", yyline, yycolumn); }

/* whilie */
while { return token(yytext(), "while", yyline, yycolumn); }

/* do whilie */
(do while) { return token(yytext(), "dowhile", yyline, yycolumn); }

/* try catch */
try { return token(yytext(), "try", yyline, yycolumn); }
catch { return token(yytext(), "catch", yyline, yycolumn); }

/* PALABRAS RESERVADAS */

/* metodos y funciones */
method { return token(yytext(), "method", yyline, yycolumn); }
return { return token(yytext(), "return", yyline, yycolumn); }
start { return token(yytext(), "start", yyline, yycolumn); }
show { return token(yytext(), "show", yyline, yycolumn); }
print { return token(yytext(), "print", yyline, yycolumn); }
rotate { return token(yytext(), "rotate", yyline, yycolumn); }
pop { return token(yytext(), "pop", yyline, yycolumn); }
push { return token(yytext(), "push", yyline, yycolumn); }
read_tec { return token(yytext(), "read_tec", yyline, yycolumn); }
read_bin { return token(yytext(), "read_bin", yyline, yycolumn); }
read_mp3 { return token(yytext(), "read_mp3", yyline, yycolumn); }
read_mg { return token(yytext(), "read_mg", yyline, yycolumn); }
save_bin { return token(yytext(), "save_bin", yyline, yycolumn); }
getPosition { return token(yytext(), "getPosition", yyline, yycolumn); }
add { return token(yytext(), "add", yyline, yycolumn); }
set { return token(yytext(), "set", yyline, yycolumn); }
random { return token(yytext(), "random", yyline, yycolumn); }
begin { return token(yytext(), "begin", yyline, yycolumn); }
finish { return token(yytext(), "finish", yyline, yycolumn); }

/* elementos interfaz gráfica */
screen { return token(yytext(), "screen", yyline, yycolumn); }
print_con { return token(yytext(), "print_con", yyline, yycolumn); }
level { return token(yytext(), "level", yyline, yycolumn); }
dimensions { return token(yytext(), "dimensions", yyline, yycolumn); }
background { return token(yytext(), "background", yyline, yycolumn); }
platform { return token(yytext(), "platform", yyline, yycolumn); }
backElement { return token(yytext(), "backElement", yyline, yycolumn); }
obstacles { return token(yytext(), "obstacles", yyline, yycolumn); }
player { return token(yytext(), "player", yyline, yycolumn); }
enemies { return token(yytext(), "enemies", yyline, yycolumn); }
music { return token(yytext(), "music", yyline, yycolumn); }
axol2D { return token(yytext(), "axol2D", yyline, yycolumn); }
positionY { return token(yytext(), "positionY", yyline, yycolumn); }
positionX { return token(yytext(), "positionX", yyline, yycolumn); }

/* importacion creacion*/
import { return token(yytext(), "import", yyline, yycolumn); }
class { return token(yytext(), "class", yyline, yycolumn); }
from { return token(yytext(), "from", yyline, yycolumn); }
new { return token(yytext(), "new", yyline, yycolumn); }

/* controladores */
controllers { return token(yytext(), "controllers", yyline, yycolumn); } 
up { return token(yytext(), "up", yyline, yycolumn); } 
down { return token(yytext(), "down", yyline, yycolumn); } 
left { return token(yytext(), "left", yyline, yycolumn); } 
right { return token(yytext(), "right", yyline, yycolumn); }

/* constantes y valores especiales */
constant { return token(yytext(), "constant", yyline, yycolumn); } 
this { return token(yytext(), "this", yyline, yycolumn); } 
null { return token(yytext(), "null", yyline, yycolumn); } 
image { return token(yytext(), "image", yyline, yycolumn); } 
size { return token(yytext(), "size", yyline, yycolumn); } 
lifes { return token(yytext(), "lifes", yyline, yycolumn); } 
enemies { return token(yytext(), "enemies", yyline, yycolumn); }

/* IDENTIFICADORES */

/* Identificador */
{Identificador} { return token(yytext(), "identificador", yyline, yycolumn); }

/* Signos de puntuación */
"." { return token(yytext(), "punto", yyline, yycolumn); }
"," { return token(yytext(), "coma", yyline, yycolumn); }
";" { return token(yytext(), "puntocoma", yyline, yycolumn); }

/* OPERADORES */

/* Operadores aritméticos */
"+" { return token(yytext(), "mas", yyline, yycolumn); }
"-" { return token(yytext(), "menos", yyline, yycolumn); }
"*" { return token(yytext(), "asterisco", yyline, yycolumn); }
"/" { return token(yytext(), "diagonal", yyline, yycolumn); }
"%" { return token(yytext(), "porcentaje", yyline, yycolumn); }
"^" { return token(yytext(), "potencia", yyline, yycolumn); }

/* Operador de asignación */
"=" { return token (yytext(), "igual", yyline, yycolumn); }
"+=" { return token (yytext(), "sumarigual", yyline, yycolumn); }
"-=" { return token (yytext(), "menorigual", yyline, yycolumn); }
"*=" { return token (yytext(), "asteriscoigual", yyline, yycolumn); }
"/=" { return token (yytext(), "diagonaligual", yyline, yycolumn); }

/* Operadores comparación */
"==" { return token(yytext(), "dobleigual", yyline, yycolumn); }
"!=" { return token(yytext(), "diferente", yyline, yycolumn); }
">" { return token(yytext(), "mayor", yyline, yycolumn); }
"<" { return token(yytext(), "menor", yyline, yycolumn); }
">=" { return token(yytext(), "mayorigual", yyline, yycolumn); }
"<=" { return token(yytext(), "menorigual", yyline, yycolumn); }

/* Operadores lógicos */
"&" { return token(yytext(), "and", yyline, yycolumn); }
"|" { return token(yytext(), "or", yyline, yycolumn); }
"!" { return token(yytext(), "diferente", yyline, yycolumn); }

/* Operadores incremento */
"++" { return token(yytext(), "incremento", yyline, yycolumn); }

/* Operadores decremento */
"--" { return token(yytext(), "decremento", yyline, yycolumn); }


/* DELIMINTADORES */

/* Operadores de agrupación */
"(" { return token(yytext(), "parentesisabre", yyline, yycolumn); }
")" { return token(yytext(), "parentesiscierra", yyline, yycolumn); }
"{" { return token(yytext(), "llaveabre", yyline, yycolumn); }
"}" { return token(yytext(), "llavecierra", yyline, yycolumn); }
"[" { return token(yytext(), "corcheteabre", yyline, yycolumn); }
"]" { return token(yytext(), "corchetecierra", yyline, yycolumn); }

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

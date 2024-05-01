
import com.formdev.flatlaf.FlatIntelliJLaf;
import compilerTools.CodeBlock;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import compilerTools.Directory;
import compilerTools.ErrorLSSL;
import compilerTools.Functions;
import compilerTools.Grammar;
import compilerTools.Production;
import compilerTools.TextColor;
import compilerTools.Token;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yisus
 */
public class Compilador extends javax.swing.JFrame {

    private String title;
    private Directory directorio;
    private ArrayList<Token> tokens;
    private ArrayList<ErrorLSSL> errors;
    private ArrayList<TextColor> textsColor;
    private Timer timerKeyReleased;
    private ArrayList<Production> identProd;
    private HashMap<String, String> identificadores;
    private boolean codeHasBeenCompiled = false;
    private HashMap<String, String> coincidenciasId = new HashMap<>();;

    /**
     * Creates new form Compilador
     */
    public Compilador() {
        initComponents();
        init();
    }

    private void init() {
        // Poner título de la ventana
        title = "Axol2D";
        setLocationRelativeTo(null);
        setTitle(title);
        // Extensión .axol
        directorio = new Directory(this, jtpCode, title, ".axol");
        // Cuando presiona la "X" de la esquina superior derecha
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                directorio.Exit();
                System.exit(0);
            }
        });
        // Función para poner lineas de codigo
        Functions.setLineNumberOnJTextComponent(jtpCode);
        // Función para colorear palabras
        timerKeyReleased = new Timer((int) (1000 * 0.3), (ActionEvent e) -> {
            timerKeyReleased.stop();
            colorAnalysis();
        });
        // Función para poner asterísco
        Functions.insertAsteriskInName(this, jtpCode, () -> {
            timerKeyReleased.restart();
        });
        // Inicializar identificadores
        tokens = new ArrayList<>();
        errors = new ArrayList<>();
        textsColor = new ArrayList<>();
        identProd = new ArrayList<>();
        identificadores = new HashMap<>();
        // Función para autocompletar (crtl + space)
        Functions.setAutocompleterJTextComponent(new String[]{"número", "color", "adelante", "atrás",
            "izquierda", "derecha", "norte", "sur", "este", "oeste", "pintar", "detenerPintar",
            "tomar", "poner", "lanzarMoneda"}, jtpCode, () -> {
            timerKeyReleased.restart();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpCode = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaOutputConsole = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTokens = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuNuevo = new javax.swing.JMenuItem();
        menuAbrir = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuGuardar = new javax.swing.JMenuItem();
        menuGuardarComo = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuLexico = new javax.swing.JMenuItem();
        menuSintactico = new javax.swing.JMenuItem();
        menuCompilar = new javax.swing.JMenuItem();
        menuEjecutar = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuFija = new javax.swing.JMenuItem();
        menuVariable = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        rootPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setViewportView(jtpCode);

        rootPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 693, 440));

        jtaOutputConsole.setEditable(false);
        jtaOutputConsole.setBackground(new java.awt.Color(255, 255, 255));
        jtaOutputConsole.setColumns(20);
        jtaOutputConsole.setRows(5);
        jScrollPane2.setViewportView(jtaOutputConsole);

        rootPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 1040, 170));

        tblTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Componente léxico", "Lexema", "[Línea, Columna]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblTokens);

        rootPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 350, 440));

        getContentPane().add(rootPanel);

        jMenu1.setText("Archivo");

        menuNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuNuevo.setText("Nuevo");
        menuNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNuevoActionPerformed(evt);
            }
        });
        jMenu1.add(menuNuevo);

        menuAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuAbrir.setText("Abrir");
        menuAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAbrirActionPerformed(evt);
            }
        });
        jMenu1.add(menuAbrir);
        jMenu1.add(jSeparator1);

        menuGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuGuardar.setText("Guardar");
        menuGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGuardarActionPerformed(evt);
            }
        });
        jMenu1.add(menuGuardar);

        menuGuardarComo.setText("Guardar Como");
        menuGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGuardarComoActionPerformed(evt);
            }
        });
        jMenu1.add(menuGuardarComo);
        jMenu1.add(jSeparator2);

        menuSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        menuSalir.setText("Salir");
        menuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSalirActionPerformed(evt);
            }
        });
        jMenu1.add(menuSalir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Compilador");

        menuLexico.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuLexico.setText("Léxico");
        menuLexico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLexicoActionPerformed(evt);
            }
        });
        jMenu2.add(menuLexico);

        menuSintactico.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuSintactico.setText("Sintáctico");
        jMenu2.add(menuSintactico);

        menuCompilar.setText("Compilar");
        menuCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCompilarActionPerformed(evt);
            }
        });
        jMenu2.add(menuCompilar);

        menuEjecutar.setText("Ejecutar");
        menuEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEjecutarActionPerformed(evt);
            }
        });
        jMenu2.add(menuEjecutar);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("TS");

        menuFija.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuFija.setText("Fija");
        jMenu3.add(menuFija);

        menuVariable.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuVariable.setText("Variable");
        menuVariable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuVariableActionPerformed(evt);
            }
        });
        jMenu3.add(menuVariable);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNuevoActionPerformed
        directorio.New();
        clearFields();
    }//GEN-LAST:event_menuNuevoActionPerformed

    private void menuAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAbrirActionPerformed
        if (directorio.Open()) {
            colorAnalysis();
            clearFields();
        }
    }//GEN-LAST:event_menuAbrirActionPerformed

    private void menuGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGuardarActionPerformed
        if (directorio.Save()) {
            clearFields();
        }
    }//GEN-LAST:event_menuGuardarActionPerformed

    private void menuGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGuardarComoActionPerformed
        if (directorio.SaveAs()) {
            clearFields();
        }
    }//GEN-LAST:event_menuGuardarComoActionPerformed

    private void menuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSalirActionPerformed
        directorio.Exit();
        System.exit(0);
    }//GEN-LAST:event_menuSalirActionPerformed

    private void menuCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCompilarActionPerformed
        if (getTitle().contains("*") || getTitle().equals(title)) {
            if (directorio.Save()) {
                compile();
            }
        } else {
            compile();
        }
    }//GEN-LAST:event_menuCompilarActionPerformed

    private void menuEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEjecutarActionPerformed
        menuCompilar.doClick();
        if (codeHasBeenCompiled) {
            if (errors.size() > 0) {
                JOptionPane.showMessageDialog(null, "No se puede ejecutar el código ya que se encontró uno o más errores",
                        "Error en la compilación", JOptionPane.ERROR_MESSAGE);
            } else {
                CodeBlock codeBlock = Functions.splitCodeInCodeBlocks(tokens, "{", "}", ";");
                System.out.println(codeBlock);
                ArrayList<String> blocksOfCode = codeBlock.getBlocksOfCodeInOrderOfExec();
                System.out.println(blocksOfCode);
                executeCode(blocksOfCode, 1);

            }
        }
    }//GEN-LAST:event_menuEjecutarActionPerformed

    private void menuLexicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLexicoActionPerformed
        if (getTitle().contains("*") || getTitle().equals(title)) {
            if (directorio.Save()) {
                compileLexica();
            }
        } else {
            compileLexica();
        }
    }//GEN-LAST:event_menuLexicoActionPerformed

    private void menuVariableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuVariableActionPerformed
        // Obtener los identificadores
        ArrayList<Token> identifiers = getVariables(tokens);

        // Ordenar los identificadores por nombre
        Collections.sort(identifiers, Comparator.comparing(Token::getLexeme));

        // Crear un modelo de tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Tipo");
        model.addColumn("Dirección en Memoria");
        model.addColumn("Valor");
        model.addColumn("Declaración");
        model.addColumn("Coincidencias");

        // Llenar el modelo de tabla con los identificadores
        for (Token identifier : identifiers) {
            String lexeme = identifier.getLexeme();
            String lines = coincidenciasId.getOrDefault(lexeme, ""); // Obtener las líneas correspondientes al lexema

            // Llenar la fila de la tabla con el lexema del identificador y sus coincidencias de líneas
            model.addRow(new Object[]{lexeme, "", "", "", "", lines});
        }
        // Abrir la ventana de variables
        TSVariable ventanaTSV = new TSVariable(model);
        ventanaTSV.setVisible(true);
    }//GEN-LAST:event_menuVariableActionPerformed

    private ArrayList<Token> getVariables(ArrayList<Token> tokens) {
        ArrayList<Token> identifiers = new ArrayList<>();
        HashSet<String> seenIdentifiers = new HashSet<>(); // HashSet para almacenar identificadores únicos

        for (Token token : tokens) {
            if (token.getLexicalComp().equals("IDENTIFICADOR")) {
                String lexema = token.getLexeme();

                // Verifica si el identificador ya ha sido agregado antes
                if (!seenIdentifiers.contains(lexema)) {
                    // Agrega el identificador a la lista de identificadores y al HashSet
                    identifiers.add(token);
                    seenIdentifiers.add(lexema);
                }

                //Coincicencias
                actualizarCoincidencias(token);
            }
        }
        return identifiers;
    }

    private void actualizarCoincidencias(Token token) {
        String lexema = token.getLexeme();
        int lineNumber = token.getLine();

        // Si el lexema ya está en el HashMap, agrega la línea al String existente
        if (coincidenciasId.containsKey(lexema)) {
            String existingLines = coincidenciasId.get(lexema);
            existingLines += ", " + lineNumber; // Agrega la línea al String existente
            coincidenciasId.put(lexema, existingLines);
        } else {
            // Si el lexema no está en el HashMap, crea un nuevo String con la línea
            coincidenciasId.put(lexema, Integer.toString(lineNumber));
        }
    }

    private void executeCode(ArrayList<String> blocksOfCode, int repeats) {
        for (int j = 1; j <= repeats; j++) {
            int repeatCode = -1;
            for (int i = 0; i < blocksOfCode.size(); i++) {
                String blockOfCode = blocksOfCode.get(i);
                if (repeatCode != -1) {
                    int[] posicionMarcador = CodeBlock.getPositionOfBothMarkers(blocksOfCode, blockOfCode);
                    executeCode(new ArrayList<>(blocksOfCode.subList(posicionMarcador[0], posicionMarcador[1])), repeatCode);
                    repeatCode = -1;
                    i = posicionMarcador[1];
                } else {
                    String[] sentences = blockOfCode.split(";");
                    for (String sentence : sentences) {
                        sentence = sentence.trim();
                        // Llamar código de ejecución (arduino, gráfico, etc)
                        if (sentence.startsWith("pintar")) {
                            String parametro;
                            if (sentence.contains("$")) {
                                parametro = identificadores.get(sentence.substring(9, sentence.length() - 2));
                            } else {
                                parametro = sentence.substring(9, sentence.length() - 2);
                            }
                            System.out.println("Pintando de color " + parametro + "...");
                        } else if (sentence.startsWith("izquierda")) {
                            System.out.println("Moviéndose a la izquierda...");
                        } else if (sentence.startsWith("derecha")) {
                            System.out.println("Moviéndose a la derecha...");
                        } else if (sentence.startsWith("adelante")) {
                            System.out.println("Moviéndose hacia adelante");
                        } else if (sentence.contains("-->")) {
                            String[] identComp = sentence.split(" ");
                            System.out.println("Declarando identificador " + identComp[1] + " igual a " + identComp[3]);
                        } else if (sentence.startsWith("atrás")) {
                            System.out.println("Moviéndose hacia atrás");
                        } else if (sentence.startsWith("repetir")) {
                            String parametro;
                            if (sentence.contains("$")) {
                                parametro = identificadores.get(sentence.substring(10, sentence.length() - 2));
                            } else {
                                parametro = sentence.substring(10, sentence.length() - 2);
                            }
                            repeatCode = Integer.parseInt(parametro);
                        }
                    }
                }
            }
        }
    }

    private void compile() {
        clearFields();
        lexicalAnalysis();
        fillTableTokens();
        syntacticAnalysis();
        semanticAnalysis();
        printConsole();
        codeHasBeenCompiled = true;
    }

    private void compileLexica() {
        clearFields();
        lexicalAnalysis();
        fillTableTokens();
        printConsole();
        codeHasBeenCompiled = true;
    }

    private void lexicalAnalysis() {
        // Extraer tokens
        Lexer lexer;
        try {
            File codigo = new File("code.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexer = new Lexer(entrada);
            while (true) {
                Token token = lexer.yylex();
                if (token == null) {
                    break;
                }
                tokens.add(token);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
    }

    private void syntacticAnalysis() {
        Grammar gramatica = new Grammar(tokens, errors);

        /* Deshabilitar mensajes y validaciones */
        gramatica.disableMessages();
        gramatica.disableValidations();

        /* Eliminación de errores */
        gramatica.delete(new String[]{"ERROR", "ERROR_1", "ERROR_2"}, 14);

        /* Agrupación de valores */
        gramatica.group("VALOR", "(NUMERO | COLOR)", true);

        /* Declaración de variables */
        gramatica.group("VARIABLE", "TIPO_DATO IDENTIFICADOR OP_ASIG VALOR", true, identProd);
        gramatica.group("VARIABLE", "TIPO_DATO OP_ASIG VALOR", true,
                1, " × Error sintáctico {}: falta el identificador en la declaración de variable [#, %]");

        gramatica.finalLineColumn();

        gramatica.group("VARIABLE", "TIPO_DATO IDENTIFICADOR OP_ASIG", true,
                2, " × Error sintáctico {}: falta el valor en la declaración de variable [#, %]");

        gramatica.initialLineColumn();

        gramatica.group("VARIABLE", "TIPO_DATO IDENTIFICADOR VALOR", true,
                3, " × Error sintáctico {}: falta el operador de asignación en la declaración de variable [#, %]", 2);
        gramatica.group("VARIABLE", "IDENTIFICADOR OP_ASIG VALOR", true,
                4, " × Error sintáctico {}: falta el tipo de dato en la declaración de variable [#, %]");

        /* Eliminación de tipos de datos y operadores de asignación */
        gramatica.delete("TIPO_DATO",
                5, " × Error sintáctico {}: el tipo de dato no está en la declaración de una variable [#, %]");
        gramatica.delete("OP_ASIG",
                6, " × Error sintáctico {}: el operador de asignación no está en la declaración de una variable [#, %]");

        /* Agrupación de identificadores como valor y definición de parámetros */
        gramatica.group("VALOR", "IDENTIFICADOR", true);
        gramatica.group("PARAMETROS", "VALOR (COMA VALOR)+");

        /* Agrupación de funciones */
        gramatica.group("FUNCION", "(MOVIMIENTO | PINTAR | DETENER_PINTAR |"
                + " TOMAR | LANZAR_MONEDA | VER | DETENER_REPETIR )", true);
        gramatica.group("FUNCION_COMP", "FUNCION PARENTESIS_A (VALOR | PARAMETROS)? PARENTESIS_C", true);
        gramatica.group("FUNCION_COMP", "FUNCION (VALOR | PARAMETROS)? PARENTESIS_C", true,
                7, " × Error sintáctico {}: falta el paréntesis que abre en la función [#, %]");
        gramatica.finalLineColumn();
        gramatica.group("FUNCION_COMP", "FUNCION PARENTESIS_A (VALOR | PARAMETROS)", true,
                8, " × Error sintáctico {}: falta el paréntesis que cierra en la función [#, %]");

        gramatica.initialLineColumn();

        /* Eliminación de funciones mal declaradas */
        gramatica.delete("FUNCION",
                9, " × Error sintáctico {}: La función no está declarada correctamente [#, %]");

        /* Expresiones lógicas */
        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            gramatica.group("EXP_LOGICA", "(EXP_LOGICA | FUNCION_COMP) (OP_LOGICO (EXP_LOGICA | FUNCION_COMP))+");
            gramatica.group("EXP_LOGICA", "PARENTESIS_A (EXP_LOGICA | FUNCION_COMP) PARENTESIS_C", true);
        });

        /* Eliminación de operadores lógicos */
        gramatica.delete("OP_LOGICO",
                10, " × Error sintáctico {}: El operador lógico no está contenido en una expresión [#, %]");

        /* Agrupación de expresiones lógicas como valores y parámetros */
        gramatica.group("VALOR", "EXP_LOGICA", true);
        gramatica.group("PARAMETROS", "VALOR (COMA VALOR)+");

        /* Agrupación de estructuras de control */
        gramatica.group("EST_CONTROL", "(REPETIR | ESTRUCTURA_SI)", true);
        gramatica.group("EST_CONTROL_COMP", "EST_CONTROL (VALOR | PARAMETROS)", true);
        gramatica.group("EST_CONTROL_COMP", "EST_CONTROL PARENTESIS_A PARENTESIS_C", true);
        gramatica.group("EST_CONTROL_COMP", "EST_CONTROL PARENTESIS_A (VALOR | PARAMETROS) PARENTESIS_C", true);
        gramatica.group("EST_CONTROL_COMP", "EST_CONTROL (VALOR | PARAMETROS) PARENTESIS_C", true,
                11, " × Error sintáctico {}: falta el paréntesis que abre en la estructura [#, %]");
        gramatica.group("EST_CONTROL_COMP", "EST_CONTROL PARENTESIS_C", true,
                12, " × Error sintáctico {}: falta el paréntesis que abre en la estructura [#, %]");
        gramatica.finalLineColumn();
        gramatica.group("EST_CONTROL_COMP", "EST_CONTROL PARENTESIS_A (VALOR | PARAMETROS)", true,
                13, " × Error sintáctico {}: falta el paréntesis que cierra en la estructura [#, %]");
        gramatica.group("EST_CONTROL_COMP", "EST_CONTROL PARENTESIS_A", true,
                14, " × Error sintáctico {}: falta el paréntesis que cierra en la estructura [#, %]");

        gramatica.initialLineColumn();

        /* Eliminación de estructuras de control mal declaradas */
        gramatica.delete("EST_CONTROL",
                15, " × Error sintáctico {}: La estructura de control no está declarada correctamente [#, %]");

        /* Eliminación de paréntesis */
        gramatica.delete(new String[]{"PARENTESIS_A", "PARENTESIS_C"},
                16, " × Error sintáctico {}: El paréntesis [] no está contenido en una agrupación [#, %]");

        /* Eliminación de valores */
        gramatica.delete("VALOR",
                17, " × Error sintáctico {}: El valor no está contenido en una función o estructura de control [#, %]");

        gramatica.finalLineColumn();

        /* Verificación de punto y coma al final de la sentencia */
        // Identificadores
        gramatica.group("VARIABLE_PC", "VARIABLE PUNTO_COMA", true);
        gramatica.group("VARIABLE_PC", "VARIABLE", true,
                18, " × Error sintáctico {}: falta el punto y coma al final de la declaración de variable [#, %]");
        // Funciones
        gramatica.group("FUNCION_COMP_PC", "FUNCION_COMP PUNTO_COMA", true);
        gramatica.group("FUNCION_COMP_PC", "FUNCION_COMP", true,
                19, " × Error sintáctico {}: falta el punto y coma al final de la declaración de función [#, %]");

        gramatica.initialLineColumn();

        /* Eliminación de punto y coma */
        gramatica.delete("PUNTO_COMA",
                20, " × Error sintáctico {}: el punto y coma no está al final de una sentencia [#, %]");

        /* Agrupación de sentencias */
        gramatica.group("SENTENCIAS", "(VARIABLE_PC | FUNCION_COMP_PC)+");
        /* Estructuras de control completas */
        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            gramatica.group("EST_CONTROL_COMP_LASLC", "EST_CONTROL_COMP LLAVE_A (SENTENCIAS)? LLAVE_C", true);
            gramatica.group("SENTENCIAS", "(SENTENCIAS | EST_CONTROL_COMP_LASLC)+");
        });

        /* Estructuras de control incompletas */
        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            gramatica.initialLineColumn();

            gramatica.group("EST_CONTROL_COMP_LASLC", "EST_CONTROL_COMP (SENTENCIAS)? LLAVE_C", true,
                    21, " × Error sintáctico {}: falta la llave que abre en la estructura de control [#, %]");

            gramatica.finalLineColumn();

            gramatica.group("EST_CONTROL_COMP_LASLC", "EST_CONTROL_COMP LLAVE_A SENTENCIAS",
                    22, " × Error sintáctico {}: falta la llave que cierra en la estructura de control [#, %]");
            gramatica.group("SENTENCIAS", "(SENTENCIAS | EST_CONTROL_COMP_LASLC)+");
        });

        /* Eliminación de llaves */
        gramatica.delete(new String[]{"LLAVE_A", "LLAVE_C"},
                23, " × Error sintáctico {}: la llave no está contenida en una agrupación [#, %]");

        /* Mostrar gramáticas */
        // gramatica.show();
    }

    private void semanticAnalysis() {
        HashMap<String, String> identDataType = new HashMap<>();
        identDataType.put("color", "COLOR");
        identDataType.put("número", "NUMERO");
        for (Production id : identProd) {
            if (!identDataType.get(id.lexemeRank(0)).equals(id.lexicalCompRank(-1))) {
                errors.add(new ErrorLSSL(1, " × Error semántico {}: valor no compatible con el tipo de dato [#, %]", id, true));
            }
            if (id.lexicalCompRank(-1).equals("COLOR") && !id.lexemeRank(-1).matches("#[0-9a-fA-F]+")) {
                errors.add(new ErrorLSSL(2, " × Error lógico {}: el color no es un número hexadecimal [#, %]", id, false));
            }
            identificadores.put(id.lexemeRank(1), id.lexemeRank(-1));
        }
    }

    private void colorAnalysis() {
        /* Limpiar el arreglo de colores */
        textsColor.clear();
        /* Extraer rangos de colores */
        LexerColor lexer;
        try {
            File codigo = new File("color.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexer = new LexerColor(entrada);
            while (true) {
                TextColor textColor = lexer.yylex();
                if (textColor == null) {
                    break;
                }
                textsColor.add(textColor);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
        Functions.colorTextPane(textsColor, jtpCode, new Color(40, 40, 40));
    }

    private void fillTableTokens() {
        tokens.forEach(token -> {
            Object[] data = new Object[]{token.getLexicalComp(), token.getLexeme(), "[" + token.getLine() + ", " + token.getColumn() + "]"};
            Functions.addRowDataInTable(tblTokens, data);
        });
    }

    private void printConsole() {
        int sizeErrors = errors.size();
        if (sizeErrors > 0) {
            Functions.sortErrorsByLineAndColumn(errors);
            String strErrors = "\n";
            for (ErrorLSSL error : errors) {
                String strError = String.valueOf(error);
                strErrors += strError + "\n";
            }
            jtaOutputConsole.setText("Compilación terminada...\n" + strErrors + "\nLa compilación terminó con errores...");
        } else {
            jtaOutputConsole.setText("Compilación terminada...");
        }
        jtaOutputConsole.setCaretPosition(0);
    }

    private void clearFields() {
        Functions.clearDataInTable(tblTokens);
        jtaOutputConsole.setText("");
        tokens.clear();
        errors.clear();
        identProd.clear();
        identificadores.clear();
        codeHasBeenCompiled = false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (UnsupportedLookAndFeelException ex) {
                System.out.println("LookAndFeel no soportado: " + ex);
            }
            new Compilador().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTextArea jtaOutputConsole;
    private javax.swing.JTextPane jtpCode;
    private javax.swing.JMenuItem menuAbrir;
    private javax.swing.JMenuItem menuCompilar;
    private javax.swing.JMenuItem menuEjecutar;
    private javax.swing.JMenuItem menuFija;
    private javax.swing.JMenuItem menuGuardar;
    private javax.swing.JMenuItem menuGuardarComo;
    private javax.swing.JMenuItem menuLexico;
    private javax.swing.JMenuItem menuNuevo;
    private javax.swing.JMenuItem menuSalir;
    private javax.swing.JMenuItem menuSintactico;
    private javax.swing.JMenuItem menuVariable;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JTable tblTokens;
    // End of variables declaration//GEN-END:variables
}

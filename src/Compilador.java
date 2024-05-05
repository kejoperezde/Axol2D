
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
    private String[] autocompletado;
    private boolean codeHasBeenCompiled = false;
    private HashMap<String, String> coincidenciasId = new HashMap<>();

    ;

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
        autocompletado = new String[]{
            "boolean", "byte", "int", "char", "stack", "queue", "list",
            "if", "else", "switch", "case", "break", "for", "while", "try", "catch ",
            "method", "return", "start", "show", "print", "rotate", "pop", "push", "read_tec", "read_bin", "read_mp3", "read_mg",
            "save_bin", "getPosition", "add", "set", "random", "begin", "finish",
            "import", "class", "from", "new",
            "screen", "print_con", "level", "dimensions", "background", "platform", "backElement", "obstacles", "player",
            "Enemies", "music", "axol2D", "positionY", "positionX",
            "Controllers", "up", "down", "left", "right",
            "constant", "this", "null", "image", "size", "lifes", "enemies"
        };
        // Función para colorear palabras
        timerKeyReleased = new Timer((int) (1000 * 0.3), (ActionEvent e) -> {
            // Función para autocompletar (crtl + space)
            Functions.setAutocompleterJTextComponent(autocompletado, jtpCode, () -> {
                timerKeyReleased.restart();
            });
            timerKeyReleased.stop();
            colorAnalysis();
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
        jMenu3 = new javax.swing.JMenu();
        menuFija = new javax.swing.JMenuItem();
        menuVariable = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
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

        jMenuBar1.add(jMenu2);

        jMenu3.setText("TS");

        menuFija.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuFija.setText("Fija");
        menuFija.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFijaActionPerformed(evt);
            }
        });
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
        coincidenciasId.clear();
    }//GEN-LAST:event_menuVariableActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void menuLexicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLexicoActionPerformed
        if (getTitle().contains("*") || getTitle().equals(title)) {
            if (directorio.Save()) {
                compileLexica();
            }
        } else {
            compileLexica();
        }
    }//GEN-LAST:event_menuLexicoActionPerformed

    private void menuFijaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFijaActionPerformed

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Operador");
        model.addColumn("Tipo");
      
        Object[][] data = {
            {"if", "Palabra reservada"},
            {"else", "Palabra reservada"},
            {"for", "Palabra reservada"},
            {"while", "Palabra reservada"},
            {"method", "Palabra reservada"},
            {"return", "Palabra reservada"},
            {"start", "Palabra reservada"},
            {"import", "Palabra reservada"},
            {"try", "Palabra reservada"},
            {"catch", "Palabra reservada"},
            {"switch", "Palabra reservada"},
            {"case", "Palabra reservada"},
            {"break", "Palabra reservada"},
            {"int", "Palabra reservada"},
            {"String", "Palabra reservada"},
            {"char", "Palabra reservada"},
            {"do", "Palabra reservada"},
            {"boolean", "Palabra reservada"},
            {"true", "Palabra reservada"},
            {"false", "Palabra reservada"},
            {"byte", "Palabra reservada"},
            {"do while", "Palabra reservada"},
            {"read_mp3", "Palabra reservada"},
            {"new", "Palabra reservada"},
            {"for each", "Palabra reservada"},
            {"print_con", "Palabra reservada"},
            {"background", "Palabra reservada"},
            {"obstacles", "Palabra reservada"},
            {"finish", "Palabra reservada"},
            {"music", "Palabra reservada"},
            {"positionX", "Palabra reservada"},
            {"up", "Palabra reservada"},
            {"read_tec", "Palabra reservada"},
            {"read_mg", "Palabra reservada"},
            {"block", "Palabra reservada"},
            {"level", "Palabra reservada"},
            {"platform", "Palabra reservada"},
            {"player", "Palabra reservada"},
            {"lifes", "Palabra reservada"},
            {"axol2D", "Palabra reservada"},
            {"add", "Palabra reservada"},
            {"random", "Palabra reservada"},
            {"down", "Palabra reservada"},
            {"read_bin", "Palabra reservada"},
            {"save_bin", "Palabra reservada"},
            {"Enemies", "Palabra reservada"},
            {"screen", "Palabra reservada"},
            {"dimensions", "Palabra reservada"},
            {"backElement", "Palabra reservada"},
            {"begin", "Palabra reservada"},
            {"enemies", "Palabra reservada"},
            {"positionY", "Palabra reservada"},
            {"set", "Palabra reservada"},
            {"controllers", "Palabra reservada"},
            {"left", "Palabra reservada"},
            {"start", "Palabra reservada"},
            {"in", "Palabra reservada"},
            {"this", "Palabra reservada"},
            {"show", "Palabra reservada"},
            {"size", "Palabra reservada"},
            {"queue", "Palabra reservada"},
            {"push", "Palabra reservada"},
            {"class", "Palabra reservada"},
            {"from", "Palabra reservada"},
            {"null", "Palabra reservada"},
            {"print", "Palabra reservada"},
            {"rotate", "Palabra reservada"},
            {"list", "Palabra reservada"},
            {"constant", "Palabra reservada"},
            {"image", "Palabra reservada"},
            {"position", "Palabra reservada"},
            {"stack", "Palabra reservada"},
            {"pop", "Palabra reservada"},
            {"+", "Operador aritmetico"},
            {"-", "Operador aritmetico"},
            {"*", "Operador aritmetico"},
            {"/", "Operador aritmetico"},
            {"%", "Operador aritmetico"},
            {"^", "Operador aritmetico"},
            {"=", "Operador de asignacion"},
            {"+=", "Operador de asignacion"},
            {"-=", "Operador de asignacion"},
            {"*=", "Operador de asignacion"},
            {"/=", "Operador de asignacion"},
            {">", "Operador relacional"},
            {"<", "Operador relacional"},
            {">=", "Operador relacional"},
            {"<=", "Operador relacional"},
            {"!=", "Operador relacional"},
            {"==", "Operador relacional"},
            {"&", "Operador logico"},
            {"|", "Operador logico"},
            {"!", "Operador logico"},
            {"++", "Incremento"},
            {"--", "Decremento"},
            {"(", "delimitador parentesis que abre"},
            {")", "delimitador parentesis que cierra"},
            {"{", "delimitador llave que abre"},
            {"}", "delimitador llave que cierra"},
            {"[", "delimitador corchete que cierra"},
            {"]", "delimitador corchete que cierra"},
            {",", "delimitador"},
            {";", "delimitador"},
            
            
        };
        
        for (Object[] row : data) {
            model.addRow(row);
        }   

        TSFija ventanaTSF = new TSFija(model);
        ventanaTSF.setVisible(true);
        
    }//GEN-LAST:event_menuFijaActionPerformed

    
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
        Grammar gramatica = new Grammar(tokens, errors);
        gramatica.group("ERROR_LEXICO_1", "ERROR_LEXICO_1",1, "Error léxico 1: El carácter no es válido en el lenguaje [#, %]");
        gramatica.group("ERROR_LEXICO_2", "ERROR_LEXICO_2",2, "Error léxico 2: El identificador supera la longitud maxima de 32 [#, %]");
        gramatica.group("ERROR_LEXICO_3", "ERROR_LEXICO_3",3, "Error léxico 3: El número tiene caracteres inválidos [#, %]");
        gramatica.group("ERROR_LEXICO_4", "ERROR_LEXICO_4",4, "Error léxico 4: El número tiene caracteres inválidos [#, %]");
        gramatica.group("ERROR_LEXICO_5", "ERROR_LEXICO_5",5, "Error léxico 5: El identificador contiene carácteres inválidos [#, %]");
        gramatica.group("ERROR_LEXICO_6", "ERROR_LEXICO_6",6, "Error léxico 6: El carácter no fue cerrado [#, %]");
        gramatica.group("ERROR_LEXICO_7", "ERROR_LEXICO_7",7, "Error léxico 7: La cadena no fue cerrada [#, %]");
        gramatica.group("ERROR_LEXICO_8", "ERROR_LEXICO_8",8, "Error léxico 8: El comentario no fue cerrado [#, %]");

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
            jtaOutputConsole.setText("Compilación terminada..."+ errors.size());
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

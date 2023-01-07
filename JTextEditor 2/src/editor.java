import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

class editor extends JFrame implements ActionListener {

    // Text component
    JTextArea t;

    Undo undo = new Undo();  // Undo class
    int index = 0; // Undo Class'ı içindeki yapılan işlemleri tutan array'in indexi


    // Frame
    JFrame f;

    static JTextField search;

    JButton searchButton;
    JButton undoButton;
    UndoManager um = new UndoManager(); // Undo için hazır class

    // Constructor
    editor() {
        // Create a frame
        f = new JFrame("Text_Editor"); //JFrame
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DefaultCaret caret = new DefaultCaret() {
            @Override
            public boolean isSelectionVisible() {
                return true;
            }
        };
        t = new JTextArea(); //Artık text area içindeki undo,search işlemleri t değişkeni ile yapılcak.

        t.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
                    @Override
                    public void undoableEditHappened(UndoableEditEvent e) {
                        um.addEdit(e.getEdit());
                    }
                }
        ); //UndoManager class'ından türeyen um, editEvent'e atıldı.

        try {
            // Set metal look and feel
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            // Set theme to ocean
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e) {
        }

        // Text component

        // Create a menubar
        JMenuBar mb = new JMenuBar();

        // Create amenu for menu
        JMenu m1 = new JMenu("File");

        // Create menu items
        JMenuItem mi1 = new JMenuItem("New");
        JMenuItem mi2 = new JMenuItem("Open");
        JMenuItem mi3 = new JMenuItem("Save");
        JMenuItem mi9 = new JMenuItem("Print");

        // Add action listener
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi9.addActionListener(this);

        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi9);

        // Create amenu for menu
        JMenu m2 = new JMenu("Edit");

        // Create menu items
        JMenuItem mi4 = new JMenuItem("cut");
        JMenuItem mi5 = new JMenuItem("copy");
        JMenuItem mi6 = new JMenuItem("paste");

        // Add action listener
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);

        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);

        JMenuItem mc = new JMenuItem("close");

        mc.addActionListener(this); //Close action

        search = new JTextField(); // Search işlemi için textField
        searchButton = new JButton("Search"); //textField içindeki text'i TextArea'da arayacak buton
        undoButton = new JButton("Undo"); //Yapılan işlemi geri alacak undo butonu
        search.addActionListener(this);
        searchButton.addActionListener(this);
        undoButton.addActionListener(this);

        //Menüler, menüBarımıza eklendi.
        mb.add(m1);
        mb.add(m2);
        mb.add(mc);
        mb.add(search);
        mb.add(searchButton);
        mb.add(undoButton);

        f.setJMenuBar(mb); //Menübarımız set edildi.
        f.add(t); //textArea Frame'e eklendi.
        f.setSize(500, 500);
        f.show();

    }

    // If a button is pressed //Butona veya itemlere tıklandığında actionlar..
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.equals("cut")) {
            undo.add(new Memento(t.getText()));
            //Memento design pattern'ı ile sağlandı.
            //Undo(yapılan copy,cut işlemlerini tutan array) arrayimize, kesilen
            // textArray'daki kısım eklendi. Ve indexte tutuldu.
            index++; //yeni işlemlere karşı index arttırıldı.
            t.cut(); //cut işlemi sağlandı.
        } else if (s.equals("copy")) {
            t.copy();
        } else if (s.equals("paste")) {
            undo.add(new Memento(t.getText()));
            //Cut işlemi ile aynı olarak paste komutuyla değiştirilen text tutuldu.
            index++; //index arttırıldı.
            t.paste();
        } else if (s.equals("Save")) {
            // Create an object of JFileChooser class
            JFileChooser j = new JFileChooser("f:");

            // Invoke the showsSaveDialog function to show the save dialog
            int r = j.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {

                // Set the label to the path of the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    // Create a file writer
                    FileWriter wr = new FileWriter(fi, false);

                    // Create buffered writer to write
                    BufferedWriter w = new BufferedWriter(wr);

                    // Write
                    w.write(t.getText());

                    w.flush();
                    w.close();
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(f, "the user cancelled the operation");
        } else if (s.equals("Print")) {
            try {
                // print the file
                t.print();
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(f, evt.getMessage());
            }
        } else if (s.equals("Open")) {
            // Create an object of JFileChooser class
            JFileChooser j = new JFileChooser("f:");

            // Invoke the showsOpenDialog function to show the save dialog
            int r = j.showOpenDialog(null);

            // If the user selects a file
            if (r == JFileChooser.APPROVE_OPTION) {
                // Set the label to the path of the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    // String
                    String s1 = "", sl = "";

                    // File reader
                    FileReader fr = new FileReader(fi);

                    // Buffered reader
                    BufferedReader br = new BufferedReader(fr);

                    // Initialize sl
                    sl = br.readLine();

                    // Take the input from the file
                    while ((s1 = br.readLine()) != null) {
                        sl = sl + "\n" + s1;
                    }

                    // Set the text
                    t.setText(sl);
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(f, "the user cancelled the operation");
        } else if (s.equals("New")) {
            t.setText(""); //text 0'landı
        } else if (s.equals("close")) {
            f.setVisible(false);
        }

        if (e.getSource() == undoButton) {
            if (index > 0) {
                t.setText(undo.get(--index).getState());
                // cut,paste işlemlerini geri almak üzere en sonki işlem bunlardan biriyse
                // butona tıklanıldığında array'den son text'i çeker ve geri alır.
                // Array'imiz Undo Class'ında tutulmaktadır.
                // Bunu ayrı olarak yazmamızım sebebi Memento design pattern'ını kullanmak.
            } else {
                if(!Objects.equals(t.getText(), "")){
                    um.undo(); //Klavyede yapılan son işlemi geri alır.
                }

            }
        }
        if (e.getSource() == searchButton) {
            // SearchButton'a basıldığında
            try {
                searchFunction(); //Search Function çağır
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        }


    }

    private void searchFunction() throws BadLocationException {
        textAreaSearchAndHighLight searchAndLight = new textAreaSearchAndHighLight(t);
        searchAndLight.run(new ArrayList<>());
        // Bu class command design pattern'ını sağlamakta.
        // TextArea'da, textField'da girilen text'i arayıp, rengini değiştirir.
    }

    public static void removeHighlights(JTextComponent textComp) {
        Highlighter hilite = textComp.getHighlighter();
        Highlighter.Highlight[] hilites = hilite.getHighlights();
        // Search edip rengini değiştirdiğimiz HighLight indexlerini alır ve eski haline dönderir.
        for (Highlighter.Highlight highlight : hilites) {
            hilite.removeHighlight(highlight);
        }
    }

}
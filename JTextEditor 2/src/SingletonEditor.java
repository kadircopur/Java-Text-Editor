import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SingletonEditor {

    // Editor(NotePad) imizi oluşturan (Singleton Design Pattern Kullanıldı)
    // ve oluştururken NotePad'imizin rengini set eden (Build design patterni kullanıldı) class.
    // Oluşturulan nesnemizin tekliği bu class kontrolünde sağlandı.

    private static final ColoredNotepadBuilder coloredNotepadBuilder = new GrayColorNotepadBuilder();
    private static final ColorArranger colorArranger = new ColorArranger(coloredNotepadBuilder);
    private static final editor instance = colorArranger.createNotePad();
    public static editor getInstance() {
        return instance;
    }
}

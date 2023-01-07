import java.awt.*;

public class GrayColorNotepadBuilder implements ColoredNotepadBuilder {

    // Oluşturulan editor(NotePad)'ümüzün Color'unu build eder ve döndürür.
    // Build Design Pattern örneğidir.
    private editor notepad;

    public GrayColorNotepadBuilder(){
        this.notepad = new editor();
    }

    @Override
    public editor buildColoredNotePad() {
        notepad.t.setBackground(Color.GRAY);
        return this.notepad;
    }

}

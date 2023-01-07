import java.awt.*;

public class YellowColorNotepadBuilder implements ColoredNotepadBuilder {
    // Build design pattern örneğidir.
    private editor notepad;

    public YellowColorNotepadBuilder(){
        this.notepad = new editor();

    }
    @Override // Editor(NotePad) background'u yellow ile set edilip döndürülmüştür.
    public editor buildColoredNotePad() {
        notepad.t.setBackground(Color.YELLOW);
        return this.notepad;
    }
}

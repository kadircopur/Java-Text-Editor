public class ColorArranger {

    //NotePad'in rengini Build eder. Build design pattern örneğidir.
    private ColoredNotepadBuilder  coloredNotepadBuilder;

    public ColorArranger(ColoredNotepadBuilder coloredNotepadBuilder)
    {
        this.coloredNotepadBuilder = coloredNotepadBuilder;
    }

    public editor createNotePad(){
        return this.coloredNotepadBuilder.buildColoredNotePad();
    } // editor(Notepad) nesnesini buildColoredNotePad implementasyonuyla return eder.

}

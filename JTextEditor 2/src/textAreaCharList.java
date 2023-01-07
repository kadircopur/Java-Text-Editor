import javax.swing.*;
import java.util.ArrayList;

public class textAreaCharList implements commandTextArea{
    // Command Design pattern örneği.
    // TextArea için her bir veriyi char olarak listeye atayan komut implement edildi.
    JTextArea t;

    public textAreaCharList(JTextArea t){
        this.t = t;
    }

    @Override
    public void run(ArrayList<Character> list) {
        String txt = t.getText(); // text area verileri stringe atandı.
        for(int i = 0; i<txt.length(); i++){ // her bir char listemize atandı.
            list.add(txt.toLowerCase().charAt(i));
        }
    }
}

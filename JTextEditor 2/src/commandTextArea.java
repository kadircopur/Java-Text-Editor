import javax.swing.text.BadLocationException;
import java.util.ArrayList;

public interface commandTextArea {

    void run(ArrayList<Character> list) throws BadLocationException;
    // TextArea için yapılacak işlemleri zorunlu tutan interface
    // Command design pattern'ı için oluşturuldu.
    // TextArea içindeki textleri char şeklinde Array'e eklemeyi yapan textAreaCharList class'ına
    // ve search'te bulunan textleri highLight yapan textAreaSearchAndHighLight class'ına implement edilecek.

}


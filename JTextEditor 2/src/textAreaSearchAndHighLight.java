import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.util.ArrayList;

public class textAreaSearchAndHighLight implements commandTextArea {
    // Command design pattern örneğidir.
    // TextArea içinde search edilen verileri bulan ve rengini değiştiren komut.
    JTextArea t;

    public textAreaSearchAndHighLight(JTextArea t){
        this.t = t;
    }

    @Override
    public void run(ArrayList<Character> list) throws BadLocationException {
        textIteratorArray arrayIterator = new textIteratorArray(t);
        // her bir char'ı tutan iterator oluşturuldu.
        StringBuilder temp = new StringBuilder(); // Array içindeki her bir char stringe atılmak üzere oluşturuldu.
        Highlighter highlighter = t.getHighlighter(); // eşleşen text'in rengini değiştirmek için oluşturuldu.
        Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.pink);
        editor.removeHighlights(t); // textArea'da highLight edilen indexleri, Arama bittikten sonra remove eden metod.
        String searchText = editor.search.getText(); // Search için oluşturulan textField'daki değer tutuldu.
        int p = 0;
        int startIndex;
        int lastIndex;
        // SearchText'in, TextArea'mızda hangi indexlerde tutulduğunu tutan değişkenler.
        while (arrayIterator.hasNext()){//Temp stringimize array'deki her bir char'ı ekleyen ve eklerken search kontrolü yapan döngü
            temp.append(arrayIterator.Next()); //Temp stringimize bir sonraki char eklendi.

            if(temp.toString().contains(searchText)){ //Search temp'te oluştuysa--
                startIndex = temp.indexOf(searchText); //başlangıç indexi tut
                lastIndex = startIndex+searchText.length(); // bitiş indexi tut
                highlighter.addHighlight(startIndex+p, lastIndex+p, painter ); //textArea'da o indexlerin rengini set et.
                temp = new StringBuilder(); // temp'i tekrar oluştur(eğer devamında da searcText var ise)
                p += lastIndex; // başlangıç indexini son index ekleyerek değiştir. Ve her bir char bitene kadar devam et.
            }
        }
    }
}

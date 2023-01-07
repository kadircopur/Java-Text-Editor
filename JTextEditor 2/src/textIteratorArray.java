import javax.swing.*;
import java.util.ArrayList;
import java.util.Stack;

public class textIteratorArray implements Iterator{
    // Iterator design pattern örneğidir.

    JTextArea text; // Editörümüzdeki text area
    ArrayList<Character> iteratorArray; // TextArea'daki her bir char'ı tutan List.
    String txt;
    int index;

    public textIteratorArray(JTextArea t){
        this.text = t;
        iteratorArray = new ArrayList<>();

        textAreaCharList chr = new textAreaCharList(text);
        //Command metodu ile oluşturduğumuz textArea'daki her bir char'ı listeye atan class oluşturuldu.

        chr.run(iteratorArray); //Run edildi(command)
    }
    @Override
    public boolean hasNext(){
        return index < iteratorArray.size();
    } // Array iteratöründe sonraki eleman mevcut mu

    @Override
    public Object Next(){
        if(this.hasNext()){
            return iteratorArray.get(index++);
        }
        return null; // Sonraki elemanı return eder.
    }


}

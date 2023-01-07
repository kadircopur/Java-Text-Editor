import java.util.ArrayList;
import java.util.List;

public class Undo {
    // Memento design pattern örneğidir.
    private List<Memento> mementoList = new ArrayList<Memento>();
    // Oluşturduğumuz State'leri tutan Memento class'ı için bir liste.
    public void add(Memento state){
        mementoList.add(state);
    } //listeye eklendi.

    public Memento get(int index){
        return mementoList.get(index);
    } //memento'ya erişildi
}

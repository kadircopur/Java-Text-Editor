public class Memento {
    // Memento Design Pattern örneğidir.
    // TextArea'daki cut ve paste ile değiştirilen stringlerimizi state değişkeninde tutar.
    // Undo class'ında Memento oluşturulmuştur.
    private String state;

    public Memento(String state){
        this.state = state;
    }

    public String getState(){
        return state;
    }
}
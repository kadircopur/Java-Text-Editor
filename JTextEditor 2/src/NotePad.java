import java.awt.*;

public class NotePad implements NotePadColor {

    // NotePad color'u build etmek için oluşturuldu.
    private Color color;

    @Override
    public void setColor(Color color)
    {
        this.color = color;
    }

}

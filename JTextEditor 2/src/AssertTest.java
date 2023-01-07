import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class AssertTest {
    editor e1 = SingletonEditor.getInstance();
    editor e2 = SingletonEditor.getInstance();
    @Test //Oluşturduğumuz iki editor(JFrame) Singleton olduğundan
    // her ikisi nesne de aynı yere referans etmelidir. Kontrolünü yapar
    public void testIsEditorSingleton() throws Exception {
        assertSame(e1, e2);
    }
    @Test(timeout = 100) // iki nesnedeki Search TextField'ı karşılaştırır ve timeout 100'den küçükse çalışır.
    public void testSearch() throws Exception{
        assertEquals(e1.search, e2.search);
    }
    @Test
    public void arrayEqualsTextArea(){
        // Oluşturduğumuz textArea'daki text ile Array'imizdeki char'lar birbirini
        // karşılıyormu diye kontrol yapar.
        textIteratorArray array = new textIteratorArray(e1.t);
        StringBuilder sb = new StringBuilder();
        for (Character ch : array.iteratorArray) {
            sb.append(ch);
        } String s=sb.toString();
        assertEquals(s, e1.t.getText());
    }
}

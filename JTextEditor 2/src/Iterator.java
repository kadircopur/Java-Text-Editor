public interface Iterator {
    // TextArea içindeki verilerimizi tutan Array classımıza implement edilecek interface.
    // Iterator design pattern örneğidir.
    // Bahsettiğimiz array search işlemi için kullanılacaktır.
    boolean hasNext();
    Object Next();
}

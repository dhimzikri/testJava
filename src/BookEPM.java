public class BookEPM {
    String Book;
    String Isin;
    String Clean_Price;

    public String getBook() {
        return Book;
    }

    public void setBook(String book) {
        Book = book;
    }

    public String getIsin() {
        return Isin;
    }

    public void setIsin(String isin) {
        Isin = isin;
    }

    public String getClean_Price() {
        return Clean_Price;
    }

    public void setClean_Price(String clean_Price) {
        Clean_Price = clean_Price;
    }

    @Override
    public String toString() {
        return "BookEPM{" +
                "Book='" + Book + '\'' +
                ", Isin='" + Isin + '\'' +
                ", Clean_Price='" + Clean_Price + '\'' +
                '}';
    }
}

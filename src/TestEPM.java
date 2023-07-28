public class TestEPM {
    String BOOK;
    String ISIN;
    String CLEAN_PRICE;


    public TestEPM(String BOOK, String ISIN, String CLEAN_PRICE) {
        this.BOOK = BOOK;
        this.ISIN = ISIN;
        this.CLEAN_PRICE = CLEAN_PRICE;
    }
    public String getBOOK() {
        return BOOK;
    }

    public void setBOOK(String BOOK) {
        this.BOOK = BOOK;
    }

    public String getISIN() {
        return ISIN;
    }

    public void setISIN(String ISIN) {
        this.ISIN = ISIN;
    }

    public String getCLEAN_PRICE() {
        return CLEAN_PRICE;
    }

    public void setCLEAN_PRICE(double CLEAN_PRICE) {
        this.CLEAN_PRICE = String.valueOf(CLEAN_PRICE);
    }

    @Override
    public String toString() {
        return "TestEPM{" +
                "BOOK='" + BOOK + '\'' +
                ", ISIN='" + ISIN + '\'' +
                ", CLEAN_PRICE=" + CLEAN_PRICE +
                '}';
    }
}

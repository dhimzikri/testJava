public class TesEPM {
    String BOOK;
    String ISIN;
    String CLEAN_PRICE;

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

    public void setCLEAN_PRICE(String CLEAN_PRICE) {
        this.CLEAN_PRICE = CLEAN_PRICE;
    }

    @Override
    public String toString() {
        return "TesEPM{" +
                "BOOK='" + BOOK + '\'' +
                ", ISIN='" + ISIN + '\'' +
                ", CLEAN_PRICE='" + CLEAN_PRICE + '\'' +
                '}';
    }
}

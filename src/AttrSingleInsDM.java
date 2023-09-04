public class AttrSingleInsDM {
    String POSITIONS;
    String BOOK;
    String PRODUCT_TYPE;
    String PRODUCT_SUBTYPE;
    String ISIN;
    String CUSIP;
    String FIGI;
    double CLEAN_PRICE;

    public AttrSingleInsDM(String POSITIONS, String BOOK, String ISIN, String PRODUCT_TYPE, String PRODUCT_SUBTYPE, String CUSIP, String FIGI, String CLEAN_PRICE) {
        this.POSITIONS = POSITIONS;
        this.BOOK = BOOK;
        this.ISIN = ISIN;
        this.PRODUCT_TYPE = PRODUCT_TYPE;
        this.PRODUCT_SUBTYPE = PRODUCT_SUBTYPE;
        this.CUSIP= CUSIP;
        this.FIGI = FIGI;
        this.CLEAN_PRICE = Double.parseDouble(CLEAN_PRICE);
//      this.DATES = DATES;
    }

    public String getPOSITIONS() {
        return POSITIONS;
    }

    public void setPOSITIONS(String POSITIONS) {
        this.POSITIONS = POSITIONS;
    }

    public String getBOOK() {
        return BOOK;
    }

    public void setBOOK(String BOOK) {
        this.BOOK = BOOK;
    }

    public String getPRODUCT_TYPE() {
        return PRODUCT_TYPE;
    }

    public void setPRODUCT_TYPE(String PRODUCT_TYPE) {
        this.PRODUCT_TYPE = PRODUCT_TYPE;
    }

    public String getPRODUCT_SUBTYPE() {
        return PRODUCT_SUBTYPE;
    }

    public void setPRODUCT_SUBTYPE(String PRODUCT_SUBTYPE) {
        this.PRODUCT_SUBTYPE = PRODUCT_SUBTYPE;
    }

    public String getISIN() {
        return ISIN;
    }

    public void setISIN(String ISIN) {
        this.ISIN = ISIN;
    }

    public String getCUSIP() {
        return CUSIP;
    }

    public void setCUSIP(String CUSIP) {
        this.CUSIP = CUSIP;
    }

    public String getFIGI() {
        return FIGI;
    }

    public void setFIGI(String FIGI) {
        this.FIGI = FIGI;
    }

    public double getCLEAN_PRICE() {
        return CLEAN_PRICE;
    }

    public void setCLEAN_PRICE(double CLEAN_PRICE) {
        this.CLEAN_PRICE = CLEAN_PRICE;
    }

    @Override
    public String toString() {
        return "AttrFetchDM{" +
                "POSITIONS='" + POSITIONS + '\'' +
                ", BOOK='" + BOOK + '\'' +
                ", PRODUCT_TYPE='" + PRODUCT_TYPE + '\'' +
                ", PRODUCT_SUBTYPE='" + PRODUCT_SUBTYPE + '\'' +
                ", ISIN='" + ISIN + '\'' +
                ", CUSIP='" + CUSIP + '\'' +
                ", FIGI='" + FIGI + '\'' +
                ", CLEAN_PRICE=" + CLEAN_PRICE +
                '}';
    }
}

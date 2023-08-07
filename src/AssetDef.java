public class AssetDef {
    String AssetID;
    String Asset_Name;
    String Currency;
    String Inst_Type;
    double TimeToMature;
    String GET_DATE;
    double PRICE;

    public String getAssetID() {
        return AssetID;
    }

    public void setAssetID(String assetID) {
        AssetID = assetID;
    }

    public String getAsset_Name() {
        return Asset_Name;
    }

    public void setAsset_Name(String asset_Name) {
        Asset_Name = asset_Name;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public String getInst_Type() {
        return Inst_Type;
    }

    public void setInst_Type(String inst_Type) {
        Inst_Type = inst_Type;
    }

    public double getTimeToMature() {
        return TimeToMature;
    }

    public void setTimeToMature(double timeToMature) {
        TimeToMature = timeToMature;
    }

    public double getPRICE() {
        return PRICE;
    }

    public void setPRICE(double PRICE) {
        this.PRICE = PRICE;
    }

    public String getGET_DATE() {
        return GET_DATE;
    }

    public void setGET_DATE(String GET_DATE) {
        this.GET_DATE = GET_DATE;
    }

    @Override
    public String toString() {
        return "AssetDef{" +
                "AssetID='" + AssetID + '\'' +
                ", Asset_Name='" + Asset_Name + '\'' +
                ", Currency='" + Currency + '\'' +
                ", Inst_Type='" + Inst_Type + '\'' +
                ", TimeToMature=" + TimeToMature +
                ", GET_DATE='" + GET_DATE + '\'' +
                ", PRICE=" + PRICE +
                '}';
    }
}

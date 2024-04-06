package informations;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsuranceCard {
    private String id;
    private String cardHolderID;
    private String policyAwner;
    private Date anExpirationDate;

    public InsuranceCard() {}

    public InsuranceCard(String id, String cardHolderID, String policyAwner, Date anExpirationDate) {
        this.id = id;
        this.cardHolderID = cardHolderID;
        this.policyAwner = policyAwner;
        this.anExpirationDate = anExpirationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardHolderID() {
        return cardHolderID;
    }

    public void setCardHolderID(String cardHolderID) {
        this.cardHolderID = cardHolderID;
    }

    public String getPolicyAwner() {
        return policyAwner;
    }

    public void setPolicyAwner(String policyAwner) {
        this.policyAwner = policyAwner;
    }

    public Date getAnExpirationDate() {
        return anExpirationDate;
    }

    public void setAnExpirationDate(Date anExpirationDate) {
        this.anExpirationDate = anExpirationDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String s = "- ID: " + id + " | Card Holder ID: " + cardHolderID + "| An Expiration Date:"
                + dateFormat.format(anExpirationDate);
        s += "\n  + Policy Awner: " + policyAwner;
        return s;
    }
}
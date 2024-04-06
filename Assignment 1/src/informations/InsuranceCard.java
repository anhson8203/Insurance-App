package informations;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsuranceCard {
    private String id;
    private String cardHolderID;
    private String policyOwner;
    private Date anExpirationDate;

    public InsuranceCard() {}

    public InsuranceCard(String id, String cardHolderID, String policyOwner, Date anExpirationDate) {
        this.id = id;
        this.cardHolderID = cardHolderID;
        this.policyOwner = policyOwner;
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

    public String getPolicyOwner() {
        return policyOwner;
    }

    public void setPolicyOwner(String policyOwner) {
        this.policyOwner = policyOwner;
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
        s += "\n  + Policy Owner: " + policyOwner;
        return s;
    }
}
package informations;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Claim {
    private String id;
    private Date claimDate;
    private String insuredPersonID;
    private String cardNumberID;
    private Date examDate;
    private ArrayList<String> listOfDocuments;
    private float claimAmount;
    private String status;
    private String receiverBankingInfo;

    public Claim() {}

    public Claim(String id, Date claimDate, String insuredPersonID, String cardNumberID, Date examDate,
                 ArrayList<String> listOfDocuments, float claimAmount, String status, String receiverBankingInfo) {
        this.id = id;
        this.claimDate = claimDate;
        this.insuredPersonID = insuredPersonID;
        this.cardNumberID = cardNumberID;
        this.examDate = examDate;
        this.listOfDocuments = listOfDocuments;
        this.claimAmount = claimAmount;
        this.status = status;
        this.receiverBankingInfo = receiverBankingInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public String getInsuredPersonID() {
        return insuredPersonID;
    }

    public void setInsuredPersonID(String insuredPersonID) {
        this.insuredPersonID = insuredPersonID;
    }

    public String getCardNumberID() {
        return cardNumberID;
    }

    public void setCardNumberID(String cardNumberID) {
        this.cardNumberID = cardNumberID;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public ArrayList<String> getListOfDocuments() {
        return listOfDocuments;
    }

    public void setListOfDocuments(ArrayList<String> listOfDocuments) {
        this.listOfDocuments = listOfDocuments;
    }

    public float getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(float claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceiverBankingInfo() {
        return receiverBankingInfo;
    }

    public void setReceiverBankingInfo(String receiverBankingInfo) {
        this.receiverBankingInfo = receiverBankingInfo;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder s = new StringBuilder("* Claim information:");
        s.append("\n- ID: ").append(id).append(" | Claim date: ").append(dateFormat.format(claimDate)).append(" | Insured PersonID: ").append(insuredPersonID).append(" | CardNumberID=").append(cardNumberID);
        s.append("\n- Exam Date: ").append(dateFormat.format(examDate)).append(" | Claim Amount: $").append(claimAmount).append(" | Status: ").append(status);
        s.append("\n- List of documents: ");
        for (String string : listOfDocuments) {
            s.append("\n  + ").append(string);
        }
        s.append("\n- Receiver Banking Info: ").append(receiverBankingInfo);

        return s.toString();
    }

    public String getDataSaveFile() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String s = "";
        s += id + "|" + dateFormat.format(claimDate) + "|" + insuredPersonID + "|"
                + cardNumberID + "|" + dateFormat.format(examDate) + "|" + claimAmount + "|" + status;
        s += "\n" + String.join("|", listOfDocuments);
        s += "\n" + receiverBankingInfo;

        return s;
    }
}
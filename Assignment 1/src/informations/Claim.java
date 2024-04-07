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
    private String receiverBankingName;
    private String receiverBankingAccount;
    private String receiverBankingNumber;

    public Claim() {}

    public Claim(String id, Date claimDate, String insuredPersonID, String cardNumberID, Date examDate,
                 ArrayList<String> listOfDocuments, float claimAmount, String status, String receiverBankingName, String receiverBankingAccount, String receiverBankingNumber) {
        this.id = id;
        this.claimDate = claimDate;
        this.insuredPersonID = insuredPersonID;
        this.cardNumberID = cardNumberID;
        this.examDate = examDate;
        this.listOfDocuments = listOfDocuments;
        this.claimAmount = claimAmount;
        this.status = status;
        this.receiverBankingName = receiverBankingName;
        this.receiverBankingAccount = receiverBankingAccount;
        this.receiverBankingNumber = receiverBankingNumber;
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

    public String getReceiverBankingName() {
        return receiverBankingName;
    }

    public void setReceiverBankingName(String receiverBankingName) {
        this.receiverBankingName = receiverBankingName;
    }

    public String getReceiverBankingAccount() {
        return receiverBankingAccount;
    }

    public void setReceiverBankingAccount(String receiverBankingAccount) {
        this.receiverBankingAccount = receiverBankingAccount;
    }

    public String getReceiverBankingNumber() {
        return receiverBankingNumber;
    }

    public void setReceiverBankingNumber(String receiverBankingNumber) {
        this.receiverBankingNumber = receiverBankingNumber;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder s = new StringBuilder("\n* Claim information:");
        s.append("\n- ID: ").append(id).append(" | Claim date: ").append(dateFormat.format(claimDate)).append(" | Insured Person ID: ").append(insuredPersonID).append(" | Insurance Card ID: ").append(cardNumberID);
        s.append("\n- Exam Date: ").append(dateFormat.format(examDate)).append(" | Claim Amount: $").append(claimAmount).append(" | Status: ").append(status);
        s.append("\n- List of documents: ");
        for (String string : listOfDocuments) {
            s.append("\n  + ").append(string).append(".pdf");
        }
        s.append("\n- Receiver Bank's Name: ").append(receiverBankingName);
        s.append("\n- Receiver Bank Account's Name: ").append(receiverBankingAccount);
        s.append("\n- Receiver Bank Account's Number: ").append(receiverBankingNumber);

        return s.toString();
    }

    public String getDataSaveFile() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String s = "";
        s += id + "|" + dateFormat.format(claimDate) + "|" + insuredPersonID + "|"
                + cardNumberID + "|" + dateFormat.format(examDate) + "|" + claimAmount + "|" + status;
        s += "\n" + String.join("|", listOfDocuments);
        s += "\n" + receiverBankingName;
        s += "\n" + receiverBankingAccount;
        s += "\n" + receiverBankingNumber;

        return s;
    }
}
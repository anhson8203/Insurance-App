package informations;

import java.util.List;

public class Claim {
    private long id;
    private Date claimDate;
    private String insuredPerson;
    private long cardNumber;
    private Date examDate;
    private List<String> documents;
    private double claimAmount;
    private ClaimStatus status;
    private BankingInfo bankingInfo;
}
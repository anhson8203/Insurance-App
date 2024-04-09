package modules;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.Random;

import UI.ClaimProcessManager;
import informations.Claim;
import informations.Customer;
import informations.InsuranceCard;

/*
 * @author Tran Anh Son - s3926557
 * */
public class ProgramManager {
    private ClaimProcessManager claimManager = new ClaimProcessManagerImpl();
    private ArrayList<Customer> listCustomers = readCustomers();
    private ArrayList<InsuranceCard> listInsuranceCards = readInsuranceCards();
    private final Scanner scanner = new Scanner(System.in);
    private SimpleDateFormat dateFormat;

    public ProgramManager() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        listCustomers = readCustomers();
        listInsuranceCards = readInsuranceCards();
        claimManager = new ClaimProcessManagerImpl();
    }

    public ArrayList<Customer> readCustomers() {
        ArrayList<Customer> lCustomers = new ArrayList<Customer>();
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\RMIT University\\Further Programming\\Insurance-App\\Assignment 1\\src\\Customers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                lCustomers.add(new Customer(parts[0], parts[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lCustomers;
    }

    public ArrayList<InsuranceCard> readInsuranceCards() {
        ArrayList<InsuranceCard> lInsuranceCards = new ArrayList<InsuranceCard>();
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\RMIT University\\Further Programming\\Insurance-App\\Assignment 1\\src\\InsuranceCards.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date anExpirationDate = dateFormat.parse(parts[2]);
                String policyOwner = br.readLine();

                InsuranceCard insuranceCard = new InsuranceCard(parts[0], parts[1], policyOwner, anExpirationDate);

                lInsuranceCards.add(insuranceCard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lInsuranceCards;
    }

    private String generateRandomID() {
        Random random = new Random();
        StringBuilder idBuilder = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            idBuilder.append(random.nextInt(10));
        }

        return idBuilder.toString();
    }

    public boolean checkIDClaim(String id) {
        if (id.length() != 10)
            return false;
        for (int i = 0; i < id.length(); i++)
            if (id.charAt(i) < '0' || id.charAt(i) > '9')
                return false;
        return true;
    }

    public void displayAllCustomers() {
        System.out.println("\nList of customers information:");
        for (Customer customer : listCustomers) {
            System.out.println(customer.toString());
        }
    }

    public void displayAllInsuranceCards() {
        System.out.println("\nList of Insurance Cards information:");
        for (InsuranceCard insuranceCard : listInsuranceCards) {
            System.out.println(insuranceCard.toString());
        }
    }

    public void addClaim() {
        if (listCustomers.isEmpty()) {
            System.out.println("There are no customers in the list yet!");
            return;
        }

        if (listInsuranceCards.isEmpty()) {
            System.out.println("There is no insurance card in the list yet!");
            return;
        }
        displayAllCustomers();
        displayAllInsuranceCards();

        System.out.println("\nEnter new claim information");
        String id;
        while (true) {
            id = generateRandomID();
            if (claimManager.getClaim(id) != null) {
                continue;
            }
            break;
        }

        Date claimDate = null;
        while (true) {
            System.out.print("Request date (yyyy-MM-dd): ");
            String claimDateStr = scanner.nextLine();
            try {
                claimDate = dateFormat.parse(claimDateStr);
                break;
            } catch (ParseException e) {
                System.out.println("Error: Invalid date format, please re-enter !");
            }
        }

        String insuredPersonID;
        while (true) {
            System.out.print("Insured Person ID: ");
            insuredPersonID = scanner.nextLine();

            boolean k = false;
            for (Customer listCustomer : listCustomers) {
                if (listCustomer.getId().equals(insuredPersonID)) {
                    k = true;
                    break;
                }
            }
            if (k)
                break;

            System.out.println("Customer ID does not exist, please re-enter!");
        }

        String cardNumberID;
        while (true) {
            System.out.print("Insurance Card ID: ");
            cardNumberID = scanner.nextLine();

            boolean k = false;
            for (InsuranceCard listInsuranceCard : listInsuranceCards) {
                if (listInsuranceCard.getId().equals(cardNumberID)
                        && listInsuranceCard.getCardHolderID().equals(insuredPersonID)) {
                    k = true;
                    break;
                }
            }

            if (k)
                break;

            System.out.println("Insurance Card ID does not exist with provided Customer ID, please re-enter!");
        }

        Date examDate = null;
        while (true) {
            System.out.print("Exam date (yyyy-MM-dd): ");
            String examDateStr = scanner.nextLine();
            try {
                examDate = dateFormat.parse(examDateStr);
                break;
            } catch (ParseException e) {
                System.out.println("Error: Invalid date format, please re-enter !");
            }
        }

        System.out.print("List of documents (separated by commas and without file format): ");
        String documentsStr = scanner.nextLine();

        String[] documentsArray = documentsStr.split(",");
        ArrayList<String> listOfDocuments = new ArrayList<>(Arrays.asList(documentsArray));

        float claimAmount;
        while (true) {
            System.out.print("Claim mount: $");
            claimAmount = scanner.nextFloat();

            if (claimAmount > 0)
                break;

            System.out.println("Error: Amount must be greater than 0, please re-enter !");
        }

        scanner.nextLine();

        String status;
        while (true) {
            System.out.print("Status(New/ Processing/ Done): ");
            status = scanner.nextLine();

            if (claimAmount > 0) {
                break;
            }

            System.out.println("Error: incorrect, please re-enter !");
        }

        System.out.print("Receiver bank's name: ");
        String receiverBankingName = scanner.nextLine();

        System.out.print("Receiver bank account's name: ");
        String receiverBankingAccount = scanner.nextLine();

        System.out.print("Receiver bank account's number: ");
        String receiverBankingNumber = scanner.nextLine();

        Claim claim = new Claim(id, claimDate, insuredPersonID, cardNumberID, examDate, listOfDocuments, claimAmount,
                status, receiverBankingName, receiverBankingAccount, receiverBankingNumber);

        claimManager.addClaim(claim);

        System.out.println("\nThe claim has been added successfully.");
    }

    public Claim inputInfoUpdate(String id) {
        Date claimDate = null;
        while (true) {
            System.out.print("Request date (yyyy-MM-dd): ");
            String claimDateStr = scanner.nextLine();
            try {
                claimDate = dateFormat.parse(claimDateStr);
                break;
            } catch (ParseException e) {
                System.out.println("Error: Invalid date format, please re-enter !");
            }
        }

        String insuredPersonID;
        while (true) {
            System.out.print("Insured Person ID: ");
            insuredPersonID = scanner.nextLine();

            boolean k = false;
            for (Customer listCustomer : listCustomers) {
                if (listCustomer.getId().equals(insuredPersonID)) {
                    k = true;
                    break;
                }
            }
            if (k)
                break;

            System.out.println("Customer ID does not exist, please re-enter!");
        }

        String cardNumberID;
        while (true) {
            System.out.print("Insurance Card ID: ");
            cardNumberID = scanner.nextLine();

            boolean k = false;
            for (InsuranceCard listInsuranceCard : listInsuranceCards) {
                if (listInsuranceCard.getId().equals(cardNumberID)
                        && listInsuranceCard.getCardHolderID().equals(insuredPersonID)) {
                    k = true;
                    break;
                }
            }

            if (k)
                break;

            System.out.println("Insurance Card ID does not exist with provided Customer ID, please re-enter!");
        }

        Date examDate;
        while (true) {
            System.out.print("Exam date (yyyy-MM-dd): ");
            String examDateStr = scanner.nextLine();
            try {
                examDate = dateFormat.parse(examDateStr);
                break;
            } catch (ParseException e) {
                System.out.println("Error: Invalid date format, please re-enter!");
            }
        }

        System.out.print("List of documents (separated by commas): ");
        String documentsStr = scanner.nextLine();

        float claimAmount;
        while (true) {
            System.out.print("Claim mount: $");
            claimAmount = scanner.nextFloat();

            if (claimAmount > 0)
                break;

            System.out.println("Error: Claim amount cannot be 0, please re-enter!");
        }

        scanner.nextLine();

        String status;
        while (true) {
            System.out.print("Status(New/ Processing/ Done): ");
            status = scanner.nextLine();
            break;
        }

        System.out.print("Receiving bank's name: ");
        String receiverBankingName = scanner.nextLine();

        System.out.print("Receiving bank account's name: ");
        String receiverBankingAccount = scanner.nextLine();

        System.out.print("Receiving bank account's number: ");
        String receiverBankingNumber = scanner.nextLine();

        String[] documentsArray = documentsStr.split(",");
        ArrayList<String> listOfDocuments = new ArrayList<>(Arrays.asList(documentsArray));

        return new Claim(id, claimDate, insuredPersonID, cardNumberID, examDate, listOfDocuments, claimAmount,
                status, receiverBankingName, receiverBankingAccount, receiverBankingNumber);
    }

    public void displayAllClaims() {
        ArrayList<Claim> claims = claimManager.getAllClaims();
        if (!claims.isEmpty()) {
            System.out.println("\nList of all claims: ");
            for (Claim claim : claims) {
                System.out.println(claim);
            }
        } else {
            System.out.println("\nClaim list is empty.");
        }
    }

    public void updateClaim() {
        ArrayList<Claim> claims = claimManager.getAllClaims();
        if (!claims.isEmpty()) {
            displayAllClaims();
            System.out.print("\nEnter the ID of the claim to update: ");
            String id = scanner.nextLine();
            Claim existingClaim = claimManager.getClaim(id);
            if (existingClaim != null) {
                System.out.println("Enter update information");
                existingClaim = inputInfoUpdate(id);
                claimManager.updateClaim(existingClaim);
                System.out.println("\nThe claim has been updated successfully.");
            } else {
                System.out.println("\nNo claims were found with the entered ID.");
            }
        } else {
            System.out.println("\nClaim list is empty.");
        }
    }

    public void deleteClaim() {
        ArrayList<Claim> claims = claimManager.getAllClaims();
        if (!claims.isEmpty()) {
            displayAllClaims();
            displayAllCustomers();
            displayAllInsuranceCards();
            System.out.print("\nEnter the ID of the claim to delete: ");
            String id = scanner.nextLine();
            Claim existingClaim = claimManager.getClaim(id);
            if (existingClaim != null) {
                claimManager.deleteClaim(id);
                System.out.println("\nThe claim has been successfully cleared.");
            } else {
                System.out.println("\nNo claims were found with the entered ID.");
            }
        } else {
            System.out.println("\nClaim list is empty.");
        }
    }
}
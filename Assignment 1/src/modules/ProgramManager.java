package modules;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import UI.ClaimProcessManager;
import informations.Claim;
import informations.Customer;
import informations.InsuranceCard;

public class ProgramManager {
    private ClaimProcessManager claimManager = new ClaimProcessManagerImpl();
    private ArrayList<Customer> listCustomers = readCustomers();
    private ArrayList<InsuranceCard> listInsuranceCards = readInsuranceCards();
    private Scanner scanner = new Scanner(System.in);
    private SimpleDateFormat dateFormat;

    public ProgramManager() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        listCustomers = readCustomers();
        listInsuranceCards = readInsuranceCards();
        claimManager = new ClaimProcessManagerImpl();
    }

    public ArrayList<Customer> readCustomers() {
        ArrayList<Customer> lCustomers = new ArrayList<Customer>();
        try (BufferedReader br = new BufferedReader(new FileReader("src\\Customers.txt"))) {
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
        try (BufferedReader br = new BufferedReader(new FileReader("src\\InsuranceCards.txt"))) {
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

    public boolean checkIDClaim(String id) {
        if (id.length() != 10)
            return false;
        for (int i = 0; i < id.length(); i++)
            if (id.charAt(i) < '0' || id.charAt(i) > '9')
                return false;
        return true;
    }

    public void displayListCustomers() {
        System.out.println("\nList of customers information:");
        for (Customer customer : listCustomers) {
            System.out.println(customer.toString());
        }
    }

    public void displayListInsuranceCards() {
        System.out.println("\nList of InsuranceCards information:");
        for (InsuranceCard insuranceCard : listInsuranceCards) {
            System.out.println(insuranceCard.toString());
        }
    }

    public void addClaim() {
        if (listCustomers.size() < 1) {
            System.out.println("There are no customers in the list yet!");
            return;
        }

        if (listInsuranceCards.size() < 1) {
            System.out.println("There is no insurance card in the list yet !");
            return;
        }
        displayListCustomers();
        displayListInsuranceCards();

        System.out.println("\nEnter new claim information");
        String id;
        while (true) {
            System.out.print("ID: ");
            id = scanner.nextLine();
            if (checkIDClaim(id)) {
                System.out.println("The id format must be 10 digits, please re-enter !");
                continue;
            }

            if (claimManager.getClaim(id) != null) {
                System.out.println("Claim ID already exists, please re-enter!");
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
            for (int i = 0; i < listCustomers.size(); i++) {
                if (listCustomers.get(i).getId().equals(insuredPersonID))
                    k = true;
            }
            if (k)
                break;

            System.out.println("Customer ID does not exist, please re-enter!");
        }

        String cardNumberID;
        while (true) {
            System.out.print("ID Insurance Card: ");
            cardNumberID = scanner.nextLine();

            boolean k = false;
            for (int i = 0; i < listInsuranceCards.size(); i++) {
                if (listInsuranceCards.get(i).getId().equals(cardNumberID)
                        && listInsuranceCards.get(i).getCardHolderID().equals(insuredPersonID))
                    k = true;
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

        System.out.print("List of documents (separated by commas): ");
        String documentsStr = scanner.nextLine();

        float claimAmount;
        while (true) {
            System.out.print("Claim mount: ");
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

            if (claimAmount > 0)
                break;

            System.out.println("Error: incorrect, please re-enter !");
        }

        System.out.print("Receiving bank information: ");
        String receiverBankingInfo = scanner.nextLine();

        String[] documentsArray = documentsStr.split(",");
        ArrayList<String> listOfDocuments = new ArrayList<>(Arrays.asList(documentsArray));

        Claim claim = new Claim(id, claimDate, insuredPersonID, cardNumberID, examDate, listOfDocuments, claimAmount,
                status, receiverBankingInfo);

        claimManager.addClaim(claim);

        System.out.println("The claim has been added successfully.");
    }

    public Claim inputInfoUpdate(String id) {
        displayListCustomers();
        displayListInsuranceCards();

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
            for (int i = 0; i < listCustomers.size(); i++) {
                if (listCustomers.get(i).getId().equals(insuredPersonID))
                    k = true;
            }
            if (k)
                break;

            System.out.println("Customer ID does not exist, please re-enter!");
        }

        String cardNumberID;
        while (true) {
            System.out.print("ID InsuranceCard: ");
            cardNumberID = scanner.nextLine();

            boolean k = false;
            for (int i = 0; i < listInsuranceCards.size(); i++) {
                if (listInsuranceCards.get(i).getId().equals(cardNumberID)
                        && listInsuranceCards.get(i).getCardHolderID().equals(insuredPersonID))
                    k = true;
            }

            if (k)
                break;

            System.out.println("InsuranceCard ID does not exist with Customer ID, please re-enter!");
        }

        Date examDate;
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

        System.out.print("List of documents (separated by commas): ");
        String documentsStr = scanner.nextLine();

        float claimAmount;
        while (true) {
            System.out.print("Claim mount: ");
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

            if (claimAmount > 0)
                break;

            System.out.println("Error: incorrect, please re-enter!");
        }

        System.out.print("Receiving bank information: ");
        String receiverBankingInfo = scanner.nextLine();

        String[] documentsArray = documentsStr.split(",");
        ArrayList<String> listOfDocuments = new ArrayList<>(Arrays.asList(documentsArray));

        Claim claim = new Claim(id, claimDate, insuredPersonID, cardNumberID, examDate, listOfDocuments, claimAmount,
                status, receiverBankingInfo);

        return claim;
    }

    public void updateClaim() {
        System.out.print("Enter the ID of the claim to update: ");
        String id = scanner.nextLine();
        Claim existingClaim = claimManager.getClaim(id);
        if (existingClaim != null) {
            System.out.print("Enter update information: ");
            existingClaim = inputInfoUpdate(id);
            claimManager.updateClaim(existingClaim);
            System.out.println("The claim has been updated successfully.");
        } else {
            System.out.println("No claims were found with the entered ID.");
        }
    }

    public void deleteClaim() {
        System.out.print("Enter the ID of the claim to delete: ");
        String id = scanner.nextLine();
        Claim existingClaim = claimManager.getClaim(id);
        if (existingClaim != null) {
            claimManager.deleteClaim(id);
            System.out.println("The claim has been successfully cleared.");
        } else {
            System.out.println("No claims were found with the entered ID.");
        }
    }

    public void displayAllClaims() {
        ArrayList<Claim> claims = claimManager.getAllClaims();
        if (!claims.isEmpty()) {
            System.out.println("List of all claims: ");
            for (Claim claim : claims) {
                System.out.println(claim);
            }
        } else {
            System.out.println("Claim list is empty.");
        }
    }
}
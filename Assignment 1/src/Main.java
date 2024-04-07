import java.util.Scanner;
import modules.ProgramManager;

/*
* @author Tran Anh Son - s3926557
* */
public class Main {
    public static void main(String[] args) {
        ProgramManager pm = new ProgramManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Claim");
            System.out.println("2. Update claim");
            System.out.println("3. Delete claim");
            System.out.println("4. Display all claims");
            System.out.println("5. Display all customers");
            System.out.println("6. Display all insurance cards");
            System.out.println("0. Exit\n");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    pm.addClaim();
                    break;
                case 2:
                    pm.updateClaim();
                    break;
                case 3:
                    pm.deleteClaim();
                    break;
                case 4:
                    pm.displayAllClaims();
                    break;
                case 5:
                    pm.displayAllCustomers();
                    break;
                case 6:
                    pm.displayAllInsuranceCards();
                    break;
                case 0:
                    System.out.println("\nThank you for using our application.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid input. Please try again.");
            }
        }
    }
}
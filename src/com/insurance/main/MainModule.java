package com.insurance.main;

import java.util.Scanner;
import com.insurance.entity.Policy;
import com.insurance.exception.PolicyNotFoundException;
import com.insurance.dao.*;

public class MainModule {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IPolicyService policyService = new InsuranceServiceImpl();

        while (true) {
            System.out.println("\n=== Insurance Management System ===");
            System.out.println("1. Create Policy");
            System.out.println("2. Get Policy by ID");
            System.out.println("3. Get All Policies");
            System.out.println("4. Update Policy");
            System.out.println("5. Delete Policy");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Policy ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter Policy Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter Coverage Amount: ");
                        double coverageAmount = scanner.nextDouble();

                        System.out.print("Enter Premium Amount: ");
                        double premium = scanner.nextDouble();

                        System.out.print("Enter Duration (in years): ");
                        int duration = scanner.nextInt();

                        Policy policy = new Policy(id, name, coverageAmount, premium, duration);
                        boolean created = policyService.createPolicy(policy);
                        System.out.println(created ? "Policy created successfully." : "Failed to create policy.");
                        break;

                    case 2:
                        System.out.print("Enter Policy ID to retrieve: ");
                        int searchId = scanner.nextInt();
                        Policy retrieved = policyService.getPolicy(searchId);
                        System.out.println("Policy Found: " + retrieved);
                        break;

                    case 3:
                        for (Policy p : policyService.getAllPolicies()) {
                            System.out.println(p);
                        }
                        break;

                    case 4:
                        System.out.print("Enter Policy ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine(); // consume newline

                        System.out.print("Enter New Policy Name: ");
                        String newName = scanner.nextLine();

                        System.out.print("Enter New Coverage Amount: ");
                        double newCoverageAmount = scanner.nextDouble();

                        System.out.print("Enter New Premium: ");
                        double newPremium = scanner.nextDouble();

                        System.out.print("Enter New Duration: ");
                        int newDuration = scanner.nextInt();

                        Policy updatedPolicy = new Policy(updateId, newName, newCoverageAmount, newPremium, newDuration);
                        boolean updated = policyService.updatePolicy(updatedPolicy);
                        System.out.println(updated ? "Policy updated successfully." : "Policy update failed.");
                        break;

                    case 5:
                        System.out.print("Enter Policy ID to delete: ");
                        int deleteId = scanner.nextInt();
                        boolean deleted = policyService.deletePolicy(deleteId);
                        System.out.println(deleted ? "Policy deleted successfully." : "Policy not found.");
                        break;

                    case 6:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid option. Try again.");
                }

            } catch (PolicyNotFoundException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Unexpected error occurred: " + e.getMessage());
            }
        }
    }
}


package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<SteamAccount> accounts = new ArrayList<>();
        
        System.out.println("Enter game URL:");
        String gameUrl = scanner.nextLine().trim();
        
        System.out.println("Enter accounts (sessionId, steamLoginSecure, accountName):");
        System.out.println("Format: sessionId steamLoginSecure accountName");
        System.out.println("Enter 'done' when finished");
        
        while (true) {
            System.out.print("Account: ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            System.out.println("Enter accounts (sessionId, steamLoginSecure, accountName):");

            String[] parts = input.split(" ");
            if (parts.length == 3) {
                String sessionId = parts[0].trim();
                String steamLoginSecure = parts[1].trim();
                String accountName = parts[2].trim();
                
                accounts.add(new SteamAccount(sessionId, steamLoginSecure, accountName));
                System.out.println("Account added. Enter next account or 'done' to finish.");
            } else {
                System.out.println("Invalid format. Use: sessionId|steamLoginSecure|accountName");
            }
        }
        
        if (accounts.isEmpty()) {
            System.out.println("No accounts entered. Exiting.");
            return;
        }
        
        System.out.println("Processing " + accounts.size() + " accounts...");
        
        int successCount = 0;
        int failureCount = 0;
        
        for (SteamAccount account : accounts) {
            System.out.println("Processing account: " + account.getAccountName());
            
            SteamBot bot = null;
            try {
                bot = new SteamBot();
                bot.loginToAccount(account);
                
                if (bot.claimFreeGame(gameUrl)) {
                    System.out.println("Successfully claimed game for: " + account.getAccountName());
                    successCount++;
                } else {
                    System.out.println("Failed to claim game for: " + account.getAccountName());
                    failureCount++;
                }
                
                Thread.sleep(5000); // Wait between accounts
                
            } catch (Exception e) {
                System.err.println("Error processing account " + account.getAccountName() + ": " + e.getMessage());
                failureCount++;
            } finally {
                if (bot != null) {
                    bot.close();
                }
            }
        }
        
        System.out.println("\n=== SUMMARY ===");
        System.out.println("Total accounts: " + accounts.size());
        System.out.println("Successful claims: " + successCount);
        System.out.println("Failed claims: " + failureCount);
        
        scanner.close();
    }
}

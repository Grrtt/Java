/*
 ATM

Use the Account class attached to simulate an ATM machine. Create ten accounts in an array with id 0, 1, ….,9, and initial balance $100. The system prompts the user to enter an id. If the id is entered incorrectly, ask the user to enter a correct id. Once an id is accepted, the main menu is displayed as shown in the sample run. You can enter a choice 1 for viewing the current balance, 2 for withdrawing money, 3 for depositing money, and 4 for exiting the main menu. Once you exit, the system will prompt for an id again. Thus, once the system starts, it will not stop

***************Sample Output**********************

 
Enter an ID: 4
 
Main Menu
1: check balance
2: withdraw
3: deposit
4: exit
 
Enter a choice: 1
The balance is $100.0
 
Main Menu
1: check balance
2: withdraw
3: deposit
4: exit
 
Enter a choice: 2
Enter an amount to withdraw: 3
 
Main Menu
1: check balance
2: withdraw
3: deposit
4: exit
 
Enter a choice: 1
The balance is $97.0
 
Main Menu
1: check balance
2: withdraw
3: deposit
4: exit
 
Enter a choice: 3
Enter an amount to deposit: 20
 
Main Menu
1: check balance
2: withdraw
3: deposit
4: exit
 
Enter a choice: 1
The balance is $117.0
 
Main Menu
1: check balance
2: withdraw
3: deposit
4: exit
 
Enter a choice: 4
 
Enter an ID:
 */
package assignment;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Garrett Shepherd
 */
public class Assignment6 {

    public static int userID;
    public static BankAccount userAccount;
    public static BankAccount[] accounts;
    
    public static void main(String[] args) {

        accounts = new BankAccount[10];
        Arrays.fill(accounts, new BankAccount());

        Scanner input;
        input = new Scanner(System.in);
        
        while(true){
            System.out.print("Enter ID number: ");
            userID = input.nextInt();
            while(userID > 10 || userID <= 0){
                System.out.println("ID not found.");
                System.out.print("Enter ID number: ");
                userID = input.nextInt();
            }
            System.out.println("User ID: " + userID);

            // !!CAUTION!!
            // Account is (userID - 1) due to zero-based Array index.
            // !!CAUTION!!
            userAccount = accounts[userID - 1];

            int choice;
            do{
                displayMenu();
                System.out.print("Choice: ");
                choice = input.nextInt();
                System.out.println();
                doChoice(choice);
            } while(choice != 4);

            System.out.println("Thank you for doing business at Shepherd's Bank!");
            System.out.println("Logging out...");
        }
    }
    
    // Return menu items. Placed into Array for easier expandability.
    private static String[] getMenuItems(){
        String[] menuItems = new String[]{"Check Balance", "Withdraw", "Deposit", "EXIT"};
        return menuItems;
    }
    
    private static void doChoice(int choice){
        Scanner accountInput;
        
        switch(choice){
            case 1:
                userAccount.checkBalance();
                break;
            case 2:
                accountInput = new Scanner(System.in);
                System.out.print("Withdraw Amount (in U.S. Dollars): ");
                
                if(userAccount.getBalance() < 0){
                    userAccount.warningAccountOverdrawn();
                }else{
                    userAccount.withdraw(accountInput.nextDouble());
                }
                
                userAccount.checkBalance();
                accountInput.close();
                break;
            case 3:
                accountInput = new Scanner(System.in);
                System.out.print("Deposit Amount (in U.S. Dollars): ");
                
                userAccount.deposit(accountInput.nextDouble());
                userAccount.checkBalance();
                
                accountInput.close();
                break;
            case 4:
                break;
            default:
                System.out.println("Please choose a valid option.");
                break;
        }
    }
    
    private static void displayMenu(){
        String[] menu;
        menu = getMenuItems();
        
        System.out.println("Main Menu");
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ": " + menu[i]);
        }
        System.out.println();
    }
}

class BankAccount{
    private double balance;

    public BankAccount(){
        balance = 100;
    }
    
    public void deposit(double amount){
        balance += amount;
    }
    
    public double getBalance(){
        return balance;
    }

    public void checkBalance(){
        System.out.println(String.format("Balance: $%.2f", balance));
        System.out.println();
    }
    
    public void withdraw(double amount){
        balance -= amount;
    }
    
    public void warningAccountOverdrawn(){
        System.out.println();
        System.out.println("!!WARNING!! Account Overdrawn!");
        System.out.println("Please deposit funds to withdraw from account.");
    }
}

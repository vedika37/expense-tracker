package ui;

import java.util.Scanner;
/*
 *  Represents the main method where the user's account is created
 */

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        ExpenseTracker userAccount = new ExpenseTracker(name);
        userAccount.runAccount();
    }

}
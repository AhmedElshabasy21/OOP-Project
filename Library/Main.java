package Library;

import java.util.Scanner;

public class Main {
     static Scanner s;
     static Database database;
    public static void main(String[] args) {
         database = new Database();//Initialize the database
        System.out.println("Welcome to Library Management System!");

        int num;
        System.out.println("0. Exit\n1. login\n2. New user");
        s = new Scanner(System.in);
        num = s.nextInt(); // Get user's choice
        switch(num) {// Handle user's selection
               case 1: login();break;// Call the login method
               case 2: newuser();break;// Call the new user registration method
           }
    }
    private static void login() { // Handles user login
        System.out.println("Enter phone number");
        String phonenumber=s.next();// Get phone number
        System.out.println("Enter email");
        String email =s.next(); // Get email
        // Attempt to log in using the database
        int n =database.login(phonenumber,email);
        if ( n != -1){
            // User found, retrieve user details and navigate to their menu
            User user = database.getUser(n);
            user.menu(database,user);
        }else{// User not found
            System.out.println("User don't exist");
        }
    }
    // Handles new user registration
    private static void newuser() {
        System.out.println("Enter name:");
        String name =s.next();
        // Check if the user already exists in the database
        if(database.userExists(name)){
            System.out.println("User exists!");
            newuser();// Retry registration if user exists
        }
        System.out.println("Enter phone number:");
        String phonenumber=s.next();
        System.out.println("Enter email:");
        String email =s.next();
        System.out.println("1.Admin\n2.Normal User");
        int n2=s.nextInt();// Choose user type (Admin or Normal User)
        User user;
        if(n2==1) {
            // Create a new Admin user
            user=new Admin(name,phonenumber,email);

        }else {
            // Create a new Normal User
             user=new NormalUser(name,phonenumber,email);

        }
        database.AddUser(user);// Add the user to the database
        user.menu(database,user);


    }

}

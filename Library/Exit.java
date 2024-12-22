package Library;

import java.util.Scanner;

public class Exit implements IOOperation{
    Scanner s;
    Database database;
    User user;
    @Override
    public void oper(Database database, User user) {
        this.database = database;
        this.user = user;
        System.out.println("\nAre you sure you want to quit\n"
                +"1. Yes\n2. Main Menu");
         s = new Scanner(System.in);
        int i = s.nextInt();
        if (i == 1) {
            System.out.println("0. Exit\n1. login\n2. New user");//Additional user options
            s = new Scanner(System.in);
           int num = s.nextInt();

            switch(num) {
                case 1: login();break;
                case 2: newuser();break;
            }
        }else{
            user.menu(database,user);// Return to main menu if you choose not to exit
        }
    }
    private void login() {
        System.out.println("Enter phone number");
        String phonenumber=s.next();
        System.out.println("Enter email");
        String email =s.next();
        int n =database.login(phonenumber,email);
        if ( n != -1){// If the user is found
            User user = database.getUser(n);
            user.menu(database,user); // View user's list
        }else{
            System.out.println("User don't exist");
        }
    }
    private void newuser() {
        System.out.println("Enter name:");
        String name =s.next();
        System.out.println("Enter phone number:");
        String phonenumber=s.next();
        System.out.println("Enter email:");
        String email =s.next();
        System.out.println("1.Admin\n2.Normal User");
        int n2=s.nextInt();
        User user;
        if(n2==1) {
            user=new Admin(name,phonenumber,email);

        }else {
            user=new NormalUser(name,phonenumber,email);

        }
        database.AddUser(user);
        user.menu(database,user);


    }
}

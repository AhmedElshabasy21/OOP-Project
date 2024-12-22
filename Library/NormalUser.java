package Library;

import java.util.Scanner;

public class NormalUser extends User {
    public NormalUser(String name){// Generator to set username only
        super(name);// Call User's constructor
        this.operations=new IOOperation[]{
                new ViewBooks(),
                new Search(),
                new PlaceOrder(),
                new BorrowBook(),
                new CalculateFine(),
                new ReturnBook(),
                new Exit(),
        };
    }
    public NormalUser(String name,String email, String phonenumber ){
        super(name,email,phonenumber);
        this.operations=new IOOperation[]{
                new ViewBooks(),
                new Search(),
                new PlaceOrder(),
                new BorrowBook(),
                new CalculateFine(),
                new ReturnBook(),
                new Exit(),
        };

    }
    @Override
    public void menu(Database database, User user) {
        System.out.println("1. View Books ");
        System.out.println("2. Search");
        System.out.println("3. Place Order");
        System.out.println("4. Borrow Books");
        System.out.println("5. Calculate Fine ");
        System.out.println("6. Return Book");
        System.out.println("7. Exit");


        Scanner s= new Scanner(System.in);
        int n = s.nextInt();
        this.operations[n-1].oper(database, user);
        s.close();

    }
    public String toString(){// Representing the normal user as a text string
        return name+"<N/>"+email+"<N/>"+phoneNumber+"<N/>"+"Normal";
    }

}

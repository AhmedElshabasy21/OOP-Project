package Library;

import java.util.Scanner;

public class Search implements IOOperation{
    @Override
    public void oper(Database database, User user) {
        System.out.println("\nEnter book name:");
        Scanner s = new Scanner(System.in);
        String name = s.next();
        int i = database.getBook(name);// اTo search for the book in the database
        if (i>-1){// If the book is found
            System.out.println("\n"+database.getBook(i).toString()+"\n");// View book details
        }else{
            System.out.println("Book Not Found!\n");
        }
        user.menu(database,user);

    }
}

package Library;

import java.util.Scanner;

public class BorrowBook implements IOOperation{
    @Override
    public void oper(Database database, User user) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter book name: ");
        String bookName = s.next();

        int i = database.getBook(bookName);// Get the index of the book from the database
        if (i>-1){// Check if the book exists in the database
            Book book = database.getBook(i);
            boolean n=true;
            for (Borrowing b : database.getBrws()){  // Check if the user has already borrowed this book
                if (b.getBook().getName().matches(bookName) &&
                b.getUser().getName().matches(user.getName())){
                    n=false;
                    System.out.println(" You have borrowed this book before!");

                }


            }
           if (n){// If the user hasn't borrowed the book before
               if(book.getBrwcopies()>1){// Check if there are enough copies available
                   Borrowing borrowing = new Borrowing(book,user);
                   book.setBrwcopies(book.getBrwcopies() - 1);// Decrease the available copies
                   database.borrowBook(borrowing,book,i);// Update the borrowing record
                   // Display borrowing details and expiry date
                   System.out.println("You must return the book before 14 days from now\n"
                           + "Expiry date: "+borrowing.getFinish()+"\nEnjoy<3\n");
               }else{
                   System.out.println("This book isn't available for borrowing\n");
               }
           }
        }else{
            System.out.println("Book not found");
        }
        user.menu(database,user);

    }
}

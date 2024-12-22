package Library;

import java.util.Scanner;

public class ReturnBook implements IOOperation{
    @Override
    public void oper(Database database, User user) {

        System.out.println("Enter book name:");
        Scanner s=new Scanner(System.in);
        String bookName=s.next();
        if (!database.getBrws().isEmpty()){//Check if there are any borrowings in the database
            for(Borrowing b : database.getBrws()) {
                // Check if the user has borrowed the specified book
                if (b.getBook().getName().matches(bookName) &&
                        b.getUser().getName().matches(user.getName())) {
                    Book book = b.getBook();
                    int i = database.getAllBooks().indexOf(book);// Get the book's index
                    // Check if the user is late in returning the book
                    if (b.getDaysleft() > 0) {
                        System.out.println("You are late!\n"
                                + "You have to pay" + Math.abs(b.getDaysleft() * 50) + "as fine");
                    }
                    // Increase the number of available copies of the book
                    book.setBrwcopies(book.getBrwcopies() + 1);
                    database.returnBook(b, book, i);// Update the database to mark the book as returned

                    System.out.println("Book returned\n Thank You\n");
                    break;
                } else {
                    System.out.println("You didn't borrow this book\n");
                }
            }

                }else{// If the user hasn't borrowed any books
            System.out.println("You didn't borrow this book\n");
            }
                user.menu(database,user);
    }

}




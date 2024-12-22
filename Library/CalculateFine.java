package Library;

import java.util.Scanner;

public class CalculateFine implements IOOperation {
    @Override
    public void oper(Database database, User user) {
        System.out.println("Enter book name:");
        Scanner s = new Scanner(System.in);
        String bookname = s.next().trim();
        boolean found = false;// Flag to check if the book is found for this user
        // Iterate through all borrowings in the database
        for (Borrowing b : database.getBrws()) {
            if (b.getBook() != null && b.getBook().getName().equalsIgnoreCase(bookname) &&
                    b.getUser() != null && b.getUser().getName().equalsIgnoreCase(user.getName())) {

                int daysLeft = b.getDaysleft();// Get the days left for the borrowing period
                System.out.println("Days left: " + daysLeft);

                if (daysLeft > 0) {// If daysLeft is positive, the book is overdue
                    System.out.println("You are late!\n" +
                            "You have to pay " + daysLeft * 50 + " as fine\n");
                } else {
                    System.out.println("You don't have to pay fine\n");
                }

                found = true;
                break;
            }
        }

        if (!found) {// If the book is not found, display an error message
            System.out.println("You didn't borrow this book!");
        }
        user.menu(database, user);
    }
}
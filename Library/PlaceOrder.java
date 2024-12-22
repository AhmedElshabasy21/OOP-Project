package Library;

import java.util.Scanner;

public class PlaceOrder implements IOOperation{
    @Override
    public void oper(Database database, User user) {
        Order order= new Order();
        System.out.println("\nEnter book name:");
        Scanner s = new Scanner(System.in);
        String bookName = s.next();
        int i =database.getBook(bookName);
        if (i<=-1) {
            System.out.println("Book does not exist");

        }else{
            Book book=database.getBook(i);// Retrieve book from database
            order.setBook(book);// Set the book to order
            order.setUser(user);// Assign the user to the request
            System.out.println("Enter qty:");// Request to enter the required quantity
            int qty = s.nextInt();
            order.setQty(qty);
            order.setPrice(book.getPrice()*qty); // Calculate the total price of the order
            int bookindex = database.getBook(book.getName());// Get the book index
            book.setQty(book.getQty()-qty);// Update the available quantity of the book
            database.addOrder(order,book,bookindex);
            System.out.println("Order placed successfully!\n");
        }
        user.menu(database,user);

    }
}

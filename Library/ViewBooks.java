package Library;

import java.util.ArrayList;

public class ViewBooks implements IOOperation {

    @Override
    public void oper(Database database, User user) {

        ArrayList<Book> books = database.getAllBooks();// Retrieve book list from database
        System.out.println("Name\t\tAuthor\t\tPublisher\t\tCLA\t\tQty\t\tprice"
        +"\t\tBrw cps"); // Display table header
        for (Book b : books) {// View details of each book in the list.
            System.out.println(b.getName()+"\t\t"+b.getAuthor()+"\t\t"+b.getPublisher()+"\t\t\t"+
                    b.getAddress()+"\t\t"+b.getQty()+"\t\t"+b.getPrice()+"\t\t"+b.getBrwcopies());
        }
        System.out.println();
        user.menu(database,user);
    }

}

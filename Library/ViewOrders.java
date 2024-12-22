package Library;

import java.util.ArrayList;
import java.util.Scanner;

public class ViewOrders implements IOOperation {
    @Override
    public void oper(Database database, User user) {

        System.out.println("\nEnter book name:");
        Scanner s = new Scanner(System.in);
        String bookName = s.next();
        int i =database.getBook(bookName);
        if (i>-1){
            System.out.println("Book\t\tUser\t\tQty\t\tprice");// Display table header
            for(Order order : database.getAllOrders()){// Go through all requests in the database
                if (order.getBook().getName().matches(bookName)){// Check if the request is related to the requested book
                    System.out.println(order.getBook().getName()+"\t\t"+
                            order.getUser().getName()+"\t\t"+order.getQty()+"\t"+order.getPrice());
                }

            }
            System.out.println();
        }else{
            System.out.println("Book not found\n");
        }
        user.menu(database,user);

    }
}

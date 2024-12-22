package Library;

import java.util.Scanner;

public class DeleteAllData implements IOOperation{
    @Override
    public void oper(Database database, User user) {
        System.out.println("\nAre you sure you want to delete all data?\n"
        +"1. Continue\n2. Main Menu");
        Scanner s = new Scanner(System.in);
        int i = s.nextInt();// Read user option
        if (i == 1) {
            database.deleteAllData();// If the user chooses to continue, all data is deleted
        }else{
            user.menu(database,user); // If the user chooses to return,  will be returned to the main menu.

        }

    }
}

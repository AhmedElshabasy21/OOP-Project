package Library;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Database { //  Defining the files that store data, and requests
        private  ArrayList<User> users = new ArrayList<User>();
        private ArrayList<String> usernames = new ArrayList<String>();
        private ArrayList<Book> books = new ArrayList<Library.Book>();
        private  ArrayList<String> booknames = new ArrayList<String>();
        private  ArrayList<Order> orders = new ArrayList<Order>();
        private  ArrayList<Borrowing> borrowings = new ArrayList<Borrowing>();
                     // تعريف الملفات التي تخزن البيانات
        private File usersfile  = new File("C:\\Users\\DELL\\IdeaProjects\\OOP-Project\\src\\Users");
        private File booksfile  = new File("C:\\Users\\DELL\\IdeaProjects\\OOP-Project\\src\\Books");
        private File ordersfile  = new File("C:\\Users\\DELL\\IdeaProjects\\OOP-Project\\src\\orders");
        private File borrowingsfile  = new File("C:\\Users\\DELL\\IdeaProjects\\OOP-Project\\src\\Borrowings");
        private File folder =new File("C:\\Users\\DELL\\IdeaProjects\\OOP-Project\\src");

        public Database() {// Create the main folder if it doesn't exist
            if(!folder.exists()){
                folder.mkdirs();
            }
            if(!usersfile.exists()) {//  Create the data files if they don't exist
                try {
                    usersfile.createNewFile();
                }catch(Exception e){}

            }
            if(!booksfile.exists()) {
                try {
                    booksfile.createNewFile();
                }catch(Exception e){}
            }
            if(!ordersfile.exists()) {
                try {
                    ordersfile.createNewFile();
                }catch(Exception e){}
            }// Retrieve data from files when the system starts
            if(!borrowingsfile.exists()) {
                try {
                    borrowingsfile.createNewFile();
                }catch(Exception e){}
            }
            getUsers();
            getBooks();
            getOrders();
            getBorrowings();



        }
            //  Add a new user to the database
        public void AddUser(User s){
            users.add(s);
            usernames.add(s.getName());// Add the username to the list of names
            saveUsers();

        }
        public int login(String phonenumber, String email){
            int n= -1;// If the user is not found
            for(User s : users){
                if(s.getName().matches(phonenumber) && s.getEmail().matches(email)){
                    n=users.indexOf(s);// Return the index of the user
                    break;
                }
            }
            return n;
        }
        public User getUser(int n){
            return users.get(n);


        }
        public void AddBook(Library.Book book){
            books.add(book);
            booknames.add(book.toString2());
            saveBooks();
        }

            private void getUsers(){// Load user data from the file
            String text1  ="";
            try {
                BufferedReader br1 = new BufferedReader(new FileReader(usersfile));
                String s1;
                while ((s1 = br1.readLine()) != null) {
                    text1 = text1+ s1;
                }
                br1.close();
            }catch (Exception e){
                System.err.println(e.toString());
            }
            if (!text1.matches("")|| !text1.isEmpty() ) {// Parse the text data and add it to the lists

                String[] a1 = text1.split("<NewUser>");
                for (String s : a1) {
                    String[] a2 = s.split("<N/>");
                    if (a2[3].matches("Admin")){
                        User user   = new Admin(a2[0],a2[1],a2[2]);
                        users.add(user);
                        usernames.add(user.getName());
                    }else {
                        User user   = new NormalUser(a2[0],a2[1],a2[2]);
                        users.add(user);
                        usernames.add(user.getName());
                    }
                }
            }
            }
            private void saveUsers(){//  Save user data to the file
            String text1  ="";
            for (User user : users) {
                text1 = text1 + user.toString()+"<NewUser>\n";
            }
            try {
                PrintWriter pw = new PrintWriter(usersfile);
                pw.println(text1);
                pw.close();
            }catch (Exception e){
                System.err.println(e.toString());
            }
            }
    private void saveBooks(){
        String text1  ="";
        for (Library.Book book : books) {
            text1 = text1 + book.toString()+"<NewBook>\n";
        }
        try {
            PrintWriter pw = new PrintWriter(booksfile);
            pw.println(text1);
            pw.close();
        }catch (Exception e){
            System.err.println(e.toString());
        }
    }
    private void getBooks(){// Load book data from the file
        String text1 ="";
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(booksfile));
            String s1;
            while ((s1 = br1.readLine()) != null) {
                text1 = text1+ s1;
            }
            br1.close();
        }catch (Exception e){
            System.err.println(e.toString());
        }
        if (!text1.matches("")|| !text1.isEmpty() ) {// Parse the text data and add it to the lists
            String[] a1 = text1.split("<NewBook>");
            for (String s : a1) {
                Library.Book book= parseBook(s);
                if (book != null) {
                    books.add(book);
                    booknames.add(book.getName());
                }


            }
        }
    }

public Library.Book parseBook(String s) {// Convert book text into a Book object
    try {
        String name = s.substring(s.indexOf("Book Name: ") + 11, s.indexOf("Book Author: ")).trim();
        String author = s.substring(s.indexOf("Book Author: ") + 13, s.indexOf("Book Publisher: ")).trim();
        String publisher = s.substring(s.indexOf("Book Publisher: ") + 16, s.indexOf("Book Collection Address: ")).trim();
        String address = s.substring(s.indexOf("Book Collection Address: ") + 25, s.indexOf("Qty: ")).trim();
        int qty = Integer.parseInt(s.substring(s.indexOf("Qty: ") + 5, s.indexOf("Price: ")).trim());
        double price = Double.parseDouble(s.substring(s.indexOf("Price: ") + 7, s.indexOf("Borrowing Copies: ")).trim());
        int brwCopies = Integer.parseInt(s.substring(s.indexOf("Borrowing Copies: ") + 18).trim());

        Library.Book book = new Library.Book();
        book.setName(name);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setAddress(address);
        book.setQty(qty);
        book.setPrice(price);
        book.setBrwcopies(brwCopies);

        return book;
    } catch (Exception e) {
        System.err.println("Error parsing book: " + s + " | " + e.getMessage());
        return null;
    }
}
    public ArrayList<Book> getAllBooks(){
            return books;
    }
    public int getBook(String name) {
        for (int i = 0; i < books.size(); i++) {

            if (books.get(i).getName().equalsIgnoreCase(name.trim())) {
                return i;
            }
        }
        return -1;
    }
    public Book getBook(int i){
        return books.get(i);
    }
    public void deleteBook(int i){
            books.remove(i);
            booknames.remove(i);
            saveBooks();
    }
    public void deleteAllData() {
        users.clear(); // Clear all users from memory
        usernames.clear();
        books.clear();
        booknames.clear();
        saveUsers();
        saveBooks();

        if (usersfile.exists()) {
            usersfile.delete(); // Delete the users file
        }
        if (booksfile.exists()) {
            booksfile.delete(); // Delete the books file
        }
        if (ordersfile.exists()) {
            ordersfile.delete(); // Delete the orders file

        }
        if (borrowingsfile.exists()) {
            borrowingsfile.delete();
        }

        System.out.println("All data has been deleted successfully.");
    }


    public void addOrder(Order order,Book book,int bookindex){
        if (!orders.contains(order)) { // Prevent duplication
            orders.add(order);
            books.set(bookindex, book);// Update book data in the list
            saveOrders();
            saveBooks();
        }
    }
    private void saveOrders(){
        String text1  ="";
        for (Order order : orders) {
            text1 = text1 + order.toString2()+"<NewOrder>\n";// Convert the order to a specific text format
        }
        try {
            PrintWriter pw = new PrintWriter(ordersfile);
            pw.print(text1);
            pw.close();
        }catch (Exception e){
            System.err.println(e.toString());
        }
    }
    private void getOrders(){//Retrieve order data from the file
        String text1 ="";
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(ordersfile));
            String s1;
            while ((s1 = br1.readLine()) != null) {
                text1 = text1+ s1;// Read all orders from File
            }
            br1.close();
        }catch (Exception e){
            System.err.println(e.toString());
        }
        if (!text1.matches("")|| !text1.isEmpty() ) {// check if the file contain data
            String[] a1 = text1.split("<NewOrder>");
            for (String s : a1) {
            Order order = parseOrder(s);
            orders.add(order);
//                Library.Book book= parseBook(s);
//                if (book != null) {
//                    books.add(book);
//                    booknames.add(book.getName());
                }

            }
        }
        public boolean userExists(String name ) {
            boolean f = false;
            for (User user : users) {
                if (user.getName().toLowerCase().matches(name.toLowerCase())) {// Search for user by name

                    f = true;// If the user is found, he will be returned.

                    break;
                }
            }
            return f;
        }

        private User getUserByName(String name){
            User u= new NormalUser("");// Virtual object if not found
            for (User user : users) {
                if (user.getName().matches(name)) {
                    u = user;
                    break;
                }
            }
            return u;
        }
        public Order parseOrder(String s) {
            try {
                String[] a = s.split("<N/>");// Split text into parts based on formatting
                if (a.length < 4) {
                    System.err.println("Error: Invalid order format: " + s);
                    return null;
                }

                int bookIndex = getBook(a[0]);
                if (bookIndex == -1) {
                    System.err.println("Error: Book not found: " + a[0]);
                    return null;
                }

                User user = getUserByName(a[1]);
                if (user == null) {
                    System.err.println("Error: User not found: " + a[1]);
                    return null;
                }

                double price = Double.parseDouble(a[2]);
                int quantity = Integer.parseInt(a[3]);

                return new Order(books.get(bookIndex), user, price, quantity);// Create and return order
            } catch (Exception e) {
                System.err.println("Error parsing order: " + s + " | " + e.getMessage());
                return null;
            }
        }
        public ArrayList<Order> getAllOrders(){
            return orders;
        }

    private void saveBorrowings() {
        String text1 = "";
        for (Borrowing borrowing : borrowings) {
            // Verify that it is not empty and all data is present
            if (borrowing != null && borrowing.getBook() != null && borrowing.getUser() != null) {
                text1 += borrowing.toString2() + "<NewBorrowing>\n";
            }
        }
        try {
            PrintWriter pw = new PrintWriter(borrowingsfile);
            pw.print(text1);
            pw.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    private void getBorrowings() {
        String text1 = "";
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(borrowingsfile));
            String s1;
            while ((s1 = br1.readLine()) != null) {
                text1 =text1 + s1; // Read texts from file
            }
            br1.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        if (!text1.isEmpty()) {
            String[] a1 = text1.split("<NewBorrowing>");
            for (String s : a1) {
                Borrowing borrowing = parseBorrowing(s); // Borrowing Text to Objects
                if (borrowing != null) {
                    borrowings.add(borrowing); // Add object to list
                }
            }
        }
    }
    private Borrowing parseBorrowing(String s) {
        try {
            // Split text into parts using "<N/>"
            String[] a = s.split("<N/>");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate start = LocalDate.parse(a[0], formatter); // First date analysis
            LocalDate finish = LocalDate.parse(a[1], formatter); // Second date analysis

            Book book = getBook(getBook(a[3]));
            User user = getUserByName(a[4]);
            Borrowing brw =new Borrowing(start, book, user);
            return brw;
        } catch (Exception e) {
            System.err.println("Error parsing borrowing data: " + s + " | " + e.getMessage());
            return null;
        }
    }

    public void borrowBook(Borrowing brw, Book book, int bookindex) {
        borrowings.add(brw);
        books.set(bookindex, book);
        saveBorrowings();
        saveBooks();

    }
    public ArrayList<Borrowing> getBrws() {
        return borrowings;
    }

    public void returnBook(Borrowing b, Book book, int bookindex) {
            borrowings.remove(b);
            books.set(bookindex, book);
            saveBorrowings();
            saveBooks();
    }

}



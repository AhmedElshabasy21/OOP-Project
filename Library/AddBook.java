package Library;

import java.util.Scanner;

public class AddBook implements IOOperation {
    @Override
    public void oper(Database database, User user) {

        Scanner s = new Scanner(System.in);
        Book book = new Book();
        System.out.println("\nEnter book name:");
        String name = s.next();
        if (database.getBook(name) < -1) { // التحقق إذا كان هناك كتاب بنفس الاسم موجود بالفعل في قاعدة البيانات
            System.out.println("There is a book with this name!\n");
            user.menu(database, user);
            return;
        } else {
            // تعيين تفاصيل الكتاب الجديد
            book.setName(name);
            System.out.println("Enter book author:");
            book.setAuthor(s.next());
            System.out.println("Enter book publisher:");
            book.setPublisher(s.next());
            System.out.println("Enter book collection address:");
            book.setAddress(s.next());
            System.out.println("Enter qty:");
            book.setQty(s.nextInt());
            System.out.println("Enter Price:");
            book.setPrice(s.nextDouble());
            System.out.println("Enter borrowing copies:");
            book.setBrwcopies(s.nextInt());
            database.AddBook(book); // إضافة الكتاب إلى قاعدة البيانات
            System.out.println("Book added Successfully\n");
            user.menu(database, user);
        }
    }
}

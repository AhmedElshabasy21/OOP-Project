package Library;

import java.util.Scanner;

public class Admin extends User {


    public Admin(String name){// مُنشئ يقوم بتعيين اسم المسؤول فقط
        super(name);// استدعاء المُنشئ الخاص بـ User

    }
    public Admin(String name,String email, String phonenumber ){
        super(name,email,phonenumber);
        this.operations=new IOOperation[]{
                new ViewBooks(),
                new AddBook(),
                new DeleteBook(),
                new Search(),
                new DeleteAllData(),
                new ViewOrders(),
                new Exit(),
        };

    }
    @Override
    public void menu(Database database, User user) {
        Scanner s= new Scanner(System.in);
        System.out.println("1. View Books");
        System.out.println("2. Add Books");
        System.out.println("3. Delete Books");
        System.out.println("4. Search");
        System.out.println("5. Delete all data");
        System.out.println("6. View Orders");
        System.out.println("7. Exit");


        int n = s.nextInt();// قراءة خيار المستخدم
        s.nextLine();// معالجة إدخال جديد بعد قراءة الرقم
        System.out.println("Option selected: "+n);// طباعة الخيار الذي تم اختياره
        this.operations[n-1].oper(database,user);// تنفيذ العملية بناءً على الخيار

    }
    public String toString(){// تمثيل المسؤول كسلسلة نصية
        return name+"<N/>"+email+"<N/>"+phoneNumber+"<N/>"+"Admin";
    }
}

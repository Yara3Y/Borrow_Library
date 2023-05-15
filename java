package borrow_library2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Borrow_Library1 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner input = new Scanner(System.in);

//-----------------------------------variable--------------------------------------------        
        String status = null;                                //change the status debending on the choice                                 
        int select;                                          //change debending on the user select
        String nameUser;                                     //name user
        long ID = 0;                                         //ID user
        long phone = 0;                                      //numder phone user
        String AddressUser;                                  //address and the city
        String nameBooks = null;                             //get on name book from user
        String[] userArray = new String[50];                 //array user
        String[] bookArray = new String[10];                 //array book
        File readFile = new File("book.txt");                //open file 
        Bill bill;                                           //object of class Bill
        Books box = null;                                    //object of class Books
        user boxx;                                           //object of class user
        readFile(readFile, bookArray);                       //sumbit for method name file and array book
        char again = 'y';                                    //using it in while loob
        int lenth=0;
        int lenth1=0;
//   --------------------------------------------method--------------------------------------------     
        //Display and get infomation user
        System.out.println("<<<< Welcome to the book Borrow Library >>>");
       
        System.out.println("Pleas enter your Name: ");
        nameUser = input.nextLine();
      
        System.out.println("Pleas enter your ID: ");
        ID = input.nextLong(); 
        lenth=String.valueOf(ID).length();
        input.nextLine();
        while(lenth!=7){                 //specify the digits
            System.out.println("Pleas enter your ID again must be 7 digit: ");
            ID = input.nextLong(); 
            lenth=String.valueOf(ID).length();
              }


        
        System.out.println("Pleas enter your Phone Number: ");
        phone = input.nextLong();
       
       lenth1=String.valueOf(phone).length();
      
       while(lenth1!=10){                    //specify the digits
             System.out.println("Pleas enter your Phone Number again must be 10 digit: ");
             phone = input.nextLong();
             lenth1=String.valueOf(phone).length();
       }
   
        System.out.println("Pleas enter your Address: ");
        AddressUser = input.nextLine();
       
        boxx = new user(nameUser, ID, phone, AddressUser);
        boxx.addUser(nameUser, ID, phone, AddressUser);
      
        boxx.printuser();                //to print user infomation

//-------------------------------------select------------------------------------       
        while (again == 'y' || again == 'Y') {
            System.out.println("--------------------------------");
            System.out.println("    Select servies you want");
            System.out.println("--------------------------------");
            System.out.print("\t1- Borrow book\n"
                    + "\t2- Return book\n");
            select = input.nextInt();
//--------------------------------------------------------------------------------         

            switch (select) {

                //select in the list  
//--------------------------------------Borrow Book---------------------------------           
                case 1:
                    input.nextLine();
                    System.out.println("Pleas enter name the book : ");
                    nameBooks = input.nextLine();
                    box = new Books(nameBooks);
                    box.getBorrowBook(bookArray, ID, nameBooks, userArray);

                    System.out.println("Insurance Borrowed the Book (10 SR)");
                    status = "Borrow Book";
                    bill = new Bill(status);
                    bill.printBill(boxx, box);

                    break;

//--------------------------------------return Book---------------------------------                 
                case 2:
                    input.nextLine();
                    System.out.println("Pleas enter name the book : ");
                    nameBooks = input.nextLine();
                    box = new Books(nameBooks);
                    box.returnBook(bookArray, nameBooks);
                  
                    System.out.println("Done Insurance returned successfully Book (10 SR)");
                    status = "return Book";
                    bill = new Bill(status);
                   
                    bill.printBill(boxx, box);
                   
                    break;
                default:
                    break;
            }

//-------------------------------------return select-------------------------------------     
            System.out.println("Do you want the list again (Yes/No)");
            again = input.next().charAt(0);
            bill = new Bill(status);
            bill.printBillinscreen(boxx, box);
        }

    }

    /**
     * this method open and read file then send it to class by object
     *
     * @param books the frist read file
     * @param bookArray the second object : to store book in array
     * @throws FileNotFoundException
     */
    public static void readFile(File books, String[] bookArray) throws FileNotFoundException {

//----------------------------------read file books----------------------------------------        
        Scanner read = new Scanner(books);

        read = new Scanner(books);

        String[] array = new String[2];

        String name = "";

        String author = "";

//-----------------------------------read line by line-------------------------------------       
        while (read.hasNext()) {

            array = read.nextLine().split("#");

            for (int x = 0; x < array.length; ++x) {
                
                if (x == 0) {
                    name = array[x];
                   

                } else if (x == 1) {
                    author = array[x];
                    Books book = new Books(name, author);

                    if (bookArray[0] == null) {
                        bookArray[0] = name;
                    } else if (bookArray[0] != null) {

                        // new book location index
                        int index = 0;

                        // looping through the array to find an empty index and assign it to the index variable
                        for (int i = 0; i < bookArray.length; i++) {
                            if (bookArray[i] == null) {
                                index = i;
                                break;
                            }
                        }

                        // adding the book to the empty location
                        bookArray[index] = name;

                    }

                }
            }
        }

    }
}

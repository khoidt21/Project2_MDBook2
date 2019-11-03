
import entity.Book;
import java.io.IOException;
import java.util.InputMismatchException;
import util.MyList;
import java.util.Scanner;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ADMIN
 */
public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        //boolean mainLoop = true;
        BookList bookList = new BookList();

        int choice = 0;
        do {
            System.out.println("Book List");
            System.out.println("1. Input Book and add to the end");
            System.out.println("2. Display books");
            System.out.println("3. Search by code");
            System.out.println("4. Input Book and add to beginning");
            System.out.println("5. Add Book after position k");
            System.out.println("6. Delete Book at position k");
            System.out.println("7. Sort Book By Code");
            System.out.println("8. Sort By Price Ascending");
            System.out.println("9. Load data from file to system");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            try {
                choice = input.nextInt();
                if (choice < 1 || choice > 9) {
                    System.out.printf("You have not entered a number between 0 and 9. " + "Try again.\n");
                    System.out.printf("Enter your choice between 0 and 9 only: \n");
                    continue;
                }

                switch (choice) {

                    case 1:
                        // addLast
                        bookList.addLast();
                        break;
                    case 2:
                        // list book
                        bookList.list();
                        break;
                    case 3:
                        // search 
                        bookList.search();
                        break;
                    case 4:
                        // addFirst
                        bookList.addFirst();
                        break;
                    case 5:
                        // addAfter
                        bookList.addAfter();
                        break;
                    case 6:
                        // deleteAt
                        bookList.deleteAt();
                        break;
                    case 7:
                        // sort by code
                        bookList.sortBookByCode();
                        bookList.list();
                        break;
                    case 8:
                        // sort price
                        bookList.sortBookByPrice();
                        bookList.list();
                        break;
                    case 9:
                        // load data from file to system 
                        try {
                            bookList.loadDataBook("data.txt");
                            System.out.println("Load data from file to system success.");
                        } catch (IOException ex) {
                            System.out.println("*****Load data: data.txt file not found.");
                        }
                        break;
                    case 0:
                        System.out.println("Exiting Program...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println(choice + " is not a valid Menu Option! Please Select Another.");

                }
            } catch (InputMismatchException ex) {
                System.out.println("You have entered choice is number. Try again.");
                break;
            }

        } while (choice != 0);

    }

}

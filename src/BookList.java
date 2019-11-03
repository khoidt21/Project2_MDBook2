/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.Book;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import util.MyList;

/**
 *
 * @author ADMIN
 */
public class BookList {

    //a list of book
    private MyList books;

    public MyList getBooks() {
        return books;
    }

    public BookList() {
        books = new MyList();
        /*
        try {
            loadDataBook("data.txt");
        } catch (IOException ex) {
            System.out.println("*****Load data: data.txt file not found.");
        }
        */       
    }

    //1.0 accept information of a Book
    private Book getBook() {

        Book newbook = new Book();
        String code = null;
        String title = null;
        boolean check = true;

        System.out.println("Enter input information book. ");

        Scanner scanner = new Scanner(System.in);
        // code
        while ((code == null) || (code.trim().isEmpty())) {
            System.out.println("Input code: ");
            code = scanner.nextLine();
        }

        while (books.search(code) != null) {
            System.out.println("Book code is available.Input code");
            code = scanner.nextLine();
        }

        Pattern codePattern = Pattern.compile("^[B]{1}[0-9]{2}$");
        while (!codePattern.matcher(code).matches()) {
            System.out.println("Bad input.Try again.Input code by format: B03 or B09 or B07 or B05");
            code = scanner.nextLine();
            while (books.search(code) != null) {
                System.out.println("Book code is available.Input code");
                code = scanner.nextLine();
            }
        }

        newbook.setbCode(code);

        // title 
        while ((title == null) || (title.trim().isEmpty())) {
            System.out.println("Input title: ");
            title = scanner.nextLine();
            newbook.setTitle(title);
        }
        // quantity 

        do {
            try {
                System.out.println("Input quantity:");
                newbook.setQuantity(scanner.nextInt());
                check = false;
            } catch (InputMismatchException e) {
                System.out.println("Quantity is number.Input quantity");
                check = true;
                scanner.nextLine();
            }
        } while (check);
        // lended 

        do {
            try {
                System.out.println("Input lended:");
                newbook.setLended(scanner.nextInt());
                check = false;
            } catch (InputMismatchException e) {
                System.out.println("Lended is number.Input lended");
                check = true;
                scanner.nextLine();
            }

        } while (check);
        // price 

        do {
            try {
                System.out.println("Input price:");
                newbook.setPrice(scanner.nextDouble());
                check = false;
                scanner.nextLine();

            } catch (InputMismatchException e) {
                System.out.println("Price is number.Input price");
                check = true;
                scanner.nextLine();
            }

        } while (check);

        return newbook;

    }

    //1.1 accept and add a new Book to the end of book list
    public void addLast() {

        books.addLast(getBook());
        // add data book to file data.txt
        try {
            saveBookToFile(books, "data.txt");
        } catch (IOException ex) {
            System.out.println("Add data file error.Add book to first error.");
        }

    }

    public void saveBookToFile(MyList books, String fileName) throws IOException {

        File fout = new File(fileName);
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        for (int i = 0; i < books.size(); i++) {

            bw.write(books.getNode(i).info.getbCode() + "," + books.getNode(i).info.getTitle() + ","
                    + books.getNode(i).info.getQuantity() + ","
                    + books.getNode(i).info.getLended() + "," + books.getNode(i).info.getPrice() + ","
                    + String.format("%.2f", books.getNode(i).info.getQuantity() * books.getNode(i).info.getPrice()));
            bw.newLine();
        }
        bw.close();
    }

    public void loadDataBook(String fileName) throws FileNotFoundException, IOException {

        FileReader fileReader = new FileReader(fileName);

        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                Book book = new Book();
                String arr[] = line.split(",");
                String bCode = arr[0].toString();
                String title = arr[1].toString();
                int quantity = Integer.parseInt(arr[2].toString());
                int ledend = Integer.parseInt(arr[3].toString());
                double price = Double.parseDouble(arr[4].toString());

                if (bCode.isEmpty()) {
                    System.out.println("Code is not empty.");
                } else {
                    book.setbCode(bCode);
                }
                if (title.isEmpty()) {
                    System.out.println("Title is not empty.");
                } else {
                    book.setTitle(title);
                }
                try {
                    book.setQuantity(quantity);
                } catch (Exception ex) {
                    System.out.println("Quantity is number.");
                }
                try {
                    book.setLended(ledend);
                } catch (Exception ex) {
                    System.out.println("Lended is number.");
                }
                try {
                    book.setPrice(price);
                } catch (Exception ex) {
                    System.out.println("Price is number.");
                }
                // add information to books 
                books.addLast(book);
            }
        }

    }

    //1.2 output information of book list
    public void list() {

        String c_code = "Code";
        String c_title = "Title";
        String c_quantity = "Quantity";
        String c_lender = "Lender";
        String c_price = "Price";
        String c_value = "Value";

        System.out.println(String.format("%s %10s %22s %7s %8s %9s", c_code, c_title, c_quantity, c_lender, c_price, c_value));

        books.traverse();

    }

    // sort book by code

    public void sortBookByCode() {
        books.sortList(new BookSortingCompartorByCode());
        try {
            saveBookToFile(books, "data.txt");
        } catch (IOException ex) {
            System.out.println("Add data file error.Add book to first error.");
        }
    }

    // sort book by price asc 

    public void sortBookByPrice() {
        books.sortList(new BookSortingComparatorByPrice());
        try {
            saveBookToFile(books, "data.txt");
        } catch (IOException ex) {
            System.out.println("Add data file error.Add book to first error.");
        }
    }

    //1.3 search book by book code
    public void search() {

        String code;
        System.out.println("Enter book code:");
        Scanner input = new Scanner(System.in);
        code = input.nextLine();

        if (books.search(code) != null) {
            System.out.println("Inforation of book code " + code);
            System.out.println(books.search(code).info);
        } else {
            System.out.println("Book is not in system.");
        }

    }

    //1.4 accept and add a new Book to the begining of book list
    public void addFirst() {
        books.addFirst(getBook());
        // add data book to file data.txt
        try {
            saveBookToFile(books, "data.txt");
        } catch (IOException ex) {
            System.out.println("Add data file error.Add book to first error.");
        }

    }

    //1.5 Add a new Book after a position k
    public void addAfter() {
        Book b = getBook();
        int k = 0;
        boolean check = true;
        System.out.println("Enter adding position: ");
        Scanner input = new Scanner(System.in);
        do {
            try {
                k = input.nextInt();
                if (books.size() < k) {
                    System.out.println("Enter retype position k.k must be less than or equal to the length of list.");
                    k = input.nextInt();
                }
                check = false;
            } catch (InputMismatchException ex) {
                System.out.println("k is number.Enter position k: ");
                input.nextLine();
                check = true;
            }
        } while (check);
        System.out.println("A new book has been added after position " + k);
        books.addAfter(b, k);
        // add data book to file data.txt
        try {
            saveBookToFile(books, "data.txt");
        } catch (IOException ex) {
            System.out.println("Add data file error.Add book to first error.");
        }
    }

    //1.6 Delete a Book at position k
    public void deleteAt() {
        int k = 0;
        System.out.println("Enter delete position: ");
        Scanner input = new Scanner(System.in);
        boolean check = true;
        do {
            try {
                k = input.nextInt();
                if (books.size() < k) {
                    System.out.println("Enter retype position k.k must be less than or equal to the length of list.");
                    k = input.nextInt();
                }
                check = false;
            } catch (InputMismatchException ex) {
                System.out.println("k is number.Enter position k: ");
                input.nextLine();
                check = true;
            }
        } while (check);
        System.out.println("A book has been delete in position " + k);
        books.deleteAt(k);

        // luu lai du lieu sau khi xoa tai vi tri k vao file data.txt
        try {
            saveBookToFile(books, "data.txt");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Save data file after delete position");
        }
        // goi lai list book 
        System.out.println("List book after delete");
        list();
    }

}

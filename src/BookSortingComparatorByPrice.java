
import entity.Book;
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class BookSortingComparatorByPrice implements Comparator<Book>{

    @Override
    public int compare(Book b1, Book b2) {
       return (int) (b1.getPrice() - b2.getPrice());
    }
       
    
}

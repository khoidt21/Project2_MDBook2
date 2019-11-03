/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import entity.Book;
import java.util.Comparator;
/**
 *
 * @author ADMIN
 */
public class BookSortingCompartorByCode implements Comparator<Book>{

    @Override
    public int compare(Book book1, Book book2) {
        return book1.getbCode().compareTo(book2.getbCode());
    }
    
}

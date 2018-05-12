package com.sky.library;

import com.sky.library.exception.BookNotFoundException;
import com.sky.library.model.Book;
import com.sky.library.service.BookService;
import com.sky.library.service.BookServiceProvider;
import org.junit.Assert;
import org.junit.Test;

public class BookServiceTest {

    BookService bookService = new BookServiceProvider();

    @Test
    public void testRetrieveBookWithNull(){
        try {
            Book book = bookService.retrieveBook(null);
        } catch (BookNotFoundException e) {
            Assert.assertEquals("The book reference name cannot be blank",e.getMessage());
        }
    }

    @Test
    public void testRetrieveBookWithBlank(){
        try {
            Book book = bookService.retrieveBook("  ");
        } catch (BookNotFoundException e) {
            Assert.assertEquals("The book reference name cannot be blank",e.getMessage());
        }
    }

    @Test
    public void testRetrieveBookInvalidText(){
        try {
            Book book = bookService.retrieveBook("INVALID-TEXT");
        } catch (BookNotFoundException e) {
            Assert.assertEquals("The book reference must begin with BOOK- for book - INVALID-TEXT",e.getMessage());
        }
    }

    @Test
    public void testRetrieveBookInvalidBook(){
        try {
            Book book = bookService.retrieveBook("BOOK-999");
        } catch (BookNotFoundException e) {
            Assert.assertEquals("No such book found - BOOK-999",e.getMessage());
        }
    }

    @Test
    public void testRetrieveBookValidBook() throws BookNotFoundException {
        Book book = bookService.retrieveBook("BOOK-GRUFF472");
        Assert.assertEquals("The Gruffalo",book.getTitle());
        Assert.assertEquals("A mouse taking a walk in the woods",book.getReview());
    }


    @Test
    public void testSummaryBookWithNull(){
        try {
            String summary = bookService.getBookSummary(null);
        } catch (BookNotFoundException e) {
            Assert.assertEquals("The book reference name cannot be blank",e.getMessage());
        }
    }

    @Test
    public void testSummaryBookWithBlank(){
        try {
            String summary = bookService.getBookSummary("  ");
        } catch (BookNotFoundException e) {
            Assert.assertEquals("The book reference name cannot be blank",e.getMessage());
        }
    }

    @Test
    public void testSummaryBookInvalidText(){
        try {
            String summary = bookService.getBookSummary("INVALID-TEXT");
        } catch (BookNotFoundException e) {
            Assert.assertEquals("The book reference must begin with BOOK- for book - INVALID-TEXT",e.getMessage());
        }
    }

    @Test
    public void testSummaryBookInvalidBook(){
        try {
            String summary = bookService.getBookSummary("BOOK-999");
        } catch (BookNotFoundException e) {
            Assert.assertEquals("No such book found - BOOK-999",e.getMessage());
        }
    }

    @Test
    public void testSummaryBookValidBook1() throws BookNotFoundException {
        String summary = bookService.getBookSummary("BOOK-GRUFF472");
        Assert.assertEquals("[BOOK-GRUFF472] The Gruffalo - A mouse taking a walk in the woods",summary);
    }

    @Test
    public void testSummaryBookValidBook2() throws BookNotFoundException {
        String summary = bookService.getBookSummary("BOOK-WILL987");
        Assert.assertEquals("[BOOK-WILL987] The Wind In The Willows - With the arrival of spring and fine weather outside...",summary);
    }
}

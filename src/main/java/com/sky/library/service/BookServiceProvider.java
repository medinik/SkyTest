package com.sky.library.service;

import com.sky.library.exception.BookNotFoundException;
import com.sky.library.model.Book;
import com.sky.library.model.BooksConstant;
import com.sky.library.repository.BookRepository;
import com.sky.library.repository.BookRepositoryStub;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class BookServiceProvider implements BookService {

    BookRepository bookRepository = new BookRepositoryStub();

    @Override
    public Book retrieveBook(String bookReference) throws BookNotFoundException {
        if(StringUtils.isEmpty(bookReference) || null == StringUtils.trimToNull(bookReference)){
            throw new BookNotFoundException("The book reference name cannot be blank");
        }

        if(!bookReference.startsWith(BooksConstant.BOOK_REFERENCE_PREFIX)){
            throw new BookNotFoundException("The book reference must begin with BOOK- for book - "+bookReference);
        }

        Book book = bookRepository.retrieveBook(bookReference);
        if(null == book){
            throw new BookNotFoundException("No such book found - "+bookReference);
        }
        return book;
    }

    @Override
    public String getBookSummary(String bookReference) throws BookNotFoundException {
        Book book = retrieveBook(bookReference);
        String[] reviewArry = null;
        boolean isEllipsis;
        String review = book.getReview();
        if(!StringUtils.isEmpty(review)){
            reviewArry = book.getReview().split(" ");
            isEllipsis = reviewArry.length > 9;
            if(isEllipsis){
                String lastWord = reviewArry[8];
                review = book.getReview().substring(0, (book.getReview().indexOf(lastWord)+ lastWord.length()))+"...";
            }
        }
        return '[' + book.getReference() + "] " + book.getTitle() + " - " + review;
    }
}

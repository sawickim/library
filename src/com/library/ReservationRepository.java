package com.library;

import java.util.*;


public class ReservationRepository {

    private BookRepository bookRepository;
    /**
     * Wypozyczone ksiazki
     */
    public Map<String, Book> orderedBooks = new HashMap<String, Book>();

    /**
     * Ksiazki jeszcze nie wyporzyczone
     */
    public Set<Book> lastBooksNotToOrdered = new HashSet<Book>();


    public Optional<Book> passBookToLibrary(String isbnToPass) {

        if (isbnToPass == null) {
            return Optional.empty();
        }

        if (getOrderedBooks().containsKey(isbnToPass)) {

            addToLastNotOrderedBooks(isbnToPass);
            removePassedBooks(isbnToPass);

            for (Book book : getLastBooksNotToOrdered())
                if (book.getIsbn().equals(isbnToPass))
                    return Optional.ofNullable(book);
        }

        return Optional.empty();
    }

    public void removePassedBooks(String isbnToPass) {
        getOrderedBooks().remove(isbnToPass);
    }

    public void removeLastBook(Book book) {
        getLastBooksNotToOrdered().remove(book);
    }

    public void addBookToOrdered(String isbn) {

        for (Book book : getLastBooksNotToOrdered())
            if (book.getIsbn().equals(isbn))
                getOrderedBooks().put(isbn, book);
    }

    public void addToLastNotOrderedBooks(String isbnToPass) {

        getLastBooksNotToOrdered().add(getOrderedBooks().get(isbnToPass));
    }

    public Map<String, Book> getOrderedBooks() {
        return orderedBooks;
    }

    public Set<Book> getLastBooksNotToOrdered() {
        return lastBooksNotToOrdered;
    }

    public void setLastBooksNotToOrdered(Map<String, Book> books) {

        for (Book book : books.values()) {
            lastBooksNotToOrdered.add(book);
        }
    }
}

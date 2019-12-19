package com.library;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Sort {

    public Set<Book> sortByTitle(Map<String, Book> books) throws IOException {
        Set<Book> sortedBooksByTitle = new TreeSet<Book>(new ComparatorTitle());

        sortedBooksByTitle.addAll(books.values());

        return sortedBooksByTitle;
    }

    public Set<Book> sortByYearPublic(Map<String, Book> books) throws IOException {
        Set<Book> sortedBooksByYear = new TreeSet<Book>(new ComparatorYear());

        sortedBooksByYear.addAll(books.values());

        return sortedBooksByYear;
    }

    public Set<Book> sortByAuthor(Map<String, Book> books) throws IOException {
        Set<Book> sortedBooksByAuthor = new TreeSet<Book>(new ComparatorAuthor());

        sortedBooksByAuthor.addAll(books.values());

        return sortedBooksByAuthor;
    }

    public Set<Book> sortById(Map<String, Book> books) throws IOException {
        Set<Book> sortedBooksById = new TreeSet<Book>(new ComparatorId());

        sortedBooksById.addAll(books.values());

        return sortedBooksById;
    }
}

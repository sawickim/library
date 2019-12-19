package com.library;

import com.library.Book;

import java.util.Comparator;

public class ComparatorId implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getIsbn().compareTo(o2.getIsbn());
    }
}

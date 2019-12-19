package com.library;

import com.library.Book;

import java.util.Comparator;

public class ComparatorTitle implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}

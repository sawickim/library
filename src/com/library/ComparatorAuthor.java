package com.library;

import com.library.Book;


import java.util.Comparator;

public class ComparatorAuthor implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getAuthor().compareTo(o2.getAuthor());
    }
}

package com.library;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class BookRepository {


    private ReservationRepository reservationRepository;
    /**
     * Wszystkie ksiazki do wypozyczenia
     */
    public Map<String, Book> books = new HashMap<String, Book>();

    public BookRepository() throws IOException {

    }

    public BookRepository(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    public void readBooksFromFile() throws IOException {

        ReadingFile fileReader = new ReadingFile();
        List<String> stringsList = fileReader.readingFile();
        for (String line : stringsList) {

            StringTokenizer st1 = new StringTokenizer(line, ";");

            String title = ((String) st1.nextElement()).trim();
            String year = ((String) st1.nextElement()).trim();
            String author = ((String) st1.nextElement()).trim();
            String isbn = ((String) st1.nextElement()).trim();

            Book book = new Book();
            book.setTitle(title);
            book.setYear(Integer.valueOf(year));
            book.setAuthor(author);
            book.setIsbn(isbn);


            books.put(isbn, book);
        }
        reservationRepository.setLastBooksNotToOrdered(books);
    }


    public Optional<Book> findAndOrderBook(String isbn) {

        if (isbn == null) {
            return Optional.empty();
        }

        if (getBooks().containsKey(isbn)) {
            reservationRepository.addBookToOrdered(isbn);
            reservationRepository.removeLastBook(getBooks().get(isbn));

            return Optional.ofNullable(getBooks().get(isbn));
        }

            return Optional.empty();
    }



    public Map<String, Book> getBooks() {
        return books;
    }

    public Set<Book> findByPhrazeTitle(String phraze) {

        Set<Book> bookPastSerchingByPhaze = new HashSet<Book>();

        for (Book book : getBooks().values()) {

            if (book.getTitle().contains(phraze)) {
                bookPastSerchingByPhaze.add(book);
            }
        }

        if (bookPastSerchingByPhaze.size() > 0)
            return bookPastSerchingByPhaze;
        else
            return null;
    }


    public void saveBooksToFile(String titleToAdd, String yearPublicToAdd, String authorToAdd, String IdToAdd) throws IOException {

        Writer output = new BufferedWriter(new FileWriter("C:\\Dokumenty\\Java\\FinalBook po zajeciach\\src\\com\\library\\booksLibrary.txt"));
        output.append("\n" + titleToAdd + ";" + yearPublicToAdd + ";" + authorToAdd + ";" + IdToAdd);
        output.close();
    }
}

package com.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Optional;

public class Run {

    public void run() throws IOException, NoSuchMethodException {

        ReservationRepository reservationRepository = new ReservationRepository();
        BookRepository bookRepository = new BookRepository(reservationRepository);
        Sort sort = new Sort();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        bookRepository.readBooksFromFile();

        while (true) {

            System.out.println("\n\nWybierz: \n" +
                    "1 - By wyporzyczyc ksiazke \n" +
                    "2 - By posortować książki \n" +
                    "3 - By dodać książkę do zbioru \n" +
                    "4 - By  zdać książkę \n" +
                    "5 - Odszukaj po frazie tytulu \n" +
                    "6 - Wyswietl zbior ksiazek \n" +
                    "7 - Exit");

            String choice = reader.readLine();
            switch (choice) {
                case "1":

                    System.out.println("Książki które mozesz jeszcze wypozyczyc");
                    for (Book book : reservationRepository.getLastBooksNotToOrdered())
                        System.out.println(book);

                    System.out.println("wypozycz ksiazke - podaj numer isbn");
                    String isbn = reader.readLine();

                    if (Optional.empty().equals(bookRepository.findAndOrderBook(isbn)))
                        System.out.println("Nie ma takiej ksiązki");

                    System.out.println("Ksiązki ktore juz wypozyczono");
                    for (Book book : reservationRepository.getOrderedBooks().values())
                        System.out.println(book);
                    break;
                case "2":

                    System.out.println("sortowanie po tytule");
                    for (Book book : sort.sortByTitle(bookRepository.getBooks()))
                        System.out.println(book);
                    System.out.println("sortowanie po roku wydania");
                    for (Book book : sort.sortByYearPublic(bookRepository.getBooks()))
                        System.out.println(book);
                    System.out.println("sortowanie po autorze");
                    for (Book book : sort.sortByAuthor(bookRepository.getBooks()))
                        System.out.println(book);
                    System.out.println("sortowanie po id");
                    for (Book book : sort.sortById(bookRepository.getBooks()))
                        System.out.println(book);
                    break;
                case "3":
                    System.out.println("Dodaj ksiazke");
                    System.out.println("Podaj tytul ksiazki do dodania do zbioru: ");
                    String titleToAdd = reader.readLine();
                    System.out.println("Podaj rok ksiazki do dodania do zbioru: ");
                    String yearPublicToAdd = reader.readLine();
                    System.out.println("Podaj autora ksiazki do dodania do zbioru: ");
                    String authorToAdd = reader.readLine();
                    System.out.println("Podaj trzynasto cyfrowy ID  ksiazki do dodania do zbioru: ");
                    String IdToAdd = reader.readLine();
                    bookRepository.saveBooksToFile(titleToAdd, yearPublicToAdd, authorToAdd, IdToAdd);
                    break;
                case "4":

                    System.out.println("Ksiązki ktore juz wypozyczono");
                    for (Book book : reservationRepository.getOrderedBooks().values())
                        System.out.println(book);

                    System.out.println("Zdaj ksiazke: ");
                    String titleBookToPass = reader.readLine();
                    reservationRepository.passBookToLibrary(titleBookToPass);

                    System.out.println("Ksiązki ktore zostaly w bibliotece");
                    for (Book book : reservationRepository.getLastBooksNotToOrdered())
                        System.out.println(book);

                    System.out.println("Zbior ksiazek");
                    for (Book book : bookRepository.getBooks().values())
                        System.out.println(book);
                    break;
                case "5":

                    System.out.println("Wpisz fraze tytulu ksiazki do wyszukania");
                    String phraze = reader.readLine();

                    System.out.println("Szukane ksiazki: ");
                    for (Book book : bookRepository.findByPhrazeTitle(phraze))
                        System.out.println(book);

                    if (bookRepository.findByPhrazeTitle(phraze).equals(Optional.empty()))
                        System.out.println("Nie ma takiej ksiazki");
                    break;
                case "6":
                    for (Book book : bookRepository.getBooks().values())
                        System.out.println(book);
                    break;
                case "7":
                    System.out.println("Exit");
                    break;
            }
            if (choice.equals("6"))
                break;
        }
    }
}

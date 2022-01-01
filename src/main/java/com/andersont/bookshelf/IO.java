package com.andersont.bookshelf;

import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

// unfortunately ObservableLists aren't serializable :(
public class IO {

    public static void writeLibrary(String saveLocation, Library library) {
        // write config file
        StringBuilder sb = new StringBuilder();
        sb.append("bookID::::" + library.getBookID() + "\n");

        StringToFile(sb.toString(), saveLocation, "library_data.txt");

        // write books
        for (Book book : library.getLibrary()) {
            StringToFile(book.writeBook(), saveLocation + "\\books", book.getString("bookID").toString());
        }

        // TODO write shelves
    }

    public static Library loadLibrary(String saveLocation) {
        Library library = new Library();

        // load config file
        try {
            String libraryData = Files.readString(Path.of(saveLocation + "\\library_data.txt"));
            String[] libraryData2  = libraryData.split("\n");
            int id = Integer.parseInt( libraryData2[0].split("::::")[1] );
            library.setBookID(id);
        } catch (IOException e) {
            System.out.println("Config file not found!");
        }

        // load books
        File[] bookFiles = new File(saveLocation + "\\books").listFiles();

        for(File file : bookFiles) {
            try {
                String bookData = Files.readString(Path.of(file.getAbsolutePath()));
                library.addBook(new Book(bookData));
            } catch (Exception ex) {
                System.out.println("Error while loading book " + file.getName());
            }
        }

        // TODO load shelves

        File[] shelves = new File(saveLocation + "\\shelves").listFiles();

        //
        if (library.getLibrary().size() == 0) {
            Book b = new Book();
            b.setProperty("title", "Matilda");
            b.setProperty("rating", "5");
            library.addBook(b);
        }

        System.out.println("Data loading complete!");
        return library;
    }

    public Book getBookByID(int bookID, ObservableList<Book> books) {
        for (Book book : books) {
            if (book.getString("bookID").equals(bookID)) {
                return book;
            }
        }
        return null;
    }

    public Shelf getShelfByName(String name, ObservableList<Shelf> shelves) {
        for (Shelf shelf : shelves) {
            if (shelf.name.equals(name)) {
                return shelf;
            }
        }
        return null;
    }


    private static void StringToFile(String data, String location, String filename) {
        try {
            PrintWriter out = new PrintWriter(location + "\\" + filename);
            out.println(data);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getBundleFile() {
        try {
            InputStream stream = new FileInputStream("resources/bundles/bundle.properties");
            Properties prop = System.getProperties();
            prop.load(stream);
            return prop;
        } catch (IOException e) {
            System.out.println("Language bundle not found!");
            e.printStackTrace();
            return null;
        }
    }
}




package com.andersont.bookshelf;

import javafx.scene.image.Image;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

// unfortunately ObservableLists aren't serializable :(
public class IO {
    private static final String bookExtension = ".txt"; // possibility to use .book :)

    public static void writeLibrary(String saveLocation, Library library) {
        // writes The library to disk

        // write library config file
        StringBuilder sb = new StringBuilder();
        sb.append("bookID::::" + library.getBookID() + "\n");
        StringToFile(sb.toString(), saveLocation, "library_data.txt");

        // write books
        for (Book book : library.getLibrary()) {
            StringToFile(book.writeBook(), saveLocation + "books\\", book.getString("bookID") + bookExtension);
        }
    }

    public static Library loadLibrary(String saveLocation) {
        // loads The library from disk
        Library library = new Library();

        // load library config file
        try {
            String libraryData = Files.readString(Path.of(saveLocation + "\\library_data.txt"));
            String[] libraryData2  = libraryData.split("\n");
            int id = Integer.parseInt( libraryData2[0].split("::::")[1] );
            library.setBookID(id);

            File[] bookFiles = new File(saveLocation + "books").listFiles();

            for(File file : bookFiles) {
                try {
                    String bookData = Files.readString(Path.of(file.getAbsolutePath()));
                    library.addBook(new Book(bookData));
                } catch (Exception ex) {
                    System.out.println("Error while loading book " + file.getName());
                }
            }

        } catch (IOException e) {
            System.out.println("Config file not found!");

        }


        // if no books found set a default book
        if (library.getLibrary().size() == 0) {
            Book b = new Book();
            b.setProperty("title", "Matilda");
            b.setProperty("rating", "10");
            library.addBook(b);
        }

        System.out.println("Data loaded!");
        return library;
    }

    private static void StringToFile(String data, String location, String filename) {
        // Convenience method
        try {
            PrintWriter out = new PrintWriter(location + filename);
            out.println(data);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties loadBundleFile() {
        // Loads bundle files from bundles folder
        // Currently only supports english :)
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

    public static Image loadImage(String absolutePath) {
        // Better to ask for forgiveness than to check for permissions first :)
        // tries to load given Image, if fails then returns placeholder Image,

        try {
            return new Image(absolutePath);
        } catch (Exception e) {}

        try {
            InputStream stream = new FileInputStream("resources/assets/placeholder.jpg");
            return new Image(stream);
        } catch (Exception e) {
            System.out.println("Error while loading default thumbnail?!");
            System.out.println(e);
            return null; // this leads to an error there is not much point in returning
        }
    }
}




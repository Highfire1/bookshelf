package com.andersont.bookshelf;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.HashMap;

// Essentially a glorified wrapper around a ObservableMap
public class Book {
    private final ObservableMap<String, StringProperty> props = FXCollections.observableMap(new HashMap<>());

    Book() {
        props.put("bookID", new SimpleStringProperty(""));
        props.put("title", new SimpleStringProperty("New Book"));
        props.put("rating", new SimpleStringProperty("0"));
        props.put("review", new SimpleStringProperty(""));
        props.put("readDate", new SimpleStringProperty(""));
        props.put("isbn", new SimpleStringProperty(""));
        props.put("thumbnail", new SimpleStringProperty(""));
    }


    Book(String data){
        String[] values = data.trim().split("::::\n");

        for (int i = 0; i < values.length;i+=2) {
            String key = values[i];
            String value = values[i+1].replace("::::", "");

            StringProperty trueValue = new SimpleStringProperty(value);
            props.put(key, trueValue);
        }
    }

    public ArrayList<String> bookData() {
        ArrayList<String> values = new ArrayList<>();
        for(StringProperty value : props.values()) {
            values.add(value.get());
        }
        return values;
    }

    public String writeBook() {
        StringBuilder sb = new StringBuilder();
        for (String key : props.keySet()) {
            sb.append(key + "::::\n");
            sb.append(props.get(key).get().replaceAll("::::", "") + "::::\n");
        }
        return sb.toString();
    }

    public void printBook() {
        System.out.println("DATA FOR BOOK: ");
        for (String key : props.keySet()) {
            System.out.println(key + " : " + props.get(key));
        }
        System.out.println();
    }

    public String toString() {
        return this.getString("title");
    }

    // getters and setters
    public String getString(String key) {
        if (!props.containsKey(key)) {
            return "ERROR FOR KEY " + key;
        }

        // because .get() can't handle null
        if (props.get(key) == null) {
            return null;
        }
        return props.get(key).get();
    }

    public StringProperty getStringProperty(String key) {
        if (!props.containsKey(key)) {
            return new SimpleStringProperty("ERROR FOR KEY " + key);
        }
        return props.get(key);
    }

    public void setProperty(String key, String value) {
        props.put(key, new SimpleStringProperty(value));
    }

    public ObservableMap getProps(){
        return props;
    }

}
module com.andersont.bookshelf {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.andersont.bookshelf to javafx.fxml;
    exports com.andersont.bookshelf;
}
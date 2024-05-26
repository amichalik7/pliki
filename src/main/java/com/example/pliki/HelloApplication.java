package com.example.pliki;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class HelloApplication extends Application {
    Button check = new Button();
    TextField patch = new TextField();
    Label result = new Label();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        check = (Button) scene.lookup("#check");
        patch = (TextField) scene.lookup("#patch");
        result = (Label) scene.lookup("#result");

        check.setOnAction(actionEvent -> {
            File plik = new File(patch.getText());
            if (plik.exists()){

            Date data = new Date(plik.lastModified());
            result.setText("Data modyfikacji pliku: " + data.toString());
            } else {
                result.setText("Plik nie istnieje");
            }
        });

    }

    public static void main(String[] args) {
        launch();
    }
}
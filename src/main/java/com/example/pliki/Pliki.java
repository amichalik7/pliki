package com.example.pliki;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class Pliki extends Application {
    Button open = new Button();
    TextField path = new TextField();
    Label result = new Label();
    TextArea text = new TextArea();
    Button save = new Button();
    File plik = null;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Pliki.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 300);
        stage.setTitle("Pliki");
        stage.setScene(scene);
        stage.show();
        open = (Button) scene.lookup("#open");
        path = (TextField) scene.lookup("#path");
        result = (Label) scene.lookup("#result");
        text = (TextArea) scene.lookup("#text");
        save = (Button) scene.lookup("#save");

        open.setOnAction(actionEvent -> {
            plik = new File(path.getText());
            if (plik.exists()) {
                if (plik.getName().endsWith("txt")) {
                    Date data = new Date(plik.lastModified());
                    result.setText("Data modyfikacji pliku: " + data + "\n" + "Rozmiar pliku: " + plik.length() / 1024 + " KB");
                    FileReader fr = null;
                    String tekst = "";
                    String linia;
                    try {
                        fr = new FileReader(path.getText());
                    } catch (Exception e) {
                        result.setText("Plik nie istnieje");
                    }
                    BufferedReader bfr = new BufferedReader(fr);
                    try {
                        while ((linia = bfr.readLine()) != null) {
                            tekst += linia + "\n";

                        }
                        text.setText(tekst);
                    } catch (Exception e) {

                    }

                } else {
                    result.setText("To nie jest plik tekstowy");
                }
            } else {
                result.setText("Plik nie istnieje");
            }
        });
        save.setOnAction(actionEvent -> {
            plik = new File(path.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Czy na pewno zapisać plik?");
            if (plik.exists()) {
                if (plik.getName().endsWith("txt")) {
                    Path path2 = Paths.get(path.getText());
                    if (!text.getText().isEmpty()) {
                        alert.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.OK) {
                                try {
                                    Files.write(path2, text.getText().getBytes());
                                    result.setText("Plik został zapisany");
                                } catch (Exception e) {

                                }
                            } else {
                                result.setText("Plik nie został zapisany");
                            }
                        });

                    } else {
                        result.setText("Dane nie mogą być puste");
                    }
                } else {
                    result.setText("To nie jest plik tekstowy");
                }
            } else result.setText("Plik nie istnieje");
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
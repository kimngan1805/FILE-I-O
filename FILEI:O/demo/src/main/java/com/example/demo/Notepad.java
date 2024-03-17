package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Notepad extends Application {

    private TextArea textArea;
    private FileChooser fileChooser;

    @Override
    public void start(Stage primaryStage) {
        textArea = new TextArea();
        textArea.setWrapText(true);

        Button openButton = new Button("Open");
        openButton.setOnAction(event -> openFile());

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> saveFile());

        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        VBox vbox = new VBox(5);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(openButton, saveButton, textArea);

        Scene scene = new Scene(vbox, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Notepad");
        primaryStage.show();
    }

    private void openFile() {
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                String content = new String(Files.readAllBytes(Paths.get(file.getPath())));
                textArea.setText(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile() {
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                Files.write(Paths.get(file.getPath()), textArea.getText().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
package com.example.project_graph;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainAplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        stage.setTitle("Graph");
        Image eelogo = new Image("logo_ee.png");
        stage.getIcons().add(eelogo);
        FXMLLoader fxmlLoader = new FXMLLoader(MainAplication.class.getResource("Main_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            quit(stage);
        });

    }

    public static void main(String[] args) {
        launch();
    }

    public void quit(Stage stage) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Window");
        alert.setHeaderText("Za raz nastąpi wyjście z aplikacji");
        alert.setContentText("Czy jesteś pewny że chcesz wyjść z aplikacji?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }
}
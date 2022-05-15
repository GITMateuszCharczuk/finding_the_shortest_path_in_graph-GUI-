package com.example.project_graph;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class GenerationGraphPane {

    public static void generationGraphPane(ScrollPane pane, int row, int col, Graph g, ActionEvent e) throws IOException {
        //Robota dla Pana Antoniego też!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!dopisać że sie generuje w tym okienku rozszerzalnym
        //pane.setBackground(new Background(new BackgroundFill(Color.YELLOW,null,null)));
        Parent root;
        Stage stage;
        Scene scene;
        FXMLLoader loader = new FXMLLoader(GenerationGraphPane.class.getResource("Main_view.fxml"));
        root = loader.load();
        Circle circle = new Circle();
        circle.setCenterX(50);
        circle.setCenterY(50);
        //pane.getChildrenUnmodifiable().add(circle);//to trzeba zrobić żeby było getChildren() to będzie można dodać i zlounchować od nowa
        //root.getChildrenUnmodifiable().add();
        stage =  (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

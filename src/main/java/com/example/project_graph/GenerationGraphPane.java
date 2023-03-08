package com.example.project_graph;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class GenerationGraphPane {

    public static void generationGraphPane(ScrollPane pane, Canvas canvas, GraphicsContext gc, int row, int col, Graph g, ActionEvent e) throws IOException {

        int iter_r = 0;
        int iter_c = 0;
        pane.setContent(canvas);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                gc.fillOval(iter_r + 17, iter_c + 17, 10, 10);
                iter_c = iter_c + 17;
            }
            iter_c = 0;
            iter_r = iter_r + 17;
        }
    }
}

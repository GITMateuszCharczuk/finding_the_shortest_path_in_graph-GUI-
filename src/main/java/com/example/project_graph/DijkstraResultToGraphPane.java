package com.example.project_graph;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;

import java.util.Deque;

public class DijkstraResultToGraphPane {

    public static void dijkstraResultToGraphPane(ScrollPane pane, Deque<Integer> answer, Canvas canvas, GraphicsContext gc, int col, int row) {

        int l = answer.size();
        int temp = 0;
        int ownCol = 0;
        int ownRow = 0;
        pane.setContent(canvas);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        for (Integer integer : answer) {
            temp = integer;
            ownCol = (temp / col) * 17;
            ownRow = (temp % col) * 17;
            gc.fillOval(17 + ownRow, 17 + ownCol, 10, 10);
        }
    }
}

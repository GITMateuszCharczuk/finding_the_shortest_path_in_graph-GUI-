package com.example.project_graph;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Deque;
import java.util.IllegalFormatConversionException;
import java.util.IllegalFormatException;
import java.util.ResourceBundle;

import static java.lang.System.exit;


public class MainController {

    @FXML
    private Label stdErr;
    @FXML
    private TextField inpFileTextField;
    @FXML
    private TextField outFileTextField;
    @FXML
    private TextField colTextField;
    @FXML
    private TextField rowTextField;
    @FXML
    private TextField minWageTextField;
    @FXML
    private TextField maxWageTextField;
    @FXML
    private TextField startDijkstraTextField;
    @FXML
    private TextField endDijkstraTextField;
    @FXML
    private TextField sliceTextField;
    @FXML
    private ScrollPane dialogue;
    @FXML
    private ScrollPane generationPane;

    public Parent root;
    Stage stage;
    Scene scene;
    Graph g = null;
    boolean wasBfsChecked=false;
    boolean isBfsGood=false;
    public void launchGenerate(ActionEvent e) throws IOException {
        int col=-2, row=-2;
        double maxWage=-2, minWage=-2;
        try {
            col = colTextField.getText() != "" ?  Integer.parseInt(colTextField.getText()) : -2;
            row = rowTextField.getText() != "" ? Integer.parseInt(rowTextField.getText()) : -2;
            maxWage = maxWageTextField.getText() != "" ? Double.parseDouble(maxWageTextField.getText()) : -2;
            minWage = minWageTextField.getText() != "" ? Double.parseDouble(minWageTextField.getText()) : -2;
        }catch(NumberFormatException err){
            stdErr.setText(stdErr.getText()+"Błąd w konwersji ciągu znaków na liczbę!!!\n");
            dialogue.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
            return;
        }
        if(inpFileTextField.getText() != "")
        {
            System.out.println("Wczytywanie z pliku");
            g = new Graph(inpFileTextField.getText(), stdErr,  dialogue);
            GenerationGraphPane.generationGraphPane(generationPane,row,col,g, e);
            g.printToScreen();
            wasBfsChecked = false;
        }else if(col != -2 && row != -2 && maxWage != -2 && minWage != -2){
            if(col > 0 && row > 0 && maxWage > 0 && minWage >= 0){
                System.out.println(col+" " +row+" " +maxWage+" " +minWage);
                dialogue.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,null,null)));
                stdErr.setText("");
                g = new Graph(row,col,minWage,maxWage);
                g.printToScreen();
                GenerationGraphPane.generationGraphPane(generationPane,row,col,g, e);
                wasBfsChecked = false;
            }else{
                stdErr.setText(stdErr.getText()+"Podana wartość konieczna do generacji grafu jest ujemna\n");
                dialogue.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
                return;
            }
        }else{
            stdErr.setText(stdErr.getText()+"Podano zbyt mało argumentów by wygenerować graf\n");
            dialogue.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
            return;
        }
    }
    public void launchBfs(ActionEvent e){
        if(g != null) {
            stdErr.setText("");
            dialogue.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,null,null)));
            if (Bfs.breadth_first_search(g) == 1) {
                stdErr.setText(stdErr.getText() + "Graf jest spójny\n");
                isBfsGood = true;
            }else {
                stdErr.setText(stdErr.getText() + "Graf jest nie spójny\n");
                isBfsGood = false;
            }
            wasBfsChecked=true;
        }else{
            stdErr.setText(stdErr.getText()+"Graf nie został wygenerowany\n");
            dialogue.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
        }
    }
    public void launchDijkstra(ActionEvent e){
        int startDijk;
        int endDijk;
        try {
            startDijk = startDijkstraTextField.getText() != "" ?  Integer.parseInt(startDijkstraTextField.getText()) : -2;
            endDijk = endDijkstraTextField.getText() != "" ? Integer.parseInt(endDijkstraTextField.getText()) : -2;
        }catch(NumberFormatException err){
            stdErr.setText(stdErr.getText()+"Błąd w konwersji ciągu znaków na liczbę!!!\n");
            dialogue.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
            return;
        }
        if(startDijk == -2 || endDijk == -2 ) {
            stdErr.setText(stdErr.getText() + "Podano zbyt mało argumentów by uruchomić sprawdzenie najkrótszej drogi\n");
            dialogue.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
            return;
        }else if(startDijk < 0 && endDijk < 0 ){
            stdErr.setText(stdErr.getText()+"Podana wartość konieczna do sprawdzenie najkrótszej drogi jest ujemna\n");
            dialogue.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
            return;
        }
        if(g != null) {
            if (wasBfsChecked == true) {
                if (isBfsGood == true) {
                    dialogue.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
                    stdErr.setText("");
                    Dijkstra dijkstraObj = new Dijkstra();
                    Deque<Integer> answer = dijkstraObj.dijkstra(g, startDijk, endDijk);
                    DijkstraResultToGraphPane.dijkstraResultToGraphPane(generationPane, answer);
                    stdErr.setText(stdErr.getText() + "Najkrótsza droga od "+startDijk+" do "+endDijk+" wynosi: " + dijkstraObj.getResult() + "\n[");
                    while (answer.isEmpty() == false)
                        stdErr.setText(stdErr.getText() + answer.removeLast() + (answer.size() != 0 ? "-> " : ""));
                    stdErr.setText(stdErr.getText() + "]");
                } else {
                    stdErr.setText(stdErr.getText() + "Graf jest nie spójny Dijkstra nie wykona się\n");
                    dialogue.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
                }
            } else {
                stdErr.setText(stdErr.getText() + "Spójność grafu nie została sprawdzona, Dijkstra nie wykona się \n");
                dialogue.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
            }
        }else{
            stdErr.setText(stdErr.getText()+"Graf nie został wygenerowany\n");
            dialogue.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
        }
    }
    public void launchGraphSlicing(ActionEvent e){
            //robota dla Antoniego teeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeż
            int howManySlices = -2;
            try {
                howManySlices = sliceTextField.getText() != "" ? Integer.parseInt(sliceTextField.getText()) : -2;
            }catch(NumberFormatException err){
                stdErr.setText(stdErr.getText()+"Błąd w konwersji ciągu znaków na liczbę!!!\n");
                dialogue.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
                return;
            }
            if(howManySlices == -2 ) {
                stdErr.setText(stdErr.getText() + "Podano zbyt mało argumentów by uruchomić cięcie grafu\n");
                dialogue.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
                return;
            }else if(howManySlices <= 2){
                stdErr.setText(stdErr.getText()+"Podana wartość konieczna do ucięcia grafu jest mniejsza od 2\n");
                dialogue.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
                return;
            }
        if(g != null) {
            dialogue.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,null,null)));
            stdErr.setText("");
            stdErr.setText("sdfguiaigaifdug");
            int isGood = GraphSlicing.graph_slice(g, howManySlices);//o tu o ta funkcja kurwi chujem może, nie testowałem
        }else{
            stdErr.setText(stdErr.getText()+"Graf nie został wygenerowany\n");
            dialogue.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
        }
    }
    public void clean(ActionEvent e) throws IOException {
        System.out.println("Czyszczenie");
        g = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main_view.fxml"));
        root = loader.load();
        stage =  (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void printToFile(){
        if(outFileTextField.getText() != "") {
            try {
                if(g != null)
                    g.printToFile(outFileTextField.getText());
                dialogue.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,null,null)));
                stdErr.setText("Zapisywanie przebiegło pomyślnie\n");
            } catch (IOException e){
                stdErr.setText(stdErr.getText()+"Coś poszło źle z zapisaniem do pliku\n");
                dialogue.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
            }
        }else{
            stdErr.setText(stdErr.getText()+"Nie podano nazwy pliku wyjściowego\n");
            dialogue.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
        }
    }


}

module com.example.project_graph {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project_graph to javafx.fxml;
    exports com.example.project_graph;
}
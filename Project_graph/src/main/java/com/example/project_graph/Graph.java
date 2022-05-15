package com.example.project_graph;


import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketOption;
import java.util.ArrayList;
import java.util.Random;

import static com.example.project_graph.GraphFromFile.graphFromFile;
import static com.example.project_graph.GraphGen.graphgen;
import static java.lang.Math.abs;

public class Graph {
    private int row,col,rowcol;
    public Neighbours[] graph;
    public Graph(int row, int col, double minwage, double maxwage){
        this.row = row;
        this.col = col;
        this.rowcol = row * col;
        this.graph = new Neighbours[rowcol];
        graphgen( this, minwage, maxwage);
    }
    public Graph(String fName, Label stdErr, ScrollPane dialogue){
        graphFromFile(fName, this,  stdErr,  dialogue );
    }
    public void addIndx(int indx, Neighbours neighbour){
        graph[indx]  = neighbour;
    }
    public int getRow(){
        return this.row;
    }
    public void setRow(int row){
        this.row = row;
    }
    public int getCol(){
        return this.col;
    }
    public void setCol(int col){
        this.col = col;
    }
    public int getRowcol(){
        return this.rowcol;
    }
    public Neighbours popIndx(int indx){
        return graph[indx];
    }

    public void weightGen(double minwage, double maxwage, int l) {
        Random random = new Random();
        double randomDouble = (Math.abs(random.nextDouble()) % (maxwage-minwage))+minwage;
        this.graph[l].addWage(randomDouble);
    }
    public void printToFile(String fileName) throws IOException {
        int i, j = 0;
        if(this == null)
            return;
            FileWriter writer = new FileWriter(fileName);
            writer.write(String.format("%d %d\n", this.getRow(), this.getCol()));
            for (i = 0; i < this.getCol() * this.getRow(); i++) {
                for (j = 0; j < this.graph[i].getNeighboorsSize(); j++) {
                    writer.write("\t" + this.graph[i].neighbourPopOnIndx(j) + " :" + this.graph[i].wagePopOnIndx(j) + " ");
                }
                writer.write("\n");
            }
            writer.close();
        }

    public void printToScreen() {
        int i, j = 0;
        System.out.format("%d %d\n",this.getRow(), this.getCol());
        for( i = 1; i < this.getCol() * this.getRow(); i++ ){
            for(j = 0; j < this.graph[i].getNeighboorsSize(); j++)
            {
                System.out.format("\t%d :%f ",this.graph[i].neighbourPopOnIndx(j), this.graph[i].wagePopOnIndx(j));
            }
            System.out.println();
        }
    }

}

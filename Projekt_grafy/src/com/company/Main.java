package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;

import static com.company.GraphFromFile.graphFromFile;
import static com.company.GraphGen.graphgen;

public class Main {

    public static void main(String[] args) throws IOException {
        int row = 6,col = 3, startDijk = 0, endDijk = 5;
        double minwage = 1, maxwage = 2;
        String outFile = "";
        String inFile = "mygraph";
        Graph g = new Graph(inFile);
	   // Graph g = new Graph(row,col,minwage,maxwage);//gituwa
//        Dijkstra dijkstraObj = new Dijkstra();
//        if(Bfs.breadth_first_search(g) == 1) //gituwa
//            System.out.println("Graf jest spójny");
//        else
//            System.out.println("Graf jest nie spójny");
//        Deque<Integer> answer = dijkstraObj.dijkstra(g, startDijk, endDijk);
//        System.out.print("Dijkstra result:"+dijkstraObj.getResult() +"\n[");
//        while(answer.isEmpty() == false)
//            System.out.print(answer.removeLast()+" ");
//        System.out.println("]");
        g.printToScreen();
        if(outFile != "")
                g.printToFile(outFile);
        }
    }

package com.example.project_graph;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GraphFromFile {//brakuje 1 linijki grafu i chuj
    static public void graphFromFile(String fileName, Graph g, Label stdErr, ScrollPane dialogue ) {
        g.graph = new Neighbours[g.getRowcol()];
        int l = 0;
        String tmp ;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String Line = reader.readLine();
            String LineColon;
            String[] rcS = Line.split("\\s+");
            if(rcS.length == 2){
                g.setRow(Integer.parseInt(rcS[0]));
                g.setCol(Integer.parseInt(rcS[1]));
                g.graph = new Neighbours[g.getRow()*g.getCol()];
            }else{
                stdErr.setText(stdErr.getText()+"błąd formatu w 1 linijce\n");
                dialogue.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
                return;
                //throw new IOException("błąd formatu w 1 linijce");
            }
            while((Line = reader.readLine()) != null)
            {
                rcS = Line.split("\\s+");//\s+ //" :|\\s* "
                if(rcS[0] == ""){
                    String[] neWrcS = new String[rcS.length-1];
                    System.arraycopy(rcS,1,neWrcS,0,rcS.length-1);
                    rcS = neWrcS;
                }
                if(rcS.length <= 8 && rcS.length % 2 == 0) {
                    g.graph[l] = new Neighbours();
                    for (int i = 0; i < rcS.length; i++) {
                        if ((i % 2 == 1)) {
                            LineColon = rcS[i].substring(1);
//                        System.out.print("(" + LineColon + ")");
                            g.graph[l].addWage(Double.parseDouble(LineColon));
                        } else {
//                        System.out.print("[" + rcS[i] + "]");
                            g.graph[l].addNeighbour(Integer.parseInt(rcS[i]));
                        }
                    }
                    l++;
//                System.out.println();
                }else{
                    stdErr.setText(stdErr.getText()+"Błędny format pliku\n");
                    dialogue.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
                    return;
                }
            }
            dialogue.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,null,null)));
            stdErr.setText("Wygenerowanie grafu z pliku wykonano poprawnie");

        }catch (FileNotFoundException e){
            stdErr.setText(stdErr.getText()+"Plik nie został odnaleziony\n");
            dialogue.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
            return;
        }catch (IOException e){
            stdErr.setText(stdErr.getText()+"Wczytywanie plikku nie powiodło się\n");
            dialogue.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
            return;
        }catch (NumberFormatException e){
            stdErr.setText(stdErr.getText()+"Błąd w konwersji ciągu znaków na liczbę\n");
            dialogue.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
            return;
        }
    }
}

package com.company;

public class GraphGen {
    static public void graphgen(Graph g, double minwage, double maxwage){
        int i, k, j, l = 0;
        for (i = 0; i < g.getRow(); i++)
        {
            for(j = 0; j < g.getCol(); j++)
            {
                g.graph[l] = new Neighbours();
                neighbourGen(i ,j ,l ,minwage ,maxwage ,g);
                l++;
            }
        }
    }
    private static void neighbourGen(int i, int j, int l, double min, double max, Graph g) {
        if((i+1) >= 0 && j >= 0 && (i+1) < g.getRow() && j < g.getCol())
        {
            g.graph[l].addNeighbour((g.getCol() * i) + j + g.getCol());
            g.weightGen(min,max,l);
        }
        if((i-1) >= 0 && j >= 0 && (i-1) < g.getRow() && j < g.getCol() )
        {
            g.graph[l].addNeighbour((g.getCol() * i) + j - g.getCol());
            g.weightGen(min,max,l);
        }
        if(i >= 0 && (j+1) >= 0 && i < g.getRow() && (j+1) < g.getCol()  )
        {
            g.graph[l].addNeighbour((g.getCol() * i) + j + 1);
            g.weightGen(min,max,l);
        }
        if(i >= 0 && (j-1) >= 0 && i < g.getRow() && (j-1) < g.getCol() )
        {
            g.graph[l].addNeighbour((g.getCol() * i) + j - 1);
            g.weightGen(min,max,l);
        }
    }
}


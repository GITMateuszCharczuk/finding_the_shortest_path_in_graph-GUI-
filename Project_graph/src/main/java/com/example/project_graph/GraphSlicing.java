package com.example.project_graph;

import java.util.ArrayDeque;
import java.util.Deque;

public class GraphSlicing {
    public static int graph_slice(Graph g, int n)
    {
        int r = g.getRow();
        int c = g.getCol();
        Dijkstra dijkstraObj = new Dijkstra();
        int item;
        Deque<Integer> path;
        int  i,j,k, start;
        int stop = (r * c) - 1;
        if(n == 0){
            throw new RuntimeException("Graf nie może zostać podzielony na 0 części!!!");
        }
        for(k = 0; k < n-1; k++)
        {
            if((2 * (k+1)) < c){
                start = (k * 2);
            }
            else{
                return 1;
            }
            path = dijkstraObj.dijkstra(g, start, stop);
            for(i = 0; path.isEmpty() == false  ; i++)
            {
                item = path.removeLast();
                for(j = 0; (j < 4) && (j < g.graph[item].getNeighboorsSize()) ; j++)
                {
                    if((item-1) == g.graph[item].neighbourPopOnIndx(j))
                    {
                        g.graph[item].neighbourRemoveOnIndx(j);
                        g.graph[item].wageRemoveOnIndx(j);
                    }
                    if((item+c) == g.graph[item].neighbourPopOnIndx(j))
                    {
                        g.graph[item].neighbourRemoveOnIndx(j);
                        g.graph[item].wageRemoveOnIndx(j);
                    }
                }
            }
        }
        return 0;
    }
}

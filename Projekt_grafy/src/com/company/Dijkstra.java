package com.company;

import java.util.*;


public class Dijkstra {

    final double INF = 100000.0;
    private double result;
        public Deque<Integer> dijkstra(Graph g, int start, int end){
        Deque<Integer> answer = new ArrayDeque<>();
        int row = g.getRow();
        int col = g.getCol();
        int  pom, next = 0, iterator = 0, n = row * col, i, k, j ;
        double distance_min = INF;
        int [] visits = new int[n];
        int [] bef = new int[n];
        double [] distances = new double[n];
        double [][] costs = new double [n][n];
        //double** costs = malloc(sizeof(double*) * n);
        for(  i = 0; i < n ; i++){
            for( j = 0; j < n; j++)
                costs[i][j] = INF;
            for( k = 0; k < g.graph[i].getNeighboorsSize(); k++)
                costs[i][g.graph[i].neighbourPopOnIndx(k)] = g.graph[i].wagePopOnIndx(k);
        }
        for(  i = 0; i < n; i++){
            visits[i] = 0;
            distances[i] = costs[start][i];
            bef[i] = start;
        }
        distances[start] = 0;
        visits[start] = 0;
        iterator = 1;
        while(iterator < n - 1){
            distance_min = INF;
            for( i = 0; i < n; i++)
                if((distances[i] < distance_min) && visits[i] == 0){//tu possible rozjebanie
                    distance_min = distances[i];
                    next = i;
                }
            visits[next] = 1;
            for( j = 0; j < n; j++)
                if(visits[j] == 0)
                    if(distance_min + costs[next][j] < distances[j]){
                        distances[j] = distance_min + costs[next][j];
                        bef[j] = next;
                    }
            iterator++;
        }
	    this.result = distances[end];
        pom = end;
        answer.add(end);
        do {
            pom = bef[pom];
            answer.add(pom);
        } while(pom != start);
        return answer;
    }
    public double getResult(){
            return this.result;
    }


}

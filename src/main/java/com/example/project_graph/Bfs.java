package com.example.project_graph;

import java.util.LinkedList;
import java.util.Queue;

public class Bfs {
    public static int breadth_first_search(Graph g) {

        int row = g.getRow();
        int col = g.getCol();
        int rc = row * col;
        int i, item;

        Queue<Integer> que = new LinkedList<>();
        int[] visited = new int[rc];
        visited[0] = 1;

        for (i = 0; i < g.graph[0].getNeighboorsSize(); i++) {
            que.add(g.graph[0].neighbourPopOnIndx(i));
        }

        while (!que.isEmpty()) {
            item = que.remove();
            if (visited[item] != 1) {
                visited[item] = 1;

                for (i = 0; i < g.graph[item].getNeighboorsSize(); i++) {
                    if (visited[g.graph[item].neighbourPopOnIndx(i)] == 0)
                        que.add(g.graph[item].neighbourPopOnIndx(i));
                }
            }
        }

        for (i = 0; i < rc; i++) {
            if (visited[i] == 0) {
                return 0;
            }
        }

        return 1;
    }
}

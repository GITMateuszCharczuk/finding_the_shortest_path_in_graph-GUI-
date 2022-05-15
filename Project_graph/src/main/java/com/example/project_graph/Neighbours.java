package com.example.project_graph;

import java.util.ArrayList;

public class Neighbours {
    private ArrayList<Integer> neighbour = new ArrayList<Integer>(4);
    private ArrayList<Double> wage = new ArrayList<Double>(4);
    public Neighbours(){;}
    public void addNeighbour(int item){
        if(neighbour.size() <= 4)
            neighbour.add(item);
        else{
            throw new RuntimeException("Neighbour overload!!!");
        }
    }
    public void addNeighbourOnIndx(int item, int indx){
        if(indx <= 4)
            neighbour.add(indx, item);
        else{
            throw new RuntimeException("Neighbour overload!!!");
        }
    }
    public int getNeighboorsSize(){
        return this.neighbour.size();
    }
    public void addWage(double item){
        if(wage.size() <= 4)
            wage.add(item);
        else{
            throw new RuntimeException("Wage overload!!!");
        }
    }
    public void addWageOnIndx(double item, int indx){
        if(indx <= 4)
            wage.add(indx, item);
        else{
            throw new RuntimeException("Wage overload!!!");
        }
    }
    public int neighbourPopOnIndx(int indx){
        return neighbour.get(indx);
    }
    public int neighbourRemoveOnIndx(int indx){
        return neighbour.remove(indx);
    }
    public double wageRemoveOnIndx(int indx){
        return wage.remove(indx);
    }
    public double wagePopOnIndx(int indx){
        return wage.get(indx);
    }
}

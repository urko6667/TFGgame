package com.example.urko.gameproject.IA;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar {
    // cost for diagonal and vertical/ horizontal moves
    public static final int DIAGONAL_COST = 14;
    public static final int V_H_COST = 10;
    // cells for our grid
    private Cell[][] grid;
    // we define a piority queue for open cells
    // open cells : the set of nodes to be evaluated
    // we put cells with lowest cost in first
    private PriorityQueue<Cell> openCells;
    // closed cells : the set of nodes already evaluated
    private boolean[][] closedCells;
    // start cell
    private int startI, startJ;
    // end cell
    private int endI, endJ;
    // array direcciones
    public int[] direccion;
    public int width, height;

    public AStar(int width, int height,int[][] blocks) {
        this.width=width;
        this.height=height;
        direccion = new int[30];
        grid = new Cell[width][height];
        closedCells = new boolean[width][height];
        openCells = new PriorityQueue<Cell>((Cell c1, Cell c2) -> {
            return c1.finalCost < c2.finalCost ? -1 : c1.finalCost > c2.finalCost ? 1 : 0;
        });

        /*startCell(si, sj);
        endCell(ei, ej);*/

        // init heuristic
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Cell(i, j);
                grid[i][j].heuristicCost = Math.abs(i - endI) + Math.abs(j - endJ);
                grid[i][j].solution = false;
            }
        }

        grid[startI][startJ].finalCost = 0;
        // we put the blocks on the grid
        for (int i = 0; i < blocks.length; i++) {
            addBlockOnCell(blocks[i][0], blocks[i][1]);
        }
    }

    public void addBlockOnCell(int i, int j) {
        grid[i][j] = null;
    }

    public void startCell(int i, int j) {
        startI = i;
        startJ = j;
    }

    public void endCell(int i, int j) {
        endI = i;
        endJ = j;
    }

    public void updateCostIfNeeded(Cell current, Cell t, int cost) {
        if (t == null || closedCells[t.i][t.j])
            return;
        int tFinalCost = t.heuristicCost + cost;
        boolean isOpen = openCells.contains(t);

        if (!isOpen || tFinalCost < t.finalCost) {
            t.finalCost = tFinalCost;
            t.parent = current;
            if (!isOpen) {
                openCells.add(t);
            }
        }

    }
    //empieza desde el inicio, recorre las celdas adllacentes actualizando su finalcost, que es la suma del
    // coste euristico mas el coste del movimiento desde la zona previa
    public void process() {
        // we add the start location to open list
        openCells.add(grid[startI][startJ]);
        Cell current;
        while (true) {
            // obtiene el primer elemento y lo elimina
            current = openCells.poll();
            //si no quedan elementos hace break
            if (current == null) {
                break;
            }
            //cierra la celda
            closedCells[current.i][current.j] = true;

            //si estamos en el final termina
            if (current.equals(grid[endI][endJ]))
                return;

            Cell t;

            if (current.i - 1 >= 0) {
                t = grid[current.i - 1][current.j];
                updateCostIfNeeded(current, t, current.finalCost + V_H_COST);
				/*if (current.j - 1 >= 0) {
					t = grid[current.i - 1][current.j - 1];
					updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
				}

				if (current.j + 1 < grid[0].length) {
					t = grid[current.i - 1][current.j + 1];
					updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
				}*/
            }

            if (current.j - 1 >= 0) {
                t = grid[current.i][current.j - 1];
                updateCostIfNeeded(current, t, current.finalCost + V_H_COST);

            }

            if (current.j + 1 < grid[0].length) {
                t = grid[current.i][current.j + 1];
                updateCostIfNeeded(current, t, current.finalCost + V_H_COST);
            }
            if (current.i + 1 < grid.length) {
                t = grid[current.i + 1][current.j];
                updateCostIfNeeded(current, t, current.finalCost + V_H_COST);

				/*if (current.j - 1 >= 0) {
					t = grid[current.i + 1][current.j - 1];
					updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
				}
				if (current.j + 1 < grid[0].length) {
					t = grid[current.i + 1][current.j + 1];
					updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
				}*/
            }
        }
    }


    public void display() {
        System.out.println("Grid: ");

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == startI && j == startJ) {
                    System.out.print("SO "); // source cell
                } else if (i == endI && j == endJ) {
                    System.out.print("DE "); // destination cell
                } else if (grid[i][j] != null) {
                    System.out.printf("%-3d", 0);
                } else {
                    System.out.print("BL ");// block cell
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void displayScores() {
        System.out.println("\nScores for cells :");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != null) {
                    System.out.printf("%-3d", grid[i][j].finalCost);
                } else {
                    System.out.print("BL ");// block cell
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void displaySolution() {
        if (closedCells[endI][endJ]) {
            System.out.print("path: ");
            Cell current = grid[endI][endJ];
            System.out.print(current);
            grid[current.i][current.j].solution = true;

            while (current.parent != null) {
                if(current.parent.i==current.i) {
                    if(current.parent.j<current.j) {
                        direccionar(4);
                    }else {
                        direccionar(2);
                    }
                }else {
                    if(current.parent.i<current.i) {
                        direccionar(1);
                    }else {
                        direccionar(3);
                    }
                }


                System.out.print(" ->" + current.parent);

                grid[current.parent.i][current.parent.j].solution = true;
                current = current.parent;
            }
            System.out.println("\n");

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (i == startI && j == startJ) {
                        System.out.print("SO "); // source cell
                    } else if (i == endI && j == endJ) {
                        System.out.print("DE "); // destination cell
                    } else if (grid[i][j] != null) {
                        System.out.printf("%-3s", grid[i][j].solution ? "x" : "0");
                    } else {
                        System.out.print("BL ");// block cell
                    }
                }
                System.out.println();
            }
            System.out.println();
        } else {
            System.out.println("No possible path");
        }
    }
    public void direccionar(int d) {
        for(int i=0;i<direccion.length; i++) {
            if(direccion[i]==0) {
                direccion[i]=d;
                //i=direccion.length;
                return;
            }
        }
    }
    public void displayRoute() {
        System.out.println();
        for(int i=0;i<direccion.length; i++) {
            if(direccion[i]!=0) {
                System.out.print(direccion[i]+" > ");

            }
        }
    }
    /*public static void main(String[] args) {
        AStar aStar = new AStar(5, 5, 0, 0, 3, 2,
                new int[][] { { 0, 4 }, { 2, 2 }, { 3, 1 }, { 3, 3 }, { 2, 1 }, { 2, 3 } });
        aStar.display();
        aStar.process(); // aply A* algorithm
        //aStar.displayScores();// dislplay scores
        aStar.displaySolution();// display solution path
        aStar.displayRoute();
    }*/
    public int[] getpath( int ej, int ei,int sj, int si){
        direccion = new int[30];
        grid = new Cell[width][height];
        closedCells = new boolean[width][height];
        openCells = new PriorityQueue<Cell>((Cell c1, Cell c2) -> {
            return c1.finalCost < c2.finalCost ? -1 : c1.finalCost > c2.finalCost ? 1 : 0;
        });

        /*startCell(si, sj);
        endCell(ei, ej);*/

        // init heuristic
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Cell(i, j);
                grid[i][j].heuristicCost = Math.abs(i - endI) + Math.abs(j - endJ);
                grid[i][j].solution = false;
            }
        }

        grid[startI][startJ].finalCost = 0;
        // we put the blocks on the grid

        startCell(si, sj);
        endCell(ei, ej );
        process();
        displaySolution();
        return direccion;
    }
}

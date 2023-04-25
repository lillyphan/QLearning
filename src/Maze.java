/**
 * Maze - class that generates a maze using a 2d integer array with depth-first search
 * Author: Lilly Phan
 * Date (last edited): 04/25/2023
 **/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Maze {
    //intialization and declarations
    private int[] directionsY = {-2, 2, 0, 0}; //can either move up, down
    private int[] directionsX = {0, 0, -2, 2}; //left or right
    private int[][] maze;
    private int width;
    private int length;
    private Random r = new Random();

    //public constructor
    public Maze(int width, int length, int[] start, int[] end){
        this.maze = new int[width][length]; //create the maze
        this.width = width;
        this.length = length;

        //fill the maze with wall value
        for (int i = 0; i < length; i++){
            for (int j = 0; j < width; j++){
                maze[i][j] = 0;
            }
        }

        gen(start[1], start[0]); //generate the maze using depth-first search

        maze[end[1]][end[0]] = 4; //set the end point to a different value

        //write the maze to a text file for q learning
        try {
            FileWriter fw = new FileWriter("maze.txt");
            for (int i = 0; i < length; i++){
                for (int j = 0; j < width; j++){
                    switch (maze[i][j]) {
                        case 1 -> fw.write('0' + " ");
                        case 0 -> fw.write('X' + " ");
                        case 4 -> fw.write('F' + " ");
                        default -> {
                        }
                    }
                }
                fw.write("\n");
            }
            fw.close();
        } catch (java.io.IOException ignored){
        }


        //printing maze for testing purposes
//        for (int a = 0; a < length; a++){
//            for (int b = 0; b < width; b++){
//                System.out.print(maze[a][b] + " ");
//            }
//            System.out.println("\n");
//        }

    }

    //private recursive helper function that uses depth-first search to generate a maze
    private void gen(int currX, int currY){
        Integer[] possibleDirections = {0, 1, 2, 3}; //AKA the indices of the x and y directions
        List<Integer> list = Arrays.asList(possibleDirections);
        Collections.shuffle(list); //randomize the list to make sure the direction being chosen is random
        list.toArray(possibleDirections);

        for(int i = 0; i < possibleDirections.length; i++){ //loop through the possible directions
            int nextX = currX + directionsX[possibleDirections[i]];
            int nextY = currY + directionsY[possibleDirections[i]];

            //check if the next direction is not viable (already visited position or out of array bounds)
            if (nextY < 0 || nextY >= length || nextX < 0 || nextX >= width || maze[nextX][nextY] != 0){
                continue; //don't carve out the maze if any are true
            }

            // only do if next x and y is a valid neighbor two tiles over
            maze[currX + directionsX[possibleDirections[i]] / 2][currY + directionsY[possibleDirections[i]] / 2] = 1;
            maze[nextX][nextY] = 1; //mark nextState as 1 or a viable position

            gen(nextX, nextY); //recursive call to gen() with the next state
        }
    }

    //returns the maze in the form of an integer array
    public int[][] getMaze(){
        return this.maze;
    }
}

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Maze {
    private int[] directionsY = {-2, 2, 0, 0}; //can either move up, down
    private int[] directionsX = {0, 0, -2, 2}; //left or right
    private int[][] maze;
    private int width;
    private int length;
    private Random r = new Random();

    public Maze(int width, int length, int[] start, int[] end){
        this.maze = new int[width][length];
        this.width = width;
        this.length = length;

        for (int i = 0; i < length; i++){
            for (int j = 0; j < width; j++){
                maze[i][j] = 0;
            }
        }

        gen(start[1], start[0]);

        maze[end[1]][end[0]] = 4;

        try {
//            File newFile = new File("maze.txt");
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

    private void gen(int currX, int currY){
        Integer[] possibleDirections = {0, 1, 2, 3}; //AKA the indices of the x and y directions
        List<Integer> list = Arrays.asList(possibleDirections);
        Collections.shuffle(list);
        list.toArray(possibleDirections);

        for(int i = 0; i < possibleDirections.length; i++){
            int nextX = currX + directionsX[possibleDirections[i]];
            int nextY = currY + directionsY[possibleDirections[i]];

            if (nextY < 0 || nextY >= length || nextX < 0 || nextX >= width || maze[nextX][nextY] != 0){
                continue;
            }

            // only do if next x and y is a valid neighbor two tiles over
            maze[currX + directionsX[possibleDirections[i]] / 2][currY + directionsY[possibleDirections[i]] / 2] = 1;
            maze[nextX][nextY] = 1;

            gen(nextX, nextY);
        }
    }

    public int[][] getMaze(){
        return this.maze;
    }
}

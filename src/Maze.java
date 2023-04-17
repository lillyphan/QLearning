import java.util.Random;

public class Maze {
    private int[] directionsY = {-1, 1, 0, 0}; //can either move up, down
    private int[] directionsX = {0, 0, -1, 1}; //left or right
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

        maze[start[1]][start[0]] = 3;
        maze[end[1]][end[0]] = 4;

        //call to a recursive search function HERE

        for (int i = 0; i < length; i++){
            for (int j = 0; j < width; j++){
                System.out.println(maze[i][j] + " ");
            }
            System.out.println("\n");
        }
    }
}

import java.util.*;

public class QLearning {
    public static void main(String[] args) {
        Maze m;
        double[][] R;
        double[][] Q;

        m = new Maze(15, 15, new int[]{0, 0}, new int[]{14, 14});
        R = new double[15][15];
        Q = new double[15][15];
    }
}

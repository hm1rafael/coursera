import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
        //write you code here
        int[][] m = new int[w.length + 1][W+1];
        for (int i = 1; i <= w.length; i++ ) {
            int weight = w[i-1];
            for (int j = 1; j <= W; j++) {
                if (j < weight) {
                    m[i][j] = m[i-1][j];
                } else {
                    int valueA = m[i-1][j-weight] + weight;
                    int valueB = m[i-1][j];
                    if (valueA > valueB) {
                        m[i][j] = valueA;
                    } else {
                        m[i][j] = valueB;
                    }
                }
            }
        }
        return m[w.length][W];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}


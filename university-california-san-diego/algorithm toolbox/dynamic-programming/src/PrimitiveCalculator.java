import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        long[] array = new long[n+1];

        for (int i = 1; i < array.length;i++) {
            array[i] = array[i-1] + 1;
            if (i % 2 == 0) {
                array[i] = array[i / 2] + 1 > array[i] ? array[i] : array[i / 2] + 1;
            }
            if (i % 3 == 0) {
                array[i] = array[i / 3] + 1 > array[i] ? array[i] : array[i / 3] + 1;
            }
        }

        List<Integer> sequence = new ArrayList<>();
        while (n >= 1) {
            sequence.add(n);
            if (array[n - 1] == array[n] - 1) {
                n = n - 1;
            } else if (n % 2 == 0 && (array[n / 2] == array[n] - 1)) {
                n = n / 2;
            } else if (n % 3 == 0 && (array[n / 3] == array[n] - 1)) {
                n = n / 3;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}


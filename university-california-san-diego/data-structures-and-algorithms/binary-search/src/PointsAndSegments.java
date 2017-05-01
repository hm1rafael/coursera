import javax.swing.text.Segment;
import java.util.*;

public class PointsAndSegments {

    private static Random random = new Random();

    private static int[] partition3(Triple[] a, int l, int r) {
        Triple x = a[l];
        int j = l;
        int k = r;

        for (int i = l + 1; i <= k; i++) {
            int i1 = a[i].compareTo(x);
            if (i1 > 0) {
                Triple t = a[i];
                a[i] = a[k];
                a[k] = t;
                k--;
                i--;
            } else if (i1 < 0) {
                j++;
                Triple t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }

        Triple t = a[l];
        a[l] = a[j];
        a[j] = t;

        return new int[]{j, k};
    }

    private static void randomizedQuickSort(Triple[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        Triple t = a[l];
        a[l] = a[k];
        a[k] = t;
        //use partition3
        //int m = partition2(a, l, r);
        int[] m = partition3(a, l, r);
        randomizedQuickSort(a, l, m[0] - 1);
        randomizedQuickSort(a, m[1] + 1, r);
    }

    private static class Triple implements Comparable<Triple> {

        private Long n;
        private Long label;
        private int index; //just for the points

        @Override
        public int compareTo(Triple triple) {
            int i = n.compareTo(triple.n);
            if (i == 0) {
                return label.compareTo(triple.label);
            }
            return i;
        }
    }

    private static long[] fastCountSegments(Triple[] triplesArray, int m) {
        long[] pointsCount = new long[m];
        long countSegments = 0;
        for (int i = 0; i < triplesArray.length; i++) {
            Triple triple = triplesArray[i];
            if (triple.label == 1) {
                countSegments++;
            } else if (triple.label == 3) {
                countSegments--;
            } else {
                pointsCount[triple.index] = countSegments;
            }
        }
        return pointsCount;
    }

    /*private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {

        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }*/

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        List<Triple> triples = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Triple tripleStart = new Triple();
            tripleStart.n = Long.parseLong(scanner.next());
            tripleStart.label = 1L;
            Triple tripleEnd = new Triple();
            tripleEnd.n = Long.parseLong(scanner.next());
            tripleEnd.label   = 3L;
            triples.add(tripleStart);
            triples.add(tripleEnd);
        }
        for (int i = 0; i < m; i++) {
            Triple triplePoint = new Triple();
            triplePoint.n = Long.parseLong(scanner.next());
            triplePoint.label = 2L;
            triplePoint.index = i;
            triples.add(triplePoint);
        }

        Triple[] triplesArray = triples.toArray(new Triple[n * 2 + m]);
        randomizedQuickSort(triplesArray, 0, triples.size()-1);
        long[] ints = fastCountSegments(triplesArray, m);
        for (int i = 0; i < ints.length; i++) {
            long a = ints[i];
            System.out.print(a + " ");
        }
        //use fastCountSegments
        //int[] cnt = naiveCountSegments(starts, ends, points);
        /*for (int x : cnt) {
            System.out.print(x + " ");
        }*/
    }
}


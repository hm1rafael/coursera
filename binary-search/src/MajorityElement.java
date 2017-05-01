import java.util.*;
import java.io.*;

public class MajorityElement {
    private static int getMajorityElement(long[] a, int left, int right) {
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            if (map.containsKey(a[i])) {
                Long aLong = map.get(a[i]);
                map.put(a[i], ++aLong);
            } else {
                map.put(a[i], 1L);
            }
        }
        int half = a.length / 2;
        Collection<Long> values = map.values();
        for (Iterator<Long> iterator = values.iterator(); iterator.hasNext(); ) {
            if (iterator.next() > half) {
                return 1;
            }
        }
        return 0;

    }

    /*static void quickSort(long[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int partition = partition(a, left, right);
        quickSort(a, left, partition - 1);
        quickSort(a, partition + 1, right);
    }

    private static int partition(long[] a, int left, int right) {
        int randomPos = new Random().nextInt(right - left) + left;
        long aux = a[randomPos];
        a[randomPos] = a[left];
        a[left] = aux;
        int j = left;

        for (int i = left + 1; i <= right; i++) {
            long l = a[i];
            long l1 = a[left];
            if (l <= l1) { //if is smaller than the pivot
                j++;
                long swap = a[i];
                a[i] = a[j];
                a[j] = swap;
            }
        }

        if (j > left) {
            aux = a[j];
            a[j] = a[left];
            a[left] = aux;
        }

        return j;
    }*/

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int majorityElement = getMajorityElement(a, 0, a.length - 1);
        System.out.println(majorityElement);
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}


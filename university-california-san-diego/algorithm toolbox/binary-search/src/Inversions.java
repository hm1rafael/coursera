import java.util.*;

public class Inversions {

    private static long getNumberOfInversions(long[] a, long[] b, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left + 1) {
            return numberOfInversions;
        }

        int ave = (left + right) / 2;
        numberOfInversions += getNumberOfInversions(a, b, left, ave);
        numberOfInversions += getNumberOfInversions(a, b, ave, right);

        int z = left;
        int i = left;
        int j = ave;
        while (i < ave && j < right) {

            long c = a[i];
            long d = a[j];

            if (c <= d) {
                b[z] = c;
                i++;
                z++;
            } else {
                b[z] = d;
                numberOfInversions = numberOfInversions + (ave - i);
                j++;
                z++;
            }

        }

        if (i < ave) {
            for (;i < ave;i++) {
                b[z] = a[i];
                z++;
            }
        } else if (j < right) {
            for (;j < right;j++) {
                b[z] = a[j];
                z++;
            }
        }

        for (int k = left; k < right; k++) {
            a[k] = b[k];
        }

        return numberOfInversions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        long[] b = new long[n];
        System.out.println(getNumberOfInversions(a, b, 0, a.length));
    }
}


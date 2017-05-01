import org.omg.PortableServer.POA;

import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Closest {

    private static Random random = new Random();

    static class Point implements Comparable<Point> {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        double dist(Point point) {
            return Math.sqrt(Math.pow(this.x - point.x, 2) + Math.pow(this.y - point.y, 2));
        }

        @Override
        public int compareTo(Point o) {
            return o.y == y ? Long.signum(x - o.x) : Long.signum(y - o.y);
        }
    }

    private static int[] partition3X(Point[] a, int l, int r) {
        long x = a[l].x;
        int j = l;
        int k = r;

        for (int i = l + 1; i <= k; i++) {
            if (a[i].x > x) {
                Point t = a[i];
                a[i] = a[k];
                a[k] = t;
                k--;
                i--;
            } else if (a[i].x < x) {
                j++;
                Point t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }

        Point t = a[l];
        a[l] = a[j];
        a[j] = t;

        int[] m = {j, k};
        return m;
    }

    private static void randomizedQuickSortX(Point[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        Point t = a[l];
        a[l] = a[k];
        a[k] = t;
        int[] m = partition3X(a, l, r);
        randomizedQuickSortX(a, l, m[0] - 1);
        randomizedQuickSortX(a, m[1] + 1, r);
    }

    private static int[] partition3Y(Point[] a, int l, int r) {
        long x = a[l].y;
        int j = l;
        int k = r;

        for (int i = l + 1; i <= k; i++) {
            if (a[i].y > x) {
                Point t = a[i];
                a[i] = a[k];
                a[k] = t;
                k--;
                i--;
            } else if (a[i].y < x) {
                j++;
                Point t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }

        Point t = a[l];
        a[l] = a[j];
        a[j] = t;

        int[] m = {j, k};
        return m;
    }

    private static void randomizedQuickSortY(Point[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        Point t = a[l];
        a[l] = a[k];
        a[k] = t;
        int[] m = partition3Y(a, l, r);
        randomizedQuickSortY(a, l, m[0] - 1);
        randomizedQuickSortY(a, m[1] + 1, r);
    }

    static double stripTheClosest(Point[] points, double min) {
        randomizedQuickSortY(points, 0, points.length-1);
        for (int i = 0; i < points.length; i++) {
            Point point = points[i];
            for(int j = i + 1; j < points.length;j++) {
                double dist = point.dist(points[j]);
                if (dist < min) {
                    min = dist;
                }
            }
        }
        return min;

    }

    static double bruteForce(Point[] points) {
        double ans = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.length; i++) {
            Point pointI = points[i];
            for (int j = i + 1; j < points.length; j++) {
                double dist = pointI.dist(points[j]);
                if (dist < ans) {
                    ans = dist;
                }
            }
        }
        return ans;
    }

    static double minimalDistance(Point[] points, int left, int right) {
        if (right - left <= 3) {
            return bruteForce(points);
        }

        int mid = ((right - left) / 2) + left;
        Point midPoint = points[mid];

        double distanceLeft = minimalDistance(points, left, mid);
        double distanceRight = minimalDistance(points, mid+1, right);

        double minimal = distanceLeft > distanceRight ? distanceRight : distanceLeft;

        List<Point> closePoints = new ArrayList<>();
        for (int i = left; i < right; i++) {
            if (Math.abs(points[i].x - midPoint.x) < minimal) {
                closePoints.add(points[i]);
            }

        }
        double v = stripTheClosest(closePoints.toArray(new Point[closePoints.size()]), minimal);
        return minimal > v ? v : minimal;
    }

    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            Point point = new Point(nextInt(), nextInt());
            points[i] = point;
        }
        randomizedQuickSortX(points, 0, points.length - 1);
        System.out.println(minimalDistance(points, 0, points.length - 1));
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}

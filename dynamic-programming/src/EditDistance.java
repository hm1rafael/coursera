import java.util.*;

class EditDistance {

    public static int EditDistance(String s, String t) {
        int[][] m = new int[s.length() + 1][t.length() + 1];

        for (int i = 0; i < s.length() + 1; i++) {
            m[i][0] = i;
        }
        for (int i = 0; i < t.length() + 1; i++) {
            m[0][i] = i;
        }

        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < t.length() + 1; j++) {
                int a = m[i - 1][j] + 1;
                int b = m[i][j - 1] + 1;
                int c = m[i - 1][j - 1];
                if (s.charAt(i - 1) != t.charAt(j - 1)) {
                    c++;
                }
                int aux = a;
                if (b < aux) {
                    aux = b;
                }
                if (c < aux) {
                    aux = c;
                }
                m[i][j] = aux;
            }
        }

        return m[s.length()][t.length()];
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        String s = scan.next();
        String t = scan.next();

        System.out.println(EditDistance(s, t));
    }

}

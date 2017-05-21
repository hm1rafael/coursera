import java.io.*;
import java.util.*;

public class MergingTables {
    private final InputReader reader;
    private final OutputWriter writer;

    public MergingTables(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        OutputWriter writer = new OutputWriter(System.out);
        new MergingTables(reader, writer).run();
        writer.writer.flush();
    }

    class Table {
        Table parent;
        int rank;
        int numberOfRows;
        int index;

        Table(int numberOfRows, int i) {
            this.numberOfRows = numberOfRows;
            rank = 0;
            parent = this;
            index = i;
        }

        Table getParent() {
            List<Table> parentsToBeUpdated = new ArrayList<>();
            Table root = this;
            while (root != root.parent) {
                parentsToBeUpdated.add(root);
                root = root.parent;
            }
            for (int i = 0; i < parentsToBeUpdated.size(); i++) {
                Table table =  parentsToBeUpdated.get(i);
                table.parent = root;
            }
            return parent;
        }
    }

    int maximumNumberOfRows = -1;

    void merge(Table destination, Table source) {
        Table realDestination = destination.getParent();
        Table realSource = source.getParent();
        if (realDestination == realSource) {
            return;
        }
        if (realSource.rank >= realDestination.rank) {
            realSource.parent = realDestination;
        } else {
            realDestination.parent = realSource;
            if (realDestination.rank == realSource.rank) {
                realSource.rank++;
            }
        }
        realDestination.numberOfRows += realSource.numberOfRows;
        realSource.numberOfRows = 0;
        if (realDestination.numberOfRows > this.maximumNumberOfRows) {
            this.maximumNumberOfRows = realDestination.numberOfRows;
        }
    }


    public void run() {
        int n = reader.nextInt();
        int m = reader.nextInt();
        Table[] tables = new Table[n];
        for (int i = 0; i < n; i++) {
            int numberOfRows = reader.nextInt();
            tables[i] = new Table(numberOfRows, i + 1);
            maximumNumberOfRows = Math.max(maximumNumberOfRows, numberOfRows);
        }
        for (int i = 0; i < m; i++) {
            int destination = reader.nextInt() - 1;
            int source = reader.nextInt() - 1;
            merge(tables[destination], tables[source]);
            writer.printf("%d\n", maximumNumberOfRows);
        }
    }


    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class OutputWriter {
        public PrintWriter writer;

        OutputWriter(OutputStream stream) {
            writer = new PrintWriter(stream);
        }

        public void printf(String format, Object... args) {
            writer.print(String.format(Locale.ENGLISH, format, args));
        }
    }
}

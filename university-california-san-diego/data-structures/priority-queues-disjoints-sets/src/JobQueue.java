import java.io.*;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    class ThreadWorker {

        int index;
        long time;

        ThreadWorker(int index) {
            this.index = index;
        }

        boolean morePriority(ThreadWorker threadWorker) {
            if (time < threadWorker.time) {
                return true;
            } else if (time > threadWorker.time) {
                return false;
            }
            return index < threadWorker.index;
        }

    }

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        ThreadWorker[] threadWorkers = new ThreadWorker[numWorkers];
        for (int i = 0; i < numWorkers; i++) {
            threadWorkers[i] = new ThreadWorker(i);
        }
        for (int i = 0; i < jobs.length; i++) {
            ThreadWorker threadWorker = threadWorkers[0];
            int jobTime = jobs[i];

            assignedWorker[i] = threadWorker.index;
            startTime[i] = threadWorker.time;

            threadWorker.time += jobTime;

            siftDown(0, threadWorkers);
        }

    }

    public void siftDown(int i, ThreadWorker[] data) {
        int maxIndex = i;
        int leftIndex = (2 * i) + 1;
        if (leftIndex < data.length && data[leftIndex].morePriority(data[maxIndex])) {
            maxIndex = leftIndex;
        }
        int r = (2 * i) + 2;
        if (r < data.length && data[r].morePriority(data[maxIndex])) {
            maxIndex = r;
        }
        if (i != maxIndex) {
            ThreadWorker aux = data[i];
            data[i] = data[maxIndex];
            data[maxIndex] = aux;
            siftDown(maxIndex, data);
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

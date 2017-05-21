import java.util.*;
import java.io.*;

public class TreeHeight {

    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    class Node {

        Node(int value) {
            this.value = value;
        }

        int value;
        List<Node> childs = new ArrayList<>();

    }

    class Tree {

        Node rootNode;

        void setRootNode(Node node) {
            this.rootNode = node;
        }

        public int height() {
            List<Node> nodes = new ArrayList<>();
            nodes.add(rootNode);
            int h = 0;
            while (!nodes.isEmpty()) {
                Iterator<Node> nodesIterator = nodes.iterator();
                List<Node> auxNodes = new ArrayList<>();
                while (nodesIterator.hasNext()) {
                    Node node = nodesIterator.next();
                    auxNodes.addAll(node.childs);
                }
                h++;
                nodes.clear();
                nodes.addAll(auxNodes);
            }
            return h;
        }

    }

    public class TreeHeightCalculator {

        int n;
        Tree tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            this.tree = new Tree();
            Node[] nodes = new Node[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = new Node(i);
            }
            Node rootNode = null;
            for (int i = 0; i < nodes.length; i++) {
                int parentIndex = in.nextInt();
                if (parentIndex == -1) {
                    rootNode = nodes[i];
                } else {
                    nodes[parentIndex].childs.add(nodes[i]);
                }
            }

            tree.setRootNode(rootNode);
        }

        int computeHeight() {
            return tree.height();
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new TreeHeight().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        TreeHeightCalculator tree = new TreeHeightCalculator();
        tree.read();
        System.out.println(tree.computeHeight());
    }
}

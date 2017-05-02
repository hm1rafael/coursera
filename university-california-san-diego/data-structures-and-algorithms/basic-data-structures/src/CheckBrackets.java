import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {

    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class CheckBrackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        Stack<Bracket> openingBracketsStack = new Stack<Bracket>();
        boolean matched = true;
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                openingBracketsStack.push(new Bracket(next, position + 1));
            } else if (next == ')' || next == ']' || next == '}') {
                Bracket bracket = null;
                if (!openingBracketsStack.empty()) {
                    bracket = openingBracketsStack.pop();
                }
                if (bracket == null || !bracket.match(next)) {
                    System.out.println(position + 1);
                    matched = false;
                    break;
                }
            }
        }

        if (openingBracketsStack.empty() && matched) {
            System.out.println("Success");
        } else if (matched) {
            System.out.println(openingBracketsStack.pop().position);
        }


        // Printing answer, write your code here
    }
}

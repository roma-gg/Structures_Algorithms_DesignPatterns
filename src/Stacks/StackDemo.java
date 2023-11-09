package Stacks;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class StackDemo {

    public String reversedString(String str) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            stack.push("" + str.charAt(i));
        }
        String reversed = "";
        while (!stack.isEmpty()) {
            reversed += stack.pop();
        }
        return reversed;
    }

    private final List<Character> leftBrackets = Arrays.asList('[', '{', '<', '(');
    private final List<Character> rightBrackets = Arrays.asList(']', '}', '>', ')');

    public boolean isBalanced (String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {

            if (leftBracket(str.charAt(i)))
                stack.push(str.charAt(i));

            if (rightBracket(str.charAt(i))) {
                if (stack.isEmpty())
                    return false;

                var top = stack.pop();
                if (bracketsMismatch(top, str.charAt(i)))
                    return false;
            }
        }
        return stack.isEmpty();
    }

    private boolean leftBracket(char ch) {
        return leftBrackets.contains(ch);
    }

    private boolean rightBracket(char ch) {
        return rightBrackets.contains(ch);
    }

    private boolean bracketsMismatch (char left, char right) {
        return leftBrackets.indexOf(left) == rightBrackets.indexOf(right);
    }



}

package special.stack_queue;

import java.util.Stack;

public class EvalRPN {


    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+")) {
                int second = Integer.parseInt(stack.pop());
                int first = Integer.parseInt(stack.pop());
                stack.push(String.valueOf(first + second));
            } else if (tokens[i].equals("-")) {
                int second = Integer.parseInt(stack.pop());
                int first = Integer.parseInt(stack.pop());
                stack.push(String.valueOf(first - second));
            } else if (tokens[i].equals("*")) {
                int second = Integer.parseInt(stack.pop());
                int first = Integer.parseInt(stack.pop());
                stack.push(String.valueOf(first * second));
            } else if (tokens[i].equals("/")) {
                int second = Integer.parseInt(stack.pop());
                int first = Integer.parseInt(stack.pop());
                stack.push(String.valueOf(first / second));
            } else {
                stack.push(tokens[i]);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static void main(String[] args) {
//        String[] tokens = {"2","1","+","3","*"};
//        String[] tokens = {"4","13","5","/","+"};
        String[] tokens = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        EvalRPN evalRPN = new EvalRPN();
        System.out.println(evalRPN.evalRPN(tokens));
    }

}

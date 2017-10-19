package cn.migu.hasika.stackdemo2;

import android.util.Log;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by hasika on 2017/10/19.
 */

public class StackDemo {

    private Stack<String> charStack = new Stack<>();
    private HashMap<String, Integer> priorityMap = new HashMap<>();

    public StackDemo() {
        charStack.clear();
        priorityMap.put("+", 1);
        priorityMap.put("-", 1);
        priorityMap.put("*", 2);
        priorityMap.put("/", 2);
    }

    public int getResultFromStr(String str) {
        String expression = getPostfixExpression(str);
        int result = getResultFromPostfixExpression(expression);

        return result;
    }

    /**
     * 1）如果遇到操作数，我们就直接将其输出。
     * 2）如果遇到操作符，则我们将其放入到栈中，遇到左括号时我们也将其放入栈中。
     * 3）如果遇到一个右括号，则将栈元素弹出，将弹出的操作符输出直到遇到左括号为止。注意，左括号只弹出并不输出。
     * 4）如果遇到任何其他的操作符，如（“+”， “*”，“（”）等，从栈中弹出元素直到遇到发现更低优先级的元素(或者栈为空)为止。弹出完这些元素后，才将遇到的操作符压入到栈中。有一点需要注意，只有在遇到" ) "的情况下我们才弹出" ( "，其他情况我们都不会弹出" ( "。
     * 5）如果我们读到了输入的末尾，则将栈中所有元素依次弹出。
     */
    private String getPostfixExpression(String originExpression) {
        charStack.clear();

        String expression = "";

        String stackTailVal = "";
        int stackTailPriority = 0;

        String currentVal = "";
        int currentPriority = 0;

        for (int i = 0; i < originExpression.length(); i++) {
            currentVal = originExpression.substring(i,i+1);
            //null next
            if (currentVal.equals(" ")) continue;

            //digist add to expression
            if (Character.isDigit(currentVal.charAt(0))) {
                expression += currentVal;
            } else if (currentVal.equals("(")) {
                charStack.push(String.valueOf(currentVal));
            } else if (currentVal.equals(")")) {
                while (true) {
                    String val = charStack.pop();
                    if (val.equals("(")) {
                        break;
                    } else {
                        expression += val;
                        continue;
                    }
                }
            } else {
                currentPriority = priorityMap.get(currentVal);
                if (charStack.size() == 0) {
                    charStack.push(currentVal);
                    stackTailPriority = priorityMap.get(currentVal);
                    stackTailVal = currentVal;
                } else {
                    if (stackTailPriority <= currentPriority) {//入栈
                        charStack.push(currentVal);
                        stackTailVal = currentVal;
                        stackTailPriority = currentPriority;
                    } else {
                        while (true) {
                            if (charStack.isEmpty()) break;

                            String c = charStack.peek();
                            int p = priorityMap.get(c);
                            if (c.equals("(")) {
                                break;
                            } else if (p < currentPriority) {
                                break;
                            } else {
                                expression += c;
                                charStack.pop();
                                continue;
                            }
                        }
                        charStack.push(currentVal);
                        stackTailVal = currentVal;
                        stackTailPriority = currentPriority;
                    }
                }
            }
            android.util.Log.d("Stack", "originExpression: " + originExpression);
            android.util.Log.d("Stack", "charStack: " + charStack.toString());
            android.util.Log.d("Stack", "expression:" + expression);
            android.util.Log.d("Stack", "---------------");
        }

        while (true) {
            if (charStack.isEmpty()) break;
            String c = charStack.pop();
            expression += c;
        }

        android.util.Log.d("Stack", "originExpression: " + originExpression);
        android.util.Log.d("Stack", "charStack: " + charStack.toString());
        android.util.Log.d("Stack", "expression:" + expression);
        android.util.Log.d("Stack", "---------------");
        return expression;
    }

    private int getResultFromPostfixExpression(String rightExpression) {
        charStack.clear();
        String c = "";
        String c1 = "";
        String c2 = "";
        int i3 = 0;

        for (int i = 0; i < rightExpression.length(); i++) {
            c = rightExpression.substring(i,i+1);
            if (Character.isDigit(c.charAt(0))) {
                charStack.push(c);
            } else {
                if (c.equals("-")) {
                    c1 = charStack.pop();
                    c2 = charStack.pop();
                    i3 = Integer.parseInt(c2) - Integer.parseInt(c1);
                    charStack.push(String.valueOf(i3));
                } else if (c.equals("+")) {
                    c1 = charStack.pop();
                    c2 = charStack.pop();
                    i3 = Integer.parseInt(c2) + Integer.parseInt(c1);
                    charStack.push(String.valueOf(i3));
                } else if (c.equals("*")) {
                    c1 = charStack.pop();
                    c2 = charStack.pop();
                    i3 = Integer.parseInt(c2) * Integer.parseInt(c1);
                    charStack.push(String.valueOf(i3));
                } else {
                    c1 = charStack.pop();
                    c2 = charStack.pop();
                    i3 = Integer.parseInt(c2) / Integer.parseInt(c1);
                    charStack.push(String.valueOf(i3));
                }
            }
            android.util.Log.d("Stack", "rightExpression: " + rightExpression);
            android.util.Log.d("Stack", "charStack: " + charStack.toString());
            android.util.Log.d("Stack", "c: " + String.valueOf(c));
            android.util.Log.d("Stack", "i3: " + String.valueOf(i3));
            android.util.Log.d("Stack", "+++++++++++");
        }
        return i3;
    }
}

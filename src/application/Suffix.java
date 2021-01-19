package application;

import java.util.ArrayList;

public class Suffix {

    private String input;
    public String out[] = new String[30];
    public int k = 0;
    Stack s = new Stack(30);

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void clearStack() {
        for (int i = s.top, j = k; s.top > 0; s.top--, j++) {
            out[j] = s.stack[s.top];
            k = j;
        }
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    public boolean isNumber(String s) {
        boolean flag = true;
        for (char c : s.toCharArray()) {
            if ((c >= '0' && c <= '9') || c == '.') {
                flag = true;
                break;
            } else {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public boolean isVariable(String s) {
        boolean flag = true;
        for (char c : s.toCharArray()) {
            if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z')) {
                flag = false;
                break;
            }
        }
        return flag;
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public int pre(String c) {

        if (c.equals("#")) {
            return -1;
        } else if (c.equals(")")) {
            return 0;
        } else if (c.equals("+") || c.equals("-") || c.equals("|") || c.equals(">") || c.equals("<")) {
            return 1;
        } else if (c.equals("*") || c.equals("/") || c.equals("&")) {
            return 2;
        } else if (c.equals("^")) {
            return 3;
        } else if (c.equals("(")) {
            return 4;
        } else if (isNumber(c) || isVariable(c)) {
            return 5;
        } else {
            return -2;
        }
    }
    
    public void setInput(String s){
        this.input=s;
    }
    
    public String getInput(){
        return input;
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void Suffix(ArrayList<String> input) {
        for (int i = 0; i < input.size(); i++) {
            if (pre(input.get(i)) > pre(s.stack[s.top]) || input.get(i).equals('(')) {
                s.push(input.get(i));
            } else if (input.get(i).equals(")")) {

                while (!s.stack[s.top].equals("(")) {
                    out[k] = s.stack[s.top];
                    k++;
                    s.pop();
                }
                s.pop();
            } else if (input.get(i).equals("^")) {

                while (pre(input.get(i)) < pre(s.stack[s.top])) {
                    out[k] = s.stack[s.top];
                    k++;
                    s.pop();
                }
                s.push(input.get(i));
            } else {

                while (pre(input.get(i)) <= pre(s.stack[s.top]) && !s.stack[s.top].equals("(")) {
                    out[k] = s.stack[s.top];
                    k++;
                    s.pop();
                }
                s.push(input.get(i));
            }
        }
        clearStack();
    }
}

package application;

import java.util.Scanner;

public class Evaluation {

    public Scanner in = new Scanner(System.in);
    NumStack st = new NumStack(15);
    Suffix su = new Suffix();
    float op1, op2, result, value;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
    public float Evaluatepostfix(String exp[], int k) {

        for (int i = 0; i < k + 1; i++) {
            if (su.pre(exp[i]) == 5) {
                st.push(Float.parseFloat(exp[i]));
            } else if (su.pre(exp[i]) != 5) {
                op2 = st.stack[st.top];
                st.pop();
                op1 = st.stack[st.top];
                st.pop();
                result = calculat(op1, op2, exp[i]);
                st.push(result);
            }
        }
        return result;

    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public float calculat(float op1, float op2, String pre) {

        float res;
        double a, b;
        switch (pre) {

            case "+":
                res = op1 + op2;
                break;
            case "-":
                res = op1 - op2;
                break;
            case "*":
                res = op1 * op2;
                break;
            case "/":
                res = op1 / op2;
                break;
            // case '^': res=Math.pow(a,b);break;   
            default:
                res = 0;

        }
        return res;

    }
}

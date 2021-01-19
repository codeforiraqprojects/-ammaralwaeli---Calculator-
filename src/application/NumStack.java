package application;

import java.util.Scanner;

public class NumStack {

    public Scanner in = new Scanner(System.in);
    public int top = -1, index;
    public float stack[];

    public NumStack(int length) {
        stack = new float[length];

    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public boolean isEmpty() {
        return (top == -1);
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public boolean isFull() {
        return (top == stack.length - 1);
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void pop() {
        if (!isEmpty()) {
            top--;
        }
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void push(float el) {
        if (!isFull()) {
            top++;
            stack[top] = el;
        }
    }

}

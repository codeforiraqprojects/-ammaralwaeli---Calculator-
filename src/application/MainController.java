package application;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {

    Suffix suf;
    Evaluation ev;

    //add FXML connector
    @FXML
    private Label Number, result;

    private ArrayList<String> k;

    @FXML
    public void clickButton(javafx.event.ActionEvent event) {

        char s = event.getSource().toString().charAt(event.getSource().toString().length() - 2);
        if (Number.getText().length() == 0) {
            Number.setText(Number.getText() + s);
        } else {

            char lastChar = Number.getText().charAt(Number.getText().length() - 1);
            if (check(s, lastChar)) {
                Number.setText(Number.getText() + s);
            }
        }
    }

    public boolean check(char c, char last) {
        if ((isNumber(c + "") && isNumber(last + ""))) {
            return true;
        } else if ((isOperand(c) && isNumber(last + ""))) {
            return true;
        } else if ((isOperand(last) && isNumber(c + ""))) {
            return true;
        } else if (c == '(' && isOperand(last)) {
            return true;
        } else if (c == ')' && last != '(') {
            int co = 0, co2 = 0;
            for (char f : Number.getText().toCharArray()) {
                if (f == '(') {
                    co++;
                }
                if (f == ')') {
                    co2++;
                }
            }
            return co > co2;
        } else if (isNumber(c + "") && last == '(') {
            return true;
        } else if (isOperand(c) && last == ')') {
            return true;
        } else {
            return false;
        }
    }

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

    public boolean isOperand(char s) {
        return s == '+' || s == '-' || s == '*' || s == '/';
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

    public void clickClear(javafx.event.ActionEvent event) {

        Number.setText("");
        result.setText("");
        suf = new Suffix();
        ev = new Evaluation();
    }

    public void clickBack(javafx.event.ActionEvent event) {

        if (!Number.getText().equals("")) {
            Number.setText(Number.getText().substring(0, Number.getText().length() - 1));
        }
    }

    public void clickResult(javafx.event.ActionEvent event) {
        int co = 0, co2 = 0;
        for (char c : Number.getText().toCharArray()) {

            if (c == '(') {
                co++;
            }
            if (c == ')') {
                co2++;
            }
        }
        if (co == co2) {
            suf = new Suffix();
            ev = new Evaluation();
            suf.setInput(Number.getText());

            k = new ArrayList<>();

            String[] t = suf.getInput().split("(?=[-+*/()])|(?<=[^-+*/][-+*/])|(?<=[()])");

            k.addAll(Arrays.asList(t));
            suf.s.push("#");
            suf.Suffix(k);
            result.setText(ev.Evaluatepostfix(suf.out, suf.k) + "");
        } else {
            result.setText("Error");
        }
    }

}

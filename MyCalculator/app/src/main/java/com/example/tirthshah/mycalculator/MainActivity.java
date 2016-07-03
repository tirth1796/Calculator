package com.example.tirthshah.mycalculator;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {
    private String[] display = new String[5];
    private int oppFlag = 0;
    private int operations;
    private int lastOperator = -1;
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttondel, buttonmul, buttonout, buttonplus, buttonsub, buttondiv, buttonc, buttonPM, buttondec;
    TextView tdp1, tdp2, tdp3;

    public void click1(View view) {
        display[0] += "1";
        display();
    }

    public void click2(View view) {
        display[0] += "2";
        display();
    }

    public void click3(View view) {
        display[0] += "3";
        display();
    }


    public void click4(View view) {
        display[0] += "4";
        display();
    }

    public void click5(View view) {
        display[0] += "5";
        display();
    }

    public void click6(View view) {
        display[0] += "6";
        display();
    }

    public void click7(View view) {
        display[0] += "7";
        display();
    }

    public void click8(View view) {
        display[0] += "8";
        display();
    }

    public void click9(View view) {
        display[0] += "9";
        display();
    }

    public void click0(View view) {
        display[0] += "0";
        display();
    }

    public void clickdec(View view) {
        display[0] += ".";
        display();
    }

    public void clickdel(View view) {

        if (display[0].length() > 0) {
            if (display[0].endsWith("*") || display[0].endsWith("/") || display[0].endsWith("+") || display[0].endsWith("-"))
                operations--;
            display[0] = display[0].substring(0, display[0].length() - 1);
            display();
        }
    }

    public void clicksub(View view) {
        if (display[0].length() == 0 || display[0].charAt(display[0].length() - 1) == '*' || display[0].charAt(display[0].length() - 1) == '/') {
            display[0] += "-";
            oppFlag = 1;
            if (operations > 0) {
                tdp2.setText(display[0]);
            } else {
                tdp1.setText(" ");
                tdp2.setText(display[0]);
            }

        }
        operations++;
        if (display[0].charAt(display[0].length() - 1) < 48) {
            display[0] = display[0].replace(display[0].substring(display[0].length() - 1), "");
        }
        display[0] += "-";

        if (operations > 0)
            tdp2.setText(display[0]);
        else tdp1.setText(display[0]);
    }

    public void clickmul(View view) {
        if (display[0].equals("")) {
            tdp1.setText("error");
        } else {
            operations++;
            if (display[0].charAt(display[0].length() - 1) < 48) {
                display[0] = display[0].replace(display[0].substring(display[0].length() - 1), "");
            }
            display[0] += "*";
            if (operations > 0)
                tdp2.setText(display[0]);
            else tdp1.setText(display[0]);
        }
    }

    public void clickdiv(View view) {
        if (display[0].equals("")) {
            tdp1.setText("error");
        } else {
            operations++;
            if (display[0].charAt(display[0].length() - 1) < 48) {
                display[0] = display[0].replace(display[0].substring(display[0].length() - 1), "");
            }
            display[0] += "/";
            if (operations > 0)
                tdp2.setText(display[0]);
            else tdp1.setText(display[0]);
        }
    }

    public void clickplus(View view) {
        if (display[0].equals("")) {
            tdp1.setText("error");
        } else {
            operations++;
            if (display[0].charAt(display[0].length() - 1) < 48) {
                display[0] = display[0].replace(display[0].substring(display[0].length() - 1), "");
            }
            display[0] += '+';
            if (operations > 0)
                tdp2.setText(display[0]);
            else tdp1.setText(display[0]);
        }
    }

    public void clickc(View view) {
        tdp1.setText("");
        tdp2.setText("");
        display[0] = "";
        operations = 0;
    }

    public void clickPM(View view) {
        if (oppFlag == 1) {
            if (display[0].startsWith("-")) {
                display[0] = display[0].replace(display[0].substring(0, 1), "+");
            } else {
                display[0] = "-" + display[0];
            }
        }
        display();
    }

    public void clickout(View view) {
        display[0] = Double.toString(getResult(display[0]));
        display();
        tdp1.setText("  " + "");
        operations = 0;

    }

    private void display() {
        if (oppFlag != 1) {
            if (display[0].length() < 30) {
                tdp2.setTextSize(15);
                if (display[0].length() > 15) {
                    tdp2.setTextSize(30);
                } else if (display[0].length() > 11)
                    tdp2.setTextSize(40);
                else tdp2.setTextSize(50);
                if (operations > 0) {


                    double a = getResult(display[0]);
                    String answer = Double.toString(a);
                    if (answer.length() > 19) {
                        tdp1.setTextSize(20);
                    } else if (answer.length() > 15) {
                        tdp1.setTextSize(30);
                    } else if (answer.length() > 11)
                        tdp1.setTextSize(40);
                    else tdp1.setTextSize(50);

                    tdp2.setText(display[0]);
                    tdp1.setText(answer);
                } else {
                    tdp1.setTextSize(40);
                    tdp1.setText(" ");
                    tdp2.setText(display[0]);
                }
            }
        } else {

            if (operations > 0) {
                tdp2.setText(display[0]);
            } else {
                tdp1.setText(" ");
                tdp2.setText(display[0]);
            }
            oppFlag = 0;
        }
    }


    private double getResult(String input) {
        double[] inputNumber = new double[10];
        int numbers = 0;
        double ans;
        int[] operators = new int[10];
        operators[0] = -1;

        for (int i = 0; i < input.length(); i++) {
            if (!(input.charAt(i) == '-' && operators[numbers] == i - 1)) {
                if (input.charAt(i) == '*' || input.charAt(i) == '-' || input.charAt(i) == '+' || input.charAt(i) == '/') {
                    inputNumber[numbers] = Double.parseDouble(input.substring(operators[numbers] + 1, i));
                    numbers++;
                    operators[numbers] = i;
                }
            }
        }


        lastOperator = operators[numbers];
        if (!(operators[numbers] == input.length() - 1))
            inputNumber[numbers] = Double.parseDouble(input.substring(operators[numbers] + 1));
        ans = inputNumber[0];
        if (this.operations > 0) {
            for (int i = 1; i <= numbers; i++) {
                if (!(operators[numbers] == input.length() - 1)) {
                    switch (input.charAt(operators[i])) {
                        case '*':
                            ans *= inputNumber[i];
                            break;
                        case '+':
                            ans += inputNumber[i];
                            break;
                        case '-':
                            ans -= inputNumber[i];
                            break;
                        case '/':
                            if (display[0].charAt(i + 1) != 0)
                                ans /= inputNumber[i];
                            break;
                    }
                }
            }
            return ans;
        }
        return inputNumber[0];
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button0 = (Button) findViewById(R.id.b0);
        button1 = (Button) findViewById(R.id.b1);
        button2 = (Button) findViewById(R.id.b2);
        button3 = (Button) findViewById(R.id.b3);
        button4 = (Button) findViewById(R.id.b4);
        button5 = (Button) findViewById(R.id.b5);
        button6 = (Button) findViewById(R.id.b6);
        button7 = (Button) findViewById(R.id.b7);
        button8 = (Button) findViewById(R.id.b8);
        button9 = (Button) findViewById(R.id.b9);
        buttondel = (Button) findViewById(R.id.bdel);
        buttonplus = (Button) findViewById(R.id.bplus);
        buttonsub = (Button) findViewById(R.id.bsub);
        buttonmul = (Button) findViewById(R.id.bmul);
        buttondiv = (Button) findViewById(R.id.bdiv);
        buttondec = (Button) findViewById(R.id.bdec);
        buttonout = (Button) findViewById(R.id.bout);
        buttonPM = (Button) findViewById(R.id.bpm);
        buttonc = (Button) findViewById(R.id.bc);
        tdp1 = (TextView) findViewById(R.id.firstDisplay);
        tdp2 = (TextView) findViewById(R.id.secondDisplay);
        display[0] = "";
        display[1] = "";
        display[2] = "";
        display[3] = "";
        display[4] = "";


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

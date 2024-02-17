package com.jayesh_ahir.tic_toc_toe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Variable Declaration
    String b1, b2, b3, b4, b5, b6, b7, b8, b9;
    TextView textView;
    Intent intent;
    private int flag = 1, cnt = 0, win_flag = 0;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, player_1, player_2, restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initialization();
        player_1.setText(intent.getStringExtra("player_1"));
        player_2.setText(intent.getStringExtra("player_2"));
        player_1.setBackgroundColor(getResources().getColor(R.color.playing));
        player_2.setBackgroundColor(getResources().getColor(R.color.stop));
        restart.setOnClickListener(v -> {
            clear();
            displayToastMassage("Game is Restart!", 0);
        });
    }

    // Initialization method
    private void initialization() {
        intent = getIntent();
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        player_1 = findViewById(R.id.btn_player_1);
        player_2 = findViewById(R.id.btn_player_2);
        restart = findViewById(R.id.btn_restart);
    }

    // Toast Display Method
    public void displayToastMassage(String txt, int time) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.activity_custom_toast, findViewById(R.id.custom_toast_root));
        textView = layout.findViewById(R.id.toast_text);
        textView.setText(txt);
        Toast toast = new Toast(getApplicationContext());
        if (time == 1) toast.setDuration(Toast.LENGTH_LONG);
        else toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    // Button getText to String Method
    public void buttonToString() {
        b1 = btn1.getText().toString();
        b2 = btn2.getText().toString();
        b3 = btn3.getText().toString();
        b4 = btn4.getText().toString();
        b5 = btn5.getText().toString();
        b6 = btn6.getText().toString();
        b7 = btn7.getText().toString();
        b8 = btn8.getText().toString();
        b9 = btn9.getText().toString();
    }

    // Reset All View Method
    public void clear() {
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        cnt = 0;
        flag = 1;
        win_flag = 0;
        player_1.setBackgroundColor(getResources().getColor(R.color.playing));
        player_2.setBackgroundColor(getResources().getColor(R.color.stop));
    }

    // Winner Declare Method
    public void win(String str) {
        if (str.equals("X")) {
            win_flag = 1;
            displayToastMassage(intent.getStringExtra("player_1") + " Win", 1);
            int delayMillis = 2000;
            Handler handler = new Handler();
            Runnable runnable = this::clear;
            handler.postDelayed(runnable, delayMillis);
        } else {
            win_flag = 1;
            displayToastMassage(intent.getStringExtra("player_2") + " Win", 1);
            int delayMillis = 2000;
            Handler handler = new Handler();
            Runnable runnable = this::clear;
            handler.postDelayed(runnable, delayMillis);
        }
    }

    // Check Winner Method
    public void winner() {
        buttonToString();
        if (b1.equals(b2) && b2.equals(b3) && !b1.equals("")) {
            win(b1);
        } else if (b4.equals(b5) && b5.equals(b6) && !b4.equals("")) {
            win(b4);
        } else if (b7.equals(b8) && b8.equals(b9) && !b9.equals("")) {
            win(b7);
        } else if (b1.equals(b4) && b4.equals(b7) && !b1.equals("")) {
            win(b1);
        } else if (b2.equals(b5) && b5.equals(b8) && !b2.equals("")) {
            win(b2);
        } else if (b3.equals(b6) && b6.equals(b9) && !b3.equals("")) {
            win(b3);
        } else if (b1.equals(b5) && b5.equals(b9) && !b1.equals("")) {
            win(b1);
        } else if (b3.equals(b5) && b5.equals(b7) && !b5.equals("")) {
            win(b3);
        }
        if (cnt == 9) {
            if (win_flag != 1) displayToastMassage("Draw Game!", 0);
            int delayMillis = 2000;
            Handler handler = new Handler();
            Runnable runnable = this::clear;
            handler.postDelayed(runnable, delayMillis);
        }
    }

    // onClick Button method
    public void btnClick(View view) {
        Button btn = (Button) view;
        if ((btn.getText().toString()).equals("") && win_flag == 0) {
            cnt++;
            if (flag == 1) {
                btn.setText("X");
                flag = 0;
                player_1.setBackgroundColor(getResources().getColor(R.color.stop));
                player_2.setBackgroundColor(getResources().getColor(R.color.playing));
            } else {
                btn.setText("O");
                flag = 1;
                player_1.setBackgroundColor(getResources().getColor(R.color.playing));
                player_2.setBackgroundColor(getResources().getColor(R.color.stop));
            }
            if (cnt > 4) {
                winner();
            }
        } else if (win_flag == 1) {
            displayToastMassage("Please Restart Game", 0);
        } else {
            displayToastMassage("Please Click On Other Field", 0);
        }
    }
}
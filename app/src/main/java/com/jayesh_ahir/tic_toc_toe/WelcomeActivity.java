package com.jayesh_ahir.tic_toc_toe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    EditText player1, player2;
    TextView textView;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initialization();
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        start.setOnClickListener(view -> {
            intent.putExtra("player_1", player1.getText().toString());
            intent.putExtra("player_2", player2.getText().toString());
            displayToastMassage("Welcome\n" + player1.getText().toString() + "\n" + player2.getText().toString(), 0);
            startActivity(intent);
        });
    }

    private void initialization() {
        player1 = findViewById(R.id.player_1);
        player2 = findViewById(R.id.player_2);
        start = findViewById(R.id.start);
    }

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
}
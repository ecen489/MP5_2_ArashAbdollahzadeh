package com.example.mp5_2_arashabdollahzadeh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Scanner;

public class questions_activity extends AppCompatActivity {
    String solution;
    Boolean Right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_activity);



        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        String topic = (String) extra.get("topic");

        String topics = (String) extra.get("topics");

        TextView txt = (TextView) findViewById(R.id.TXT);

        Scanner scan_animal = new Scanner(getResources().openRawResource(R.raw.questions_ez_animals));
        final String[] questions_animals = new String[9];

        int i = 0;
        while (scan_animal.hasNextLine()){
            String line=scan_animal.nextLine();
            questions_animals[i] = line;
            i++;
        }

        Scanner scan_stuff = new Scanner(getResources().openRawResource(R.raw.questions_ez_stuff));
        final String[] questions_stuff = new String[9];

        i = 0;
        while (scan_stuff.hasNextLine()){
            String line=scan_stuff.nextLine();
            questions_stuff[i] = line;
            i++;
        }

        Scanner scan_mafs = new Scanner(getResources().openRawResource(R.raw.questions_ez_mafs));
        final String[] questions_mafs = new String[9];

        i = 0;
        while (scan_mafs.hasNextLine()){
            String line=scan_mafs.nextLine();
            questions_mafs[i] = line;
            i++;
        }

        Random rand = new Random();
        int value = rand.nextInt(3);

        if (topic.equals("questions_ez_animals")){
            txt.setText(questions_animals[value*3] + "\n\n" + questions_animals[value*3 + 1]);
            solution = questions_animals[value*3 + 2];
        }
        if (topic.equals("questions_ez_stuff")){
            txt.setText(questions_stuff[value*3] + "\n\n" + questions_stuff[value*3 + 1]);
            solution = questions_stuff[value*3 + 2];
        }
        if (topic.equals("questions_ez_mafs")){
            txt.setText(questions_mafs[value*3] + "\n\n" + questions_mafs[value*3 + 1]);
            solution = questions_mafs[value*3 + 2];
        }


    }
    void submitted(View view){

        EditText edit = (EditText) findViewById(R.id.edit);

        String answer = edit.getText().toString();

        if (answer.charAt(answer.length() - 1) == solution.charAt(solution.length() - 1)){
            //right
            Intent intentBack = new Intent();
            Right = true;
            intentBack.putExtra("answer", Right);
            setResult(RESULT_OK, intentBack);
            finish();
        }
        else {
            Intent intentBack = new Intent();
            Right = false;
            intentBack.putExtra("answer", Right);
            setResult(RESULT_OK, intentBack);
            finish();
        }
    }
}

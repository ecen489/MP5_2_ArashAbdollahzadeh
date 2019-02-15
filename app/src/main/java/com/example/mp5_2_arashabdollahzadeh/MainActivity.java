package com.example.mp5_2_arashabdollahzadeh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_CODE_ANS = 2468;
    int Score = 0;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] questions_topics = new String[3];

        Scanner scan = new Scanner(getResources().openRawResource(R.raw.questions_topics));

        int i = 0;
        while (scan.hasNextLine()){
            String line=scan.nextLine();
            questions_topics[i] = line;
            i++;
        }
        scan.close();

        ListView list = (ListView) findViewById(R.id.List_of_Items);

        final Intent intent = new Intent(this, questions_activity.class);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, questions_topics);

        list.setAdapter(myAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String word = parent.getItemAtPosition(position).toString();

                if (word.equals(questions_topics[0])){
                    String typ = new String();
                    typ = questions_topics[0];
//                    Toast.makeText(MainActivity.this, typ, Toast.LENGTH_LONG).show();
                    intent.putExtra("topic", typ);
                    intent.putExtra("topics", questions_topics);
                    startActivityForResult(intent, REQ_CODE_ANS);
                }
                else if (word.equals(questions_topics[1])){
                    String typ = new String();
                    typ = questions_topics[1];
//                    Toast.makeText(MainActivity.this, typ, Toast.LENGTH_LONG).show();
                    intent.putExtra("topic", questions_topics[1]);
                    intent.putExtra("topics", questions_topics);
                    startActivityForResult(intent, REQ_CODE_ANS);
                }
                else if (word.equals(questions_topics[2])){
                    String typ = new String();
                    typ = questions_topics[2];
//                    Toast.makeText(MainActivity.this, typ, Toast.LENGTH_LONG).show();
                    intent.putExtra("topic", questions_topics[2]);
                    intent.putExtra("topics", questions_topics);
                    startActivityForResult(intent, REQ_CODE_ANS);
                }
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(requestCode==REQ_CODE_ANS) {
//            String ret_ans = intent.getStringExtra("family");
//            Toast.makeText(MainActivity.this, ret_ans, Toast.LENGTH_SHORT).show();
            Bundle extra = intent.getExtras();

            Boolean result = (Boolean) extra.get("answer");

            TextView txt = (TextView) findViewById(R.id.SCORE);

            ImageView img = (ImageView) findViewById(R.id.img);

            if (result){
                Score++;
                txt.setText("SCORE: " + Score);
                img.setImageResource(R.drawable.right);
            }
            else{
                Score--;
                txt.setText("SCORE: " + Score);
                img.setImageResource(R.drawable.wrong);
            }
        }
    }
    void reset(View view){
        this.Score = 0;
        TextView txt = (TextView) findViewById(R.id.SCORE);
        txt.setText("SCORE: " + Score);
    }
}

package com.bfifinance.quiz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends Activity {

    //DECLARE VARIABLE
    List<Question> quesList;
    int score=0;
    int qid=0;
    Question currentQ;
    TextView txtQuestion;
    RadioButton rda, rdb, rdc;
    Button butNext;

    MediaPlayer MPQ;
    MediaPlayer MPB;
    MediaPlayer MPL;

    TextView TVJwb;
    TextView TVplh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SET OBJECT
        TextView TV = (TextView) findViewById(R.id.tvName);
        TVJwb = (TextView) findViewById(R.id.TVJwb);
        TVplh = (TextView) findViewById(R.id.TVPlh);
        txtQuestion=(TextView)findViewById(R.id.tvQuestion);
        rda=(RadioButton)findViewById(R.id.r1);
        rdb=(RadioButton)findViewById(R.id.r2);
        rdc=(RadioButton)findViewById(R.id.r3);
        butNext=(Button)findViewById(R.id.btnSubmit);

        //SET MUSIC
        MPQ = MediaPlayer.create(this, R.raw.pertanyaan);
        MPB = MediaPlayer.create(this, R.raw.benar);
        MPL = MediaPlayer.create(this, R.raw.salah);

        //SET INTENT ACTIVITY
        Intent intent = getIntent();
        String nama = intent.getStringExtra("nama");

        TV.setText("Welcome, "+nama);
        TV.setTextColor(Color.BLUE);
        TV.setTypeface(null, Typeface.BOLD);
        txtQuestion.setTypeface(null, Typeface.BOLD);
        txtQuestion.setTextColor(Color.BLUE);


        //SET DB HANDLER
        DBHelper db=new DBHelper(this);
        quesList=db.getAllQuestions();
        currentQ=quesList.get(qid);

        setQuestionView();
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//// Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }

    //GENERATE QUESTION
    private void setQuestionView()
    {
        //MPQ.start();

        rda.setChecked(false);
        rdb.setChecked(false);
        rdc.setChecked(false);

        txtQuestion.setText(currentQ.getQUESTION());
        rda.setText(currentQ.getOPTA());
        rdb.setText(currentQ.getOPTB());
        rdc.setText(currentQ.getOPTC());
        qid++;
    }

    public void cekjawaban(View view) {
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RadioGroup grp=(RadioGroup)findViewById(R.id.Rgroup);

                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
                //RadioButton answers=(RadioButton)findViewById(grp.getId());

                //Log.d("Disini", currentQ.toString());

                TVJwb.setText(currentQ.getANSWER());
                TVplh.setText(answer.getText());

                if(currentQ.getANSWER().equals(answer.getText()))
                {
                    MPQ.stop();
                    MPB.start();
                    currentQ=quesList.get(qid);
                    setQuestionView();

                    score++;
                }
                else
                {
                    if(currentQ.getOPTA() == currentQ.getANSWER())
                    {
                        rda.setTextColor(Color.RED);
                    }
                    else if (currentQ.getOPTB() == currentQ.getANSWER())
                    {
                        rdb.setTextColor(Color.RED);
                    }
                    else
                    {
                        rdc.setTextColor(Color.RED);
                    }

                    butNext.setEnabled(false);
                    rda.setEnabled(false);
                    rdb.setEnabled(false);
                    rdc.setEnabled(false);
                    MPL.start();
                    MPQ.stop();
                }
            }
        });
    }


    //EXIT
    public void menyerah(View view) {

        Intent intentNyerah = new Intent(this, LayarLogin.class);

        startActivity(intentNyerah);

    }

    //CEK MENANG
    public void cekmenang()
    {

    }

    @Override
    protected void onResume() {
        super.onResume();
        MPQ.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MPQ.pause();
    }
}

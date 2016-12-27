package com.bfifinance.quiz;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LayarLogin extends AppCompatActivity {

    String login;
    MediaPlayer MP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_login);

        MP = MediaPlayer.create(this, R.raw.intro);
        ImageView IVLogo = (ImageView) findViewById(R.id.ivLogo);
        IVLogo.setImageResource(R.drawable.logo);

    }

    @Override
    protected void onPause() {
        super.onPause();
        MP.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MP.start();
    }

    public void startgame(View view) {

        EditText ET = (EditText) findViewById(R.id.etUsername);
        login = ET.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);


        if (login.equals("")||login.isEmpty())
        {
            Toast.makeText(LayarLogin.this, "Isi nama dulu benga !!!", Toast.LENGTH_SHORT).show();
        }
        else if (login.contains("?")||login.contains("!")||login.contains("'")||login.contains(",")||login.contains("#")||login.contains("@")||login.contains("%")||login.contains("&")||login.contains("*"))
        {
            Toast.makeText(LayarLogin.this, "Jangan pake karakter2 aneh, benga juga lu !!!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            intent.putExtra("nama", login);
            startActivity(intent);
        }

    }
}

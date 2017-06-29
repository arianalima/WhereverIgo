package com.example.bernardojr.whereverigo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import static com.example.bernardojr.whereverigo.R.id.questionario_recycler_view;

public class QuestionarioActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Button btnProx;
    private int QUESTINARIO_PROGRESSO = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionario);
        initiViews();
        btnProx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progressBar.getProgress() == 100){
                    Intent it = new Intent(getApplicationContext(),LocaisSugeridosActivity.class);
                    startActivity(it);
                }
                proximo();
            }
        });
    }

    private void initiViews(){
        progressBar = (ProgressBar) findViewById(R.id.questionario_progress_bar);
        progressBar.setProgress(0);
        btnProx = (Button) findViewById(R.id.questionario_btn_prox);
        recyclerView = (RecyclerView) findViewById(questionario_recycler_view);
    }

    private void proximo(){
        if (progressBar.getProgress() <= 99 - QUESTINARIO_PROGRESSO){
            progressBar.incrementProgressBy(QUESTINARIO_PROGRESSO);
            btnProx.setText(R.string.questionario_btn_prox);
        }else {
            btnProx.setText(R.string.questionario_btn_terminar);
            progressBar.incrementProgressBy(QUESTINARIO_PROGRESSO);
        }

    }


}

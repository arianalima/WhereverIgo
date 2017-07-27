package com.example.bernardojr.whereverigo.gui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bernardojr.whereverigo.R;

import static android.R.attr.checked;

public class QuestionarioActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Button btnProx;
    private int QUESTINARIO_PROGRESSO = 20;

    GridView gridView;
    String tagsList[] = {"praia", "frio", "romântico", "radical", "gastronômico", "família", "pacato", "histórico", "religioso"};
    int imagensList[] = {R.drawable.recife,R.drawable.recife,R.drawable.recife,R.drawable.recife, R.drawable.recife,R.drawable.recife,R.drawable.recife,R.drawable.recife, R.drawable.recife};

    int checked = 0;
    int selectedPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionario);
        initViews();
        btnProx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(QuestionarioActivity.this,AvaliacaoLocaisActivity.class);
                startActivity(it);
                /*if (progressBar.getProgress() == 100){
                    Intent it = new Intent(getApplicationContext(),AvaliacaoLocaisActivity.class);
                    //Intent it = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(it);
                }
                proximo();*/
            }
        });


        QuestionarioActivity.GridAdapter gridAdapter = new QuestionarioActivity.GridAdapter(QuestionarioActivity.this,imagensList,tagsList);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(QuestionarioActivity.this, "Tag:"+tagsList[position] , Toast.LENGTH_SHORT).show();
                Log.i("Clicked", "Tag###########");
                //img_select.setVisibility(View.INVISIBLE);
                gridView.setFocusable(true);
                gridView.setEnabled(true);

                if(checked==0){
                    gridView.setBackgroundResource(R.drawable.ic_menu_camera);
                    //GreetingTextContainer greet = GreetingTextContainer.getSingletonObject();
                    //greet.setPosition(position);
                    checked =1;
                }
                else
                {
                    gridView.setBackgroundResource(0);
                    checked=0;
                }
            }
        });
    }

    private void initViews(){
        //progressBar = (ProgressBar) findViewById(R.id.questionario_progress_bar);
        //progressBar.setProgress(0);
        btnProx = (Button) findViewById(R.id.questionario_btn_prox);
        gridView = (GridView) findViewById(R.id.gridView);
        //recyclerView = (RecyclerView) findViewById(questionario_recycler_view);
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

    private class GridAdapter extends BaseAdapter{
        private int imagens[];
        private String tags[];
        private Context context;
        private LayoutInflater inflater;



        public GridAdapter(Context context, int imagens[], String tags[]){
            this.context = context;
            this.imagens = imagens;
            this.tags = tags;
        }

        @Override
        public int getCount() {
            return tags.length;
        }

        @Override
        public Object getItem(int position) {
            return tags[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View gridView = convertView;

            if (convertView == null){
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                gridView = inflater.inflate(R.layout.image_grid_item, null);
            }

            ImageView imagem = (ImageView) gridView.findViewById(R.id.tagImagem);
            TextView tag = (TextView) gridView.findViewById(R.id.tagNome);

            imagem.setImageResource(imagens[position]);
            tag.setText(tags[position]);
            return gridView;
        }
    }

}

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
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bernardojr.whereverigo.R;

import static android.R.attr.checked;
import static android.R.attr.windowElevation;

public class QuestionarioActivity extends AppCompatActivity {

    private Button btnProx;

    private GridView gridView;
    private String tagsList[] = {"praia", "frio", "romântico", "radical", "família", "culinária", "calmo", "histórico", "religioso"};
    private int escolha[] = {0,0,0,0,0,0,0,0,0};
    private int imagensList[] = {R.mipmap.praia,R.mipmap.frio,R.mipmap.romantico,R.mipmap.esporte_radical, R.mipmap.familia,
            R.mipmap.gastronomia,R.mipmap.tranquilo,R.mipmap.historico, R.mipmap.religioso};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionario);
        initViews();
        btnProx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verificarCheckBox(escolha)){
                String resultado = "";
                for (int i = 0; i < escolha.length; i++){
                    if (escolha[i] == 1){
                        resultado = resultado.concat(tagsList[i] + " ");
                    }
                }
                Intent it = new Intent(QuestionarioActivity.this,AvaliacaoLocaisActivity.class);
                it.putExtra("RESULTADO",resultado);
                startActivity(it);
                finish();
                }else {
                    Toast.makeText(getApplicationContext(),"Selecione ao menos uma tag",Toast.LENGTH_SHORT).show();
                }
            }
        });


        GridAdapter gridAdapter = new GridAdapter(QuestionarioActivity.this,imagensList,tagsList);
        gridView.setAdapter(gridAdapter);

    }
    // no mínimo uma tag checada, return true
    public boolean verificarCheckBox(int [] escolha){
        for (int i = 0; i < escolha.length; i++){
            if (escolha[i] != 0){
                return true;
            }
        }
        return false;

    }
    private void initViews(){
        btnProx = (Button) findViewById(R.id.questionario_btn_prox);
        gridView = (GridView) findViewById(R.id.gridView);
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
            final int posicao = position;
            ImageView imagem = (ImageView) gridView.findViewById(R.id.tagImagem);
            final CheckBox checkBox = (CheckBox) gridView.findViewById(R.id.itemCheckBox);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked()){
                        escolha[posicao] = 1;
                    }else {
                        escolha[posicao] = 0;
                    }

                }
            });
            imagem.setScaleType(ImageView.ScaleType.FIT_XY);
            TextView tag = (TextView) gridView.findViewById(R.id.tagNome);


            imagem.setImageResource(imagens[position]);
            tag.setText(tags[position]);
            return gridView;
        }
    }

}

package com.example.bernardojr.whereverigo.gui;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bernardojr.whereverigo.R;
import com.example.bernardojr.whereverigo.dominio.Local;

import java.util.List;

public class AvaliacaoLocaisActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AvaliacaoLocaisActivity.LocalAdapter adapter;
    private List<Local> locais;

    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao_locais);


        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        Local l = new Local();
        locais = l.listarLocais();
        recyclerView = (RecyclerView) findViewById(R.id.avaliacao_locais_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new AvaliacaoLocaisActivity.LocalAdapter(locais);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new AvaliacaoLocaisActivity.SpacesItemDecoration(24));
        recyclerView.setHasFixedSize(true);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i);
                //Toast.makeText(AvaliacaoLocaisActivity.this,String.valueOf(LocalHolder.ratingBar.getRating()),Toast.LENGTH_SHORT).show();

            }

        });
    }

    private class LocalAdapter extends RecyclerView.Adapter<AvaliacaoLocaisActivity.LocalHolder>{

        private List<Local> locais;
        private List<Float> notas;

        private LocalAdapter(List<Local> locais) {
            this.locais = locais;
        }

        @Override
        public AvaliacaoLocaisActivity.LocalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//
//            // Inflate the custom layout
//            View contactView = inflater.inflate(R.layout.avaliacao_locais_list_adapter, parent, false);

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.avaliacao_locais_list_adapter, parent, false);

            // Return a new holder instance
            AvaliacaoLocaisActivity.LocalHolder view = new AvaliacaoLocaisActivity.LocalHolder(v);
            return view;
        }

        @Override
        public void onBindViewHolder(final AvaliacaoLocaisActivity.LocalHolder holder, int position) {
            holder.cidade.setText(locais.get(position).getCidade());
            holder.estadoPais.setText(locais.get(position).getEstadoPais());
            holder.imagem.setImageResource(locais.get(position).getImagem());
            holder.descricao.setText(locais.get(position).getDescricao());
            holder.txtRatingValue.setText(String.valueOf(holder.ratingBar.getRating()));

            holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                public void onRatingChanged(RatingBar ratingBar, float rating,
                                            boolean fromUser) {

                    holder.txtRatingValue.setText(String.valueOf(rating));

                }
            });
        }

        @Override
        public int getItemCount() {
            return locais.size();
        }
    }

    private class LocalHolder extends RecyclerView.ViewHolder {

        private TextView cidade;
        private TextView estadoPais;
        private ImageView imagem;
        private TextView descricao;

        private RatingBar ratingBar;
        private TextView txtRatingValue;


        private LocalHolder(View itemView) {
            super(itemView);
            cidade = (TextView) itemView.findViewById(R.id.local_adapter_cidade);
            estadoPais = (TextView) itemView.findViewById(R.id.local_adapter_estado_pais);
            imagem = (ImageView) itemView.findViewById(R.id.local_adapter_imagem);
            descricao = (TextView) itemView.findViewById(R.id.local_adapter_descricao);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            txtRatingValue = (TextView) itemView.findViewById(R.id.txtRatingValue);
        }

    }

    private class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        private SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {

            outRect.right = space;
            outRect.top = space;
            outRect.left = space;

        }
    }
}

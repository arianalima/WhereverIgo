package com.example.bernardojr.whereverigo.gui;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bernardojr.whereverigo.R;
import com.example.bernardojr.whereverigo.dominio.Local;

import java.util.List;

public class LocaisSugeridosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageView banner;
    private LocaisAdapter adapter;
    private List<Local> locais;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locais_sugeridos);
        Local l = new Local();
        locais = l.listarLocais();
        recyclerView = (RecyclerView) findViewById(R.id.locais_recycler_view);
        banner = (ImageView) findViewById(R.id.locais_banner);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        adapter = new LocaisAdapter(locais);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpacesItemDecoration(24));

    }


    private class LocaisAdapter extends RecyclerView.Adapter<LocaisHolder>{

        private List<Local> locais;



        public LocaisAdapter(List<Local> locais) {
            this.locais = locais;
        }

        @Override
        public LocaisHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//
//            // Inflate the custom layout
//            View contactView = inflater.inflate(R.layout.locais_item_adapter, parent, false);

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.locais_item_adapter, parent, false);

            // Return a new holder instance
            LocaisHolder view = new LocaisHolder(v);
            return view;
        }

        @Override
        public void onBindViewHolder(LocaisHolder holder, int position) {
            holder.cidade.setText(locais.get(position).getCidade());
            holder.estadoPais.setText(locais.get(position).getEstadoPais());
            holder.imagem.setImageResource(locais.get(position).getImagem());
            holder.descricao.setText(locais.get(position).getDescricao());
        }

        @Override
        public int getItemCount() {
            return locais.size();
        }
    }

    private class LocaisHolder extends RecyclerView.ViewHolder{

        private TextView cidade;
        private TextView estadoPais;
        private ImageView imagem;
        private TextView descricao;
        private TextView gostei;
        private TextView naoGostei;

        public LocaisHolder(View itemView) {
            super(itemView);
            cidade = (TextView) itemView.findViewById(R.id.locais_cidade);
            estadoPais = (TextView) itemView.findViewById(R.id.locais_estado_pais);
            imagem = (ImageView) itemView.findViewById(R.id.locais_imagem);
            descricao = (TextView) itemView.findViewById(R.id.locais_descricao);
            gostei = (TextView) itemView.findViewById(R.id.locais_gostei);
            naoGostei = (TextView) itemView.findViewById(R.id.locais_nao_gostei);
        }
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
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




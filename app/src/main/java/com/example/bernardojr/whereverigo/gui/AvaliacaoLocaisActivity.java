package com.example.bernardojr.whereverigo.gui;

import android.content.Context;
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
import com.example.bernardojr.whereverigo.infra.ImagemRetangular;
import com.example.bernardojr.whereverigo.negocio.LocalService;
import com.example.bernardojr.whereverigo.negocio.SessaoUsuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AvaliacaoLocaisActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AvaliacaoLocaisActivity.LocalAdapter adapter;
    private List<Local> locais;
    private static List<Float> notas;
    private String todosLocais;

    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao_locais);

        Intent intent = getIntent();
        String resposta = intent.getExtras().getString("RESULTADO");

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        recyclerView = (RecyclerView) findViewById(R.id.avaliacao_locais_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemViewCacheSize(10);
        recyclerView.setAdapter(null);
        recyclerView.addItemDecoration(new AvaliacaoLocaisActivity.SpacesItemDecoration(24));
        recyclerView.setHasFixedSize(true);

        requestLocais(getApplicationContext(),resposta);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String lugares = "";
                String strNotas = "";
                int id = SessaoUsuario.getInstancia().getPessoaLogada().getId();
                if (locais != null && notas != null && locais.size() == notas.size()){

                    for(int i=0; i<locais.size(); i++){
                        lugares = lugares.concat("" + locais.get(i).getCidade() + "/");
                        strNotas = strNotas.concat("" + notas.get(i) + "/");
                    }
                    sendLocaisComNota(getApplicationContext(),id,lugares,strNotas,todosLocais);

                }

            }

        });

    }

    private class LocalAdapter extends RecyclerView.Adapter<AvaliacaoLocaisActivity.LocalHolder>{

        private List<Local> locais;

        private LocalAdapter(List<Local> locais) {
            this.locais = locais;
        }

        @Override
        public AvaliacaoLocaisActivity.LocalHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.avaliacao_locais_list_adapter, parent, false);

            AvaliacaoLocaisActivity.LocalHolder view = new AvaliacaoLocaisActivity.LocalHolder(v);
            return view;

        }

        @Override
        public void onBindViewHolder(final AvaliacaoLocaisActivity.LocalHolder holder, int position) {

            holder.cidade.setText(locais.get(position).getCidade());
            holder.estadoPais.setText(locais.get(position).getEstadoPais());
            holder.imagem.setImageResource(locais.get(position).getImagem());
            holder.descricao.setText(locais.get(position).getDescricao());
            final int posicao = position;

            holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                public void onRatingChanged(RatingBar ratingBar, float rating,
                                            boolean fromUser) {
                    notas.set(posicao,rating);
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
        private TextView descricao;

        private ImageView imagem;
        private RatingBar ratingBar;


        private LocalHolder(View itemView) {
            super(itemView);
            cidade = (TextView) itemView.findViewById(R.id.local_adapter_cidade);
            estadoPais = (TextView) itemView.findViewById(R.id.local_adapter_estado_pais);
            imagem = (ImagemRetangular) itemView.findViewById(R.id.local_adapter_imagem);
            descricao = (TextView) itemView.findViewById(R.id.local_adapter_descricao);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
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

    public void requestLocais(final Context context, String string){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.31.191:8080/WhereverIGo/rest/LocalService/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LocalService localService = retrofit.create(LocalService.class);

        Call<ArrayList<Local>> locaisCall = localService.getLocaisPorTag(string);

        locaisCall.enqueue(new Callback<ArrayList<Local>>() {
            @Override
            public void onResponse(Call<ArrayList<Local>> call, Response<ArrayList<Local>> response) {
                if(response.isSuccessful()){

                    locais = new ArrayList<>();
                    notas = new ArrayList<>();
                    Local local;
                    ArrayList<Local> novosLocais = response.body();
                    todosLocais = "";

                    for(int i = 0; i < novosLocais.size(); i++){
                        local = novosLocais.get(i);
                        int id = context.getResources().getIdentifier(local.getStrImagem(), "drawable", context.getPackageName());
                        local.setImagem(id);
                        todosLocais = todosLocais.concat(local.getCidade() + "/");
                    }

                    Collections.shuffle(novosLocais);

                    int quantidade;
                    if (novosLocais.size() >= 5) {
                        quantidade = 5;
                    }else {
                        quantidade = novosLocais.size();
                    }
                    for (int i = 0; i < quantidade; i++) {
                        locais.add(novosLocais.get(i));
                        notas.add(0f);
                    }
                    if(locais.size() != 0){
                        adapter = new LocalAdapter(locais);
                        recyclerView.setAdapter(adapter);
                    }

                }else{
                    Toast.makeText(context, response.code() ,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Local>> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
            }

        });

    }

    Gson gson = new GsonBuilder()
            .enableComplexMapKeySerialization()
            .serializeNulls()
            .setPrettyPrinting()
            .setVersion(1.0)
            .setLenient()
            .create();

    public void sendLocaisComNota(final Context context,int id, String lugares, String notas, String todosLocais){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.31.191:8080/WhereverIGo/rest/LocalService/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        LocalService localService = retrofit.create(LocalService.class);

        Call<String> locaisCall = localService.sendLugarComNota(id,lugares,notas,todosLocais);

        locaisCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    if (response.body().equals("sucess")){
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), R.string.erro_failure_metodo,Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(context, response.code() ,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

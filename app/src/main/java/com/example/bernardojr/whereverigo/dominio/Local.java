package com.example.bernardojr.whereverigo.dominio;

import android.widget.ImageView;

import com.example.bernardojr.whereverigo.R;

import java.util.ArrayList;
import java.util.List;


public class Local {
    private String cidade;
    private String estadoPais;
    private int imagem;
    private String descricao;

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstadoPais() {
        return estadoPais;
    }

    public void setEstadoPais(String estadoPais) {
        this.estadoPais = estadoPais;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Local() {
    }

    public Local(String cidade, String estadoPais, int imagem, String descricao) {

        this.cidade = cidade;
        this.estadoPais = estadoPais;
        this.imagem = imagem;
        this.descricao = descricao;
    }

    public List<Local> listarLocais(){
        ArrayList<Local> locais = new ArrayList<>();

        Local recife = new Local("Recife", "Pernambuco - Brasil", R.drawable.recife,
                "Recife é um município brasileiro, capital do estado de Pernambuco, localizado na Região Nordeste do país. Pertence à Mesorregião Metropolitana do Recife e à Microrregião do Recife.");
        locais.add(recife);

        Local olinda = new Local("Olinda", "Pernambuco - Brasil", R.drawable.olinda,
                "Olinda é um município brasileiro do estado de Pernambuco, situado na mesorregião Metropolitana do Recife e na Microrregião do Recife, Região Nordeste do país.");
        locais.add(olinda);

        return locais;
    }
}

package com.example.bernardojr.whereverigo.gui;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bernardojr.whereverigo.R;
import com.example.bernardojr.whereverigo.dominio.Local;

import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private ImageView banner;
    private HomeActivity.LocaisAdapter adapter;
    private List<Local> locais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Local l = new Local();
        locais = l.listarLocais();
        recyclerView = (RecyclerView) findViewById(R.id.locais_recycler_view);
        banner = (ImageView) findViewById(R.id.locais_banner);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        adapter = new HomeActivity.LocaisAdapter(locais);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new HomeActivity.SpacesItemDecoration(24));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            startQuestionarioActivity();
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startQuestionarioActivity() {
        Intent i = new Intent(HomeActivity.this,QuestionarioActivity.class);
        //Intent i = new Intent(HomeActivity.this,AvaliacaoLocaisActivity.class);//alterar dps
        startActivity(i);
    }

    private class LocaisAdapter extends RecyclerView.Adapter<HomeActivity.LocaisHolder>{

        private List<Local> locais;



        public LocaisAdapter(List<Local> locais) {
            this.locais = locais;
        }

        @Override
        public HomeActivity.LocaisHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//
//            // Inflate the custom layout
//            View contactView = inflater.inflate(R.layout.locais_item_adapter, parent, false);

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.locais_item_adapter, parent, false);

            // Return a new holder instance
            HomeActivity.LocaisHolder view = new HomeActivity.LocaisHolder(v);
            return view;
        }

        @Override
        public void onBindViewHolder(HomeActivity.LocaisHolder holder, int position) {
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

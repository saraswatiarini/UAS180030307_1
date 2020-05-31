package com.bi183.saraswatiarini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnLihat, btnTambah, btnKelola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnLihat = (Button)findViewById(R.id.btn_lihat);
        btnTambah = (Button)findViewById(R.id.btn_tambah);
        btnKelola = (Button)findViewById(R.id.btn_kelola);

        // tombol lihat daftar buku
        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LihatActivity.class);
                startActivity(intent);
            }
        });

        // tombol tambah daftar buku
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TambahActivity.class);
                startActivity(intent);
            }
        });

        // tombol kelola daftar buku
        btnKelola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KelolaActivity.class);
                startActivity(intent);
            }
        });
    }
}

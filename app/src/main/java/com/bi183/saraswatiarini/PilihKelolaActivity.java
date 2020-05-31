package com.bi183.saraswatiarini;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PilihKelolaActivity extends AppCompatActivity {

    TextView tvJudul, tvPenulis, tvPenerbit, tvTahun, tvTebal, tvIsbn;
    ImageView imgBuku;
    Button btnPerbaharui, btnHapus, btnBatal;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_kelola);

        tvJudul = (TextView)findViewById(R.id.tv_judul);
        tvPenulis = (TextView)findViewById(R.id.tv_penulis);
        tvPenerbit = (TextView)findViewById(R.id.tv_penerbit);
        tvTahun = (TextView)findViewById(R.id.tv_tahun);
        tvTebal = (TextView)findViewById(R.id.tv_tebal);
        tvIsbn = (TextView)findViewById(R.id.tv_isbn);
        imgBuku = (ImageView)findViewById(R.id.img_buku);
        btnPerbaharui = (Button)findViewById(R.id.btn_Perbaharui);
        btnHapus = (Button)findViewById(R.id.btn_hapus);
        btnBatal = (Button)findViewById(R.id.btn_batal);
        db = new DatabaseHandler(this);

        Intent imgId = getIntent();
        final Integer id = Integer.parseInt(imgId.getExtras().getString("id"));
        imgBuku.setImageBitmap(db.getImage(id));

        // menampilkan detail data buku yang dipilih berdasarkan id
        Cursor res = db.getBukuData(id);
        if (res.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            while (res.moveToNext()) {
                tvJudul.setText(res.getString(0));
                tvPenulis.setText(res.getString(1));
                tvPenerbit.setText(res.getString(2));
                tvTahun.setText(res.getString(3));
                tvTebal.setText(res.getString(4));
                tvIsbn.setText(res.getString(5));
            }
        }

        // tombol menuju tampilan perbaharui data
        btnPerbaharui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PerbaharuiActivity.class);
                intent.putExtra("id", id.toString());
                startActivity(intent);
                finish();
            }
        });

        // tombol untuk hapus data
        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.deleteData(id)) {
                    Toast.makeText(getApplicationContext(), "Hapus data berhasil", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), KelolaActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Hapus data gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // tombol batal
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KelolaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

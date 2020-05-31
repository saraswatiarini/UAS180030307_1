package com.bi183.saraswatiarini;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LihatActivity extends AppCompatActivity {
    DatabaseHandler db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat);

        db = new DatabaseHandler(this);
        // mendapatkan data buku dan menampilkan pada listview
        final ListView lvBuku = (ListView)findViewById(R.id.lv_buku);
        ArrayList<Buku> bukuList = db.getList();
        ListBukuAdapter listBukuAdapter = new ListBukuAdapter(getApplicationContext(), R.layout.activity_itembuku, bukuList);
        lvBuku.setAdapter(listBukuAdapter);

        // menampilkan detail buku dari listview yang di tap
        lvBuku.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LinearLayout linearLayoutParent = (LinearLayout) view;
                TextView tvId = (TextView) linearLayoutParent.getChildAt(1);
                String idBuku = tvId.getText().toString();

                Intent intent = new Intent(getApplicationContext(), PilihBukuActivity.class);
                intent.putExtra("id", idBuku);
                startActivity(intent);
            }
        });
    }
}

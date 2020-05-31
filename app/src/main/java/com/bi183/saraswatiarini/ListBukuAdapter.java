package com.bi183.saraswatiarini;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListBukuAdapter extends ArrayAdapter<Buku> {
    private Context mContext;
    int mResource;
    private static class ViewHolder {
        ImageView imgThumbbuku;
        TextView tvId, tvTitle, tvPenulis, tvIsbn;
    }
    public ListBukuAdapter(Context context, int resource, ArrayList<Buku> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Integer id = getItem(position).getIdbuku();
        String judul = getItem(position).getJudul();
        String penulis = getItem(position).getPenulis();
        String penerbit = getItem(position).getPenerbit();
        String tahun = getItem(position).getTahun();
        String tebal = getItem(position).getTebal();
        String isbn = getItem(position).getIsbn();
        byte[] img = getItem(position).getImg();

        Buku buku = new Buku(id, judul, penulis, penerbit, tahun, tebal, isbn, img);
        View res;
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();
            holder.tvId = (TextView)convertView.findViewById(R.id.tv_id);
            holder.tvTitle = (TextView)convertView.findViewById(R.id.tv_judul);
            holder.tvPenulis = (TextView)convertView.findViewById(R.id.tv_penulis);
            holder.tvIsbn = (TextView)convertView.findViewById(R.id.tv_isbn);
            holder.imgThumbbuku = (ImageView)convertView.findViewById(R.id.img_thumbbuku);
            res = convertView;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
            res = convertView;
        }
        holder.tvId.setText(buku.getIdbuku().toString());
        holder.tvTitle.setText(buku.getJudul());
        holder.tvPenulis.setText(buku.getPenulis());
        holder.tvIsbn.setText(buku.getIsbn());
        holder.imgThumbbuku.setImageBitmap(BitmapFactory.decodeByteArray(buku.getImg(), 0, buku.getImg().length));
        return convertView;
    }
}

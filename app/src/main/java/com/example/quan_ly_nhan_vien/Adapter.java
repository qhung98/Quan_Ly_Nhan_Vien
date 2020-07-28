package com.example.quan_ly_nhan_vien;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<NhanVien> {
    private final Context context;
    private final ArrayList<NhanVien> list;

    public Adapter(Context context, ArrayList<NhanVien> list){
        super(context, R.layout.row_layout, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);

        TextView tvTen, tvGioiTinh, tvNamSinh, tvQueQuan, tvHocVan, tvChuyenMon, tvDaoTao, tvLamViec, tvPhong;
        Button btnSuaNhanVien;

        tvTen = view.findViewById(R.id.tvTen);
        tvGioiTinh = view.findViewById(R.id.tvGioiTinh);
        tvNamSinh = view.findViewById(R.id.tvNamSinh);
        tvQueQuan = view.findViewById(R.id.tvQueQuan);
        tvHocVan = view.findViewById(R.id.tvHocVan);
        tvChuyenMon = view.findViewById(R.id.tvChuyenMon);
        tvDaoTao = view.findViewById(R.id.tvDaoTao);
        tvLamViec = view.findViewById(R.id.tvLamViec);
        tvPhong = view.findViewById(R.id.tvPhong);
        btnSuaNhanVien = view.findViewById(R.id.btnSuaNhanVien);

        tvTen.setText(list.get(position).getTen());
        tvGioiTinh.setText(list.get(position).getGioiTinh());
        tvNamSinh.setText(String.valueOf(list.get(position).getNamSinh()));
        tvQueQuan.setText(list.get(position).getQueQuan());
        tvHocVan.setText(list.get(position).getHocVan());
        tvChuyenMon.setText(list.get(position).getChuyenMon());
        tvDaoTao.setText(list.get(position).getDaoTao());
        tvLamViec.setText(String.valueOf(list.get(position).getLamViec()));
        tvPhong.setText(list.get(position).getPhong());

        final int id = list.get(position).getId();

        btnSuaNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SuaNhanVien.class);
                intent.putExtra("id", id);
                ((Activity)context).startActivityForResult(intent, 2);

            }
        });

        return view;
    }
}

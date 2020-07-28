package com.example.quan_ly_nhan_vien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ThongKe extends AppCompatActivity {
    Button btnTuoi, btnGioiTinh, btnChuyenMon, btnShow;
    TextView tvSoLuong;
    Spinner spTuoi, spGioiTinh, spChuyenMon;
    ListView listView;

    String[] listTuoi = {"20-40", "40-60"};
    String[] listGioiTinh = {"NAM", "NU"};
    String[] listChuyenMon = {"CNTT", "KE TOAN", "MARKETING"};

    String tuoi, gioiTinh, chuyenMon;
    int option=0;

    ArrayList<NhanVien> list;
    DatabaseHelper db;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);

        btnTuoi = findViewById(R.id.btnTuoi);
        btnGioiTinh = findViewById(R.id.btnGioiTinh);
        btnChuyenMon = findViewById(R.id.btnChuyenMon);
        btnShow = findViewById(R.id.btnShow);
        tvSoLuong = findViewById(R.id.tvSoLuong);
        spTuoi = findViewById(R.id.spTuoi);
        spGioiTinh = findViewById(R.id.spGioiTinh);
        spChuyenMon = findViewById(R.id.spChuyenMon);
        listView = findViewById(R.id.listThongKe);

        db = new DatabaseHelper(this);

        spTuoi.setVisibility(View.GONE);
        spGioiTinh.setVisibility(View.GONE);
        spChuyenMon.setVisibility(View.GONE);

        ArrayAdapter<String> adapterTuoi = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listTuoi);
        spTuoi.setAdapter(adapterTuoi);

        spTuoi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tuoi = listTuoi[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapterGioiTinh = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listGioiTinh);
        spGioiTinh.setAdapter(adapterGioiTinh);

        spGioiTinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gioiTinh = listGioiTinh[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapterChuyenMon = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listChuyenMon);
        spChuyenMon.setAdapter(adapterChuyenMon);

        spChuyenMon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chuyenMon = listChuyenMon[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnTuoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spTuoi.setVisibility(View.VISIBLE);
                spGioiTinh.setVisibility(View.GONE);
                spChuyenMon.setVisibility(View.GONE);

                option = 1;
            }
        });

        btnGioiTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spTuoi.setVisibility(View.GONE);
                spGioiTinh.setVisibility(View.VISIBLE);
                spChuyenMon.setVisibility(View.GONE);

                option = 2;
            }
        });

        btnChuyenMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spTuoi.setVisibility(View.GONE);
                spGioiTinh.setVisibility(View.GONE);
                spChuyenMon.setVisibility(View.VISIBLE);

                option = 3;
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (option){
                    case 1:
                        list = db.thongKeTuoi(tuoi);
                        tvSoLuong.setText(String.valueOf(list.size()));
                        adapter = new Adapter(ThongKe.this, list);
                        listView.setAdapter(adapter);
                        break;
                    case 2:
                        list = db.thongKeGioiTinh(gioiTinh);
                        tvSoLuong.setText(String.valueOf(list.size()));
                        adapter = new Adapter(ThongKe.this, list);
                        listView.setAdapter(adapter);
                        break;
                    case 3:
                        list = db.thongKeChuyenMon(chuyenMon);
                        tvSoLuong.setText(String.valueOf(list.size()));
                        adapter = new Adapter(ThongKe.this, list);
                        listView.setAdapter(adapter);
                        break;
                }
            }
        });
    }
}

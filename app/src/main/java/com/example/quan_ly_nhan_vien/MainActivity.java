package com.example.quan_ly_nhan_vien;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnThem, btnLietKe, btnThongKe;
    TextView textView;
    ListView listView;

    DatabaseHelper db;
    ArrayList<NhanVien> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnThem = findViewById(R.id.btnThemNhanVien);
        btnLietKe = findViewById(R.id.btnLietKe);
        btnThongKe = findViewById(R.id.btnThongKe);
        listView = findViewById(R.id.listNhanVien);
        textView = findViewById(R.id.text);

        db = new DatabaseHelper(this);

        list = db.getAllNhanVien();
//        textView.setText(list.get(0).getTen());

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getBaseContext(), ThemNhanVien.class),1);
            }
        });

        btnLietKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), LietKe.class));
            }
        });

        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), ThongKe.class));
            }
        });

        Adapter adapter = new Adapter(this, list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 1) {
            startActivity(new Intent(getBaseContext(), MainActivity.class));
            this.finish();
        }

        if(resultCode == RESULT_OK && requestCode == 2) {
            startActivity(new Intent(getBaseContext(), MainActivity.class));
            this.finish();
        }
    }
}

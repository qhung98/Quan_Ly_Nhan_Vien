package com.example.quan_ly_nhan_vien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class LietKe extends AppCompatActivity {
    ListView listView;
    TextView text2;
    ArrayList<NhanVien> list;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liet_ke);

        listView = findViewById(R.id.listLietKe);
        text2 = findViewById(R.id.text2);

        db = new DatabaseHelper(this);

        list = db.lietKe();

        text2.setText(String.valueOf(list.size()));

        Adapter adapter = new Adapter(this, list);
        listView.setAdapter(adapter);
    }
}

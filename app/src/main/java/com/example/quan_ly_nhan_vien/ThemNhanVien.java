package com.example.quan_ly_nhan_vien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ThemNhanVien extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    EditText edTen, edQueQuan, edHocVan, edDaoTao, edLamViec;
    Spinner spGioiTinh, spChuyenMon, spPhong;
    Button btnNamSinh, btnThem;
    TextView tvYear;

    DatabaseHelper db;

    String[] listGioiTinh = {"NAM", "NU"};
    String[] listChuyenMon = {"CNTT", "KE TOAN", "MARKETING"};
    String[] listPhong = {"PHAN MEM", "KIEM DINH", "KINH DOANH"};
    String gioiTinh, chuyenMon, phong;
    int namSinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them__nhan__vien);

        edTen = findViewById(R.id.edTen);
        spGioiTinh = findViewById(R.id.spGioiTinh);
        btnNamSinh = findViewById(R.id.btnNamSinh);
        edQueQuan = findViewById(R.id.edQueQuan);
        edHocVan = findViewById(R.id.edHocVan);
        spChuyenMon = findViewById(R.id.spChuyenMon);
        edDaoTao = findViewById(R.id.edDaoTao);
        edLamViec = findViewById(R.id.edLamViec);
        spPhong = findViewById(R.id.spPhong);
        btnThem = findViewById(R.id.btnThem);
        tvYear = findViewById(R.id.tvYear);

        db = new DatabaseHelper(this);

        btnNamSinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ThemNhanVien.this, ThemNhanVien.this,
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
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

        ArrayAdapter<String> adapterPhong = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listPhong);
        spPhong.setAdapter(adapterPhong);

        spPhong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                phong = listPhong[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien nhanVien = new NhanVien(edTen.getText().toString(), gioiTinh, namSinh, edQueQuan.getText().toString(),
                        edHocVan.getText().toString(), chuyenMon, edDaoTao.getText().toString(), Integer.parseInt(edLamViec.getText().toString()), phong);

                db.themNhanVien(nhanVien);
                Toast.makeText(getBaseContext(), "THEM THANH CONG", Toast.LENGTH_SHORT).show();

                setResult(RESULT_OK, null);
                finish();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        namSinh = year;
        tvYear.setText(String.valueOf(year));
    }
}

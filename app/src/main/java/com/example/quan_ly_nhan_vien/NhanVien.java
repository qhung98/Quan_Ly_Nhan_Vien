package com.example.quan_ly_nhan_vien;

public class NhanVien {
    private int id;
    private String ten, gioiTinh;
    private int namSinh;
    private String queQuan, hocVan, chuyenMon, daoTao;
    private int lamViec;
    private String phong;

    public NhanVien() {
    }

    public NhanVien(int id, String ten, String gioiTinh, int namSinh, String queQuan, String hocVan, String chuyenMon, String daoTao, int lamViec, String phong) {
        this.id = id;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.namSinh = namSinh;
        this.queQuan = queQuan;
        this.hocVan = hocVan;
        this.chuyenMon = chuyenMon;
        this.daoTao = daoTao;
        this.lamViec = lamViec;
        this.phong = phong;
    }

    public NhanVien(String ten, String gioiTinh, int namSinh, String queQuan, String hocVan, String chuyenMon, String daoTao, int lamViec, String phong) {
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.namSinh = namSinh;
        this.queQuan = queQuan;
        this.hocVan = hocVan;
        this.chuyenMon = chuyenMon;
        this.daoTao = daoTao;
        this.lamViec = lamViec;
        this.phong = phong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLamViec() {
        return lamViec;
    }

    public void setLamViec(int lamViec) {
        this.lamViec = lamViec;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getHocVan() {
        return hocVan;
    }

    public void setHocVan(String hocVan) {
        this.hocVan = hocVan;
    }

    public String getChuyenMon() {
        return chuyenMon;
    }

    public void setChuyenMon(String chuyenMon) {
        this.chuyenMon = chuyenMon;
    }

    public String getDaoTao() {
        return daoTao;
    }

    public void setDaoTao(String daoTao) {
        this.daoTao = daoTao;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }
}

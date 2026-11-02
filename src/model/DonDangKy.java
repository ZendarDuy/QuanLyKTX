/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Timestamp;

public class DonDangKy {
    private int maDon;
    private Timestamp ngayNop;
    private String doiTuongUuTien;
    private String tepMinhChung;
    private String trangThai;
    private String lyDoTuChoi;
    private int maLoaiPhong;
    private String maSinhVien;

    public DonDangKy() {}

    // Getters and Setters
    public int getMaDon() { return maDon; }
    public void setMaDon(int maDon) { this.maDon = maDon; }
    public Timestamp getNgayNop() { return ngayNop; }
    public void setNgayNop(Timestamp ngayNop) { this.ngayNop = ngayNop; }
    public String getDoiTuongUuTien() { return doiTuongUuTien; }
    public void setDoiTuongUuTien(String doiTuongUuTien) { this.doiTuongUuTien = doiTuongUuTien; }
    public String getTepMinhChung() { return tepMinhChung; }
    public void setTepMinhChung(String tepMinhChung) { this.tepMinhChung = tepMinhChung; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
    public String getLyDoTuChoi() { return lyDoTuChoi; }
    public void setLyDoTuChoi(String lyDoTuChoi) { this.lyDoTuChoi = lyDoTuChoi; }
    public int getMaLoaiPhong() { return maLoaiPhong; }
    public void setMaLoaiPhong(int maLoaiPhong) { this.maLoaiPhong = maLoaiPhong; }
    public String getMaSinhVien() { return maSinhVien; }
    public void setMaSinhVien(String maSinhVien) { this.maSinhVien = maSinhVien; }
}
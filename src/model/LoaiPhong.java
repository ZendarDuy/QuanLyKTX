/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class LoaiPhong {
    private int maLoaiPhong;
    private String tenLoaiPhong;
    private int soLuongNguoiToiDa;
    private double donGiaThang;
    private String moTa;

    // Constructors
    public LoaiPhong() {}

    public LoaiPhong(int maLoaiPhong, String tenLoaiPhong, int soLuongNguoiToiDa, double donGiaThang, String moTa) {
        this.maLoaiPhong = maLoaiPhong;
        this.tenLoaiPhong = tenLoaiPhong;
        this.soLuongNguoiToiDa = soLuongNguoiToiDa;
        this.donGiaThang = donGiaThang;
        this.moTa = moTa;
    }

    // Getters and Setters...
    public int getMaLoaiPhong() { return maLoaiPhong; }
    public void setMaLoaiPhong(int maLoaiPhong) { this.maLoaiPhong = maLoaiPhong; }
    public String getTenLoaiPhong() { return tenLoaiPhong; }
    public void setTenLoaiPhong(String tenLoaiPhong) { this.tenLoaiPhong = tenLoaiPhong; }
    public double getDonGiaThang() { return donGiaThang; }

    // Ghi đè phương thức toString để hiển thị tên loại phòng đẹp mắt trên JComboBox
    @Override
    public String toString() {
        return tenLoaiPhong + " - " + String.format("%,.0f VNĐ", donGiaThang) + "/tháng";
    }
}

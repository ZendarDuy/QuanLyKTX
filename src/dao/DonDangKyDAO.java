/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.DonDangKy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DonDangKyDAO {
    
    // Kiểm tra xem sinh viên đã có đơn Chờ duyệt hoặc đang lưu trú hay chưa (Luồng rẽ nhánh 3.2.1 UC01)
    public boolean checkDonTonTaiHoacLuuTru(String maSinhVien) throws Exception {
        String sql = "SELECT COUNT(*) FROM DONDANGKY WHERE MaSinhVien = ? AND TrangThai IN ('Chờ duyệt', 'Đã duyệt')";
        // Trong thực tế sẽ cần JOIN thêm với bảng LUUTRU để kiểm tra tình trạng cư trú hiện tại
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maSinhVien);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    // Lưu đơn đăng ký mới vào bảng DONDANGKY
    public boolean insertDonDangKy(DonDangKy don) throws Exception {
        String sql = "INSERT INTO DONDANGKY (NgayNop, DoiTuongUuTien, TepMinhChung, TrangThai, MaLoaiPhong, MaSinhVien) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, don.getNgayNop());
            ps.setString(2, don.getDoiTuongUuTien());
            ps.setString(3, don.getTepMinhChung());
            ps.setString(4, don.getTrangThai());
            ps.setInt(5, don.getMaLoaiPhong());
            ps.setString(6, don.getMaSinhVien());
            
            return ps.executeUpdate() > 0;
        }
    }
}
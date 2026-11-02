/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.DonDangKyDAO;
import model.DonDangKy;
import java.io.File;
import java.sql.Timestamp;

public class DonDangKyService {
    private DonDangKyDAO donDangKyDAO;

    public DonDangKyService() {
        this.donDangKyDAO = new DonDangKyDAO();
    }

    // Logic xử lý nộp đơn đăng ký (Luồng cơ bản & rẽ nhánh UC01)
    public String noptDonDangKy(DonDangKy don, File fileMinhChung) throws Exception {
        // 1. Kiểm tra tồn tại đơn cũ
        if (donDangKyDAO.checkDonTonTaiHoacLuuTru(don.getMaSinhVien())) {
            return "LỖI: Bạn đã có đơn đăng ký đang chờ xử lý hoặc hiện đang cư trú tại KTX.";
        }

        // 2. Kiểm tra tệp minh chứng (Luồng rẽ nhánh 3.2.2 UC01)
        if (fileMinhChung != null) {
            long fileSizeInMB = fileMinhChung.length() / (1024 * 1024);
            if (fileSizeInMB > 5) {
                return "LỖI: Tệp minh chứng không hợp lệ, kích thước vượt quá 5MB.";
            }
            String fileName = fileMinhChung.getName().toLowerCase();
            if (!fileName.endsWith(".pdf") && !fileName.endsWith(".jpg") && !fileName.endsWith(".png")) {
                return "LỖI: Tệp minh chứng không hợp lệ, chỉ hỗ trợ PDF/JPG/PNG.";
            }
            // Giả lập lưu file vào hệ thống và lấy đường dẫn
            don.setTepMinhChung("/uploads/minhchung/" + fileMinhChung.getName());
        }

        // 3. Thiết lập thông tin mặc định khi tạo mới
        don.setNgayNop(new Timestamp(System.currentTimeMillis()));
        don.setTrangThai("Chờ duyệt");
        don.setLyDoTuChoi(null);

        // 4. Gọi DAO lưu xuống CSDL
        boolean isSuccess = donDangKyDAO.insertDonDangKy(don);
        if (isSuccess) {
            return "SUCCESS";
        } else {
            return "LỖI: Không thể lưu dữ liệu vào cơ sở dữ liệu.";
        }
    }
}
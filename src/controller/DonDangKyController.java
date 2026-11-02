package controller;

import model.DonDangKy;
import model.LoaiPhong;
import service.DonDangKyService;
import view.FormDangKyUI;
import javax.swing.JOptionPane;
import java.io.File;

public class DonDangKyController {
    private FormDangKyUI view;
    private DonDangKyService service;

    public DonDangKyController(FormDangKyUI view) {
        this.view = view;
        this.service = new DonDangKyService();
        initController();
    }

    private void initController() {
        // Lắng nghe sự kiện click nút Gửi đơn đăng ký từ View
        view.getBtnSubmit().addActionListener(e -> handleDangKy());
    }

    private void handleDangKy() {
        try {
            // Thu thập dữ liệu từ giao diện
            String maSV = view.getTxtMaSV().getText().trim();
            String doiTuongUuTien = view.getTxtDoiTuong().getText().trim();
            LoaiPhong loaiPhong = (LoaiPhong) view.getCbLoaiPhong().getSelectedItem();
            File fileMinhChung = view.getSelectedFile();

            if (maSV.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập Mã Sinh Viên!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Gán dữ liệu vào Model
            DonDangKy don = new DonDangKy();
            don.setMaSinhVien(maSV); // Trong thực tế lấy từ Session đăng nhập
            don.setDoiTuongUuTien(doiTuongUuTien);
            don.setMaLoaiPhong(loaiPhong.getMaLoaiPhong());

            // Gọi Service xử lý nghiệp vụ
            String result = service.noptDonDangKy(don, fileMinhChung);

            // Xử lý phản hồi (Luồng cơ bản 3.1.2 UC01)
            if (result.equals("SUCCESS")) {
                JOptionPane.showMessageDialog(view, "Nộp đơn đăng ký thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                view.resetForm();
            } else {
                JOptionPane.showMessageDialog(view, result, "Lỗi nộp đơn", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Lỗi kết nối máy chủ: " + ex.getMessage(), "Lỗi Hệ Thống", JOptionPane.ERROR_MESSAGE);
        }
    }
}
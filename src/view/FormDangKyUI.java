package view;

import model.LoaiPhong;
import controller.DonDangKyController;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FormDangKyUI extends JFrame {
    private JTextField txtMaSV;
    private JTextField txtDoiTuong;
    private JComboBox<LoaiPhong> cbLoaiPhong;
    private JButton btnChooseFile, btnSubmit;
    private JLabel lblFileName;
    private File selectedFile;

    public FormDangKyUI() {
        setTitle("Hệ thống Quản lý KTX HaUI - Đăng ký nội trú");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        
        // Khởi tạo Controller điều phối màn hình này
        new DonDangKyController(this);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Mã Sinh Viên:"));
        txtMaSV = new JTextField(); // Thực tế trường này có thể disabled nếu lấy từ account
        panel.add(txtMaSV);

        panel.add(new JLabel("Loại phòng mong muốn:"));
        cbLoaiPhong = new JComboBox<>();
        // Demo dữ liệu (Thực tế Controller sẽ gọi LoaiPhongDAO để nạp)
        cbLoaiPhong.addItem(new LoaiPhong(1, "Cơ bản 4 người", 4, 500000, ""));
        cbLoaiPhong.addItem(new LoaiPhong(2, "Dịch vụ 2 người", 2, 1000000, ""));
        panel.add(cbLoaiPhong);

        panel.add(new JLabel("Đối tượng ưu tiên (Nếu có):"));
        txtDoiTuong = new JTextField();
        panel.add(txtDoiTuong);

        panel.add(new JLabel("Minh chứng ưu tiên:"));
        JPanel filePanel = new JPanel(new BorderLayout());
        btnChooseFile = new JButton("Chọn tệp...");
        lblFileName = new JLabel("Chưa chọn tệp (PDF/JPG)");
        filePanel.add(btnChooseFile, BorderLayout.WEST);
        filePanel.add(lblFileName, BorderLayout.CENTER);
        panel.add(filePanel);

        btnSubmit = new JButton("Gửi đơn đăng ký");
        btnSubmit.setBackground(new Color(0, 102, 204));
        btnSubmit.setForeground(Color.WHITE);
        panel.add(new JLabel()); // Ô trống
        panel.add(btnSubmit);

        add(panel);

        // Sự kiện chọn file
        btnChooseFile.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                lblFileName.setText(selectedFile.getName());
            }
        });
    }

    // Expose các thành phần cho Controller truy cập
    public JTextField getTxtMaSV() { return txtMaSV; }
    public JTextField getTxtDoiTuong() { return txtDoiTuong; }
    public JComboBox<LoaiPhong> getCbLoaiPhong() { return cbLoaiPhong; }
    public JButton getBtnSubmit() { return btnSubmit; }
    public File getSelectedFile() { return selectedFile; }

    public void resetForm() {
        txtMaSV.setText("");
        txtDoiTuong.setText("");
        cbLoaiPhong.setSelectedIndex(0);
        lblFileName.setText("Chưa chọn tệp (PDF/JPG)");
        selectedFile = null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormDangKyUI().setVisible(true));
    }
}
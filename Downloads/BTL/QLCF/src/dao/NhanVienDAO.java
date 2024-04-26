package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entiy.NhanVien;

public class NhanVienDAO {
    private Connection connection;

    public NhanVienDAO(Connection connection) {
        this.connection = connection;
    }

    // Lấy danh sách nhân viên từ cơ sở dữ liệu
    public List<NhanVien> getAllNhanVien() throws SQLException {
        List<NhanVien> nhanVienList = new ArrayList<>();
        String sql = "SELECT * FROM QLNV";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(rs.getString("maNV"));
                nhanVien.setTenNV(rs.getString("tenNV"));
                nhanVien.setGioiTinh(rs.getString("gioiTinh"));
                nhanVien.setNgaySinh(rs.getDate("ngaySinh"));
                nhanVien.setSdt(rs.getString("sdt"));
                nhanVien.setDiaChi(rs.getString("diaChi"));
                nhanVien.setTaiKhoan(rs.getString("taiKhoan"));
                nhanVien.setMatKhau(rs.getString("matKhau"));
                nhanVienList.add(nhanVien);
            }
        }
        return nhanVienList;
    }

    // Thêm một nhân viên vào cơ sở dữ liệu
    public void addNhanVien(NhanVien nhanVien) throws SQLException {
        String sql = "INSERT INTO QLNV(maNV, tenNV, gioiTinh, ngaySinh, sdt, diaChi, taiKhoan, matKhau) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nhanVien.getMaNV());
            ps.setString(2, nhanVien.getTenNV());
            ps.setString(3, nhanVien.getGioiTinh());
            ps.setDate(4, new Date(nhanVien.getNgaySinh().getTime()));
            ps.setString(5, nhanVien.getSdt());
            ps.setString(6, nhanVien.getDiaChi());
            ps.setString(7, nhanVien.getTaiKhoan());
            ps.setString(8, nhanVien.getMatKhau());
            ps.executeUpdate();
        }
    }

    // Xóa một nhân viên khỏi cơ sở dữ liệu
    public void deleteNhanVien(String maNV) throws SQLException {
        String sql = "DELETE FROM QLNV WHERE maNV = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, maNV);
            ps.executeUpdate();
        }
    }

    // Cập nhật thông tin của một nhân viên trong cơ sở dữ liệu
    public void updateNhanVien(NhanVien nhanVien) throws SQLException {
        String sql = "UPDATE QLNV SET tenNV=?, gioiTinh=?, ngaySinh=?, sdt=?, diaChi=?, taiKhoan=?, matKhau=? WHERE maNV=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nhanVien.getTenNV());
            ps.setString(2, nhanVien.getGioiTinh());
            //ps.setDate(3, new Date(nhanVien.getNgaySinh().getTime()));
            if (nhanVien.getNgaySinh() != null) {
                ps.setDate(3, new java.sql.Date(nhanVien.getNgaySinh().getTime()));
            } else {
                ps.setNull(3, java.sql.Types.DATE); // Set ngaySinh to NULL in the database if it's null in the object
            }
            ps.setString(4, nhanVien.getSdt());
            ps.setString(5, nhanVien.getDiaChi());
            ps.setString(6, nhanVien.getTaiKhoan());
            ps.setString(7, nhanVien.getMatKhau());
            ps.setString(8, nhanVien.getMaNV());
            ps.executeUpdate();
        }
    }
    //tìm nhan viên
    public List<NhanVien> searchNhanVien(String searchTerm) throws SQLException {
        List<NhanVien> searchResults = new ArrayList<>();
        String sql = "SELECT * FROM QLNV WHERE maNV LIKE ? OR tenNV LIKE ? OR gioiTinh LIKE ? OR ngaySinh LIKE ? OR sdt LIKE ? OR diaChi LIKE ? OR taiKhoan LIKE ? OR matKhau LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // Setting search parameters
            for (int i = 1; i <= 8; i++) {
                ps.setString(i, "%" + searchTerm + "%");
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(rs.getString("maNV"));
                nhanVien.setTenNV(rs.getString("tenNV"));
                nhanVien.setGioiTinh(rs.getString("gioiTinh"));
                nhanVien.setNgaySinh(rs.getDate("ngaySinh"));
                nhanVien.setSdt(rs.getString("sdt"));
                nhanVien.setDiaChi(rs.getString("diaChi"));
                nhanVien.setTaiKhoan(rs.getString("taiKhoan"));
                nhanVien.setMatKhau(rs.getString("matKhau"));
                searchResults.add(nhanVien);
            }
        }
        return searchResults;
    }
}

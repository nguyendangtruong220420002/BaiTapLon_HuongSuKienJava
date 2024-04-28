package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entiy.DatBan;



public class DatBanDAO {
    private Connection connection;

    public DatBanDAO(Connection connection) {
        this.connection = connection;
    }

    // Lấy danh sách đặt bàn từ cơ sở dữ liệu
    public List<DatBan> getAllDatBan() throws SQLException {
        List<DatBan> datBanList = new ArrayList<>();
        String sql = "SELECT * FROM DatBan";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DatBan datBan = new DatBan();
                datBan.setTenKH(rs.getString("tenKH"));
                datBan.setSdt(rs.getString("sdt"));
                datBan.setBan(rs.getInt("ban"));
                datBan.setThoiGian(rs.getString("thoiGian"));
                datBan.setNgay(rs.getDate("ngay"));
                datBan.setThanhToan(rs.getString("thanhToan"));
                datBan.setGhiChu(rs.getString("ghiChu"));
                datBanList.add(datBan);
            }
        }
        return datBanList;
    }

    // Thêm một đặt bàn vào cơ sở dữ liệu
    public void addDatBan(DatBan datBan) throws SQLException {
        String sql = "INSERT INTO DatBan (tenKH, sdt, ban, thoiGian, ngay, thanhToan, ghiChu) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, datBan.getTenKH());
            ps.setString(2, datBan.getSdt());
            ps.setInt(3, datBan.getBan());
            ps.setString(4, datBan.getThoiGian());
            ps.setDate(5, new java.sql.Date(datBan.getNgay().getTime()));
            ps.setString(6, datBan.getThanhToan());
            ps.setString(7, datBan.getGhiChu());
            ps.executeUpdate();
        }
    }

    // Xóa một đặt bàn khỏi cơ sở dữ liệu
    public void deleteDatBan(String sdt) throws SQLException {
        String sql = "DELETE FROM DatBan WHERE sdt=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, sdt);
            ps.executeUpdate();
        }
    }

    // Cập nhật thông tin của một đặt bàn trong cơ sở dữ liệu
    public void updateDatBan(DatBan datBan) throws SQLException {
        String sql = "UPDATE DatBan SET tenKH=?, ban=?, thoiGian=?, ngay=?, thanhToan=?, ghiChu=? WHERE sdt=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, datBan.getTenKH());
            ps.setInt(2, datBan.getBan());
            ps.setString(3, datBan.getThoiGian());
            ps.setDate(4, new java.sql.Date(datBan.getNgay().getTime()));
            ps.setString(5, datBan.getThanhToan());
            ps.setString(6, datBan.getGhiChu());
            ps.setString(7, datBan.getSdt());
            ps.executeUpdate();
        }
    }
}

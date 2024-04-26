package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entiy.Nuoc;

public class Nuoc_Dao {
    private Connection connection;

    public Nuoc_Dao(Connection connection) {
        this.connection = connection;
    }

    // Lấy danh sách nước từ cơ sở dữ liệu
    public List<Nuoc> layDanhSachNuoc() {
        List<Nuoc> danhSachNuoc = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM QLNuoc";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String maNuoc = resultSet.getString("maNuoc");
                String loaiNuoc = resultSet.getString("loaiNuoc");
                String tenNuoc = resultSet.getString("tenNuoc");
                String donVi = resultSet.getString("donVi");
                int soLuong = resultSet.getInt("soLuong");
                double giaBan = resultSet.getDouble("giaBan");

                Nuoc nuoc = new Nuoc(maNuoc, loaiNuoc, tenNuoc, donVi, soLuong, giaBan);
                danhSachNuoc.add(nuoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachNuoc;
    }

    // Thêm một nước vào cơ sở dữ liệu
    public void themNuoc(Nuoc nuoc) {
        String sql = "INSERT INTO QLNuoc(maNuoc, loaiNuoc, tenNuoc, donVi, soLuong, giaBan) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nuoc.getMaNuoc());
            ps.setString(2, nuoc.getLoaiNuoc());
            ps.setString(3, nuoc.getTenNuoc());
            ps.setString(4, nuoc.getDonVi());
            ps.setInt(5, nuoc.getSoLuong());
            ps.setDouble(6, nuoc.getGiaBan());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa một nước khỏi cơ sở dữ liệu
    public void xoaNuoc(String maNuoc) {
        String sql = "DELETE FROM QLNuoc WHERE maNuoc = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, maNuoc);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cập nhật thông tin của một nước trong cơ sở dữ liệu
    public void capNhatNuoc(Nuoc nuoc) {
        String sql = "UPDATE QLNuoc SET loaiNuoc=?, tenNuoc=?, donVi=?, soLuong=?, giaBan=? WHERE maNuoc=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nuoc.getLoaiNuoc());
            ps.setString(2, nuoc.getTenNuoc());
            ps.setString(3, nuoc.getDonVi());
            ps.setInt(4, nuoc.getSoLuong());
            ps.setDouble(5, nuoc.getGiaBan());
            ps.setString(6, nuoc.getMaNuoc());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Tìm kiếm nước
    public List<Nuoc> timNuoc(String searchTerm) {
        List<Nuoc> searchResults = new ArrayList<>();
        String sql = "SELECT * FROM QLNuoc WHERE maNuoc LIKE ? OR loaiNuoc LIKE ? OR tenNuoc LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // Setting search parameters
            String likeTerm = "%" + searchTerm + "%";
            ps.setString(1, likeTerm);
            ps.setString(2, likeTerm);
            ps.setString(3, likeTerm);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNuoc = rs.getString("maNuoc");
                String loaiNuoc = rs.getString("loaiNuoc");
                String tenNuoc = rs.getString("tenNuoc");
                String donVi = rs.getString("donVi");
                int soLuong = rs.getInt("soLuong");
                double giaBan = rs.getDouble("giaBan");
                Nuoc nuoc = new Nuoc(maNuoc, loaiNuoc, tenNuoc, donVi, soLuong, giaBan);
                searchResults.add(nuoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchResults;
    }

    public boolean kiemTraMaNuocTonTai(String maNuoc) {
        String sql = "SELECT COUNT(*) FROM QLNuoc WHERE maNuoc = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, maNuoc);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

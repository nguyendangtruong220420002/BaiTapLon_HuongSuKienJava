package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entiy.Ban;



public class BanDAO {
    private Connection conn;

    public BanDAO(Connection conn) {
        this.conn = conn;
    }

    public Ban getById(String tenNuoc) {
        Ban ban = null;
        String sql = "SELECT * FROM BanHang WHERE ban = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tenNuoc);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ban = new Ban(
                            rs.getInt("ban"),
                            rs.getString("tenNuoc"),
                            rs.getInt("soLuong"),
                            rs.getDouble("thanhTien")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ban;
    }

    public List<Ban> getAll() {
        List<Ban> banList = new ArrayList<>();
        String sql = "SELECT * FROM BanHang";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Ban ban = new Ban(
                        rs.getInt("ban"),
                        rs.getString("tenNuoc"),
                        rs.getInt("soLuong"),
                        rs.getDouble("thanhTien")
                );
                banList.add(ban);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banList;
    }

    public boolean addBan(Ban ban) {
        String sql = "INSERT INTO BanHang (ban, tenNuoc, soLuong, thanhTien) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ban.getBan());
            stmt.setString(2, ban.getTenNuoc());
            stmt.setInt(3, ban.getSoLuong());
            stmt.setDouble(4, ban.getThanhTien());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBan(Ban ban) {
        String sql = "UPDATE BanHang SET tenNuoc = ?, soLuong = ?, thanhTien = ? WHERE ban = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ban.getTenNuoc());
            stmt.setInt(2, ban.getSoLuong());
            stmt.setDouble(3, ban.getThanhTien());
            stmt.setInt(4, ban.getBan());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBan(int banId) {
        String sql = "DELETE FROM BanHang WHERE ban = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, banId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Ban> searchBans(String keyword) {
        List<Ban> banList = new ArrayList<>();
        String sql = "SELECT * FROM BanHang WHERE tenNuoc LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Ban ban = new Ban(
                            rs.getInt("ban"),
                            rs.getString("tenNuoc"),
                            rs.getInt("soLuong"),
                            rs.getDouble("thanhTien")
                    );
                    banList.add(ban);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banList;
    }

    public ResultSet executeCheckQuery() {
        ResultSet rs = null;
        String sql = "SELECT * FROM BanHang"; 
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rs;
    }


}

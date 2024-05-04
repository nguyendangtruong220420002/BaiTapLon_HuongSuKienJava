package entiy;
public class Ban {
    private int ban;          
    private String tenNuoc;  
    private int soLuong;      
    private double thanhTien; 
  
    public Ban(int ban, String tenNuoc, int soLuong, double thanhTien) {
        this.ban = ban;
        this.tenNuoc = tenNuoc;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

    // Getters and setters
    public int getBan() {
        return ban;
    }

    public void setBan(int ban) {
        this.ban = ban;
    }

    public String getTenNuoc() {
        return tenNuoc;
    }

    public void setTenNuoc(String tenNuoc) {
        this.tenNuoc = tenNuoc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
}

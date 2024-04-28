package entiy;
import java.util.Date;

public class DatBan {
    private String tenKH;
    private String sdt;
    private int ban;
    private String thoiGian;
    private Date ngay;
    private String thanhToan;
    private String ghiChu;

    // Constructors, Getters, and Setters
    public DatBan() {
        // Default constructor
    }

    public DatBan(String tenKH, String sdt, int ban, String thoiGian, Date ngay, String thanhToan, String ghiChu) {
        this.tenKH = tenKH;
        this.sdt = sdt;
        this.ban = ban;
        this.thoiGian = thoiGian;
        this.ngay = ngay;
        this.thanhToan = thanhToan;
        this.ghiChu = ghiChu;
    }

    // Getters and Setters
    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getBan() {
        return ban;
    }

    public void setBan(int ban) {
        this.ban = ban;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public String getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(String thanhToan) {
        this.thanhToan = thanhToan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}

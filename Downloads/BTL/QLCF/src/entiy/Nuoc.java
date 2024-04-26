package entiy;

public class Nuoc {
    private String maNuoc;
    private String loaiNuoc;
    private String tenNuoc;
    private String donVi;
    private int soLuong;
    private double giaBan;
    
    // Constructor
    public Nuoc(String maNuoc, String loaiNuoc, String tenNuoc, String donVi, int soLuong, double giaBan) {
        this.maNuoc = maNuoc;
        this.loaiNuoc = loaiNuoc;
        this.tenNuoc = tenNuoc;
        this.donVi = donVi;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
    }

    public Nuoc() {
	}

	// Getters and setters
    public String getMaNuoc() {
        return maNuoc;
    }

    public void setMaNuoc(String maNuoc) {
        this.maNuoc = maNuoc;
    }

    public String getLoaiNuoc() {
        return loaiNuoc;
    }

    public void setLoaiNuoc(String loaiNuoc) {
        this.loaiNuoc = loaiNuoc;
    }

    public String getTenNuoc() {
        return tenNuoc;
    }

    public void setTenNuoc(String tenNuoc) {
        this.tenNuoc = tenNuoc;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }
}

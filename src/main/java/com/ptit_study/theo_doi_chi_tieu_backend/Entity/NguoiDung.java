package com.ptit_study.theo_doi_chi_tieu_backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "nguoi_dung")
public class NguoiDung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tai_khoan", nullable = false, unique = true)
    private String taiKhoan;

    @Column(name = "mat_khau", nullable = false)
    private String matKhau;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "ngay_tao", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime ngayTao;

    // Quan hệ 1-n với chi_tieu
    @OneToMany(mappedBy = "nguoiDung", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ChiTieu> chiTieus;

    @OneToMany(mappedBy = "nguoiDung", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ChiTieuKhac> chiTieuKhacs;

    @OneToMany(mappedBy = "nguoiDung", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DinhMucChiTieu> dinhMucChiTieus;

    public NguoiDung() {
        this.ngayTao = LocalDateTime.now();
    }

    // ===== Getters và Setters =====
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTaiKhoan() { return taiKhoan; }
    public void setTaiKhoan(String taiKhoan) { this.taiKhoan = taiKhoan; }

    public String getMatKhau() { return matKhau; }
    public void setMatKhau(String matKhau) { this.matKhau = matKhau; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public LocalDate getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(LocalDate ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public LocalDateTime getNgayTao() { return ngayTao; }
    public void setNgayTao(LocalDateTime ngayTao) { this.ngayTao = ngayTao; }

    public List<ChiTieu> getChiTieus() { return chiTieus; }
    public void setChiTieus(List<ChiTieu> chiTieus) { this.chiTieus = chiTieus; }

    public List<ChiTieuKhac> getChiTieuKhacs() { return chiTieuKhacs; }
    public void setChiTieuKhacs(List<ChiTieuKhac> chiTieuKhacs) { this.chiTieuKhacs = chiTieuKhacs; }

    public List<DinhMucChiTieu> getDinhMucChiTieus() { return dinhMucChiTieus; }
    public void setDinhMucChiTieus(List<DinhMucChiTieu> dinhMucChiTieus) { this.dinhMucChiTieus = dinhMucChiTieus; }
}

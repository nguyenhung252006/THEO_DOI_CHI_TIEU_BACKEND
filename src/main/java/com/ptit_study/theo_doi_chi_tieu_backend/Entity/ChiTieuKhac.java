package com.ptit_study.theo_doi_chi_tieu_backend.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;

@Entity
@Table(name = "chi_tieu_khac")
public class ChiTieuKhac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_nguoi_dung")
    @JsonBackReference
    private NguoiDung nguoiDung;

    public ChiTieuKhac() {
        this.thoiGianNhap = LocalDateTime.now();
    }

    @Column(name = "ten_khoan", nullable = false)
    private String tenKhoan;

    @Column(name = "so_tien", nullable = false)
    private BigDecimal soTien;

    @Column(name = "thoi_gian_nhap", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime thoiGianNhap;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public String getTenKhoan() {
        return tenKhoan;
    }

    public void setTenKhoan(String tenKhoan) {
        this.tenKhoan = tenKhoan;
    }

    public BigDecimal getSoTien() {
        return soTien;
    }

    public void setSoTien(BigDecimal soTien) {
        this.soTien = soTien;
    }

    public LocalDateTime getThoiGianNhap() {
        return thoiGianNhap;
    }

    public void setThoiGianNhap(LocalDateTime thoiGianNhap) {
        this.thoiGianNhap = thoiGianNhap;
    }

}

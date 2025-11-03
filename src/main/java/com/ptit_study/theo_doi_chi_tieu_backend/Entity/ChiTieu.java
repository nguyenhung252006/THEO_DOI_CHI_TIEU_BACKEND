package com.ptit_study.theo_doi_chi_tieu_backend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "chi_tieu")
public class ChiTieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_nguoi_dung")
    @JsonBackReference
    private NguoiDung nguoiDung;


    @Enumerated(EnumType.STRING)
    @Column(name = "loai_chi_tieu", nullable = false)
    private LoaiChiTieu loaiChiTieu;

    @Column(name = "so_tien", nullable = false)
    private BigDecimal soTien;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "thoi_gian_nhap", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime thoiGianNhap;

    public ChiTieu() {
        this.thoiGianNhap = LocalDateTime.now();
    }


    // ===== Getters và Setters =====
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public NguoiDung getNguoiDung() { return nguoiDung; }
    public void setNguoiDung(NguoiDung nguoiDung) { this.nguoiDung = nguoiDung; }

    public LoaiChiTieu getLoaiChiTieu() { return loaiChiTieu; }
    public void setLoaiChiTieu(LoaiChiTieu loaiChiTieu) { this.loaiChiTieu = loaiChiTieu; }

    public BigDecimal getSoTien() { return soTien; }
    public void setSoTien(BigDecimal soTien) { this.soTien = soTien; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }

    public LocalDateTime getThoiGianNhap() { return thoiGianNhap; }
    public void setThoiGianNhap(LocalDateTime thoiGianNhap) { this.thoiGianNhap = thoiGianNhap; }
}

// Enum LoaiChiTieu
enum LoaiChiTieu {
    AN_UONG,
    MUA_SAM,
    GIAI_TRI
}

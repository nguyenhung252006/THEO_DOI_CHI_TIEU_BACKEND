package com.ptit_study.theo_doi_chi_tieu_backend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "dinh_muc_chi_tieu")
public class DinhMucChiTieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_nguoi_dung")
    @JsonBackReference
    private NguoiDung nguoiDung;



    @Column(name = "so_tien_dinh_muc", nullable = false)
    private BigDecimal soTienDinhMuc;

    @Column(name = "so_ngay", nullable = false)
    private Integer soNgay;

    @Column(name = "ngay_luu", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime ngayLuu;

    public DinhMucChiTieu() {
        this.ngayLuu = LocalDateTime.now();
    }

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

    public BigDecimal getSoTienDinhMuc() {
        return soTienDinhMuc;
    }

    public void setSoTienDinhMuc(BigDecimal soTienDinhMuc) {
        this.soTienDinhMuc = soTienDinhMuc;
    }

    public Integer getSoNgay() {
        return soNgay;
    }

    public void setSoNgay(Integer soNgay) {
        this.soNgay = soNgay;
    }

    public LocalDateTime getNgayLuu() {
        return ngayLuu;
    }

    public void setNgayLuu(LocalDateTime ngayLuu) {
        this.ngayLuu = ngayLuu;
    }
// getters và setters
}

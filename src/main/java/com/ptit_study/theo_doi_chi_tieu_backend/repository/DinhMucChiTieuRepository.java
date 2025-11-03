package com.ptit_study.theo_doi_chi_tieu_backend.repository;
import com.ptit_study.theo_doi_chi_tieu_backend.Entity.DinhMucChiTieu;
import com.ptit_study.theo_doi_chi_tieu_backend.Entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DinhMucChiTieuRepository extends JpaRepository<DinhMucChiTieu, Integer> {
    List<DinhMucChiTieu> findByNguoiDungId(Integer nguoiDungId);
}


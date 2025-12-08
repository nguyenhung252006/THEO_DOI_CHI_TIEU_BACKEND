package com.ptit_study.theo_doi_chi_tieu_backend.repository;
import com.ptit_study.theo_doi_chi_tieu_backend.Entity.ChiTieu;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChiTieuRepository extends JpaRepository<ChiTieu, Integer> {
    List<ChiTieu> findByNguoiDungId(Integer nguoiDungId);
}


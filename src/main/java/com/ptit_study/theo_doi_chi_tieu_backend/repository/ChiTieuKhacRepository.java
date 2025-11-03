package com.ptit_study.theo_doi_chi_tieu_backend.repository;

import com.ptit_study.theo_doi_chi_tieu_backend.Entity.ChiTieuKhac;
import com.ptit_study.theo_doi_chi_tieu_backend.Entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChiTieuKhacRepository extends JpaRepository<ChiTieuKhac, Integer> {
    List<ChiTieuKhac> findByNguoiDungId(Integer nguoiDungId);
}



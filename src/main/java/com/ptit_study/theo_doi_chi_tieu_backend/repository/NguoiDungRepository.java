package com.ptit_study.theo_doi_chi_tieu_backend.repository;

import com.ptit_study.theo_doi_chi_tieu_backend.Entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    NguoiDung findByEmail(String email);
    NguoiDung findByTaiKhoan(String taiKhoan);
}

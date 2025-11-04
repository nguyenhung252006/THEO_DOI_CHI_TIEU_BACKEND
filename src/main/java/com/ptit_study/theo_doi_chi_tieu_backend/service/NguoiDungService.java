package com.ptit_study.theo_doi_chi_tieu_backend.service;

import java.time.LocalDateTime;

import com.ptit_study.theo_doi_chi_tieu_backend.Entity.*;
import com.ptit_study.theo_doi_chi_tieu_backend.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class NguoiDungService {

    private final NguoiDungRepository nguoiDungRepo;
    private final ChiTieuRepository chiTieuRepo;
    private final ChiTieuKhacRepository chiTieuKhacRepo;
    private final DinhMucChiTieuRepository dinhMucRepo;

    public NguoiDungService(NguoiDungRepository nguoiDungRepo,
                            ChiTieuRepository chiTieuRepo,
                            ChiTieuKhacRepository chiTieuKhacRepo,
                            DinhMucChiTieuRepository dinhMucRepo) {
        this.nguoiDungRepo = nguoiDungRepo;
        this.chiTieuRepo = chiTieuRepo;
        this.chiTieuKhacRepo = chiTieuKhacRepo;
        this.dinhMucRepo = dinhMucRepo;
    }

    public Map<String, Object> getNguoiDungByID(Integer id) {
        NguoiDung user = nguoiDungRepo.findById(id).orElse(null);
        if (user == null) return Map.of("error", "Không tìm thấy người dùng");

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("nguoi_dung", user);

        result.put("chi_tieu", chiTieuRepo.findByNguoiDungId(id));
        result.put("chi_tieu_khac", chiTieuKhacRepo.findByNguoiDungId(id));
        result.put("dinh_muc_chi_tieu", dinhMucRepo.findByNguoiDungId(id));

        return result;
    }

    // them chi_tieu bang id
    public ChiTieu addChiTieu(Integer userId, ChiTieu chiTieuMoi) {
        NguoiDung user = nguoiDungRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với ID: " + userId));

        chiTieuMoi.setNguoiDung(user);

        return chiTieuRepo.save(chiTieuMoi);
    }

    // Thêm chi tiêu khác cho người dùng theo ID
    public ChiTieuKhac addChiTieuKhac(Integer id, ChiTieuKhac chiTieuKhacMoi) {

        NguoiDung user = nguoiDungRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với ID: " + id));

        chiTieuKhacMoi.setNguoiDung(user);

        if (chiTieuKhacMoi.getThoiGianNhap() == null) {
            chiTieuKhacMoi.setThoiGianNhap(LocalDateTime.now());
        }

        return chiTieuKhacRepo.save(chiTieuKhacMoi);
    }

    // Thêm định mức chi tiêu cho người dùng
    public DinhMucChiTieu addDinhMucChiTieu(Integer id, DinhMucChiTieu dinhMucMoi) {

        NguoiDung user = nguoiDungRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với ID: " + id));

        dinhMucMoi.setNguoiDung(user);
        if (dinhMucMoi.getNgayLuu() == null) {
            dinhMucMoi.setNgayLuu(LocalDateTime.now());
        }

        return dinhMucRepo.save(dinhMucMoi);
    }

    // Chỉnh sửa định mức (update)
    public DinhMucChiTieu updateDinhMuc(Integer dinhMucId, DinhMucChiTieu dinhMucCapNhat) {
        DinhMucChiTieu existing = dinhMucRepo.findById(dinhMucId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy định mức với ID: " + dinhMucId));

        existing.setSoTienDinhMuc(dinhMucCapNhat.getSoTienDinhMuc());
        existing.setSoNgay(dinhMucCapNhat.getSoNgay());
        existing.setNgayLuu(LocalDateTime.now());

        return dinhMucRepo.save(existing);
    }

    //chỉnh sửa mục chi tiêu (update)
    public ChiTieu updateChiTieu (Integer chiTieuId, ChiTieu chiTieuCapNhat) {
        ChiTieu exitsting = chiTieuRepo.findById(chiTieuId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chi tiêu với ID: " + chiTieuId));

        exitsting.setSoTien(chiTieuCapNhat.getSoTien());
        exitsting.setLoaiChiTieu(chiTieuCapNhat.getLoaiChiTieu());
        exitsting.setGhiChu(chiTieuCapNhat.getGhiChu());
        exitsting.setThoiGianNhap(chiTieuCapNhat.getThoiGianNhap());

        return chiTieuRepo.save(exitsting);
    }

    // chỉnh sửa người dùng theo id
    public NguoiDung updateNguoiDung (Integer id, NguoiDung nguoiDungCapNhat) {
        NguoiDung exitsting = nguoiDungRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng ID: " + id));
        exitsting.setHoTen(nguoiDungCapNhat.getHoTen());
        exitsting.setNgaySinh(nguoiDungCapNhat.getNgaySinh());
        exitsting.setSoDienThoai(nguoiDungCapNhat.getSoDienThoai());
        exitsting.setEmail(nguoiDungCapNhat.getEmail());
        exitsting.setMatKhau(nguoiDungCapNhat.getMatKhau());

        return nguoiDungRepo.save(exitsting);
    }

    // xoa chi tieu theo ID
    @Transactional
    public void deleteChiTieu(Integer chiTieuId) {
        ChiTieu chiTieu = chiTieuRepo.findById(chiTieuId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chi tiêu"));

        NguoiDung user = chiTieu.getNguoiDung();
        user.getChiTieus().remove(chiTieu); // bỏ khỏi danh sách trước
        nguoiDungRepo.save(user);           // Hibernate sẽ tự xóa bản ghi khỏi DB
    }


}




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

    // thêm người dùng
    public NguoiDung createNguoiDung (NguoiDung nguoiDungMoi) {
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setTaiKhoan(nguoiDungMoi.getTaiKhoan());
        nguoiDung.setMatKhau(nguoiDungMoi.getMatKhau());
        nguoiDung.setEmail(nguoiDungMoi.getEmail());
        nguoiDung.setHoTen(nguoiDungMoi.getHoTen());
        nguoiDung.setSoDienThoai(nguoiDungMoi.getSoDienThoai());
        nguoiDung.setNgaySinh(nguoiDungMoi.getNgaySinh());
        nguoiDungRepo.save(nguoiDung);
        return nguoiDung;
    }

    // lấy dữ liệu người dùng bằng id
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

    // chỉnh sửa chi tiêu khác
    public ChiTieuKhac updateChiTieuKhac (Integer chiTieuKhacId, ChiTieuKhac chiTieuKhacMoi) {
        ChiTieuKhac existing = chiTieuKhacRepo.findById(chiTieuKhacId)
                .orElseThrow(() -> new RuntimeException("không tìm thấy chi tiêu khác với id" + chiTieuKhacId));

        existing.setSoTien(chiTieuKhacMoi.getSoTien());
        existing.setThoiGianNhap(chiTieuKhacMoi.getThoiGianNhap());
        existing.setTenKhoan(chiTieuKhacMoi.getTenKhoan());

        return chiTieuKhacRepo.save(existing);
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

    // xoa chi tieu khac theo ID
    @Transactional
    public void deleteChiTieuKhac(Integer id) {
        ChiTieuKhac chiTieuKhac = chiTieuKhacRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy mục chi tiêu khác"));

        NguoiDung user = chiTieuKhac.getNguoiDung();
        user.getChiTieuKhacs().remove(chiTieuKhac);
        nguoiDungRepo.save(user);
    }

    // xoa dinh muc chi tieu theo id
    @Transactional
    public void deleteDinhMucChiTieu(Integer id) {
        DinhMucChiTieu dinhMucChiTieu = dinhMucRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thất mục chi tiêu này"));
        NguoiDung user = dinhMucChiTieu.getNguoiDung();
        user.getDinhMucChiTieus().remove(dinhMucChiTieu);
        nguoiDungRepo.save(user);
    }
}




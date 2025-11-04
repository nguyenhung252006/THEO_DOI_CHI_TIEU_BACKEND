package com.ptit_study.theo_doi_chi_tieu_backend.controller;

import com.ptit_study.theo_doi_chi_tieu_backend.Entity.ChiTieu;
import com.ptit_study.theo_doi_chi_tieu_backend.Entity.ChiTieuKhac;

import com.ptit_study.theo_doi_chi_tieu_backend.Entity.DinhMucChiTieu;
import com.ptit_study.theo_doi_chi_tieu_backend.Entity.NguoiDung;
import com.ptit_study.theo_doi_chi_tieu_backend.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/nguoi-dung")
public class NguoiDungController {

    @Autowired
    private NguoiDungService nguoiDungService;

    //lấy thông tin bằng id người dùng
    @GetMapping("/{id}")
    public Map<String, Object> getNguoiDung(@PathVariable Integer id) {
        return nguoiDungService.getNguoiDungByID(id);
    }

    //them chi tiêu qua id người dùng
    @PostMapping(value = "/chi-tieu/{id}", consumes = "application/json;charset=UTF-8")
    public ChiTieu addChiTieu(@PathVariable Integer id, @RequestBody ChiTieu chiTieu) {
        return nguoiDungService.addChiTieu(id, chiTieu);
    }

    // thêm chi tiêu khác bằng id người dùng
    @PostMapping("/chi-tieu-khac/{id}")
    public ResponseEntity<?> addChiTieuKhac(@PathVariable Integer id, @RequestBody ChiTieuKhac chiTieuKhacMoi) {
        ChiTieuKhac savedChiTieuKhac = nguoiDungService.addChiTieuKhac(id, chiTieuKhacMoi);
        return ResponseEntity.ok(savedChiTieuKhac);
    }

    // thêm định mức qua id người dùng
    @PostMapping("/dinh-muc/{id}")
    public ResponseEntity<?> addDinhMucChiTieu(@PathVariable Integer id, @RequestBody DinhMucChiTieu dinhMucMoi) {
        DinhMucChiTieu saveDinhMuc = nguoiDungService.addDinhMucChiTieu(id, dinhMucMoi);
        return ResponseEntity.ok(saveDinhMuc);
    }

    // PUT chỉnh sửa định mức
    @PutMapping("/dinh-muc/{dinhMucId}")
    public ResponseEntity<DinhMucChiTieu> updateDinhMuc(@PathVariable Integer dinhMucId,
                                                        @RequestBody DinhMucChiTieu dinhMucCapNhat) {
        DinhMucChiTieu updated = nguoiDungService.updateDinhMuc(dinhMucId, dinhMucCapNhat);
        return ResponseEntity.ok(updated);
    }

    // PUT chỉnh sửa chi tiêu
    @PutMapping("/chi-tieu/{chiTieuId}")
    public ResponseEntity<ChiTieu> updateChiTieu (@PathVariable Integer chiTieuId, @RequestBody ChiTieu chiTieuMoi) {
        ChiTieu updated = nguoiDungService.updateChiTieu(chiTieuId, chiTieuMoi);
        return ResponseEntity.ok(updated);
    }

    // PUT chỉnh sửa người dùng
    @PutMapping("/{id}")
    public ResponseEntity<NguoiDung>updateNguoiDung (@PathVariable Integer id, @RequestBody NguoiDung nguoiDungMoi) {
        NguoiDung updated = nguoiDungService.updateNguoiDung(id, nguoiDungMoi);
        return ResponseEntity.ok(updated);
    }

    // DELETE xóa chi tiêu bằng ID

    @DeleteMapping("/chi-tieu/{id}")
    public ResponseEntity<String> deleteChiTieu(@PathVariable Integer id) {
        nguoiDungService.deleteChiTieu(id);
        return ResponseEntity.ok("Đã xóa chi tiêu có ID: " + id);
    }

}






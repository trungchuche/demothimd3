package org.example.demothimd3.dao;

import org.example.demothimd3.model.SinhVien;
import org.example.demothimd3.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class SinhVienDAOImpl implements ISinhVienDAO{



    @Override
    public List<SinhVien> getAll(String keyword) {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM sinhvien_noitru";

        // Nếu có keyword thì thêm điều kiện WHERE
        if (keyword != null && !keyword.trim().isEmpty()) {
            sql += " WHERE ten_sv LIKE ?";
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (keyword != null && !keyword.trim().isEmpty()) {
                ps.setString(1, "%" + keyword.trim() + "%");
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SinhVien(
                        rs.getInt("id"),
                        rs.getString("ten_sv"),
                        rs.getString("sdt"),
                        rs.getString("ngay_bat_dau"),
                        rs.getString("hinh_thuc_thanh_toan"),
                        rs.getString("ghi_chu")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public SinhVien getById(int id) {
        String sql = "SELECT * FROM sinhvien_noitru WHERE id = ?";
        try (Connection conn = (Connection) DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new SinhVien(
                        rs.getInt("id"),
                        rs.getString("ten_sv"),
                        rs.getString("sdt"),
                        rs.getString("ngay_bat_dau"),
                        rs.getString("hinh_thuc_thanh_toan"),
                        rs.getString("ghi_chu")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(SinhVien sv) {
        String sql = "INSERT INTO sinhvien_noitru(ten_sv, sdt, ngay_bat_dau, hinh_thuc_thanh_toan, ghi_chu) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = (Connection) DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sv.getTenSv());
            ps.setString(2, sv.getSdt());
            ps.setString(3, sv.getNgayBatDau());
            ps.setString(4, sv.getHinhThucThanhToan());
            ps.setString(5, sv.getGhiChu());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(SinhVien sv) {
        String sql = "UPDATE sinhvien_noitru SET ten_sv = ?, sdt = ?, ngay_bat_dau = ?, hinh_thuc_thanh_toan = ?, ghi_chu = ? WHERE id = ?";
        try (Connection conn = (Connection) DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sv.getTenSv());
            ps.setString(2, sv.getSdt());
            ps.setString(3, sv.getNgayBatDau());
            ps.setString(4, sv.getHinhThucThanhToan());
            ps.setString(5, sv.getGhiChu());
            ps.setInt(6, sv.getId());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM sinhvien_noitru WHERE id = ?";
        try (Connection conn = (Connection) DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

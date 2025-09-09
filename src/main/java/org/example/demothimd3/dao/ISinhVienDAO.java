package org.example.demothimd3.dao;

import org.example.demothimd3.model.SinhVien;

import java.util.List;

public interface ISinhVienDAO {
    List<SinhVien> getAll(String keyword);

    SinhVien getById(int id);

    void insert(SinhVien sv);

    void update(SinhVien sv);

    void delete(int id);
}

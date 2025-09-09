package org.example.demothimd3.servlet;

import org.example.demothimd3.dao.SinhVienDAOImpl;
import org.example.demothimd3.model.SinhVien;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "sinhVienServlet", value = "/sinhvienServlet")
public class SinhVienServlet extends HttpServlet {
    private SinhVienDAOImpl sinhVienDAO = new SinhVienDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.getRequestDispatcher("form.jsp").forward(req, resp);
                break;

            case "edit":
                int editId = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("sinhVien", sinhVienDAO.getById(editId));
                req.getRequestDispatcher("form.jsp").forward(req, resp);
                break;

            case "delete":
                int deleteId = Integer.parseInt(req.getParameter("id"));
                sinhVienDAO.delete(deleteId);
                resp.sendRedirect("sinhvienServlet");
                break;

            case "search":
                String keyword = req.getParameter("search");
                List<SinhVien> searchList = sinhVienDAO.getAll(keyword);
                req.setAttribute("list", searchList);
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                break;

            default:
                // load toàn bộ danh sách
                List<SinhVien> list = sinhVienDAO.getAll(null);
                req.setAttribute("list", list);
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // Lấy dữ liệu từ form
        String idStr = req.getParameter("id");
        String tenSv = req.getParameter("ten_sv");
        String sdt = req.getParameter("sdt");
        String ngayBatDau = req.getParameter("ngay_bat_dau");
        String hinhThucThanhToan = req.getParameter("hinh_thuc_thanh_toan");
        String ghiChu = req.getParameter("ghi_chu");

        SinhVien sv = new SinhVien();
        sv.setTenSv(tenSv);
        sv.setSdt(sdt);
        sv.setNgayBatDau(ngayBatDau);
        sv.setHinhThucThanhToan(hinhThucThanhToan);
        sv.setGhiChu(ghiChu);

        if (idStr == null || idStr.isEmpty()) {
            // Insert mới
            sinhVienDAO.insert(sv);
        } else {
            // Update
            sv.setId(Integer.parseInt(idStr));
            sinhVienDAO.update(sv);
        }

        // Sau khi lưu xong → redirect về danh sách
        resp.sendRedirect(req.getContextPath() + "/sinhvienServlet?action=search&search=");

    }
}

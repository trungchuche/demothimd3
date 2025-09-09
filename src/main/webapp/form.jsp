<%@ page import="org.example.demothimd3.model.SinhVien" %><%
    SinhVien sv = (SinhVien) request.getAttribute("sinhVien");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
</head>
<body class="container mt-4">
<h2><%= (sv == null ? "Thêm mới" : "Chỉnh sửa")%> sinh viên</h2>
<form action="${pageContext.request.contextPath}/sinhvienServlet" method="post">
    <input type="hidden" name="id" value="<%= (sv != null ? sv.getId() : "") %>"/>
    <div class="mb-3">
        <lable>Tên sinh viên</lable>
        <input type="text" name="ten_sv" class="form-control" value="<%= (sv != null ? sv.getTenSv() : "") %>" required/>
    </div>

    <div class="mb-3">
        <lable>SĐT</lable>
        <input type="number" name="sdt" class="form-control" value="<%= (sv != null ? sv.getSdt() : "") %>" required/>
    </div>

    <div class="mb-3">
        <lable>Ngày Bắt đầu</lable>
        <input type="text" name="ngay_bat_dau" class="form-control" value="<%= (sv != null ? sv.getNgayBatDau() : "") %>" required/>
    </div>

    <div class="mb-3">
        <lable>Hình thức thanh toán</lable>
        <input type="text" name="hinh_thuc_thanh_toan" class="form-control" value="<%= (sv != null ? sv.getHinhThucThanhToan() : "") %>" required/>
    </div>

    <div class="mb-3">
        <lable>Ghi chú</lable>
        <input type="text" name="ghi_chu" class="form-control" value="<%= (sv != null ? sv.getGhiChu() : "") %>" required/>
    </div>

    <button type="submit" class="btn btn-primary">Lưu</button>
    <a href="SinhVienServlet" class="btn btn-secondary">Hủy</a>
</form>

</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quản lý sinh viên nội trú</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
<h2>Quản lý sinh viên nội trú</h2>

<form action="sinhvienServlet" method="get">
    <input type="hidden" name="action" value="search"/>
    <input type="text" name="search" class="form-control me-2" placeholder="Tìm kiếm theo tên..."/>
    <%--    ấn thanh tìm kiếm với nội dung rỗng để quay lại bảng--%>
    <button type="submit" class="btn btn-primary">Tìm kiếm</button>
    <%--    chuẩn sang phương thức new của servlet sẽ--%>
    <a href="sinhvienServlet?action=new" class="btn btn-success ms-2">+ Tạo mới</a>
</form>

<table class="table table-bordered">
    <thead>
    <tr>
        <th>Tên sinh viên</th>
        <th>SĐT</th>
        <th>Ngày Bắt Đầu</th>
        <th>Hình thức thanh toán</th>
        <th>Ghi chú</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${not empty list}">
        <c:forEach var="sv" items="${list}">
            <tr>
                <td>${sv.tenSv}</td>
                <td>${sv.sdt}</td>
                <td>${sv.ngayBatDau}</td>
                <td>${sv.hinhThucThanhToan}</td>
                <td>${sv.ghiChu}</td>
                <td>
                    <a href="sinhvienServlet?action=edit&id=${sv.id}">Sửa</a>
                    <a href="sinhvienServlet?action=delete&id=${sv.id}"
                       onclick="return confirm('Bạn có chắc chắn muốn xóa?')">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>

</body>
</html>

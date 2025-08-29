<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Trang chủ</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container py-4">
  <h3 class="mb-3">Xin chào, <c:out value="${sessionScope.account.fullName}"/></h3>

  <div class="list-group">
    <a class="list-group-item list-group-item-action"
       href="<c:url value='/admin/category/list'/>">Quản lý Category</a>
    <a class="list-group-item list-group-item-action"
       href="<c:url value='/logout'/>">Đăng xuất</a>
  </div>
</body>
</html>

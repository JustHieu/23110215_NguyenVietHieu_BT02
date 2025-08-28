<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8" />
  <title>Đăng nhập</title>

  <!-- Nếu bạn dùng Bootstrap, nhớ include CSS này:
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
  -->
</head>
<body class="p-3">

  <form action="<c:url value='/login'/>" method="post" class="container" style="max-width:480px;">
    <h2 class="mb-3">Đăng nhập</h2>

    <c:if test="${not empty alert}">
      <div class="alert alert-danger" role="alert">${alert}</div>
    </c:if>

    <div class="mb-3">
      <label for="username" class="form-label">Tài khoản</label>
      <input id="username" name="username" type="text" class="form-control" placeholder="Nhập tài khoản" required />
    </div>

    <div class="mb-3">
      <label for="password" class="form-label">Mật khẩu</label>
      <input id="password" name="password" type="password" class="form-control" placeholder="Nhập mật khẩu" required />
    </div>

    <div class="form-check mb-3">
      <input class="form-check-input" type="checkbox" id="remember" name="remember" />
      <label class="form-check-label" for="remember">Ghi nhớ đăng nhập</label>
    </div>

    <button type="submit" class="btn btn-primary w-100">Đăng nhập</button>
  </form>

</body>
</html>

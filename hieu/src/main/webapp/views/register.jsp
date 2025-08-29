<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Đăng ký</title>

  <!-- Bootstrap 5 -->
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
  <!-- Font Awesome (icon) -->
  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>

  <style>
    body{background:#f6f8fb}
    .card-auth{
      max-width: 560px; margin: 6vh auto; padding: 28px;
      background:#fff; border-radius:14px; box-shadow:0 10px 28px rgba(0,0,0,.08)
    }
    .input-help{font-size:.9rem;color:#6b7280}
  </style>
</head>
<body>

<div class="card-auth">
  <h3 class="mb-1">Tạo tài khoản mới</h3>
  <p class="text-muted mb-4">Nhập thông tin bên dưới để đăng ký.</p>

  <!-- Thông báo thành công -->
  <c:if test="${param.success == '1'}">
    <div class="alert alert-success">Đăng ký thành công. Bạn có thể đăng nhập ngay.</div>
  </c:if>

  <!-- Thông báo lỗi chung -->
  <c:if test="${not empty alert}">
    <div class="alert alert-danger" role="alert">${alert}</div>
  </c:if>

  <!-- Tạo URL trước để tránh lỗi parser với thẻ c:url trong thuộc tính -->
  <c:url var="registerUrl" value="/register" />
  <c:url var="loginUrl" value="/login" />

  <!-- Nếu có upload avatar thì cần enctype="multipart/form-data" -->
  <form action="${registerUrl}" method="post" novalidate>

    <!-- USERNAME -->
    <div class="mb-3">
      <label for="username" class="form-label">Tài khoản</label>
      <div class="input-group">
        <span class="input-group-text"><i class="fa fa-user"></i></span>
        <input id="username" type="text" name="username" class="form-control"
               placeholder="ví dụ: hieunguyen"
               value="${param.username}" required>
      </div>
      <div class="input-help">Tối thiểu 4 ký tự, không dấu cách.</div>
    </div>

    <!-- EMAIL -->
    <div class="mb-3">
      <label for="email" class="form-label">Email</label>
      <div class="input-group">
        <span class="input-group-text"><i class="fa fa-envelope"></i></span>
        <input id="email" type="email" name="email" class="form-control"
               placeholder="abc@example.com"
               value="${param.email}" required>
      </div>
    </div>

    <!-- FULL NAME -->
    <div class="mb-3">
      <label for="fullname" class="form-label">Họ và tên</label>
      <div class="input-group">
        <span class="input-group-text"><i class="fa fa-id-card"></i></span>
        <input id="fullname" type="text" name="fullname" class="form-control"
               placeholder="Nguyễn Văn A"
               value="${param.fullname}" required>
      </div>
    </div>

    <!-- PHONE -->
    <div class="mb-3">
      <label for="phone" class="form-label">Số điện thoại</label>
      <div class="input-group">
        <span class="input-group-text"><i class="fa fa-phone"></i></span>
        <input id="phone" type="tel" name="phone" class="form-control"
               placeholder="09xxxxxxxx"
               value="${param.phone}">
      </div>
    </div>

    <!-- PASSWORD -->
    <div class="mb-3">
      <label for="pw" class="form-label">Mật khẩu</label>
      <div class="input-group">
        <span class="input-group-text"><i class="fa fa-lock"></i></span>
        <input id="pw" type="password" name="password" class="form-control"
               placeholder="Tối thiểu 6 ký tự" minlength="6" required>
        <button type="button" class="btn btn-outline-secondary" onclick="togglePw('pw', this)">
          <i class="fa fa-eye"></i>
        </button>
      </div>
    </div>

    <!-- CONFIRM PASSWORD -->
    <div class="mb-3">
      <label for="re" class="form-label">Nhập lại mật khẩu</label>
      <div class="input-group">
        <span class="input-group-text"><i class="fa fa-lock"></i></span>
        <input id="re" type="password" name="repassword" class="form-control" minlength="6" required>
        <button type="button" class="btn btn-outline-secondary" onclick="togglePw('re', this)">
          <i class="fa fa-eye"></i>
        </button>
      </div>
    </div>

    <!-- AVATAR (tùy chọn) -->
   

    <button class="btn btn-primary w-100">Đăng ký</button>
  </form>

  <p class="text-center mt-3">
    Đã có tài khoản?
    <a href="${loginUrl}">Đăng nhập</a>
  </p>
</div>

<script>
  function togglePw(id, btn){
    const input = document.getElementById(id);
    const isPw = input.type === 'password';
    input.type = isPw ? 'text' : 'password';
    btn.querySelector('i').classList.toggle('fa-eye-slash', isPw);
  }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

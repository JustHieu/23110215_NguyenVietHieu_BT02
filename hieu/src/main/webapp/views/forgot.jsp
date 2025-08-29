<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quên mật khẩu</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container p-4" style="max-width:480px">

<h2 class="mb-3">Quên mật khẩu</h2>

<c:if test="${not empty msg}">
    <div class="alert alert-info">${msg}</div>
</c:if>

<form action="<c:url value='/forgot'/>" method="post">
    <div class="mb-3">
        <label for="email" class="form-label">Nhập email đã đăng ký</label>
        <input type="email" id="email" name="email" class="form-control"
               placeholder="ví dụ: abc@example.com" required/>
    </div>
    <button type="submit" class="btn btn-primary w-100">Gửi liên kết đặt lại</button>
</form>

<p class="mt-3"><a href="<c:url value='/login'/>">← Quay lại đăng nhập</a></p>

</body>
</html>

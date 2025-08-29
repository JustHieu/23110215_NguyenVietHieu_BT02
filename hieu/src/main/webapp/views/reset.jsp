<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đặt lại mật khẩu</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container p-4" style="max-width:480px">

<h2 class="mb-3">Đặt lại mật khẩu</h2>

<c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
</c:if>

<form action="<c:url value='/reset'/>" method="post">
    <!-- token lấy từ URL param -->
    <input type="hidden" name="token" value="${param.token}"/>

    <div class="mb-3">
        <label for="password" class="form-label">Mật khẩu mới</label>
        <input type="password" id="password" name="password" class="form-control"
               placeholder="Nhập mật khẩu mới" minlength="6" required/>
    </div>

    <div class="mb-3">
        <label for="retype" class="form-label">Nhập lại mật khẩu</label>
        <input type="password" id="retype" name="retype" class="form-control"
               placeholder="Nhập lại mật khẩu" minlength="6" required/>
    </div>

    <button type="submit" class="btn btn-success w-100">Cập nhật mật khẩu</button>
</form>

<p class="mt-3"><a href="<c:url value='/login'/>">← Quay lại đăng nhập</a></p>

</body>
</html>

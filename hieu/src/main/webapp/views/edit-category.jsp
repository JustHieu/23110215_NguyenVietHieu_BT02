<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Edit Category</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>
  <style>
    body{background:#f4f6f9}
    .layout{display:grid; grid-template-columns:250px 1fr; min-height:100vh}
    .sidebar{background:#1f2937; color:#cbd5e1}
    .sidebar .brand{padding:16px 20px; font-weight:700; color:#fff; border-bottom:1px solid rgba(255,255,255,.06)}
    .nav-link{color:#cbd5e1}
    .nav-link.active, .nav-link:hover{background:#111827; color:#fff}
    .topbar{background:#fff; border-bottom:1px solid #e5e7eb}
    .page{padding:24px}
    .btn-primary{background:#4f46e5;border:0}
    .avatar{border-radius:8px}
  </style>
</head>
<body>
<div class="layout">
  <aside class="sidebar d-flex flex-column">
    <div class="brand">Admin Panel</div>
    <nav class="nav flex-column p-2 gap-1">
      <a class="nav-link rounded px-3 py-2" href="<c:url value='/admin/home'/>"><i class="fa fa-gauge me-2"></i>Dashboard</a>
      <a class="nav-link rounded px-3 py-2 active" href="<c:url value='/admin/category/list'/>"><i class="fa fa-list me-2"></i>Categories</a>
    </nav>
    <div class="mt-auto p-2">
      <a class="btn btn-sm btn-outline-secondary w-100" href="<c:url value='/logout'/>"><i class="fa fa-right-from-bracket me-1"></i> Logout</a>
    </div>
  </aside>

  <main class="d-flex flex-column">
    <div class="topbar d-flex align-items-center justify-content-between px-3" style="height:60px">
      <div class="fw-semibold">Edit Category</div>
      <a class="btn btn-sm btn-outline-secondary" href="<c:url value='/logout'/>"><i class="fa fa-right-from-bracket me-1"></i>Logout</a>
    </div>

    <div class="page">
      <div class="card shadow-sm">
        <div class="card-body">
          <form action="<c:url value='/admin/category/edit'/>" method="post" enctype="multipart/form-data" class="row g-3">
            <input type="hidden" name="id" value="${category.id}">
            <div class="col-12">
              <label class="form-label">Tên</label>
              <input type="text" name="name" class="form-control" value="${category.name}" required>
            </div>
            <div class="col-12">
              <label class="form-label d-block">Icon hiện tại</label>
              <c:if test="${not empty category.icon}">
                <img class="avatar mb-2" src="<c:url value='/image?fname=${category.icon}'/>" width="64" height="64">
              </c:if>
              <input type="file" name="icon" class="form-control">
              <div class="form-text">Để trống nếu muốn giữ icon cũ.</div>
            </div>
            <div class="col-12">
              <button class="btn btn-primary"><i class="fa fa-floppy-disk me-1"></i> Cập nhật</button>
              <a class="btn btn-light ms-2" href="<c:url value='/admin/category/list'/>">Quay lại</a>
            </div>
          </form>
        </div>
      </div>
    </div>
  </main>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

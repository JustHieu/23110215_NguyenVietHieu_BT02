<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Categories</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>
  <style>
    body{background:#f4f6f9}
    .layout{display:grid; grid-template-columns: 250px 1fr; min-height:100vh}
    .sidebar{background:#1f2937; color:#cbd5e1}
    .sidebar .brand{padding:16px 20px; font-weight:700; color:#fff; border-bottom:1px solid rgba(255,255,255,.06)}
    .nav-link{color:#cbd5e1}
    .nav-link.active, .nav-link:hover{background:#111827; color:#fff}
    .content{background:#f4f6f9}
    .topbar{background:#fff; border-bottom:1px solid #e5e7eb}
    .page{padding:24px}
    .table th{background:#f8fafc}
    .btn-primary{background:#4f46e5;border:0}
    .btn-outline-secondary{border-color:#cbd5e1;color:#374151}
    .avatar{border-radius:8px}
  </style>
</head>
<body>
<div class="layout">
  <!-- Sidebar -->
  <aside class="sidebar d-flex flex-column">
    <div class="brand">Admin Panel</div>
    <nav class="nav flex-column p-2 gap-1">
      <a class="nav-link rounded px-3 py-2" href="<c:url value='/admin/home'/>"><i class="fa fa-gauge me-2"></i>Dashboard</a>
      <a class="nav-link rounded px-3 py-2 active" href="<c:url value='/admin/category/list'/>"><i class="fa fa-list me-2"></i>Categories</a>
      <!-- thêm mục khác nếu cần -->
    </nav>
    <div class="mt-auto p-2">
      <a class="btn btn-sm btn-outline-secondary w-100" href="<c:url value='/logout'/>">
        <i class="fa fa-right-from-bracket me-1"></i> Logout
      </a>
    </div>
  </aside>

  <!-- Main -->
  <main class="d-flex flex-column">
    <!-- Topbar -->
    <div class="topbar d-flex align-items-center justify-content-between px-3" style="height:60px">
      <div class="fw-semibold">Categories</div>
      <div>
        <span class="me-3 text-muted"><i class="fa fa-user me-1"></i><c:out value="${sessionScope.account.fullName}"/></span>
        <a class="btn btn-sm btn-outline-secondary" href="<c:url value='/logout'/>">
          <i class="fa fa-right-from-bracket me-1"></i>Logout
        </a>
      </div>
    </div>

    <div class="page">
      <div class="d-flex justify-content-between align-items-center mb-3">
        <h5 class="mb-0">Danh sách</h5>
        <a class="btn btn-primary" href="<c:url value='/admin/category/add'/>"><i class="fa fa-plus me-1"></i> Add Category</a>
      </div>

      <div class="card shadow-sm">
        <div class="card-body p-0">
          <div class="table-responsive">
            <table class="table mb-0 align-middle">
              <thead>
                <tr>
                  <th style="width:60px">#</th>
                  <th>Tên</th>
                  <th style="width:120px">Icon</th>
                  <th style="width:220px">Hành động</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${cateList}" var="c" varStatus="s">
                <tr>
                  <td class="text-muted">${s.index+1}</td>
                  <td>${c.name}</td>
                  <td>
                    <c:if test="${not empty c.icon}">
                      <img class="avatar" src="<c:url value='/image?fname=${c.icon}'/>" alt="icon" width="50" height="50">
                    </c:if>
                  </td>
                  <td>
                    <a class="btn btn-sm btn-outline-secondary me-2" href="<c:url value='/admin/category/edit?id=${c.id}'/>"><i class="fa fa-pen me-1"></i>Sửa</a>
                    <a class="btn btn-sm btn-outline-danger"
                       href="<c:url value='/admin/category/delete?id=${c.id}'/>"
                       onclick="return confirm('Xóa category này?')"><i class="fa fa-trash me-1"></i>Xóa</a>
                  </td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>

    </div>
  </main>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

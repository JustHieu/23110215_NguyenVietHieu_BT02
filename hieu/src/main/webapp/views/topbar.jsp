<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>

<style>
  .topbar{background:#0f172a; background-image:linear-gradient(90deg,#0f172a 0%,#1f2937 60%,#111827 100%); color:#e5e7eb; box-shadow:0 6px 20px rgba(0,0,0,.12)}
  .topbar .navbar-brand{font-weight:800; letter-spacing:.3px; color:#fff}
  .topbar .nav-link{color:#cbd5e1}
  .topbar .nav-link:hover{color:#fff}
  .topbar .btn-outline-light{--bs-btn-color:#e5e7eb; --bs-btn-border-color:#475569; --bs-btn-hover-bg:#334155; --bs-btn-hover-border-color:#475569}
  .search-input{background:#0b1324; border:1px solid #334155; color:#e5e7eb}
  .search-input::placeholder{color:#94a3b8}
  .avatar-initial{width:32px;height:32px;border-radius:50%;background:#6366f1;display:inline-flex;align-items:center;justify-content:center;color:#fff;font-weight:700;text-transform:uppercase;font-size:.85rem}
</style>

<c:url var="loginUrl"    value="/login"/>
<c:url var="registerUrl" value="/register"/>
<c:url var="logoutUrl"   value="/logout"/>
<c:url var="accountUrl"  value="/member/myaccount"/>

<nav class="navbar navbar-expand-lg topbar py-2 navbar-dark"><!-- navbar-dark để hiện icon toggler -->
  <div class="container-fluid">
    <a class="navbar-brand d-flex align-items-center gap-2" href="<c:url value='/admin/home'/>">
      <i class="fa-solid fa-gauge-high"></i> Admin Panel
    </a>

    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#topbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="topbarNav">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item"><a class="nav-link" href="<c:url value='/admin/category/list'/>"><i class="fa fa-list me-1"></i>Categories</a></li>
      </ul>

      <form class="d-none d-md-flex me-3" role="search" onsubmit="return false;">
        <div class="input-group">
          <span class="input-group-text bg-transparent text-secondary border-0"><i class="fa fa-search"></i></span>
          <input class="form-control search-input" type="search" placeholder="Search…" aria-label="Search">
        </div>
      </form>

      <c:choose>
        <%-- Chưa đăng nhập --%>
        <c:when test="${empty sessionScope.account}">
          <div class="d-flex gap-2">
            <a class="btn btn-outline-light btn-sm" href="${loginUrl}">
              <i class="fa fa-right-to-bracket me-1"></i> Đăng nhập
            </a>
            <a class="btn btn-light btn-sm" href="${registerUrl}">
              <i class="fa fa-user-plus me-1"></i> Đăng ký
            </a>
          </div>
        </c:when>

        <%-- Đã đăng nhập --%>
        <c:otherwise>
          <div class="dropdown">
            <a class="d-flex align-items-center text-decoration-none text-light dropdown-toggle"
               href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              <span class="avatar-initial me-2">
                <c:out value="${fn:substring(sessionScope.account.fullName,0,1)}"/>
              </span>
              <span class="me-2"><c:out value="${sessionScope.account.fullName}"/></span>
            </a>
            <ul class="dropdown-menu dropdown-menu-end shadow">
              <li><a class="dropdown-item" href="${accountUrl}"><i class="fa fa-user me-2"></i> Tài khoản của tôi</a></li>
              <li><hr class="dropdown-divider"></li>
              <li><a class="dropdown-item text-danger" href="${logoutUrl}"><i class="fa fa-right-from-bracket me-2"></i> Đăng xuất</a></li>
            </ul>
          </div>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
</nav>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

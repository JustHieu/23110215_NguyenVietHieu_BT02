<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8" />
  <title>Đăng nhập</title>

  <!-- Bootstrap -->
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />

  <style>
    :root{
      --grad-a:#7b2ff7;
      --grad-b:#f107a3;
      --card-shadow:0 12px 30px rgba(0,0,0,.08);
      --radius:18px;
    }
    *{box-sizing:border-box}
    body{
      margin:0;
      min-height:100vh;
      display:flex; align-items:center; justify-content:center;
      background:#f6f8fb;
      font-family:system-ui,-apple-system,Segoe UI,Roboto,Helvetica,Arial;
    }

    .auth-shell{
      width:min(1100px, 94vw);
      min-height:520px;
      background:#fff;
      border-radius:var(--radius);
      box-shadow:var(--card-shadow);
      display:grid;
      grid-template-columns:1.05fr 0.95fr;
      overflow:hidden;
      position:relative;
    }

    .hero{
      position:relative;
      background:linear-gradient(135deg,var(--grad-a),var(--grad-b));
      color:#fff;
      padding:48px clamp(28px,4vw,56px);
      display:flex; flex-direction:column; justify-content:center;
    }

    /* đường cong ngăn panel */
    .hero::after{
      content:"";
      position:absolute;
      inset:0 -40px 0 auto;  /* bớt lấn sang phải */
      width:90px;            /* thu hẹp chiều rộng */
      background:#fff;
      clip-path:path("M0,0 C70,140 70,380 0,520 L90,520 L90,0 Z");
      z-index:1;
      opacity:.9;
      pointer-events:none;   /* không chặn click */
    }

    .hero h1{font-weight:800; font-size:clamp(28px,4.2vw,48px); margin-bottom:14px}
    .hero p{opacity:.92; max-width:42ch; line-height:1.55}
    .hero .btn-wrap{margin-top:22px; display:flex; gap:12px; flex-wrap:wrap}
    .btn-ghost, .btn-white{
      border-radius:999px; padding:10px 18px; border:2px solid rgba(255,255,255,.6);
      background:transparent; color:#fff; font-weight:600; text-decoration:none;
    }
    .btn-white{background:#fff; color:#6b21a8; border-color:#fff}

    .panel{
      padding:40px clamp(24px,4vw,56px);
      display:flex; align-items:center; justify-content:center;
      background:#fff;
      position:relative;
      z-index:2; /* nổi lên trên đường cong */
    }

    .login-card{width:100%; max-width:420px;}
    .login-card h3{font-weight:800; margin-bottom:6px}
    .muted{color:#6b7280; font-size:.95rem; margin-bottom:20px}

    .form-control{
      height:48px;
      border-radius:12px;
    }
    .btn-primary{
      background:linear-gradient(135deg,var(--grad-a),var(--grad-b));
      border:0; height:48px; border-radius:999px; font-weight:700;
      box-shadow:0 6px 18px rgba(123,47,247,.32);
    }
    .btn-primary:hover{filter:brightness(.98)}
    .link{color:#6b21a8; text-decoration:none}
    .link:hover{text-decoration:underline}

    @media(max-width:900px){
      .auth-shell{grid-template-columns:1fr}
      .hero::after{display:none}
    }
  </style>
</head>
<body>

  <div class="auth-shell">
    <section class="hero">
      <h1>Welcome Back</h1>
      <p>
        Lorem ipsum has been the industry's standard dummy text ever since the 1500s,
        when an unknown printer took a galley of type.
      </p>
      <div class="btn-wrap">
        <a href="#" class="btn-white">Learn More</a>
        <a href="#" class="btn-ghost">Our Features</a>
      </div>
    </section>

    <section class="panel">
      <div class="login-card">
        <h3>Login</h3>
        <p class="muted">Vui lòng nhập tài khoản để tiếp tục.</p>

        <c:if test="${param.reset == 'success'}">
          <div class="alert alert-success py-2">Đổi mật khẩu thành công. Hãy đăng nhập lại.</div>
        </c:if>
        <c:if test="${not empty alert}">
          <div class="alert alert-danger py-2" role="alert">${alert}</div>
        </c:if>

        <form action="<c:url value='/login'/>" method="post" novalidate>
          <div class="mb-3">
            <label for="username" class="form-label">Tài khoản</label>
            <input id="username" name="username" type="text"
                   class="form-control" placeholder="Nhập tài khoản"
                   value="${param.username}" required />
          </div>

          <div class="mb-2">
            <label for="password" class="form-label">Mật khẩu</label>
            <input id="password" name="password" type="password"
                   class="form-control" placeholder="Nhập mật khẩu" required />
          </div>

          <div class="d-flex justify-content-between align-items-center mb-3">
            <div class="form-check">
              <input class="form-check-input" type="checkbox" id="remember" name="remember" />
              <label class="form-check-label" for="remember">Remember me</label>
            </div>
            <a href="<c:url value='/forgot'/>" class="link small">Forgot Password?</a>
          </div>

          <button type="submit" class="btn btn-primary w-100">Login</button>
        </form>

        <p class="text-center mt-3">
          Don’t have an account?
          <a href="<c:url value='/register'/>" class="link">Sign Up</a>
        </p>
      </div>
    </section>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

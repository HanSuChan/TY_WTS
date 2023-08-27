<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Login</title>
<!-- plugins:css -->
<link rel="stylesheet"
	href="/theme/template/assets/vendors/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet"
	href="/theme/template/assets/vendors/css/vendor.bundle.base.css">
<!-- endinject -->
<!-- Plugin css for this page -->
<!-- End plugin css for this page -->
<!-- inject:css -->
<!-- endinject -->
<!-- Layout styles -->
<link rel="stylesheet" href="/theme/template/assets/css/style.css">
<!-- End layout styles -->
<link rel="shortcut icon"
	href="/theme/template/assets/images/favicon.png" />
</head>
<body>
	<div class="container-scroller">
		<div class="container-fluid page-body-wrapper full-page-wrapper">
			<div class="row w-100 m-0">
				<div
					class="content-wrapper full-page-wrapper d-flex align-items-center auth login-bg">
					<div class="card col-lg-4 mx-auto">
						<div class="card-body px-5 py-5">
							<h3 class="card-title text-left mb-3">Login</h3>
							<form action="/home/index" method="post">
								<div class="form-group">
									<label for="username">ID</label> <input type="text" name="id"
										id="id" placeholder="아이디를 입력해주세요" class="form-control p_input"
										required><br>
								</div>
								<div class="form-group">
									<label for="password">비밀번호</label><br> <input
										type="password" name="userpw1" id="pw"
										placeholder="비밀번호를 입력해주세요" onchange="check_pw()"
										class="form-control p_input" required><br>
								</div>
								<div
									class="form-group d-flex align-items-center justify-content-between">

									<a href="/member/findid" class="forgot-pass">ID를 잊으셨나요?</a><br>
									<br>
								</div>
								<div class="text-center">
									<button type="submit"
										class="btn btn-primary btn-block enter-btn">Login</button>
								</div>
							</form>
							<p class="sign-up">
								회원 가입하시겠습니까? <a href="/member/membership"> Register Member</a>
							</p>
						</div>
					</div>
				</div>
				<!-- content-wrapper ends -->
			</div>
			<!-- row ends -->
		</div>
		<!-- page-body-wrapper ends -->
	</div>
	<!-- container-scroller -->
	<!-- plugins:js -->
	<script src="/theme/assets/vendors/js/vendor.bundle.base.js"></script>
	<!-- endinject -->
	<!-- Plugin js for this page -->
	<!-- End plugin js for this page -->
	<!-- inject:js -->
	<script src="/theme/template/assets/js/off-canvas.js"></script>
	<script src="/theme/template/assets/js/hoverable-collapse.js"></script>
	<script src="/theme/template/assets/js/misc.js"></script>
	<script src="/theme/template/assets/js/settings.js"></script>
	<script src="/theme/template/assets/js/todolist.js"></script>
	 <script src="/js/member/check_pw.js"></script>
	<!-- endinject -->
</body>
</html>

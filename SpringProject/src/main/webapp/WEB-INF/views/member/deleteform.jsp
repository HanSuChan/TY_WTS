<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>회원 탈퇴</title>
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
							<h3 class="card-title text-left mb-3">회원 탈퇴</h3>
							<form action="/member/deletedata" method="post">
								<label for="username">비밀 번호</label> <br>
								<div class="form-group d-flex align-items-center">
									<input type="password" name="pw" id="pw" required
										class="form-control p_input w-50"> <span class="ml-2"> &nbsp;&nbsp;를
										입력해주세요</span>
								</div>
								<div
									class="form-group d-flex align-items-center justify-content-between">

									<a href="/member/findpw" class="forgot-pass">비밀번호를 잊으셨나요?</a><br>
									<br>
								</div>
								<div class="text-center">
									<button type="submit" class="btn btn-primary">회원 탈퇴</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="btn btn-danger" id="cancelButton">로그인
										화면으로 되돌아가기</button>
								</div>
							</form>

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
	<script>
		document.getElementById("cancelButton").addEventListener("click",
				function() {
					window.location.href = "/member/loginform";
				});
	</script>
</body>
</html>

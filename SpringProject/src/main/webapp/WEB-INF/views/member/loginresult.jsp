<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>회원 결과</title>
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
							<br> <br>
							<div class="form-group">${loggedInUser.id}님 환영합니다.</div>
							<br> <br>
							<div class="text-center">
								<button type="button" class="btn btn-primary" id="cancelButton">Home</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn btn-danger" id="cancelButton1">로그아웃</button>
								<button type="button" class="btn btn-primary" id="cancelButton2">내정보
									보기</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn btn-danger" id="cancelButton3">회원정보수정</button>
								<button type="button" class="btn btn-primary" id="cancelButton4">회원
									탈퇴</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
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
		document.getElementById("cancelButton1").addEventListener("click",
				function() {
					window.location.href = "/member/logout";
				});
		document.getElementById("cancelButton2").addEventListener("click",
				function() {
					window.location.href = "/member/logindata";
				});
		document.getElementById("cancelButton3").addEventListener("click",
				function() {
					window.location.href = "/member/updateform";
				});
		document.getElementById("cancelButton4").addEventListener("click",
				function() {
					window.location.href = "/member/deleteform";
				});
	</script>
</body>
</html>

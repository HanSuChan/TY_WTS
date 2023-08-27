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
							<br>
							<div class="form-group">회원 탈퇴가 정상적으로 처리되었습니다.</div>
							<br> <br>
							<p id="countdown">5</p>
							<script>
								//카운트다운과 리디렉션을 처리하는 함수
								function countdownRedirect(seconds, targetURL) {
									var countdownElement = document
											.getElementById("countdown");
									// 카운트다운 표시
									var interval = setInterval(function() {
										countdownElement.innerHTML = seconds
												+ " 초 뒤에 로그인 페이지로 이동합니다";
										seconds--;
										if (seconds < 0) {
											clearInterval(interval);
											window.location.href = targetURL;
										}
									}, 1000); // 1초마다 업데이트
								}
								// 5초 후에 로그인 페이지로 리디렉션
								countdownRedirect(5, "/member/loginform"); // 로그인 페이지 경로로 수정
							</script>
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

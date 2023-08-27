<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>회원 정보 보기</title>
<!-- plugins:css -->
<link rel="stylesheet"
	href="/theme/template/assets/vendors/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet"
	href="/theme/template/assets/vendors/css/vendor.bundle.base.css">
<link rel="stylesheet" href="/css/member/vendor.bundle.base.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
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
							<h3 class="card-title text-left mb-3">회원 정보</h3>
							<form action="/member/loginform" method="post">
								<div class="form-group">
									<label>아이디</label> <input type="text" name="id" id="id"
										required class="form-control p_input"
										value="${loggedInUser.id}"> <br>
								</div>
								<div class="form-group">
									<label>이름</label> <input type="text"
										class="form-control p_input" name="name" id="name"
										value="${loggedInUser.name}">
								</div>
								<div class="form-group">
									<label>Email</label> <input type="email" name="email"
										class="form-control p_input" value="${loggedInUser.email}"
										required>
								</div>
								<div class="form-group">
									<label>전화 번호</label> <input type="tel" name="phone" id="phone"
										placeholder="- 을 제외하고 입력해주세요" class="form-control p_input"
										oninput="updatePhoneNumberInput()"
										value="${loggedInUser.phone}" required>
								</div>
								<div class="form-group">
									<label>성별</label><br> <label class="form-check-label"></label>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" id="male"
										name="sex" class="form-check-input" value="남자"
										${loggedInUser.sex == '남자' ? 'checked' : ''} readonly>남자
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
										type="radio" id="female" name="sex" class="form-check-input"
										value="여자" ${loggedInUser.sex == '여자' ? 'checked' : ''}
										readonly>여자
								</div>
								<div class="form-group d-flex align-items-center">
									&nbsp;<label class="mr-2">생년월일</label> &nbsp;&nbsp; <input
										type="number" name="year" id="year" placeholder="년(4)자"
										class="form-control p_input w-25 mr-1"
										oninput="limitYearInput(this)" value="${loggedInUser.year}">&nbsp;&nbsp;
									<select name="month" class="form-control p_input w-25 mr-1">
										<option value="1">1월</option>
										<option value="2">2월</option>
										<option value="3">3월</option>
										<option value="4">4월</option>
										<option value="5">5월</option>
										<option value="6">6월</option>
										<option value="7">7월</option>
										<option value="8">8월</option>
										<option value="9">9월</option>
										<option value="10">10월</option>
										<option value="11">11월</option>
										<option value="12">12월</option>
									</select> &nbsp;&nbsp;<input type="number" name="day" id="day"
										placeholder="일" class="form-control p_input w-25 mr-1"
										oninput="limitDayInput(this)" value="${loggedInUser.day}">
								</div>
								<label>주소</label>
								<div class="form-group d-flex align-items-center">
									<input type="text" name="address" id="sample6_address"
										class="form-control p_input" placeholder="주소"
										value="${loggedInUser.address}" required>&nbsp;&nbsp;
									<input type="text" name="extraAddress"
										id="sample6_extraAddress" placeholder="참고항목"
										class="form-control p_input"
										value="${loggedInUser.extraAddress}" required>
									&nbsp;&nbsp; <input type="text" name="detailAddress"
										id="sample6_detailAddress" placeholder="상세주소"
										class="form-control p_input"
										value="${loggedInUser.detailAddress}" required>
								</div>
								<div class="text-center">
									<button type="submit" class="btn btn-primary" id="cancelButton">로그인 화면으로 되돌아가기</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="reset" class="btn btn-danger" id="cancelButton">로그아웃</button>
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
	<script src="/theme/template/assets/vendors/js/vendor.bundle.base.js"></script>
	<!-- endinject -->
	<!-- Plugin js for this page -->
	<!-- End plugin js for this page -->
	<!-- inject:js -->
	<script src="/theme/template/assets/js/off-canvas.js"></script>
	<script src="/theme/template/assets/js/hoverable-collapse.js"></script>
	<script src="/theme/template/assets/js/misc.js"></script>
	<script src="/theme/template/assets/js/settings.js"></script>
	<script src="/theme/template/assets/js/todolist.js"></script>

	<!-- endinject -->
	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="/js/member/check_pw.js"></script>
	<script src="/js/member/address.js"></script>
	<script src="/js/member/validateRegNum.js"></script>
	<script src="/js/member/formatPhoneNumber.js"></script>
	<script src="/js/member/day.js"></script>
	<script src="/js/member/checkDuplicate.js"></script>
	<script>
			document.getElementById("cancelButton").addEventListener("click",
				function() {
					window.location.href = "/member/logout";
				});
	</script>
</body>
</html>

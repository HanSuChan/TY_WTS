<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>회원 가입</title>
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
							<h3 class="card-title text-left mb-3">회원 가입</h3>
							<form action="/member/insertUserDTO" method="post">
								<div class="form-group">
									<label>아이디</label> <input type="text" name="id" id="id"
										required class="form-control p_input" required placeholder="ID를 적어주세요"> <br>
									<button type="button" id="checkDuplicate"
										class="btn btn-primary">ID 중복확인</button>
									<span id="duplicateMessage"></span><br>
								</div>
								<div class="form-group">
									<label>비밀번호</label> <input type="password" name="userpw1"
										id="pw" class="form-control p_input" onchange="check_pw()"
										required placeholder="비밀번호는 특수문자를 포함한 6글자 이상, 16글자 이하만 이용 가능합니다.">
								</div>
								<div class="form-group">
									<label>비밀번호 확인</label> <input type="password" name="userpw2"
										id="pw2" class="form-control p_input" onchange="check_pw()"
										required placeholder="비밀번호는 특수문자를 포함한 6글자 이상, 16글자 이하만 이용 가능합니다."> 
										<span id="check"></span><br>
								</div>
								<div class="form-group">
									<label>이름</label> <input type="text"
										class="form-control p_input" name="name" id="name" required placeholder="이름을 적어주세요">
								</div>
								<div class="form-group d-flex align-items-center">
									<label class="mr-3">주민등록 번호</label> &nbsp;&nbsp; <input
										type="text" class="form-control p_input w-25 mr-2"
										id="regnum1" name="regnum1" placeholder="앞 6자리를 입력해주세요"
										maxlength="6" required> &nbsp;&nbsp; <input
										type="password" class="form-control p_input w-25 mr-2"
										id="regnum2" name="regnum2" placeholder="뒤 7자리를 입력해주세요"
										value="" maxlength="7" required>
									&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name=""
										class="btn btn-primary" value="확인" onclick="validateRegNum()">
								</div>
								<div class="form-group">
									<label>Email</label> <input type="email" name="email"
										class="form-control p_input" required placeholder="example@email.com">
								</div>
								<div class="form-group">
									<label>전화 번호</label> <input type="tel" name="phone" id="phone"
										placeholder="- 을 제외하고 입력해주세요" class="form-control p_input"
										oninput="updatePhoneNumberInput()" required>
								</div>
								<div class="form-group">
									<label>성별</label><br> <label class="form-check-label"></label>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" id="male"
										name="sex" class="form-check-input" value="남자">남자
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
										type="radio" id="female" name="sex" class="form-check-input"
										value="여자">여자
								</div>
								<div class="form-group d-flex align-items-center">
									&nbsp;<label class="mr-2">생년월일</label> &nbsp;&nbsp; <input
										type="number" name="year" id="year" placeholder="년(4)자"
										class="form-control p_input w-25 mr-1"
										oninput="limitYearInput(this)">&nbsp;&nbsp; <select
										name="month" class="form-control p_input w-25 mr-1">
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
										oninput="limitDayInput(this)">
								</div>
								<label>주소</label> <input type="button"
									onclick="sample6_execDaumPostcode()" value="우편번호 찾기"
									class="form-control p_input"><br>
								<div class="form-group d-flex align-items-center">
									<input type="text" name="address" id="sample6_address"
										class="form-control p_input" placeholder="주소" required>&nbsp;&nbsp;
									<input type="text" name="extraAddress"
										id="sample6_extraAddress" placeholder="참고항목"
										class="form-control p_input" required> &nbsp;&nbsp; <input
										type="text" name="detailAddress" id="sample6_detailAddress"
										placeholder="상세주소" class="form-control p_input" required>
								</div>
								<div class="text-center">
									<button type="submit" class="btn btn-primary">가입</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="reset" class="btn btn-danger">취소</button>
								</div>
								<p class="sign-up text-center">
									이미 계정이 있으신가요?<a href="/memeber/loginform"> Sign Up</a>
								</p>
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
</body>
</html>

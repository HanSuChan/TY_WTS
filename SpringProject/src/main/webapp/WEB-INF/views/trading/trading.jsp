<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Corona Admin</title>
<!-- plugins:css -->
<link rel="stylesheet"
	href="/theme/template/assets/vendors/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet"
	href="/theme/template/assets/vendors/css/vendor.bundle.base.css">
<!-- endinject -->
<!-- Plugin css for this page -->
<link rel="stylesheet"
	href="/theme/template/assets/vendors/jvectormap/jquery-jvectormap.css">
<link rel="stylesheet"
	href="/theme/template/assets/vendors/flag-icon-css/css/flag-icon.min.css">
<link rel="stylesheet"
	href="/theme/template/assets/vendors/owl-carousel-2/owl.carousel.min.css">
<link rel="stylesheet"
	href="/theme/template/assets/vendors/owl-carousel-2/owl.theme.default.min.css">
<!-- End plugin css for this page -->
<!-- inject:css -->
<!-- endinject -->
<!-- Layout styles -->
<link rel="stylesheet" href="/theme/template/assets/css/style.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<style>
#example td {
	color: #E1F6FA;
}

#example th {
	color: #E1F6FA;
}

#orderBookTableBody td, #ordered td, #orderBookTableBody th, #ordered th
	{
	color: #E1F6FA;
}

.ui-autocomplete {
	position: absolute;
	z-index: 9999; /* 팝업 결과를 다른 요소들보다 더 위에 표시하도록 설정 */
}

.highlightedOrderBook {
	border: 1px solid white;
}
</style>
<style type="text/css">
svg {
   font: 10px sans-serif;
}
.axis {
   shape-rendering: crispEdges;
}
.axis path, .axis line {
   stroke-width: .5px;
}
.x.axis path {
    stroke: #fff
}
.x.axis line {
   stroke: #fff;
   stroke-opacity: .5;
}
.y.axis line {
   stroke: #fff
}
path.line {
   stroke: #000;
   stroke-width: .5px;
}
.x.axis text {
    fill: #fff; /* Make x-axis labels white */
}

.y.axis text {
    fill: #fff; /* Make y-axis labels white */
}

.tooltip {
   position: absolute;
   background-color: white;
   border: 1px solid #ccc;
   padding: 5px;
   display: none;
   top: 0;
   left: 0;
}
#box, #box2{
   position: relative;
   width: 100%;
   height: 400px;
   border: 1px solid gray;
   background-color: black;
}
.yDiv {
   position: absolute;
   top: 0;
   left: 0;
   width: 60px;
   height: calc(60%);
   border: 1px solid gray;
}
.xDiv {
   position: absolute;
   top: calc(60%);
   left: 60px;
   width: calc(100% - 60px);
   height: 30px;
   border: 1px solid gray;      
}
.chartDiv {
   position: absolute;
   top: 0;
   left: 60px;
   width: calc(100% - 60px);
   height: calc(60%);
   border: 1px solid gray; /* 높이 조정 */
}
.vyDiv {
   position: absolute;
   bottom: 0;
   left: 0;
   width: 60px;
   height: calc(40% - 30px);
   border: 1px solid gray;
}
.vchartDiv {
   position: absolute;
   bottom: 0;
   left: 60px;
   width: calc(100% - 60px);
   height: calc(40% - 30px);
   border: 1px solid gray;   
}
.left{
   position: absolute;
   top: calc(60%);
   left: 0px;
   width: 30px;
   height: 30px;
   border: 1px solid gray;
   color: red;
}
.right{
   position: absolute;
   top: calc(60%);
   left: 30px;
   width: 30px;
   height: 30px;
   border: 1px solid gray;   
   color: red;
}
   
.chartnopadmagin {
   margin: 0;
   padding: 0;
}

</style>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<!-- End layout styles -->
<link rel="shortcut icon"
	href="/theme/template/assets/images/favicon.png" />
</head>
<body>
	<script src="https://d3js.org/d3.v7.min.js"></script>
	<div class="container-scroller">
		<!-- partial:partials/_sidebar.html -->
		<nav class="sidebar sidebar-offcanvas" id="sidebar">
			<div
				class="sidebar-brand-wrapper d-none d-lg-flex align-items-center justify-content-center fixed-top">
				<a class="sidebar-brand brand-logo" href="/home"><img
					src="/theme/template/assets/images/Ty.png" alt="logo" /></a> <a
					class="sidebar-brand brand-logo-mini" href="/home"><img
					src="/theme/template/assets/images/Ty.png" alt="logo" /></a>
			</div>
			<ul class="nav">
				<li class="nav-item profile">
					<div class="profile-desc">
						<div class="profile-pic">
							<div class="count-indicator">
								<img class="img-xs rounded-circle "
									src="/theme/template/assets/images/faces/symbol.png" alt="">
								<span class="count bg-success"></span>
							</div>
							<div class="profile-name">
								<h5 class="mb-0 font-weight-normal">4조 Web HTS</h5>
								<span>동양 시스템즈 프로젝트</span>
							</div>
						</div>
						<a href="#" id="profile-dropdown" data-bs-toggle="dropdown"><i
							class="mdi mdi-dots-vertical"></i></a>
						<div
							class="dropdown-menu dropdown-menu-right sidebar-dropdown preview-list"
							aria-labelledby="profile-dropdown">
							<a href="#" class="dropdown-item preview-item">
								<div class="preview-thumbnail">
									<div class="preview-icon bg-dark rounded-circle">
										<i class="mdi mdi-settings text-primary"></i>
									</div>
								</div>
								<div class="preview-item-content">
									<p class="preview-subject ellipsis mb-1 text-small">Account
										settings</p>
								</div>
							</a>
							<div class="dropdown-divider"></div>
							<a href="#" class="dropdown-item preview-item">
								<div class="preview-thumbnail">
									<div class="preview-icon bg-dark rounded-circle">
										<i class="mdi mdi-onepassword  text-info"></i>
									</div>
								</div>
								<div class="preview-item-content">
									<p class="preview-subject ellipsis mb-1 text-small">Change
										Password</p>
								</div>
							</a>
							<div class="dropdown-divider"></div>
							<a href="#" class="dropdown-item preview-item">
								<div class="preview-thumbnail">
									<div class="preview-icon bg-dark rounded-circle">
										<i class="mdi mdi-calendar-today text-success"></i>
									</div>
								</div>
								<div class="preview-item-content">
									<p class="preview-subject ellipsis mb-1 text-small">To-do
										list</p>
								</div>
							</a>
						</div>
					</div>
				</li>

				<li class="nav-item nav-category"><span class="nav-link">메뉴</span>
				</li>
				<li class="nav-item menu-items"><a class="nav-link"
					href="/home"> <span class="menu-icon"> <i
							class="mdi mdi-speedometer"></i>
					</span> <span class="menu-title">홈 화면</span>
				</a></li>
				<li class="nav-item menu-items"><a class="nav-link"
					href="/StockCodeAndName?code=A005930&name=삼성전자"> <span
						class="menu-icon"> <i class="mdi mdi-speedometer"></i>
					</span> <span class="menu-title">종목정보</span>
				</a></li>
				<li class="nav-item menu-items"><a class="nav-link"
					href="/trading?stockCode=A005930&stockName=삼성전자"> <span
						class="menu-icon"> <i class="mdi mdi-speedometer"></i>
					</span> <span class="menu-title">매매 화면</span>
				</a></li>
				<li class="nav-item menu-items"><a class="nav-link"
					href="/board"> <span class="menu-icon"> <i
							class="mdi mdi-speedometer"></i>
					</span> <span class="menu-title">종목 토론방</span>
				</a></li>
				<li class="nav-item menu-items"><a class="nav-link"
					href="/account"> <span class="menu-icon"> <i
							class="mdi mdi-speedometer"></i>
					</span> <span class="menu-title">계좌</span>
				</a></li>

			</ul>
		</nav>
		<!-- partial -->
		<div class="container-fluid page-body-wrapper">
			<!-- partial:partials/_navbar.html -->
			<nav class="navbar p-0 fixed-top d-flex flex-row">
				<div
					class="navbar-brand-wrapper d-flex d-lg-none align-items-center justify-content-center">
					<a class="navbar-brand brand-logo-mini" href="index.html"><img
						src="assets/images/logo-mini.svg" alt="logo" /></a>
				</div>
				<div
					class="navbar-menu-wrapper flex-grow d-flex align-items-stretch">
					<button class="navbar-toggler navbar-toggler align-self-center"
						type="button" data-toggle="minimize">
						<span class="mdi mdi-menu"></span>
					</button>
					<ul class="navbar-nav w-100">
						<li class="nav-item w-100">
							<form class="nav-link mt-2 mt-md-0 d-none d-lg-flex search">
								<input type="text" class="form-control w-100"
									id="autoCompleteInput" placeholder="종목검색" height="100px">
							</form>
							<form class="nav-link mt-2 mt-md-0 d-none d-lg-flex search"
								id="stockForm" action="/StockCodeAndName" method="GET">
								<input type="hidden" id="stockCode" name="code" value="">
								<input type="hidden" id="stockName" name="name" value="">
							</form>
						</li>
						<li class="nav-item"><a
							class="nav-link btn btn-success create-new-button"
							aria-expanded="false" href="#"> 검색하기 </a></li>
					</ul>
					<ul class="navbar-nav navbar-nav-right">


						<li class="nav-item nav-settings d-none d-lg-block"><a
							class="nav-link" href="#"> <i class="mdi mdi-view-grid"></i>
						</a></li>
						<li class="nav-item dropdown border-left"><a
							class="nav-link count-indicator dropdown-toggle"
							id="messageDropdown" href="#" data-bs-toggle="dropdown"
							aria-expanded="false"> <i class="mdi mdi-email"></i> <span
								class="count bg-success"></span>
						</a>
							<div
								class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list"
								aria-labelledby="messageDropdown">
								<h6 class="p-3 mb-0">Messages</h6>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item preview-item">
									<div class="preview-thumbnail">
										<img src="assets/images/faces/face4.jpg" alt="image"
											class="rounded-circle profile-pic">
									</div>
									<div class="preview-item-content">
										<p class="preview-subject ellipsis mb-1">Mark send you a
											message</p>
										<p class="text-muted mb-0">1 Minutes ago</p>
									</div>
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item preview-item">
									<div class="preview-thumbnail">
										<img src="assets/images/faces/face2.jpg" alt="image"
											class="rounded-circle profile-pic">
									</div>
									<div class="preview-item-content">
										<p class="preview-subject ellipsis mb-1">Cregh send you a
											message</p>
										<p class="text-muted mb-0">15 Minutes ago</p>
									</div>
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item preview-item">
									<div class="preview-thumbnail">
										<img src="assets/images/faces/face3.jpg" alt="image"
											class="rounded-circle profile-pic">
									</div>
									<div class="preview-item-content">
										<p class="preview-subject ellipsis mb-1">Profile picture
											updated</p>
										<p class="text-muted mb-0">18 Minutes ago</p>
									</div>
								</a>
								<div class="dropdown-divider"></div>
								<p class="p-3 mb-0 text-center">4 new messages</p>
							</div></li>
						<li class="nav-item dropdown border-left"><a
							class="nav-link count-indicator dropdown-toggle"
							id="notificationDropdown" href="#" data-bs-toggle="dropdown">
								<i class="mdi mdi-bell"></i> <span class="count bg-danger"></span>
						</a>
							<div
								class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list"
								aria-labelledby="notificationDropdown">
								<h6 class="p-3 mb-0">Notifications</h6>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item preview-item">
									<div class="preview-thumbnail">
										<div class="preview-icon bg-dark rounded-circle">
											<i class="mdi mdi-calendar text-success"></i>
										</div>
									</div>
									<div class="preview-item-content">
										<p class="preview-subject mb-1">Event today</p>
										<p class="text-muted ellipsis mb-0">Just a reminder that
											you have an event today</p>
									</div>
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item preview-item">
									<div class="preview-thumbnail">
										<div class="preview-icon bg-dark rounded-circle">
											<i class="mdi mdi-settings text-danger"></i>
										</div>
									</div>
									<div class="preview-item-content">
										<p class="preview-subject mb-1">Settings</p>
										<p class="text-muted ellipsis mb-0">Update dashboard</p>
									</div>
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item preview-item">
									<div class="preview-thumbnail">
										<div class="preview-icon bg-dark rounded-circle">
											<i class="mdi mdi-link-variant text-warning"></i>
										</div>
									</div>
									<div class="preview-item-content">
										<p class="preview-subject mb-1">Launch Admin</p>
										<p class="text-muted ellipsis mb-0">New admin wow!</p>
									</div>
								</a>
								<div class="dropdown-divider"></div>
								<p class="p-3 mb-0 text-center">See all notifications</p>
							</div></li>
						<li class="nav-item dropdown"><a class="nav-link"
							id="profileDropdown" href="#" data-bs-toggle="dropdown">
								<div class="navbar-profile">
									<img class="img-xs rounded-circle"
										src="/theme/template/assets/images/faces/face15.jpg" alt="">
									<p class="mb-0 d-none d-sm-block navbar-profile-name">${loggedInUser.name}
										님 환영합니다</p>
									<i class="mdi mdi-menu-down d-none d-sm-block"></i>
								</div>
						</a>
							<div
								class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list"
								aria-labelledby="profileDropdown">
								<h6 class="p-3 mb-0">Profile</h6>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item preview-item">
									<div class="preview-thumbnail">
										<div class="preview-icon bg-dark rounded-circle">
											<i class="mdi mdi-settings text-success"></i>
										</div>
									</div>
									<div class="preview-item-content">
										<p class="preview-subject mb-1">Settings</p>
									</div>
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item preview-item">
									<div class="preview-thumbnail">
										<div class="preview-icon bg-dark rounded-circle">
											<i class="mdi mdi-logout text-danger"></i>
										</div>
									</div>
									<div class="preview-item-content">
										<p class="preview-subject mb-1">Log out</p>
									</div>
								</a>
								<div class="dropdown-divider"></div>
								<p class="p-3 mb-0 text-center">Advanced settings</p>
							</div></li>
					</ul>
					<button
						class="navbar-toggler navbar-toggler-right d-lg-none align-self-center"
						type="button" data-toggle="offcanvas">
						<span class="mdi mdi-format-line-spacing"></span>
					</button>
				</div>
			</nav>
			<!-- partial -->
			<div class="main-panel">
				<div class="content-wrapper">
					<div class="row">
						<div class="col-md-6 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">${stockName}차트</h4>
									<!-- 여기에 용환님 캔들차트 올라와야함 -->
									<div id="box">
										<div class="left">&nbsp;&lt;</div>
										<div class="right">&nbsp;&nbsp;&gt;</div>
									</div>
									<div class="form-group row" style="margin-top: 10px">
										<h4 class="card-title">주문 내역</h4>
										<div class="table-responsive">
											<table id="ordered" class="table">
												<tr>
													<th>종목</th>
													<th>개수</th>
													<th>가격</th>
													<th>유형</th>
												</tr>
												<tr>
													<td>${stockName}</td>
													<td id="orderedQuantity"></td>
													<td id="orderedPrice"></td>
													<td id="orderedAction"></td>
												</tr>
											</table>
										</div>
									</div>
									<div class="form-group row" style="margin-top: 10px">
										<h4 class="card-title">단일 종목 보유 현황</h4>
										<div class="table-responsive">
											<table id="example" class="table">
												<tr>
													<th>종목명</th>
													<th>보유 개수</th>
													<th>평균 단가</th>
												</tr>
												<tr>
													<td>${stockName}</td>
													<td id="holdingQuantity"></td>
													<td id="holdingAvgPrice"></td>
												</tr>
											</table>
										</div>
									</div>
									<label for="myAccount" class="col-sm-3 col-form-label">주문 가능 현금</label>
									<div class="col-sm-9">
										<input class="form-control" type="text" name="myAccount"
											id="myAccount" readonly
											style="background-color: transparent;">
									</div>

								</div>
							</div>
						</div>
						<div class="col-lg-3 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">호가창</h4>
									<div class="table-responsive">
										<table class="table" id="orderBookTableBody">
											<tr>
												<th>매도잔량</th>
												<th>호가</th>
												<th>매수잔량</th>
											</tr>
											<tbody id="sellPricesTableBody"></tbody>

											<tbody id="buyPricesTableBody"></tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-3 grid-margin stretch-card">
							<!-- 매매 폼 -->
							<div class="card">
								<div class="card-body">
									<div class="form-group row" style="margin-top: 5px">
										<h4 class="card-title">정보</h4>
										<div class="table-responsive">
											<table id="ordered" class="table">
												<tr>
													<td>종목명</td>
													<td>${stockCurrentInfo.name}</td>
												</tr>
												<tr>
													<td>종가</td>
													<td>${stockCurrentInfo.cprice}</td>
												</tr>
												<tr>
													<td>대비</td>
													<td id = "stockCurrentInfoDiff">${stockCurrentInfo.diff}</td>
												</tr>
												<tr>
													<td>시가</td>
													<td id="stockCurrentInfoOpen">${stockCurrentInfo.open}</td>
												</tr>
												<tr>
													<td>고가</td>
													<td>${stockCurrentInfo.high}</td>
												</tr>
												<tr>
													<td>저가</td>
													<td>${stockCurrentInfo.low}</td>
												</tr>
												<tr>
													<td>거래량</td>
													<td>${stockCurrentInfo.vol}</td>
												</tr>
												<tr>
													<td>거래대금</td>
													<td>${stockCurrentInfo.vol_value}</td>
												</tr>
											</table>
										</div>
									</div>
									<h4 class="card-title">주문</h4>
									<p class="card-description">${stockName}</p>
									<form class="forms-sample" name="sellForm" id="orderForm">
										<div class="form-group row">
											<label for="price" class="col-sm-3 col-form-label">매매가</label>
											<div class="col-sm-9">
												<input type="text" class="form-control" name="price"
													id="orderBookPrice" placeholder="₩0,000" readonly
													style="background-color: transparent;">
											</div>
										</div>
										<div class="form-group row">
											<label for="number" class="col-sm-3 col-form-label">수량</label>
											<div class="col-sm-9">
												<input type="number" min="0" class="form-control"
													name="number" id="number" placeholder="0">
											</div>
										</div>

										<button type="button" class="btn btn-primary me-2"
											id="buyButton" name="buyButton">매수</button>
										<button type="button" class="btn btn-primary me-2"
											id="sellButton" name="sellButton">매도</button>
										<button type="reset" class="btn btn-primary me-2">취소</button>
									</form>


								</div>
							</div>
						</div>
					</div>
					<!-- 계좌 폼 -->
					<div class="row"></div>
				</div>
				<!-- content-wrapper ends -->
				<!-- partial:partials/_footer.html -->
				<footer class="footer">
					<div
						class="d-sm-flex justify-content-center justify-content-sm-between">
						<span
							class="text-muted d-block text-center text-sm-left d-sm-inline-block">Copyright
							© bootstrapdash.com 2021</span> <span
							class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">
							Free <a
							href="https://www.bootstrapdash.com/bootstrap-admin-template/"
							target="_blank">Bootstrap admin template</a> from
							Bootstrapdash.com
						</span>
					</div>
				</footer>
				<!-- partial -->
			</div>
			<!-- main-panel ends -->
		</div>
		<!-- page-body-wrapper ends -->
	</div>
	<!-- container-scroller -->
	<!-- plugins:js -->
	<script src="/theme/template/assets/vendors/js/vendor.bundle.base.js"></script>
	<!-- endinject -->
	<!-- Plugin js for this page -->
	<script src="/theme/template/assets/vendors/chart.js/Chart.min.js"></script>
	<script
		src="/theme/template/assets/vendors/progressbar.js/progressbar.min.js"></script>
	<script
		src="/theme/template/assets/vendors/jvectormap/jquery-jvectormap.min.js"></script>
	<script
		src="/theme/template/assets/vendors/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script
		src="/theme/template/assets/vendors/owl-carousel-2/owl.carousel.min.js"></script>
	<script src="/theme/template/assets/js/jquery.cookie.js"
		type="text/javascript"></script>
	<!-- End plugin js for this page -->
	<!-- inject:js -->
	<script src="/theme/template/assets/js/off-canvas.js"></script>
	<script src="/theme/template/assets/js/hoverable-collapse.js"></script>
	<script src="/theme/template/assets/js/misc.js"></script>
	<script src="/theme/template/assets/js/settings.js"></script>
	<script src="/theme/template/assets/js/todolist.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<!-- endinject -->
	<!-- Custom js for this page -->
	<script src="/theme/template/assets/js/dashboard.js"></script>
	<!-- End custom js for this page -->
	<!-- 스크립트 -->
	<script src="/board/js/board.js"></script>
	<script id="loggedIn_id" data-name="${loggedInUser.id}"
		src="/Balance/js/balance.js?loggedIn_id"></script>
	<script src="/trading/js/orderBookClick.js"></script>
	<script>
		$('#autoCompleteInput')
				.autocomplete(
						{
							source : function(request, response) {
								$
										.ajax({
											url : "/ajax/autocomplete.do",
											type : "GET",
											dataType : "JSON",
											data : {
												value : request.term
											},
											contentType : "application/x-www-form-urlencoded; charset=UTF-8",
							success : function(data) {
												var results = $
														.map(
																data.resultList,
																function(item) {
																	return {
																		label : item.NAME,
																		value : item.NAME,
																		code : item.CODE
																	};
																});

												response(results.slice(0, 12)); // 최대 12개의 결과 표시
											},
											error : function() {
												alert("오류가 발생했습니다.");
											}
										});
							},
							select : function(evt, ui) {
								$('#stockCode').val(ui.item.code); // 선택한 항목의 코드를 숨은 입력 필드에 설정
								$('#stockName').val(ui.item.value); // 선택한 항목의 이름을 숨은 입력 필드에 설정
								$('#stockForm').submit(); // 폼을 서버로 전송
							}
						});
	</script>
	<script>
		var loggedInUserId = "${loggedInUser.id}";
		var stockCode = "${stockCode}";
		var stockName = "${stockName}";
	</script>
	<script src="/trading/js/updatePrices.js"></script>
	<script src="/trading/js/detectStockHolding.js"></script>
	<script src="/trading/js/order.js"></script>
	<script src="/trading/js/detectOrder.js"></script>

	<script>
	const startDate = new Date(2022,8,25); 
	function parseDate(dateString) {
		  const year = parseInt(dateString.substr(0, 4));
		  const month = parseInt(dateString.substr(4, 2)) - 1;
		  const day = parseInt(dateString.substr(6, 2));
		  return new Date(year, month, day);
		}
	
	const candleData = [
		   <c:forEach items="${stockChart.data}" var="item" varStatus="status">
		      {
		         date: parseDate("${item.date}"), // 날짜 형식 변환
		         open: ${item.open},
		         close: ${item.close},
		         high: ${item.high},
		         low: ${item.low},
		         vol: ${item.volume}
		      }<c:if test="${!status.last}">,</c:if>
		   </c:forEach>
		   ];
	function makeCharts(candleData,boxnum) { 
		  let dateFormat = d3.timeFormat("%b/%e");
		  let dateFormatTool = d3.timeFormat("%G/%b/%e");
		  let margin = 60;
		  let box = document.querySelector(boxnum);
		  let width = box.offsetWidth - margin;
		  let height = box.offsetHeight*0.6;
		  let vHeight = box.offsetHeight*0.4 - 30;
		  let zoomlimit =  candleData.length / 10 ;
		  let candleWidth = width / candleData.length;
		  let zoom = d3.zoom()
		  .scaleExtent([1, zoomlimit])
		  .extent([[0, 0], [width, height]])
		  .translateExtent([[0, 0], [width, height]])
		  .on("zoom", zoomed);
		   
		  //각 div에 svg 객체 생성
		  let boxzoom = d3.select(boxnum)
		  boxzoom
		  .append("svg")
		  .attr("class", "del")
		  .attr("width", "100%")
		  .attr("height", "100%")
		  boxzoom.append("div").attr("class", "chartDiv del")
		  boxzoom.append("div").attr("class", "vchartDiv del")
		  boxzoom.append("div").attr("class", "xDiv del")
		  boxzoom.append("div").attr("class", "yDiv del")
		  boxzoom.append("div").attr("class", "vyDiv del")
		  boxzoom.append("div").attr("class", "tooltip del")
		  
		  let chart = boxzoom.select(".chartDiv")
		  .append("svg")
		  .attr("class", "chart")
		  .attr("width", "100%")
		  .attr("height", height)
		  .call(zoom);
		  chart.append("g")
		   
		  let vchart = boxzoom.select(".vchartDiv")
		  .append("svg")
		  .attr("class", "vchart")
		  .attr("width", "100%")
		  .attr("height", vHeight)
		  .append("g")
		    
		 
		  let xdiv = boxzoom.select(".xDiv")
		  .append("svg")
		  .attr("class", "xaxis")
		  .attr("width", "100%")
		  .attr("height", 30)
		  .attr("transform", "translate(0,0)")
		  //.call(zoom);
		  let ydiv = boxzoom.select(".yDiv")
		  .append("svg")
		  .attr("class", "yaxis")
		  .attr("width", margin)
		  .attr("height", "100%")
		 // .call(zoom);;

		  let vydiv = boxzoom.select(".vyDiv")
		  .append("svg")
		  .attr("class", "vyaxis")
		  .attr("width", margin)
		  .attr("height", "100%")
		 // .call(zoom);
		 
		  
		  //스케일링
		  // 적당한 거리를 계산하여 설정
		  
		  let x = d3.scaleTime()
		  .domain(d3.extent(candleData,function (d){return d.date})) // 날짜 범위로 설정
		  .range([candleWidth, width - candleWidth])
		  
		  let newx = d3.scaleTime()
		  .domain(d3.extent(candleData,function (d){return d.date})) // 날짜 범위로 설정
		  .range([candleWidth, width - candleWidth])
		  
		  let y = d3.scaleLinear()
		  .domain([
		     d3.min(candleData, function(d) { return d.low }) ,
		     d3.max(candleData, function(d) { return d.high })
		     ])
		     .range([height,0])
		  
		  let vy = d3.scaleLinear()
		  .domain([0 , 1.2*d3.max(candleData, function(d) { return d.vol })])
		  .range([vHeight, 0])
		  
		  let xAxis = d3.axisBottom(x)
		  .ticks(5)  
		  .tickFormat(function(d) { return dateFormat(d); })

		  let yAxis = d3.axisRight(y)
		  .ticks(5)
		  
		  let vyAxis = d3.axisRight(vy)
		  .ticks(5)
		  .tickValues(vy.ticks(5).slice(1));
		  

		  //chart 생성
		  chart.append("g")
		  .attr("class", "cs_rect_container")
		  .selectAll("rect.cs_rect")
		  .data(candleData)
		  .enter().append("rect")
		  .attr("class", "cs_rect")
		  .attr("x", function(d) { return x(d.date) - 0.25 * candleWidth; })
		  .attr("y", function(d) { return y(d3.max([d.open, d.close])); })
		  .attr("height", function(d) { return y(d3.min([d.open, d.close])) - y(d3.max([d.open, d.close])); })
		  .attr("width", 0.5 * candleWidth)
		  .attr("fill", function(d) { return d.open > d.close ? "red" : "lime" ; })
		  .on("mouseover", function(event, d) { uptool(event,d)})
		  .on("mouseout", function() {boxzoom.select(".tooltip").style("display", "none");})

		  chart.append("g")
		  .attr("class", "cs_stem_container")
		  .selectAll("line.cs_stem")
		  .data(candleData)
		  .enter().append("line")
		  .attr("class", "cs_stem")
		  .attr("x1", function(d) { return x(d.date); })
		  .attr("x2", function(d) { return x(d.date); })
		  .attr("y1", function(d) { return y(d.high); })
		  .attr("y2", function(d) { return y(d.low); })
		  .attr("stroke", function(d) { return d.open > d.close ? "red" : "lime"; })  
		  .on("mouseover", function(event, d) { uptool(event,d)})
		  .on("mouseout", function() {boxzoom.select(".tooltip").style("display", "none");});
		  
		  vchart.append("g")
		  .attr("class", "vc_bar_container")
		  .selectAll("rect.vc_rect")
		  .data(candleData)
		  .enter().append("rect")
		  .attr("class", "vc_rect")
		  .attr("x", function(d) { return newx(d.date) - 0.25 * candleWidth; })
		  .attr("y", function(d) { return vy(d.vol); })
		  .attr("width", 0.5 * candleWidth)
		  .attr("height", function(d) { return vHeight - vy(d.vol)})
		  .attr("fill", "gray")
		  .on("mouseover", function(event, d) { uptool(event,d)})
		  .on("mouseout", function() {boxzoom.select(".tooltip").style("display", "none");});
		  
		 
		  xdiv.append("g")
		  .attr("class", "x axis")  
		  .call(xAxis)
		  
		  ydiv.append("g")
		  .attr("class", "y axis")
		  .call(yAxis); 
		  
		  vydiv.append("g")
		  .attr("class", "y axis" )
		  .call(vyAxis);

		  
		  function zoomed(event) {
		    
		     ydiv.select('.y.axis').call(yAxis.scale(event.transform.rescaleY(y)));
		     newx.range([candleWidth, width - candleWidth].map(function(d) { return event.transform.applyX(d) } ));
		     xdiv.select('.x.axis').call(xAxis.scale(event.transform.rescaleX(x)));
		     
		     chart.selectAll('.cs_stem')
		     .attr("transform", event.transform)
		     
		     chart.select('.cs_rect_container')
		     .attr("transform", event.transform);
		     
		     vchart.select(".vc_bar_container")
		     .selectAll("rect.vc_rect")
		     .attr("x", function(d) { return newx(d.date) - event.transform.k * 0.25 * candleWidth;})
		     .attr("width", event.transform.k*0.5 * candleWidth);
		     
		  }
		  function uptool(event, d) {
		     let tooltip = boxzoom.select(".tooltip")
		     tooltip.html("날짜 : "+dateFormatTool(d.date)+"<br>시가 : "+d.open+"<br>종가 : "+d.close+"<br>최고가 : "+d.high+"<br>최저가 : "+d.low+"<br>거래량 : "+d.vol);
		     tooltip.style("display", "block");
		     }
		}


	let now = 0;
	
	let splitedData = splitData(candleData);

	makeCharts(splitedData[now], "#box")// 페이지 로딩시 차트그림


	window.addEventListener("resize", function() {reSize(splitedData, '#box', now)});
	

	d3.select("#box").select(".right").on("click",function() { now = rightClick(splitedData,"#box", now)});
	d3.select("#box").select(".left").on("click",function() { now = leftClick(splitedData,"#box", now)}); //다른 날로 이동한 차트 그림



	  function reSize(data,box,now){
	     let remove =  d3.select(box).selectAll(".del")
	     remove.remove()
	     makeCharts(data[now],box)
	  }
	  function rightClick(data,box, now){
	    now--;
	    let remove =  d3.select(box).selectAll(".del")
	    if(now>-1){
	       remove.remove();
	       makeCharts(data[now],box)
	    }else{
	       now = 0;
	    }
	    return now;
	  }
	  function leftClick(data, box , now){
	     let remove = d3.select(box).selectAll(".del")
	     now++;
	     if(now<data.length){
	        remove.remove();
	        makeCharts(data[now],box)
	     }else{
	        now = data.length - 1;
	     }
	     return now;
	  }

	function splitData(data) {
		let re = [];
		let fill = [];
		let start = 0;
		let next = 0;
		
		for (let i = 0; data.length > i; i++, next++) {
			fill[next] = data[i]
			if (next == 99) {
				re[start] = fill;
				fill = [];
				next = -1;
				start++;
				continue;
			}
			if (i == data.length - 1) {
				re[start] = fill.slice(0,next)
			}
		}
		return re;
	}
	</script>

</body>
</html>
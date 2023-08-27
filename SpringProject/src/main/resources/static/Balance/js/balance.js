$(document).ready(function() {
	// stockCode 값을 가져오기
	const loggedIn_id = document.getElementById('loggedIn_id').getAttribute('data-name');
	console.log(loggedIn_id);

	// 주문 데이터 업데이트 함수
	function updateBalance() {
		$.ajax({
			url: "/balance?loggedIn_id=" + loggedIn_id,
			type: "GET",
			dataType: "json",
			success: function(data) {
				// 데이터를 받아서 테이블에 업데이트
				$("#myAccount").val(data.balance);
			}
		});
	}

	// 페이지 로딩 시 초기 데이터 로딩
	updateBalance();

	// 1초마다 데이터 갱신
	setInterval(updateBalance, 1000);
});

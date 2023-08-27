// order.js

// 전달받은 input 값들을 저장하기 위한 변수
var LoggedInUser_Id;
var StockCode;
var StockName;


function setOrderInfo(loggedInUserId, stockCode, stockName) {
	LoggedInUser_Id = loggedInUserId;
	StockCode = stockCode;
	StockName = stockName
}

$(document).ready(function() {
	// order.js에 있는 함수를 이용하여 필요한 값을 설정할 수 있음
	setOrderInfo(loggedInUserId, stockCode, stockName);

	// 매수 버튼 클릭 이벤트 처리
	$("#buyButton").click(function() {
		// AJAX 요청을 보냄
		var Price = document.getElementById('orderBookPrice').value;
		var Quantity = document.getElementById('number').value;
		$.ajax({
			url: "/order", // 
			type: "GET",
			data: {
				loggedInUserId: LoggedInUser_Id,
				stockCode: StockCode,
				stockName: StockName,
				price: Price,
				quantity: Quantity,
				action: "buy" // 매수 액션을 나타내는 값
			},
			success: function(response) {
				// 요청이 성공한 경우 수행할 작업
				$("#orderedQuantity").text(response.stockQuantity); // 매수/매도 수량 업데이트
				$("#orderedPrice").text(response.stockPrice); // 매수/매도 가격 업데이트
				$("#orderedAction").text("매수");
			},
			error: function(xhr, status, error) {
				// 요청이 실패한 경우 수행할 작업
			}
		});
	});

	// 매도 버튼 클릭 이벤트 처리
	$("#sellButton").click(function() {
		// AJAX 요청을 보냄
		var Price = document.getElementById('orderBookPrice').value; // 매도 가격 값
		var Quantity = document.getElementById('number').value; // 매도 수량 값
		$.ajax({
			url: "/order",
			type: "GET",
			data: {
				loggedInUserId: LoggedInUser_Id,
				stockCode: StockCode,
				stockName: StockName,
				price: Price,
				quantity: Quantity,
				action: "sell"
			},
			success: function(response) {
				// 요청이 성공한 경우 수행할 작업
				$("#orderedQuantity").text(response.stockQuantity); // 매수/매도 수량 업데이트
				$("#orderedPrice").text(response.stockPrice); // 매수/매도 가격 업데이트
				$("#orderedAction").text("매도");
			},
			error: function(xhr, status, error) {
				// 요청이 실패한 경우 수행할 작업
			}
		});
	});
});

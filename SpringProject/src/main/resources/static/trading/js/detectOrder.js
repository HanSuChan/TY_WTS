$(document).ready(function() {

	function sendConcludeOrderRequest() {
		const orderedQuantity = document.getElementById('orderedQuantity').textContent;
		const orderedPrice = document.getElementById('orderedPrice').textContent;
		const orderedAction = document.getElementById('orderedAction').textContent;
		const currentPrice = document.getElementById('currentPrice').textContent;
		console.log("Sending order request...");
		console.log("loggedInUserId:", loggedInUserId);
		console.log("stockCode:", stockCode);
		console.log("stockName:", stockName);
		console.log("orderedQuantity:", orderedQuantity);
		console.log("orderedPrice:", orderedPrice);
		console.log("orderedAction:", orderedAction);
		console.log("currentPrice:", currentPrice);

		$.ajax({
			url: '/concludeOrder',
			type: 'GET',
			data: {
				loggedInUserId: loggedInUserId,
				stockCode: stockCode,
				stockName: stockName,
				orderedQuantity: orderedQuantity,
				orderedPrice: orderedPrice,
				orderedAction: orderedAction,
				currentPrice: currentPrice
			},
			success: function(response) {
				if (response === "true") {
					console.log("체결 완료!");
					$("#orderedQuantity").text(""); // 매수/매도 수량 업데이트
					$("#orderedPrice").text(""); // 매수/매도 가격 업데이트
					$("#orderedAction").text("");
				}
				else {
					console.log("주문 창 감지중..");
				}
			},
			error: function(xhr, status, error) {
				console.log("주문 창 감지 오류!");
			}
		});
	}
	// 1초마다 주문 체결 요청 수행
	setInterval(sendConcludeOrderRequest, 1000);
});

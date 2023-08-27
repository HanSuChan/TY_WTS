//detectStockHolding.js

$(document).ready(function() {
	function detectStockHolding() {
		// AJAX 요청 수행
		$.ajax({
			type: 'GET',
			url: '/stockHolding',
			data: {
				loggedInUserId: loggedInUserId,
				stockCode: stockCode
			}
			,
			success: function(response) {
				if (response === '') {
					$("#holdingQuantity").text('');
					$("#holdingAvgPrice").text('');
				} else {
					$("#holdingQuantity").text(response.stockQuantity);
					$("#holdingAvgPrice").text(response.stockPrice);
				}
			},
			error: function(xhr, status, error) {
				console.log("오류");
			}
		});
	}

	// 1초마다 데이터 갱신
	setInterval(detectStockHolding, 1000);
});

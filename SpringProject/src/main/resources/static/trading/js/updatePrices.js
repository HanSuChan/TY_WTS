$(document).ready(function() {
	// 주문 데이터 업데이트 함수
	function updateOrderBook() {
		const stockCurrentInfoDiff = parseInt(document.getElementById('stockCurrentInfoDiff').textContent);
		//const stockCurrentInfoOpen = parseInt(document.getElementById('stockCurrentInfoOpen').textContent);

		$.ajax({
			url: "/OrderBookData?stockCode=" + stockCode,
			type: "GET",
			dataType: "json",
			success: function(data) {
				// 데이터를 받아서 테이블에 업데이트
				var currentPrice = parseInt(data.currentPrice);
				$("#changeAndPercentage").text(data.change + ' ' + data.changePercentage.toFixed(2) + '%');

				// 매수호가를 테이블에 업데이트
				var buyPricesHtml = '';
				for (var i = 0; i < data.buyPrices.length - 4; i++) {
					var price = parseInt(data.buyPrices[i]);
					//var color = price > stockCurrentInfoOpen ? 'lime' : 'red';
					var color = stockCurrentInfoDiff > 0 ? 'lime' : 'red';
					if (i + 1 === data.buyPrices.length - 4) {
						buyPricesHtml += '<tr><td>' + data.totalSell + '</td><td style="color:' + color + ';">' + data.buyPrices[i] + '</td><td>' + data.totalBuy + '</td></tr>';
					}
					else {
						if (price === currentPrice) {
							buyPricesHtml += '<tr style="border: 2px solid white;"><td></td><td id="currentPrice" style="color:' + color + ';">' + data.buyPrices[i] + '</td><td style="color:' + color + ';">' + data.change + ' ' + data.changePercentage.toFixed(2) + '%' + '</td></tr>';
						} else {
							buyPricesHtml += '<tr><td></td><td style="color:' + color + ';">' + data.buyPrices[i] + '</td><td></td></tr>';
						}
					}
				}

				$("#buyPricesTableBody").html(buyPricesHtml);

				// 매도호가를 테이블에 업데이트
				var sellPricesHtml = '';
				for (var i = data.sellPrices.length - 5; i >= 0; i--) {
					var price = parseInt(data.sellPrices[i]);
					//var color = price > stockCurrentInfoOpen ? 'lime' : 'red';
					var color = stockCurrentInfoDiff > 0 ? 'lime' : 'red';

					if (price === currentPrice) {
						sellPricesHtml += '<tr style="border: 2px solid white;"><td></td><td id="currentPrice" style="color:' + color + ';">' + data.sellPrices[i] + '</td><td style="color:' + color + ';">' + data.change + ' ' + data.changePercentage.toFixed(2) + '%' + '</td></tr>';
					} else {
						sellPricesHtml += '<tr><td></td><td style="color:' + color + ';">' + data.sellPrices[i] + '</td><td></td></tr>';
					}
				}
				$("#sellPricesTableBody").html(sellPricesHtml);
			}
		});
	}

	// 페이지 로딩 시 초기 데이터 로딩
	updateOrderBook();

	// 1초마다 데이터 갱신
	setInterval(updateOrderBook, 380);
});

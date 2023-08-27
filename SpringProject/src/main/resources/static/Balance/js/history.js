
$(document).ready(function() {
	function sendRequest() {
		$.ajax({
			url: "/stockHistory",
			type: "GET",
			dataType: "json",
			data: { memberId: loggedInUserId },
			success: function(response) {
				// 서버로부터 응답을 받았을 때 실행되는 함수
				console.log("주식 거래 기록:", response);

				// 테이블 본문에 데이터 추가
				var tableBody = $("#stockHistoryTable"); // 수정된 부분
				tableBody.empty(); // 기존 내용 제거

				$.each(response, function(index, item) {
					var row = $("<tr>");
					row.append($("<td>").text(item.userstock_name));
					row.append($("<td>").text(item.price));
					row.append($("<td>").text(item.quantity));
					row.append($("<td>").text(item.type === 'sell' ? '매도' : '매수'));
					// 날짜와 시간 형식 변환
					var tradingDate = new Date(item.trading_date);
					var formattedDate = tradingDate.toLocaleDateString('ko-KR', { year: '2-digit', month: '2-digit', day: '2-digit' });
					var formattedTime = tradingDate.toLocaleTimeString('ko-KR', { hour: '2-digit', minute: '2-digit', second: '2-digit' });
					row.append($("<td>").text(formattedDate + ' ' + formattedTime));


					tableBody.append(row);
				});
			},
			error: function(xhr, status, error) {
				console.error("요청 실패:", status, error);
			}
		});
	}

	setInterval(sendRequest, 1000);
});
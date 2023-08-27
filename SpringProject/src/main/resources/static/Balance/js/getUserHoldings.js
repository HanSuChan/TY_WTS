$(document).ready(function() {
	const loggedIn_id = document.getElementById('loggedIn_id').getAttribute('data-name');

	function updateBalanceAndHoldings() {
		// AJAX 요청 수행: balance 업데이트
		$.ajax({
			url: "/balance?loggedIn_id=" + loggedIn_id,
			type: "GET",
			dataType: "json",
			success: function(balanceData) {
				// 데이터를 받아서 테이블에 업데이트
				const myAccountBalance = balanceData.balance;
				$("#myAccount").val(myAccountBalance.toLocaleString()); // Format balance

				// 다음 AJAX 요청 수행: holdings 업데이트
				$.ajax({
					type: 'GET',
					url: '/getUserHoldings',
					data: {
						loggedInUserId: loggedIn_id,
					},
					success: function(holdingsData) {
						updateTable(holdingsData);
						updateTotalBalance(myAccountBalance, holdingsData); // 합산한 총액 업데이트
					},
					error: function(xhr, status, error) {
						// 에러 처리 로직 추가
					}
				});
			}
		});
	}

	function updateTable(data) {
		const tableBody = $('#holdingTableBody');
		tableBody.empty(); // 기존 데이터 삭제

		data.forEach(function(holding) {
			const row = $('<tr>');
			row.append($('<td>').text(holding.stockName));
			row.append($('<td>').text(holding.stockQuantity));
			row.append($('<td>').text(holding.stockPrice.toLocaleString()));
			const totalValue = holding.stockPrice * holding.stockQuantity;
			const formattedTotalValue = totalValue.toLocaleString();

			row.append($('<td>').text(formattedTotalValue));

			tableBody.append(row);
		});
	}

	function updateTotalBalance(myAccountBalance, data) {
		let totalBalance = myAccountBalance;

		data.forEach(function(holding) {
			totalBalance += holding.stockPrice * holding.stockQuantity;
		});

		$("#totalBalance").text(totalBalance.toLocaleString()); // Format and update totalBalance
	}

	// 페이지 로딩 시 초기 데이터 로딩
	updateBalanceAndHoldings();

	// 1초마다 데이터 갱신
	setInterval(updateBalanceAndHoldings, 1000);
});

$(document).ready(function() {
	const loggedIn_id = document.getElementById('loggedIn_id').getAttribute('data-name');

	function updateChart() {
		// AJAX 요청 수행: 도넛 차트 데이터 업데이트
		$.ajax({
			url: "/chart",  // URL만 지정하고 파라미터는 data 속성을 통해 전달
			type: "GET",
			dataType: "json",
			data: {
				loggedInUserId: loggedIn_id  // 파라미터 전달
			},
			success: function(chartData) {
				// Chart.js를 사용하여 도넛 차트 생성
				const labels = chartData.map(item => item.stockName);
				const data = chartData.map(item => item.stockQuantity);
				const backgroundColor = [
					'#6d9eeb',
					'#f1c232',
					'#cc0000',
					'#6aa84f',
					'#f0f8ff',
					'#8b008b',
					'#191970',
					'#696969'
					
				];

				const ctx = document.getElementById('donutChart').getContext('2d');
				const donutChart = new Chart(ctx, {
					type: 'doughnut',
					data: {
						labels: labels,
						datasets: [{
							data: data,
							backgroundColor: backgroundColor,
						}],
					},
					options: {  // Add the options object here
						responsive: true,  // Make the chart responsive
						maintainAspectRatio: false,  // Don't maintain aspect ratio
						width: 400,  // Set the desired width
						height: 400, // Set the desired height
					},
				});
			}
		});
	}

	// 페이지 로딩 시 초기 데이터 로딩
	updateChart();

	// 1초마다 데이터 갱신
	setInterval(updateChart, 5000);
});

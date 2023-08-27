$('#autoCompleteInput')
	.autocomplete(
		{
			source: function(request, response) {
				$
					.ajax({
						url: "/ajax/autocomplete.do",
						type: "GET",
						dataType: "JSON",
						data: {
							value: request.term
						},
						contentType: "application/x-www-form-urlencoded; charset=UTF-8",
						success: function(data) {
							var results = $
								.map(
									data.resultList,
									function(item) {
										return {
											label: item.NAME,
											value: item.NAME,
											code: item.CODE
										};
									});

							response(results.slice(0, 12)); // 최대 12개의 결과 표시
						},
						error: function() {
							alert("오류가 발생했습니다.");
						}
					});
			},
			select: function(evt, ui) {
				$('#stockCode').val(ui.item.code); // 선택한 항목의 코드를 숨은 입력 필드에 설정
				$('#stockName').val(ui.item.value); // 선택한 항목의 이름을 숨은 입력 필드에 설정
				$('#stockForm').submit(); // 폼을 서버로 전송
			}
		});
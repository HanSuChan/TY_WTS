$(document).ready(
	function() {
		$("#writeBtn").click(function() {
			location.href = "write";
		})
		$.ajax({
			url: "boardList",
			success: function(result) {
				var html = "";
				result.forEach(function(item) {
					html += "<tr> <td><a href = 'view?idx=" + item.idx
						+ "'>" + item.title + "</a>"
				})
				$("#listArea").append(html)
				$('#example').DataTable();
			}
		});
		$("#deleteBtn").click(function() {
			location.href = "write";
		})
	});
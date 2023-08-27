	var IDX = getQueryStringObject().idx;
	function getQueryStringObject() {
		var a = window.location.search.substr(1).split('&');
		if (a == "")
			return {};
		var b = {};
		for (var i = 0; i < a.length; ++i) {
			var p = a[i].split('=', 2);
			if (p.length == 1)
				b[p[0]] = "";
			else
				b[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " "));
		}
		return b;
	}

	$.ajax({
		url : "boardView?idx=" + IDX,
		success : function(result) {
			$("#image").append(
					'<img src="resources/' + result.image
							+ '" style="width: 70%;">');
			$("#title").text(result.title);
			$("#contents").text(result.contents);
		}
	});

	$.ajax({
		url : "replyList?idx=" + IDX,
		success : function(replys) {
			drawReply(replys)
		}
	});

	function drawReply(replys) {
		var html = '';
		html += '<form class="form-inline" action="writeReply" method="post"><input type="hidden" name="idx" value = "' + IDX + '"><input type="hidden" name="replyIdx" value = "0"><input type="text" class="form-control mb-2 mr-sm-2" style="width: 70%; height: 100px;" id="contents" placeholder="답글" name="contents"><button type="submit" class="btn btn-primary mb-2">등록</button></form>';

		replys
				.forEach(function(reply) {
					if (reply.replyIdx == 0) {
						var rc = 0;
						replys.forEach(function(i) {
							if (reply.idx == i.replyIdx)
								rc++;
						})
			            // 댓글 내용과 삭제 버튼 추가
			            html += '<div class="row"><div class="col-sm-12">';
			            html += '<form class="form-inline" action="writeReply" method="post"><label for="pwd" class="mr-sm-2">'
			                    + reply.contents + '(' + rc + ')' + '</label>';
			            html += '<input type="hidden" name="idx" value="' + IDX + '">';
			            html += '<input type="hidden" name="replyIdx" value="' + reply.idx + '">';
			            html += '<input type="text" class="form-control mb-2 mr-sm-2" id="contents" placeholder="답글" name="contents">';
			            html += '<button type="submit" class="btn btn-primary mb-2">등록</button>';
			            html += '</form>';

			            // 댓글 삭제 버튼 추가
			            html += '<form class="form-inline" action="deleteReply" method="get">';
			            html += '<input type="hidden" name="idx" value="' + IDX + '">';
			            html += '<input type="hidden" name="replyIdx" value="' + reply.idx + '">';
			            html += '<button type="submit" class="btn btn-warning ml-2 mb-2">삭제</button>';
			            html += '</form>';

			            html += '<div class="row"><div class="col-sm-12 sub' + reply.idx + '"></div></div></div></div>';
			            
			            
			            
			        }
				})
		$("#replyArea").append(html);
		replys
				.forEach(function(reply) {
					if (reply.replyIdx != 0) {
						var rc = 0;
						replys.forEach(function(i) {
							if (reply.idx == i.replyIdx)
								rc++;
						})
						var subHtml = '';
						subHtml = '<div class="row"><div class="col-sm-12 subReply">';
						subHtml += '<form class="form-inline" action="writeReply" method="post"><label for="pwd" class="mr-sm-2">'
								+ reply.contents + '(' + rc + ')' + '</label>'
						subHtml += '<input type="hidden" name="idx" value = "' + IDX + '"><input type="hidden" name="replyIdx" value = "' + reply.idx + '"><input type="text"  class="form-control mb-2 mr-sm-2" id="contents"  placeholder="답글" name="contents"><button type="submit" class="btn btn-primary mb-2">등록</button></form>';
						subHtml += '<div class="row"><div class="col-sm-12 sub' + reply.idx + '"></div></div></div></div>';
						$(".sub" + reply.replyIdx).append(subHtml);
					}
				})
	}
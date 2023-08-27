


makePopup()

//팝업 만들기
function makePopup() {
	console.log("makePopup");
	$.ajax({
		
		url: "popup?to_id=test",         // 요청을 보낼 URL
		method: "GET",				// HTTP 요청 메서드 (GET, POST 등)         // 응답 데이터 타입 (json, html, text 등)
		success: function(data) { // 성공했을 때 실행할 함수
			// 서버로부터 받은 데이터를 처리합니다.
			console.log(data);
			let dropdowns = d3.select("#dropdown-item");//드롭다운 메뉴 찾아서 id추가
			let popRemove = d3.selectAll(".removePop")
			popRemove.remove();
			if (data.length > 0) {
				console.log("makePopup");
				var items = dropdowns.selectAll(".dropdown-item")
					.data(data)
					.enter()
					.append("div")
					.attr("class", "dropdown-item preview-item removePop")
				

				// 각 아이템 내부에 구조를 추가합니다.
				items.each(function(d) {
					var item = d3.select(this);
					item.append("div")
						.attr("class", "preview-thumbnail removePop")
						.text(d.from_id)
						.on("click",function(event,d){console.log(d.from_id);openModal(d,1)});

					var content = item.append("div")
						.attr("class", "preview-item-content removePop")
						.on("click",function(event,d){console.log(d.title);openModal(d,0)} );;

					content.append("p")
						.attr("class", "preview-subject ellipsis mb-1 removePop")
						.text(d.title)
						
					content.append("p")
						.attr("class", "text-muted mb-0 removePop")
						.text(makeTime(d.letter_date));
				});
				} else {
				/*<a class="dropdown-item preview-item">
					메세지가 없습니다.
				</a>*/
			}


		},
		error: function( status) { // 실패했을 때 실행할 함수
			console.error("Request failed with status:", status);
		}
	});
}
//모달열기
function openModal(data,check) {
	if(check ==0){
		d3.select('#lfrom_id').property('value', data.from_id);
	    d3.select('#lto_id').property('value', data.to_id);
	    d3.select('#ltitle').property('value', data.title);
	    d3.select('#lcontent').property('value', data.content);
	    d3.select('#ltime').property('value',data.letter_date) ;
	    document.getElementById('myModal').style.display = 'block';
    }
    else {
		if(check == 1)d3.select('#to_idl').property('value', data.from_id);			    	  		
		d3.select('#from_idl').property('value', 'test');//나중에 세션에서 아이디 추가
	    document.getElementById('yourModal').style.display = 'block';
	}
}

function closeModal(c) {
		if(c==0)document.getElementById('myModal').style.display = 'none';
    	else document.getElementById('yourModal').style.display = 'none';
    }
//d3.select('messageWrite').on("click",openModal())
// 모달 닫기 버튼 클릭 이벤트 처리
$(document).ready(function() {
console.log("에러체크5")
$("#yourForm").submit(function(event) {
		console.log("에러체크")
	    event.preventDefault(); // 기본 제출 동작 중단
		console.log("에러체크3")
	    // AJAX를 사용하여 데이터 서버로 전송
	    var formData = new FormData(this);
	    $.ajax({
	        type: "POST",
	        url: "writeLetter",
	        data: formData,
	        processData: false,
	        contentType: false,
	        success: function() {
	            // AJAX 요청이 성공한 경우
	            console.log("에러체크2")
	            $("#yourModal").css("display", "none");
	        },
	        error: function() {
	            // AJAX 요청이 실패한 경우
	            alert('데이터 처리에 실패했습니다.');
	        }
	    });
	});
// 폼 제출 이벤트 처리
	$("#myForm").submit(function(event) {
		console.log("zzzz")
		console.log(event)
	    event.preventDefault(); // 기본 제출 동작 중단
	
	    // AJAX를 사용하여 데이터 서버로 전송
	    var formData = new FormData(this);
	    $.ajax({
	        type: "POST",
	        url: "letterDelete",
	        data: formData,
	        processData: false,
	        contentType: false,
	        success: function() {
	            // AJAX 요청이 성공한 경우
	            $("#myModal").css("display", "none");
	            makePopup() // 모달 닫기
	        },
	        error: function(err) {
	            // AJAX 요청이 실패한 경우
	            alert(err);
	        }
	    });
	});	
});
//시간변환 함수
function makeTime(letter_date){
const date = new Date(letter_date);
const year = date.getUTCFullYear();
const month = date.getUTCMonth() + 1; // 월은 0부터 시작하므로 1을 더합니다.
const day = date.getUTCDate();
const hours = date.getUTCHours();
const minutes = date.getUTCMinutes();
return year+"."+month+"."+day+" "+hours+":"+minutes;
}


function makeSendBox() { }
function makeReceiveBox() { }
function makePage() { }
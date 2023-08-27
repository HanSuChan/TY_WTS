document.addEventListener("DOMContentLoaded", function() {
    const checkButton = document.getElementById("checkDuplicate");
    const idInput = document.getElementById("id");
    const duplicateMessage = document.getElementById("duplicateMessage");

    checkButton.addEventListener("click", function() {
        const id = idInput.value;

        // 서버로 ID 중복 체크 요청 보내기
        fetch("/member/checkDuplicate?id=" + encodeURIComponent(id))
            .then(response => response.json())
            .then(data => {
                if (data.duplicate) {
                    duplicateMessage.textContent = "이미 사용 중인 아이디입니다.";
                } else {
                    duplicateMessage.textContent = "사용 가능한 아이디입니다.";
                }
            })
            .catch(error => {
                console.error("Error checking duplicate ID:", error);
            });
    });
});

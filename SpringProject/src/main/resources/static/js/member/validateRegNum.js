/**
 * 주민번호 유효검사 스크립트
 */
function validateRegNum() {
    var regnum1 = document.getElementById("regnum1").value;
    var regnum2 = document.getElementById("regnum2").value;
    var regnum2Input = document.getElementById('regnum2');
    var firstDigit = regnum2Input.value.charAt(0);
    var maleRadio = document.getElementById('male');
    var femaleRadio = document.getElementById('female');

    // 숫자 이외의 문자 제거
    regnum1 = regnum1.replace(/[^0-9]/g, "");

    // 주민등록번호 유효성 검사 로직을 여기에 추가할 수 있습니다.

    if (regnum1.length !== 6 || regnum2.length !== 7) {
        alert("주민등록번호 형식이 올바르지 않습니다.");
        return;
    }

    // 여기에 주민등록번호 유효성 검사나 처리 로직을 추가할 수 있습니다.

    // 주민 번호 앞자리로 성별 구분 1,3 남자 2,4 여자
    if (firstDigit === '1' || firstDigit === '3') {
        maleRadio.checked = true;
    } else if (firstDigit === '2' || firstDigit === '4') {
        femaleRadio.checked = true;
    }

    alert("주민등록번호가 확인되었습니다.");
}

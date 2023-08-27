/**
 *  회원가입 생년월일 입력을 제한하는 스크립트
 */
function limitYearInput(inputElement) {
	inputElement.value = inputElement.value.replace(/\D/g, ''); // 숫자 이외의 문자 제거

	if (inputElement.value.length > 4) {
		inputElement.value = inputElement.value.slice(0, 4);
	}
}

function limitDayInput(inputElement) {
	inputElement.value = inputElement.value.replace(/\D/g, ''); // 숫자 이외의 문자 제거

	var dayValue = parseInt(inputElement.value, 10);
	if (dayValue < 1) {
		inputElement.value = '1';
	} else if (dayValue > 31) {
		inputElement.value = '31';
	}
}
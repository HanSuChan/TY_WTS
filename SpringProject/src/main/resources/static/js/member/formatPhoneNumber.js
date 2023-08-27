/**
 * 전화번호에 대한 스크립트
 */
 function formatPhoneNumber(input) {
            // 숫자를 제외한 문자 모두 제거
            var cleaned = input.replace(/\D/g, '');
            
            // 전화번호 형식으로 변환
            var formatted = '';
            if (cleaned.length > 0) {
                formatted += cleaned.substring(0, 3);
                if (cleaned.length > 3) {
                    formatted += '-' + cleaned.substring(3, 7);
                    if (cleaned.length > 7) {
                        formatted += '-' + cleaned.substring(7, 11);
                    }
                }
            }
            return formatted;
        }
        
        function updatePhoneNumberInput() {
            var inputElement = document.getElementById('phone');
            var formattedNumber = formatPhoneNumber(inputElement.value);
            inputElement.value = formattedNumber;
        }


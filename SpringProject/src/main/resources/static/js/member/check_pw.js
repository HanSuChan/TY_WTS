/**
 *  비밀번호 체크 스크립트
 */
    function check_pw(){
        	// pw = 입력한 비밀번호
        	// SC = 내가 정한 임의의 특수문자
        	// check_SC  = 특수문자를 체크하는 변수 설정
            var pw = document.getElementById('pw').value;
            var SC = ["!","@","#","$","%"];
            var check_SC = 0;
 
            // 비밀번호에 대한 설명을 적고 조건을 달음
            if(pw.length < 6 || pw.length>16){
                window.alert('비밀번호는 특수문자를 포함한 6글자 이상, 16글자 이하만 이용 가능합니다.');
                document.getElementById('pw').value='';
            }
            
            // 특수문자에 대한 갯수에 대한 조건 이랑 비밀번호 랑 인증 비밀번호가 일치하는지 여부를 
            // 확인하는 조건문을 담
            for(var i=0;i<SC.length;i++){
                if(pw.indexOf(SC[i]) != -1){
                    check_SC = 1;
                }
            }
            if(check_SC == 0){
                window.alert('!,@,#,$,% 의 특수문자가 들어가 있지 않습니다.')
                document.getElementById('pw').value='';
            }
            if(document.getElementById('pw').value !='' && document.getElementById('pw2').value!=''){
                if(document.getElementById('pw').value==document.getElementById('pw2').value){
                    document.getElementById('check').innerHTML='비밀번호가 일치합니다.'
                    document.getElementById('check').style.color='blue';
                }
                else{
                    document.getElementById('check').innerHTML='비밀번호가 일치하지 않습니다.';
                    document.getElementById('check').style.color='red';
                }
            }
        }
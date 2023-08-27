# -*- coding: utf-8 -*-
import requests
import json
import datetime



authkey= 
# 오늘 날짜 가져오기
today = datetime.datetime.today()

# 오늘 날짜를 숫자로 표현하는 문자열 생성 (yyyyMMdd 형식)
searchdate = today.strftime('%Y%m%d')
data='AP01'

url ='https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey={}&searchdate={}&data={}'.format(authkey,searchdate,data)

response = requests.get(url)
r_data = response.json()
filtered_data = []
for entry in r_data:
    filtered_entry = {
        "price": entry['kftc_bkpr'],
        "name": entry['cur_nm']
    }
    filtered_data.append(filtered_entry)
json_data = {
    "exchange" : filtered_data
}
print(json.dumps(json_data, ensure_ascii=False))


#컬럼정보

#result             조회 결과
#cur_unit           통화코드
#ttb                전신환(송금)받으실때
#tts                전신환(송금)보내실때
#deal_bas_r         매매 기준율
#bkpr               장부가격
#yy_efee_r          년환가료율
#ten_dd_efee_r      10일환가료율
#kftc_bkpr          서울외국환중개 매매기준율
#kftc_deal_bas_r    서울외국환중개 장부가격
#cur_nm             국가/통화명
# -*- coding: utf-8 -*-
import win32com.client
import time
from datetime import datetime, timedelta
import json

instStockChart = win32com.client.Dispatch("CpSysDib.StockChart")
nCpCybos = win32com.client.Dispatch("CpUtil.CpCybos")

CPE_MARKET_KIND = {'KOSPI':'U001', 'KOSDAQ':'U201'}
# 오늘 날짜 가져오기
today = datetime.today()

# 오늘 날짜를 숫자로 표현하는 문자열 생성 (yyyyMMdd 형식)
date_string = today.strftime('%Y%m%d')
fromDate = (datetime.today() - timedelta(days=365)).strftime('%Y%m%d')


def get_index_data(market_code):
    instStockChart.SetInputValue(1, ord('1'))  # 개수
    instStockChart.SetInputValue(2, date_string)  # 종목코드 (공백)
    instStockChart.SetInputValue(3, fromDate)  # 조회날짜 (공백)
    instStockChart.SetInputValue(4, 300)  # 조회개수
    instStockChart.SetInputValue(5, (0, 2, 3, 4, 5))  # 필드
    instStockChart.SetInputValue(6, ord('D'))  # 차트 주기 (일 단위)
    instStockChart.SetInputValue(9, ord('1'))  # 수정주가 사용

    remain_request_count = nCpCybos.GetLimitRemainCount(1)

    if remain_request_count == 0:

        while True:
            time.sleep(2)
            remain_request_count = nCpCybos.GetLimitRemainCount(1)
            if remain_request_count > 0:
                break
            print('대기 중...')

    instStockChart.SetInputValue(0, market_code)

    # BlockRequest
    instStockChart.BlockRequest()

    # GetHeaderValue
    numData = instStockChart.GetHeaderValue(3)
    numField = instStockChart.GetHeaderValue(1)

    # Prepare data list
    data = []
    for i in range(numData):
        date = instStockChart.GetDataValue(0, i)
        open_price = instStockChart.GetDataValue(1, i)
        high_price = instStockChart.GetDataValue(2, i)
        low_price = instStockChart.GetDataValue(3, i)
        close_price = instStockChart.GetDataValue(4, i)
        data.append({
            "date": date,
            "open": open_price,
            "high": high_price,
            "low": low_price,
            "close": close_price
        })

    return data


all_data = {}  # 모든 시장 데이터를 담을 딕셔너리

for key, value in CPE_MARKET_KIND.items():
    market_data = get_index_data(value)
    all_data[key] = market_data

json_data = {
    "data": all_data
}
print(json.dumps(json_data, ensure_ascii=False))
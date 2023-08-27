# -*- coding: utf-8 -*-
import win32com.client
import json
objCpCybos = win32com.client.Dispatch("CpUtil.CpCybos")
bConnect = objCpCybos.IsConnect
if bConnect == 0:
    exit()

objCpCodeMgr = win32com.client.Dispatch("CpUtil.CpCodeMgr")
codeList = objCpCodeMgr.GetStockListByMarket(1)  # 거래소
codeList2 = objCpCodeMgr.GetStockListByMarket(2)  # 코스닥

stock_data = []  # 주식 코드 정보를 담을 리스트

for i, code in enumerate(codeList):
    secondCode = objCpCodeMgr.GetStockSectionKind(code)
    name = objCpCodeMgr.CodeToName(code)
    stdPrice = objCpCodeMgr.GetStockStdPrice(code)

    # 주식 코드 정보를 딕셔너리로 추가
    stock_data.append({

        "code": code,
        "secondCode": secondCode,
        "stdPrice": stdPrice,
        "name": name
    })

for i, code in enumerate(codeList2):
    secondCode = objCpCodeMgr.GetStockSectionKind(code)
    name = objCpCodeMgr.CodeToName(code)
    stdPrice = objCpCodeMgr.GetStockStdPrice(code)

    # 주식 코드 정보를 딕셔너리로 추가
    stock_data.append({
        "code": code,
        "secondCode": secondCode,
        "stdPrice": stdPrice,
        "name": name
    })
    json_data={
        "stockList" : stock_data
    }
print(json.dumps(json_data, ensure_ascii=False))

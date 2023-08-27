# -*- coding: utf-8 -*-
import win32com.client
import json
import sys

class CpEvent:
    instance = None

    def OnReceived(self):
        timess = CpEvent.instance.GetHeaderValue(18)  # 초
        exFlag = CpEvent.instance.GetHeaderValue(19)  # 예상체결 플래그
        cprice = CpEvent.instance.GetHeaderValue(13)  # 현재가
        diff = CpEvent.instance.GetHeaderValue(2)  # 대비
        cVol = CpEvent.instance.GetHeaderValue(17)  # 순간체결수량
        vol = CpEvent.instance.GetHeaderValue(9)  # 거래량

        if exFlag == ord('1'):  # 동시호가 시간 (예상체결)
            print("실시간(예상체결)", timess, "*", cprice, "대비", diff, "체결량", cVol, "거래량", vol)
        elif exFlag == ord('2'):  # 장중(체결)
            print("실시간(장중 체결)", timess, cprice, "대비", diff, "체결량", cVol, "거래량", vol)

class CpStockCur:
    def Subscribe(self, code):
        self.objStockCur = win32com.client.Dispatch("DsCbo1.StockCur")
        win32com.client.WithEvents(self.objStockCur, CpEvent)
        self.objStockCur.SetInputValue(0, code)
        CpEvent.instance = self.objStockCur
        self.objStockCur.Subscribe()

    def Unsubscribe(self):
        self.objStockCur.Unsubscribe()

class CpStockMst:
    def Request(self, code):
        self.code = code
        self.name = None  # 종목명
        self.time = None  # 시간
        self.cprice = None  # 현재가
        self.diff = None  # 대비
        self.open = None  # 시가
        self.high = None  # 고가
        self.low = None  # 저가
        self.offer = None  # 매도호가
        self.bid = None  # 매수호가
        self.vol = None  # 거래량
        self.vol_value = None  # 거래대금
        objCpCybos = win32com.client.Dispatch("CpUtil.CpCybos")
        bConnect = objCpCybos.IsConnect
        if bConnect == 0:
            print("PLUS가 정상적으로 연결되지 않음.")
            return False

        objStockMst = win32com.client.Dispatch("DsCbo1.StockMst")
        objStockMst.SetInputValue(0, code)  # 종목 코드
        objStockMst.BlockRequest()

        rqStatus = objStockMst.GetDibStatus()
        rqRet = objStockMst.GetDibMsg1()
        if rqStatus != 0:
            return False

        self.code = objStockMst.GetHeaderValue(0)  # 종목코드
        self.name = objStockMst.GetHeaderValue(1)  # 종목명
        self.time = objStockMst.GetHeaderValue(4)  # 시간
        self.cprice = objStockMst.GetHeaderValue(11)  # 종가
        self.diff = objStockMst.GetHeaderValue(12)  # 대비
        self.open = objStockMst.GetHeaderValue(13)  # 시가
        self.high = objStockMst.GetHeaderValue(14)  # 고가
        self.low = objStockMst.GetHeaderValue(15)  # 저가
        self.offer = objStockMst.GetHeaderValue(16)  # 매도호가
        self.bid = objStockMst.GetHeaderValue(17)  # 매수호가
        self.vol = objStockMst.GetHeaderValue(18)  # 거래량
        self.vol_value = objStockMst.GetHeaderValue(19)  # 거래대금

        
        return True
stock_code = sys.argv[1]  # sys.argv[0]은 스크립트의 이름, sys.argv[1]이 stock_code
stock_mst = CpStockMst()
is_success = stock_mst.Request(stock_code)
if is_success:
    stock_cur = CpStockCur()
    stock_cur.Subscribe(stock_code)

    # 현재가 정보를 JSON 형태로 반환
    current_price_info = {
        "code": stock_mst.code,
        "name": stock_mst.name,
        "time": stock_mst.time,
        "cprice": stock_mst.cprice,
        "diff": stock_mst.diff,
        "open": stock_mst.open,
        "high": stock_mst.high,
        "low": stock_mst.low,
        "offer": stock_mst.offer,
        "bid": stock_mst.bid,
        "vol": stock_mst.vol,
        "vol_value": stock_mst.vol_value
    }
print(json.dumps(current_price_info, ensure_ascii=False))

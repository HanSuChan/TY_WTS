# -*- coding: utf-8 -*-

import win32com.client
import json

g_objCodeMgr = win32com.client.Dispatch('CpUtil.CpCodeMgr')
g_objCpStatus = win32com.client.Dispatch('CpUtil.CpCybos')

def InitPlusCheck():
    if g_objCpStatus.IsConnect == 0:
        print("PLUS가 정상적으로 연결되지 않음.")
        return False

    return True

class CpEvent:
    def set_params(self, client, name, caller):
        self.client = client
        self.name = name
        self.caller = caller

    def OnReceived(self):
        if self.name == 'marketwatch':
            code = self.client.GetHeaderValue(0)
            name = g_objCodeMgr.CodeToName(code)
            cnt = self.client.GetHeaderValue(2)

            for i in range(cnt):
                item = {}
                time = self.client.GetDataValue(0, i)
                h, m = divmod(time, 100)
                item['시간'] = '%02d:%02d' % (h, m)
                update = self.client.GetDataValue(1, i)
                item['코드'] = code
                item['종목명'] = name
                cate = self.client.GetDataValue(2, i)
                self.caller.listWatchData.insert(0, item)

class CpPublish:
    def __init__(self, name, serviceID):
        self.name = name
        self.obj = win32com.client.Dispatch(serviceID)
        self.bIsSB = False

    def Subscribe(self, var, caller):
        if self.bIsSB:
            self.Unsubscribe()

        if len(var) > 0:
            self.obj.SetInputValue(0, var)

        handler = win32com.client.WithEvents(self.obj, CpEvent)
        handler.set_params(self.obj, self.name, caller)
        self.obj.Subscribe()
        self.bIsSB = True

    def Unsubscribe(self):
        if self.bIsSB:
            self.obj.Unsubscribe()
        self.bIsSB = False

class CpPBMarkeWatch(CpPublish):
    def __init__(self):
        super().__init__('marketwatch', 'CpSysDib.CpMarketWatchS')

class CpRpMarketWatch:
    def __init__(self):
        self.objStockMst = win32com.client.Dispatch('CpSysDib.CpMarketWatch')
        self.objpbMarket = CpPBMarkeWatch()
        return

    def Request(self, code, caller):
        self.objpbMarket.Unsubscribe()

        self.objStockMst.SetInputValue(0, code)
        rqField = '1,2'
        self.objStockMst.SetInputValue(1, rqField)
        self.objStockMst.SetInputValue(2, 0)

        ret = self.objStockMst.BlockRequest()
        if self.objStockMst.GetDibStatus() != 0:
            print('통신상태', self.objStockMst.GetDibStatus(), self.objStockMst.GetDibMsg1())
            return False

        cnt = self.objStockMst.GetHeaderValue(2)
        for i in range(cnt):
            item = {}

            time = self.objStockMst.GetDataValue(0, i)
            h, m = divmod(time, 100)
            
            caller.listWatchData.append({
                "time" : '%02d:%02d' % (h, m),
                "code" : self.objStockMst.GetDataValue(1, i),
                "name" : g_objCodeMgr.CodeToName(self.objStockMst.GetDataValue(1, i)),
                #cate = self.objStockMst.GetDataValue(3, i)
                "content" : self.objStockMst.GetDataValue(4, i)
                }
            )
        stock_data = {
            "newsData" : caller.listWatchData
        } 
        print(json.dumps(stock_data, ensure_ascii=False))

        self.objpbMarket.Subscribe(code, caller)

        return True

class MyProgram:
    def __init__(self):
        if not InitPlusCheck():
            exit()

        self.listWatchData = []
        self.objMarketWatch = CpRpMarketWatch()
        self.objMarketWatch.Request('*', self)

        #print(json.dumps(self.listWatchData, ensure_ascii=False))
if __name__ == "__main__":
    myProgram = MyProgram()

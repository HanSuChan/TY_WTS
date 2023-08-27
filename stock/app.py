#-*- coding: utf-8 -*-
#import requests 
import subprocess
from flask import Flask, jsonify ,request
import dart_fss as dart
import pythoncom
import json
import requests

# api key = 

# Open DART API KEY 설정
dart.set_api_key(api_key=api_key)
# DART 에 공시된 회사 리스트 불러오기
corp_list = dart.get_corp_list()
app = Flask(__name__)


@app.route("/getStockList", methods=['GET'])
def getStockList():
    result = subprocess.run(
        ["python", "getStockList.py"],
        stdout=subprocess.PIPE, 
        stderr=subprocess.PIPE, 
        text=True, 
        timeout=30
        )
    captured_output = result.stdout.strip()
    print(captured_output)

    # 캡처한 출력을 JSON으로 파싱
    try:
        current_price_info = json.loads(captured_output)
        response = jsonify(current_price_info)
        response.charset = 'utf-8'  # 문자열 인코딩 설정
        return response
    except json.JSONDecodeError as e:
        return jsonify({"error": "Failed to parse JSON output"})

@app.route('/getStockCurrentPriceInfo', methods=['GET'])
def getStockCurrentPriceInfo():
    stock_code = request.args.get("stockcode")  # stockCode를 쿼리 파라미터로 받음
    result = subprocess.run(
        ["python", "getStockCurrentPriceInfo.py", stock_code],
        stdout=subprocess.PIPE, 
        stderr=subprocess.PIPE, 
        text=True, 
        timeout=30
        )
    captured_output = result.stdout.strip()
    print(captured_output)

    # 캡처한 출력을 JSON으로 파싱
    try:
        current_price_info = json.loads(captured_output)
        response = jsonify(current_price_info)
        response.charset = 'utf-8'  # 문자열 인코딩 설정
        return response
    except json.JSONDecodeError as e:
        return jsonify({"error": "Failed to parse JSON output"})
@app.route("/getFinancialStatements", methods=["GET"])
def getFinancialStatements():
    stock_code = request.args.get("stockcode")  # stockCode를 쿼리 파라미터로 받음
    url = f'https://opendart.fss.or.kr/api/fnlttSinglAcntAll.json'
    # 삼성전자 검색
    print(stock_code)
    stock_code = stock_code[1:]
    samsung = corp_list.find_by_stock_code(stock_code)
    print(samsung.corp_code)
    #dartcode로 해야함.

    params = {
        'crtfc_key': api_key,
        'corp_code': samsung.corp_code,
        'bsns_year': '2021',  # 재무제표를 가져올 연도
        'reprt_code': '11011',  # 11011: 사업보고서, 11012: 반기보고서
        'fs_div': 'CFS',  # CFS: 연결재무제표, OFS: 재무제표
    }
    response = requests.get(url, params=params)
    financial_statements = response.json()
   
    return str(financial_statements), 200, {'Content-Type': 'application/json; charset=UTF-8'}

@app.route('/getStockChart', methods=['GET'])
def getStockChart():
    stock_code = request.args.get("stockcode")  # stockCode를 쿼리 파라미터로 받음
    result = subprocess.run(
        ["python", "getStockChart.py", stock_code],
        stdout=subprocess.PIPE, 
        stderr=subprocess.PIPE, 
        text=True, 
        timeout=30
        )
    captured_output = result.stdout.strip()
    print(captured_output)

    # 캡처한 출력을 JSON으로 파싱
    try:
        current_price_info = json.loads(captured_output)
        response = jsonify(current_price_info)
        response.charset = 'utf-8'  # 문자열 인코딩 설정
        return response
    except json.JSONDecodeError as e:
        return jsonify({"error": "Failed to parse JSON output"})

@app.route("/StockNews", methods=['GET'])
def getStockNews():
    result = subprocess.run(
        ["python", "StockNews.py"],
        stdout=subprocess.PIPE, 
        stderr=subprocess.PIPE, 
        text=True, 
        timeout=30
        )
    captured_output = result.stdout.strip()
    print(captured_output)

    # 캡처한 출력을 JSON으로 파싱
    try:
        current_price_info = json.loads(captured_output)
        response = jsonify(current_price_info)
        response.charset = 'utf-8'  # 문자열 인코딩 설정
        return response
    except json.JSONDecodeError as e:
        return jsonify({"error": "Failed to parse JSON output"})

@app.route("/StockRise",methods=['GET'])
def StockRise():
    result = subprocess.run(
        ["python", "StockRise.py"],
        stdout=subprocess.PIPE, 
        stderr=subprocess.PIPE, 
        text=True, 
        timeout=30
        )
    captured_output = result.stdout.strip()
    print(captured_output)

    # 캡처한 출력을 JSON으로 파싱
    try:
        current_price_info = json.loads(captured_output)
        response = jsonify(current_price_info)
        response.charset = 'utf-8'  # 문자열 인코딩 설정
        return response
    except json.JSONDecodeError as e:
        return jsonify({"error": "Failed to parse JSON output"})

@app.route("/StockExchange",methods=['GET'])
def StockExchange():
    result = subprocess.run(
        ["python", "Exchange.py"],
        stdout=subprocess.PIPE, 
        stderr=subprocess.PIPE, 
        text=True, 
        timeout=30
        )
    captured_output = result.stdout.strip()
    print(captured_output)

    # 캡처한 출력을 JSON으로 파싱
    try:
        current_price_info = json.loads(captured_output)
        response = jsonify(current_price_info)
        response.charset = 'utf-8'  # 문자열 인코딩 설정
        return response
    except json.JSONDecodeError as e:
        return jsonify({"error": "Failed to parse JSON output"})

@app.route('/StockIndex', methods=['GET'])
def StockIndex():
    result = subprocess.run(
        ["python", "StockIndex.py"],
        stdout=subprocess.PIPE, 
        stderr=subprocess.PIPE, 
        text=True, 
        timeout=30
        )
    captured_output = result.stdout.strip()
    print(captured_output)

    # 캡처한 출력을 JSON으로 파싱
    try:
        current_price_info = json.loads(captured_output)
        response = jsonify(current_price_info)
        response.charset = 'utf-8'  # 문자열 인코딩 설정
        return response
    except json.JSONDecodeError as e:
        return jsonify({"error": "Failed to parse JSON output"})


@app.route("/OrderBook",methods=['GET'])
def OrderBook():
    stock_code = request.args.get("stockcode")  # stockCode를 쿼리 파라미터로 받음
    result = subprocess.run(
        ["python", "OrderBook.py",stock_code],
        stdout=subprocess.PIPE, 
        stderr=subprocess.PIPE, 
        text=True, 
        timeout=30
        )
    captured_output = result.stdout.strip()
    print(captured_output)

    # 캡처한 출력을 JSON으로 파싱
    try:
        current_price_info = json.loads(captured_output)
        response = jsonify(current_price_info)
        response.charset = 'utf-8'  # 문자열 인코딩 설정
        return response
    except json.JSONDecodeError as e:
        return jsonify({"error": "Failed to parse JSON output"})


if __name__ == "__main__":
    app.run(host='0.0.0.0', port=5000)

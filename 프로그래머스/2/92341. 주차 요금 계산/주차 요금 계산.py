import math

def solution(fees, records):
    answer = []
    cars = {}
    default_time, default_fee, unit_time, unit_fee = fees

    def find_parking_time(entry_time, departure_time):
        entry_h, entry_m = map(int, entry_time.split(":"))
        depart_h, depart_m = map(int, departure_time.split(":"))
        
        if entry_m < depart_m:
            return 60 * (depart_h - entry_h) + depart_m - entry_m 
        else:
            return 60 * (depart_h - entry_h - 1) + 60 - entry_m + depart_m
    # records
    for record in records:
        a, b, c = record.split(" ")
        
        # 해당 dic 확인한다
        if c == "IN":
            if b in cars: # 두 번째 입차 (시간 들어가있음)
                cars[b] = (a, cars[b])
            else:
                cars[b] = (a, 0)
                
        elif c == "OUT":
            entry_time, total = cars[b]
            cars[b] = total + find_parking_time(entry_time, a)
            
    # 11:59까지 출차 안한 차들 계산
    for car in cars:
        if type(cars[car]) is tuple:
            entry_time, total = cars[car]
            cars[car] = total + find_parking_time(entry_time, "23:59") 

    # cars 딕셔너리 돌면서 total fee 계산
    for car in cars:
        if cars[car] > default_time:
            cars[car] = default_fee + math.ceil((cars[car] - default_time) / unit_time) * unit_fee
        else:
            cars[car] = default_fee
            
    answer = [value for (key, value) in sorted(cars.items())]
    return answer
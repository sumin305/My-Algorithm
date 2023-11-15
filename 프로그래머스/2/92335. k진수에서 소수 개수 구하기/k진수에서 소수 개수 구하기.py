import math
def solution(n, k):
    answer = 0
    
    # n -> k진수로 변환
    def convert(n, k):
        temp = ''
        
        while n > 1:
            mod = n % k
            n = n // k
            temp += str(mod)
        temp += str(n)
        
        return str(int(temp[::-1]))    
    
    def is_prime_number(n):
        
        if n <= 1: return 0
        for i in range(2, int( math.sqrt(n) ) + 1):
            if n % i == 0: 
                return 0
        return 1

    
    convert_num = convert(n, k)
    temp = ''
    print(convert_num)

    for num in convert_num:
        if num == '0':
            if temp == '' or temp == '1': 
                temp = ''
                continue
            if is_prime_number(int(temp)):
                print(int(temp))
                answer += 1
            temp = ''
            continue
        temp += num

    if temp != '' and is_prime_number(int(temp)):
        print(int(temp))

        answer += 1
    return answer
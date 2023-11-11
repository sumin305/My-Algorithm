import math 

def find_depth(binary):
    digit = 2 ** (int(math.log(len(binary), 2)) + 1) - 1
    binary = "0" * (digit - len(binary)) + binary
    return binary

def check_complete(bin, parent):

    length = len(bin)
    mid = length // 2

    if not mid:
        if bin[0] == '1' and not parent:
            return 0
        else:
            return 1

    if bin[mid] == '1' and not parent:
        return 0

    left = bin[:mid]
    right = bin[mid+1:]

    return check_complete(left, int(bin[mid])) and check_complete(right, int(bin[mid]))        

def solution(numbers):
    
    answer = []
    
    for number in numbers:
        binary = bin(number)[2:]
        binary = (find_depth(binary))

        mid = len(binary) // 2
        
        if not binary[mid]: 
            answer.append(0)
                    
        elif check_complete(binary, binary[mid]):
            answer.append(1)
            
        else: # 이진트리로 표현할 수 없을 때
            answer.append(0)
            
    return answer
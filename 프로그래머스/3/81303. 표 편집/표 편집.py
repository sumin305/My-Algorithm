import copy

class node:
    def __init__(self, num):
        self.num = num
        self.left = None
        self.right = None

def solution(n, k, cmd):
    answer = ['O' for i in range(n)]
    tables = []
    
    for i in range(n):
        tables.append(node(i))
    
    for i in range(n):
        if i != 0:
            tables[i].left = tables[i-1]
        if i != n-1:
            tables[i].right = tables[i+1]

    garbages = []
    
    current = k
    
    for c in cmd:
        commands = c.split(" ")
        if commands[0] == "D": # 아래로 이동
            count = int(commands[1])
            for i in range(count):
                current = tables[current].right.num
                
        elif commands[0] == "U": # 위로 이동
            count = int(commands[1])
            for i in range(count):
                current = tables[current].left.num
                
        elif commands[0] == "C": # 삭제
            answer[current] = 'X'
            garbages.append(current)

            if tables[current].right == None: # 마지막 행인 경우
                current = tables[current].left.num
                tables[current].right = None
                
            elif tables[current].left == None:
                current = tables[current].right.num
                tables[current].left = None
                
            else:
                temp = current
                current = tables[current].right.num
                tables[current].left = tables[temp].left
                tables[temp].left.right = tables[current]
                
        elif commands[0] == "Z": # 복구
            pop_number = garbages.pop()
            answer[pop_number] = "O"
    
            if tables[pop_number].right != None:
                tables[pop_number].right.left = tables[pop_number]
            if tables[pop_number].left != None:
                tables[pop_number].left.right = tables[pop_number]


    return ''.join(answer)
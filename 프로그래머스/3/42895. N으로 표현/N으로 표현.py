def solution(N, number):
    global answer
    answer = 1e9
    dp = [set() for i in range(9)]
    
    def calculate(num1, op, num2):
        if op == "+": return num1 + num2
        elif op == "-": return num1 - num2
        elif op == "*": return num1 * num2
        else: return num1 // num2
            
    def solve():
        global answer

        if N == number: 
            answer = 1
            return
        for i in range(1, 9):
            num = int(str(N) * i)
            dp[i].add(num)
            if num == number: 
                answer = i
                return
            for j in range(1, i):
                for num1 in dp[j]:
                    for num2 in dp[i-j]:
                        for op in ["+", "-", "*", "//"]:
                            if num2 == 0: continue
                            new = calculate(num1, op, num2)
                            if new in dp[i]: continue
                            dp[i].add(new)
                            if new == number: 
                                answer = min(answer, i)

    solve()
    print(dp[2])
    if answer == 1e9: answer = -1
    return answer
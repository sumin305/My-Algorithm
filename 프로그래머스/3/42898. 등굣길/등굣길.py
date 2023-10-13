def solution(m, n, puddles):
    answer = 0
    puddles = list(map(tuple, puddles))
    dp = [[0] * m for i in range(n)]
    dp[0] = [1] * m
    for i in range(n):
        dp[i][0] = 1
    
    for i in range(0, n):
        for j in range(0, m):
            if (j+1,i+1) in puddles: 
                dp[i][j] = 0
                continue
            if i == 0:
                dp[i][j] = dp[i][j-1]
            elif j == 0:
                dp[i][j] = dp[i-1][j]
            else:
                dp[i][j] = dp[i-1][j] + dp[i][j-1]
    for d in dp:
        print(d)
    return dp[-1][-1] % 1000000007
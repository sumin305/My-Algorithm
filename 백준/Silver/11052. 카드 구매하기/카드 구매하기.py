N = int(input())
arr = [0]
arr.extend(list(map(int, input().split())))

dp = [[0 for i in range(N+1)] for j in range(len(arr))]

for i in range(1, len(arr)):
    for j in range(1, N+1):
        if j < i: dp[i][j] = dp[i-1][j]
        else: 
            dp[i][j] = max(dp[i-1][j], dp[i][j - i] + arr[i])
print(dp[-1][-1])
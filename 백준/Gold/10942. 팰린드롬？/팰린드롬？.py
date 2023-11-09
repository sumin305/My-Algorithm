import sys
input = sys.stdin.readline
N = int(input())
arr = list(map(int, input().split()))

dp = [[0 for i in range(N)] for j in range(N)]

for i in range(N):
    dp[i][i] = 1
    if i < N-1 and arr[i] == arr[i+1]:
        dp[i][i+1] = 1

# dp 초기화
for j in range(N):
    for i in range(N):
        if j < i or i == j: continue

        if arr[i] == arr[j] and dp[i+1][j-1]:
            dp[i][j] = 1


M = int(input())

for _ in range(M):
    S, E = map(int, input().split())
    print(dp[S-1][E-1])
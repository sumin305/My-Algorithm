import sys
input = sys.stdin.readline
N = int(input())
dp = [0] * (N+1)
t = []
p = []
for i in range(N):
    a,b = map(int,input().split())
    t.append(a)
    p.append(b)
M = 0
for n in range(N):
    M = max(M, dp[n])
    if n+t[n] > N :
        continue
    dp[n+t[n]] = max(M+p[n], dp[n+t[n]])
print(max(dp))
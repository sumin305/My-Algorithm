import sys
input=sys.stdin.readline
N=int(input())
lst =[0 for i in range(301)]
dp=[0 for i in range(301)]
for i in range(N):
    lst[i]=int(input())
dp[0]=lst[0]
dp[1]=lst[0]+lst[1]
dp[2]=max(lst[0],lst[1])+lst[2]
for i in range(3,N):
    dp[i]=max(dp[i-2],lst[i-1]+dp[i-3])+lst[i]
print(dp[N-1])
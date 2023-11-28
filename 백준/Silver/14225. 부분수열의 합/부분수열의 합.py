N = int(input())
arr = list(map(int, input().split()))


visited = [0] * 10000000

def dfs(index, sum):
    if index == N:
        return
    sum += arr[index]
    visited[sum] = 1
    dfs(index+1, sum)
    dfs(index+1, sum - arr[index])

dfs(0, 0)
print(visited[1:].index(0)+1)
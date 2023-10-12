N, S = map(int, input().split())
lst = list(map(int, input().split()))
visited = [False for i in range(N)]
global count 
count = 0
def dfs(idx, total):
    global count 
    if total == S:
        count += 1
    for i in range(idx+1, N):
        if not visited[i]:
            visited[i] = True
            dfs(i, total+lst[i])
            visited[i] = False
for i in range(N):
    visited[i] = True
    dfs(i, lst[i])
print(count)
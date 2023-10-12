N, M = map(int, input().split())

visited = [[False for i in range(M)] for i in range(N)]
map = [list(map(int, input().split())) for _ in range(N)]
global result
result = 0

def dfs(x, y, size, count):
    global result

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    if size == 4:
        result = max(result, count)
        return
    
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if 0<=nx<N and 0<=ny<M and not visited[nx][ny]:

            if size == 2:
                visited[nx][ny] = True
                dfs(x, y, size+1, count + map[nx][ny])
                visited[nx][ny] = False

            visited[nx][ny] = True
            dfs(nx, ny, size+1, count+ map[nx][ny])
            visited[nx][ny] = False 

for i in range(N):
    for j in range(M):
        if not visited[i][j]:
            visited[i][j] = True
            dfs(i, j, 1, map[i][j])
            visited[i][j] = False
            
print(result)

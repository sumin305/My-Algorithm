from collections import deque
def solution(maps):
    global answer
    answer = 1e9
    n, m = len(maps), len(maps[0])
    def BFS(x, y):
        global answer
        visited = [[False] * m for i in range(n)]
        queue = deque([[x, y, 1]])
        dx = [-1, 1, 0, 0]
        dy = [0, 0, 1, -1]
        visited[x][y] = True    
        while queue:
            target = queue.popleft()
            if target[0] == n-1 and target[1] == m-1:
                answer = min(answer, target[2])
            for i in range(4):
                nx = target[0] + dx[i]
                ny = target[1] + dy[i]
                if 0<=nx<n and 0<=ny<m and not visited[nx][ny] and maps[nx][ny] == 1:
                    visited[nx][ny] = True
                    queue.append([nx, ny, target[2] + 1])
                
    BFS(0, 0)
    if answer == 1e9: answer = -1

    return answer
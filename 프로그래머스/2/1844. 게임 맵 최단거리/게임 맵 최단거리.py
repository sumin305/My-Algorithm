from collections import deque
def solution(maps):
    n, m = len(maps), len(maps[0])
    def BFS(x, y):
        global answer
        queue = deque([[x, y, 1]])
        dx = [-1, 1, 0, 0]
        dy = [0, 0, 1, -1]
        while queue:
            target = queue.popleft()
            if target[0] == n-1 and target[1] == m-1:
                return target[2]
            for i in range(4):
                nx = target[0] + dx[i]
                ny = target[1] + dy[i]
                if 0<=nx<n and 0<=ny<m and maps[nx][ny] == 1:
                    maps[nx][ny] += 1
                    queue.append([nx, ny, target[2] + 1])
        return -1
    return BFS(0, 0)
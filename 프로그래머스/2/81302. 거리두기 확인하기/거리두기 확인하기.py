from collections import deque

def solution(places):
    answer = []
    
    def BFS(place, x, y):
        visited = [[0 for i in range(5)] for j in range(5)]
        visited[x][y] = 1
        dx = [-1, 1, 0, 0]
        dy = [0, 0, -1, 1]
        queue = deque([(x, y, 0)])
        
        while queue:
            x, y, cost = queue.popleft()

            if place[x][y] == "P":
                if 0 < cost <= 2:
                    return 0
            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]
                if 0<=nx<5 and 0<=ny<5 and not visited[nx][ny] and place[nx][ny] != "X" and cost <= 2: 
                    queue.append((nx, ny, cost+1))
                    visited[nx][ny] = 1
        return 1
                
            
    def check_keep_distance(place):
        flag = 1  # 거리두기 지키면 1, 아니면 0
        for i in range(5):
            for j in range(5):
                if place[i][j] == "P":
                    flag = BFS(place, i, j)
                    if flag == 0: return 0
        return flag
        
    for place in places: # 하나의 응시실씩 검사한다.
        if check_keep_distance(place): answer.append(1)
        else: answer.append(0)
    return answer

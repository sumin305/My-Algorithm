import sys
sys.setrecursionlimit(5000)

def solution(n, m, x, y, r, c, k):
    global result, flag
    result = "impossible"
    
    dist = abs(x-r) + abs(y-c)
    if dist > k or (k - dist) % 2 == 1: return result
    map = [['.' for i in range(m)] for j in range(n)]
    
    map[x-1][y-1] = 'S'
    map[r-1][c-1] = 'E'
    
    vector = ['d', 'l', 'r', 'u']
    
    dx = [1, 0, 0, -1]
    dy = [0, -1, 1, 0]
    flag = 0
    
    def DFS(distance, x, y, path):
        global result, flag
        
        if distance == k:
            if map[x][y] == "E" and not flag: 
                result = path
                flag = 1
            return 
        
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            
            if 0<=nx<n and 0<=ny<m and not flag and abs(nx-r+1) + abs(ny-c+1) + (distance) <= k : 
                DFS(distance + 1, nx, ny, path + vector[i])
                
    DFS(0, x-1, y-1, '')
    return result
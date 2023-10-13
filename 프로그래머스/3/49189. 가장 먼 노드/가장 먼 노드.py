from collections import deque

def solution(n, edge):
    answer = 0
    nodes = [[] for i in range(n+1)]
    depths = [0, 1]
    
    for e in edge:
        nodes[e[0]].append(e[1])
        nodes[e[1]].append(e[0])
    visited = [0 for i in range(n+1)]
    visited[1] = 1

    def bfs():
        queue = deque([(1, 0)])
        while queue:
            num, depth = queue.popleft()
            
            print(num, depth)
            if depth > depths[0]:
                depths[0] = depth
                depths[1] = 1
            elif depth == depths[0]:
                depths[1] += 1
                
            for node in nodes[num]:
                if not visited[node]:
                    queue.append((node, depth+1))
                    visited[node] = 1
    bfs()    
    print(depths)  

    return depths[1]
from collections import deque

def BFS(coms, idx, visited):
    queue = deque([])
    queue.append(idx)
    visited[idx] = 1
    while queue:
        target = queue.popleft()
        for idx in coms[target]:
            if visited[idx] == 0:
                queue.append(idx)
                visited[idx] = 1
            
def solution(n, computers):
    answer = 0
    visited = [0 for i in range(n)]
    coms = {}
    for idx in range(n):
        coms[idx] = [i for (i, elem) in enumerate(computers[idx]) if i != idx and elem == 1]
    for (idx, elem) in coms.items():
        if visited[idx] == 0:
            BFS(coms, idx, visited)
            answer += 1
    return answer
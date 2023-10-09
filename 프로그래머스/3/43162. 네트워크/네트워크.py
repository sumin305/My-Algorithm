def solution(n, computers):
    global answer, visited
    answer = 0
    visited = [0] * (n)
    com_array = [[] for i in range(n)]
    for i in range(n):
        for j in range(n):
            if i == j: continue
            if computers[i][j] == 1: com_array[i].append(j)
    
    def dfs(n):
        global answer, visited

        for node in com_array[n]:
            if not visited[node]:
                visited[node] = 1
                dfs(node)
                
    for i in range(n):
        if not visited[i]:
            answer += 1
            visited[i] = 1
            dfs(i)
    return answer
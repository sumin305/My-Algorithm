def solution(n, results):
    answer = 0
    nodes = [[0] * n for i in range(n)]
    
    for win, lose in results:
        nodes[win-1][lose-1] = 1
        nodes[lose-1][win-1] = -1
        
    for row in nodes:
        print(row)
    for k in range(n):
        for i in range(n):
            for j in range(n):
                if i == j or nodes[i][j] in [1, -1]:
                    continue
                if nodes[i][k] == nodes[k][j] == 1:
                    nodes[i][j] = 1
                    nodes[j][i] = nodes[k][i] = nodes[j][k] = -1
    for row in nodes:
        print(row)
        if row.count(0) == 1:
            answer += 1
    return answer
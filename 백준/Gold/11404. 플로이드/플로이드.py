import heapq

n = int(input())
m = int(input())

result = []

bus = [[] for i in range(n+1)]

for i in range(m):
    a, b, c = map(int, input().split())
    bus[a].append((b, c))

def dijkstra(start):
    visited =  [1e9 for i in range(n+1)]
    visited[start] = 0
    queue = [(0, start)]

    while queue:
        cost,  node = heapq.heappop(queue)

        if cost > visited[node]:
            continue 
        
        for next_node, next_cost in bus[node]: # 주위에 있는 노드들을 살핀다.
            if cost + next_cost < visited[next_node]:
                visited[next_node] = min(cost+ next_cost, visited[next_node])
                heapq.heappush(queue, (visited[next_node], next_node))

    for i in range(1, n+1):
        if visited[i] == 1e9:
            visited[i] = 0

    return visited
    
for start in range(1, n+1):
    answer = dijkstra(start)
    result.append(answer)

for r in result:
    print(*r[1:])
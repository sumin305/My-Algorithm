import heapq, sys
V, E = map(int, sys.stdin.readline().strip().split())

nodes = [[] for i in range(V+1)]
for i in range(E):
    A, B, C = map(int, sys.stdin.readline().strip().split())
    nodes[A].append((B, C))
    nodes[B].append((A, C))

visited = [False] * (V+1)

def dijkstra(start):
    q = []
    heapq.heappush(q, (0, start))
    answer = 0
    result_array = []

    while len(result_array) < V:
        current_distance, current_node = heapq.heappop(q)
        if visited[current_node]:
            continue
        visited[current_node] = True
        result_array.append(current_node)
        answer += current_distance

        for node in nodes[current_node]:
                heapq.heappush(q, (node[1], node[0]))
    return answer

print(dijkstra(1))
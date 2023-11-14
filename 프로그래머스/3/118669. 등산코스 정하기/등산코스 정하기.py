import heapq
def solution(n, paths, gates, summits):
    
    global visited
    global maps
    maps = [1e9 for i in range(n+1)]
    visited = [0 for i in range(n+1)]
    routes = [[] for i in range(n+1)]
    gates = set(gates)
    summits = set(summits)
    for i, j, w in paths:
        routes[i].append((j, w))
        routes[j].append((i, w))
        

    
    def dijkstra(gate, visited):
        global maps
        queue = []
        heapq.heappush(queue, (0, gate))
        maps[gate] = 0
        visited[gate] = 1
        while queue:
            dist, now = heapq.heappop(queue)
            if now in summits: # 봉우리라면 
                continue
                
            if maps[now] < dist:
                continue
                
            for next_node, next_edge in routes[now]:
                if next_node in gates: continue
                cost = max(maps[now], next_edge)
                    
                if cost < maps[next_node]:
                    maps[next_node] = cost
                    heapq.heappush(queue, (cost, next_node))
    min_value = 1e9
    min_node = 0
    # gates를 돌면서 찾아본다.

    for gate in gates:
        dijkstra(gate, visited)

    for summit in summits:
        if (min_value > maps[summit]) or (min_value == maps[summit] and min_node > summit):
            min_node = summit
            min_value = maps[summit]
    return [min_node, min_value]
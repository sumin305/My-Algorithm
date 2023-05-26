func solution(_ n:Int, _ edge:[[Int]]) -> Int {
    
    // 입력받은 값들 그래프 형식으로 나타내기 -> Dictionary 사용해보자
    // BFS이용하여 그래프 탐색
    // 가장 depth 높은 것들의 개수 -> 마지막 노드들의 depth 모아서 계산
    var graph: [[Int]] = Array(repeating: [], count: n+1)
    var minLength: [Int] = Array(repeating: 0, count: n+1)
    var maxValue = -1
    //var visited: [Bool] = Array(repeating: false, count: n+1)
    for e in edge {
        graph[e[0]].append(e[1])
        graph[e[1]].append(e[0])
    }
    func BFS(_ start: Int) {
        var queue: [(vertex: Int, depth: Int)] = [(start, 0)]
    
        while !queue.isEmpty {
            let (currentVertex, currentDepth) = queue.removeFirst()
        
            if currentDepth > maxValue {
                maxValue = currentDepth
            }

            for adj in graph[currentVertex] {
                if minLength[adj] == 0 && adj != 1{
                    queue.append((adj, currentDepth + 1))
                    minLength[adj] = currentDepth + 1
                }
            }
        }
    }
    BFS(1)
    return minLength.filter{$0 == maxValue}.count
}
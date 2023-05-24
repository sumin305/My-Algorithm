import Foundation

func solution(_ n:Int, _ wire:[[Int]]) -> Int {
    var graph: [[Int]] = Array(repeating: [], count: n+1)
    var visited: [Bool] = Array(repeating: false, count: n+1)
    var resultArr: [Int] = []
    
    func dfs (_ first: Int, _ wire: [Int]) -> Int {
        var queue: [Int] = [first]
        var count = 0
        while !queue.isEmpty {
            let target = queue.removeFirst()
            visited[target] = true
            count += 1
            
            for adj in graph[target] {
               if wire.contains(target) {
                    if adj == wire[1 - wire.firstIndex(of: target)!] {
                        continue
                    }
                }
                if !visited[adj] {
                   queue.append(adj)
                }
            }
        }
        return count
    }
    
    for w in wire {
        graph[w[0]].append(w[1])
        graph[w[1]].append(w[0])
    }
    
    for w in wire {
        visited = Array(repeating: false, count: n+1)
        var countArr: [Int] = []
        for i in 1...n {
            if !visited[i] {
                countArr.append(dfs(i, w))
            }
        }
        resultArr.append(abs(countArr[0]-countArr[1]))
    }
    
    return resultArr.min()!
}
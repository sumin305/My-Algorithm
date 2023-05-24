import Foundation

func solution(_ n:Int, _ computers:[[Int]]) -> Int {
    var visited: [Bool] = Array(repeating: false, count: n)
    var result = 0
    func BFS(startIdx: Int) {
        var queue: [Int] = [startIdx]
        visited[startIdx] = true
        while !queue.isEmpty {
            let target = queue.removeFirst()

            for idx in 0..<computers[target].count {
                if idx == target {
                    continue
                }
                if computers[target][idx] == 1 && !visited[idx] {
                    queue.append(idx)
                    visited[idx] = true
                }
            }
        }
    }
    
    for i in 0..<n {
        if !visited[i] {
            BFS(startIdx: i)
            result += 1
        }
    }
    return result
}
import Foundation

let n = Int(readLine()!)!
let m = readLine()!.split(separator: " ").map{Int(String($0))!}
let (A, B) = (m[0], m[1])

var nodes: [[Int]] = Array(repeating: [], count: n+1)
var visited: [Bool] = Array(repeating: false, count: n+1)
for i in 0..<Int(readLine()!)! {
    let a = readLine()!.split(separator: " ").map{Int(String($0))!}
    nodes[a[0]].append(a[1])
    nodes[a[1]].append(a[0])
}

func BFS(_ first: Int, _ end: Int) -> Int {
    var queue: [(num: Int, count: Int)] = [(first, 0)]
    visited[first] = true
    
    while !queue.isEmpty {
        let target = queue.removeFirst()
        if target.num == end {
            return target.count
        }
        for node in nodes[target.num] {
            if !visited[node] {
                queue.append((node, target.count+1))
                visited[node] = true
            }
        }
    }
    return -1
}

print(BFS(A, B))

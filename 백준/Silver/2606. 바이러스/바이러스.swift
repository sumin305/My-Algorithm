import Foundation
let N = Int(readLine()!)!
let M = Int(readLine()!)!
var lst: [[Int]] = Array(repeating: [], count: N+1)
var visited: [Bool] = Array(repeating: false, count: N+1)
var queue: [Int] = [1]
var count = 0
for i in 0..<M {
    let a = readLine()!.split(separator: " ").map{Int(String($0))!}
    lst[a[0]].append(a[1])
    lst[a[1]].append(a[0])
}
visited[1] = true
while !queue.isEmpty {
    let target = queue.removeFirst()
    for node in lst[target] {
        if !visited[node] {
            queue.append(node)
            visited[node] = true
            count += 1
        }
    }
}
print(count)
import Foundation 
let n = readLine()!.split(separator: " ").map{Int(String($0))!}
let N = n[0]
let M = n[1]

let array: [Int] = readLine()!.split(separator: " ").map{Int(String($0))!}

var visited: [Bool] = Array(repeating: false, count: N)
var result = 0

makeGroup(0, 0, 0)
func makeGroup(_ idx: Int, _ start: Int, _ sum: Int) {
    if idx == 3 {
        if sum <= M {
            result = max(result, sum)
        }
        return
    }
    for i in start..<N {
        if !visited[i] {
            visited[i] = true
            makeGroup(idx + 1, i + 1, sum + array[i])
            visited[i] = false
        }
    }
}
print(result)
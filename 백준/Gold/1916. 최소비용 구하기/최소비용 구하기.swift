import Foundation

let N = Int(readLine()!)!
let M = Int(readLine()!)!
var bus: [[(Int, Int)]] = Array(repeating: [], count: N+1)
for i in 0..<M{
    let n = readLine()!.split(separator: " ").map{Int(String($0))!}
    bus[n[0]].append((n[1], n[2]))
}
let s = readLine()!.split(separator: " ").map{Int(String($0))!}
var (current, end) = (s[0], s[1])
var visited: [Bool] = Array(repeating: false, count: N+1)
var min_pay = [Int](repeating: Int.max, count: N+1)
min_pay[current] = 0
while visited[end] == false{
    var current = min_pay.enumerated().filter({!visited[$0.offset]}).min(by: {$0.element < $1.element})!.offset
    visited[current] = true
    for adj in bus[current]{
        if !visited[adj.0]{
            min_pay[adj.0] = min(min_pay[current] + adj.1, min_pay[adj.0])
        }
    }
}
print(min_pay[end])
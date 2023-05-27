import Foundation

let n = readLine()!.split(separator: " ").map{Int(String($0))!}
let (F,S,G,U,D) = (n[0], n[1], n[2], n[3], n[4])


func canGo(_ num: Int) -> Bool {
    if 1...F ~= num {
        return true
    } else {
        return false
    }
}
func BFS(_ start: Int) -> Int {
    var queue: [(num: Int, depth: Int)] = [(start, 0)]
    var visited: [Bool] = Array(repeating: false, count: F+1)
    let next: [Int] = [U, (-1)*D]
    visited[start] = true
    while !queue.isEmpty {
        let target = queue.removeFirst()
        
        if target.num == G {
            return target.depth
        }
        for n in next {
            let nextNum = target.num + n
            if canGo(nextNum) && !visited[nextNum] {
                queue.append((nextNum, target.depth+1))
                visited[nextNum] = true
            }
        }
    }
    return -1
}

let result = BFS(S)
if result == -1 {
    print("use the stairs")
} else {
    print(result)
}
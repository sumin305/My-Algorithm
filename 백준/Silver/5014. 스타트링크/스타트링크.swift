import Foundation

struct Queue<T> {
    var inbox: [T]
    var outbox: [T] = []
    var count: Int {
        return inbox.count + outbox.count
    }
    var isEmpty: Bool {
        return inbox.isEmpty && outbox.isEmpty
    }
    init(_ queue: [T]) {
        inbox = queue
    }
    mutating func pushLast(_ element: T) {
        inbox.append(element)
    }
    mutating func pushFirst(_ element: T) {
        outbox.append(element)
    }
    mutating func popLast() -> T {
        if inbox.isEmpty {
            inbox = outbox.reversed()
            outbox.removeAll()
        }
        return inbox.popLast()!
    }
    mutating func popFirst() -> T {
        if outbox.isEmpty {
            outbox = inbox.reversed()
            inbox.removeAll()
        }
        return outbox.popLast()!
    }
}

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
    var queue = Queue([(num: start, depth: 0)])
    var visited: [Bool] = Array(repeating: false, count: F+1)
    visited[start] = true
    let next: [Int] = [U, (-1)*D]
    while !queue.isEmpty {
        let target = queue.popFirst()
        if target.num == G {
            return target.depth
        }
        for n in next {
            let nextNum = target.num + n
            if canGo(nextNum) && !visited[nextNum] {
                queue.pushLast((nextNum, target.depth+1))
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

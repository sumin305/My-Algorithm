import Foundation
class Queue<T> {
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
    
    func pushLast(_ element: T) {
        inbox.append(element)
    }
    
    func pushFirst(_ element: T) {
        outbox.append(element)
    }
    
    func popLast() -> T {
        if inbox.isEmpty {
            inbox = outbox.reversed()
            outbox.removeAll()
        }
        return inbox.popLast()!
    }
    
    func popFirst() -> T {
        if outbox.isEmpty {
            outbox = inbox.reversed()
            inbox.removeAll()
        }
        return outbox.popLast()!
    }
}
let S = Int(readLine()!)!
var queue = Queue([(1, 0, 0)])
var visited = Array(repeating: Array(repeating: false, count: 2000), count: 2000)

while !queue.isEmpty {
    let (board, clip, time) = queue.popFirst()
    
    if board == S {
        print(time)
        break
    }
    if (1..<2000) ~= board {
        if !visited[board][board] {
            visited[board][board] = true
            queue.pushLast((board, board, time+1))
        }
        if !visited[board-1][clip] {
            visited[board-1][clip] = true
            queue.pushLast((board-1, clip, time+1))
        }
      
    }
        // 한 개 지우기
    if clip > 0 && board + clip < 2000 {
        if !visited[board+clip][clip] {
            visited[board+clip][clip] = true
            queue.pushLast((board+clip, clip, time+1))
        }
    }
}

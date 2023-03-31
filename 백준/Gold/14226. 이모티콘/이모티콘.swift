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
    // 복사하기
    if (1...1000) ~= board {
        if !visited[board][board] {
            visited[board][board] = true
            queue.pushLast((board, board, time+1))
        }
    }
    // 삭제하기
    if (1...1000) ~= board-1 {
        if !visited[board-1][clip] {
            visited[board-1][clip] = true
            queue.pushLast((board-1, clip, time+1))
        }
    }
    // 붙여넣기
    // 클립보드가 비어있지 않고, 붙여넣는 값과 화면의 값의 합이 1000을 넘지 않는 경우
    if clip > 0 && board+clip <= 1000 {
        if !visited[board+clip][clip] {
            visited[board+clip][clip] = true
            queue.pushLast((board+clip, clip, time+1))
        }
    }
}

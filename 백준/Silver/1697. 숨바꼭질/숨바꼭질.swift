import Foundation
//import Foundation
//struct Queue<T> {
//    var inbox: [T]
//    var outbox: [T] = []
//    var count: Int {
//        return inbox.count + outbox.count
//    }
//
//    var isEmpty: Bool {
//        return inbox.isEmpty && outbox.isEmpty
//    }
//
//    init(_ queue: [T]) {
//        inbox = queue
//    }
//
//    mutating func pushLast(_ element: T) {
//        inbox.append(element)
//    }
//
//    mutating func pushFirst(_ element: T) {
//        outbox.append(element)
//    }
//
//    mutating func popLast() -> T {
//        if inbox.isEmpty {
//            inbox = outbox.reversed()
//            outbox.removeAll()
//        }
//        return inbox.popLast()!
//    }
//
//    mutating func popFirst() -> T {
//        if outbox.isEmpty {
//            outbox = inbox.reversed()
//            inbox.removeAll()
//        }
//        return outbox.popLast()!
//    }
//}
struct Queue<T> {
    var inbox: [T]
    var outbox: [T] = []
    var count: Int {
        return self.inbox.count + self.outbox.count
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
let (N, K) = (n[0], n[1])

func BFS(_ start: Int) -> Int {
    var queue = Queue([(num: start, depth: 0)])
    var visited: [Bool] = Array(repeating: false, count: 100001)
    while !queue.isEmpty {
        let target = queue.popFirst()
        
        visited[target.num] = true
        if target.num == K {
            return target.depth
        }
        
        let next = [target.num-1, target.num+1, target.num*2]
        
        for n in next {
            if 0...100000 ~= n && !visited[n] {
                queue.pushLast((n, target.depth+1))
            }
        }
    }
    return -1
}

print(BFS(N))
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
let input = readLine()!.split(separator: " ").map{Int(String($0))!}
let (N, K) = (input[0], input[1])
var visited = Array(repeating: false, count: 100001)
var prev = Array(repeating: 0, count: 100001)
var count = 0
var minDepth = 100001

//방문하지 않았고, 순간이동할 수 있는 위치인지 확인
func isVisitedPossible(_ x: Int) -> Bool {
    if ((0...100000) ~= x) && !visited[x] {
        return true
    }
    else{
        return false
    }
}
func printPath(_ num: Int) {
    var tmp = num
    var tempArray: [Int] = []
    while tmp != N {
        tempArray.append(tmp)
        tmp = prev[tmp]
    }
    tempArray.append(N)
    for i in tempArray.reversed() {
        print(i, terminator: " ")
    }
}
func findSister(_ startX: Int) {
    if N == K {
        print(0)
        print(N)
        return
    }
    let queue = Queue([(startX, 0)])
    while !queue.isEmpty {
        var (num, depth) = queue.popFirst()  
        for next in [num+1, num-1, num*2] {
            if isVisitedPossible(next) {
                visited[next] = true
                queue.pushLast((next,depth+1))
                prev[next] = num
                if next == K {
                    print(depth+1)
                    printPath(next)
                    return
                }
            }
        }
    }
}

findSister(N)
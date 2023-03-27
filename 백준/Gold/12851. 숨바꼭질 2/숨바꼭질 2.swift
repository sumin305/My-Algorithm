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
func findSister(_ startX: Int) {
    // 수빈이 위치 queue에 넣고 visited true로 초기화.
    let queue = Queue([(startX, 0)])
    visited[startX] = true
    
    while !queue.isEmpty {
        let (num, depth) = queue.popFirst()
        // 큐에서 pop할 때 방문처리
        visited[num] = true
        
        // 동생을 찾은 경우
        if num == K {
            // 처음 동생을 찾은 경우
            if count == 0 {
                minDepth = depth
                print(minDepth)
                count += 1
            }
            else {
                // 같은 가장 빠른 시간 내에 또 다른 동생을 찾는 방법
                if minDepth == depth {
                    count += 1
                }
            }
        }
        
        if isVisitedPossible(num+1) {
            queue.pushLast((num+1, depth+1))
        }
        if isVisitedPossible(num-1) {
            queue.pushLast((num-1, depth+1))
        }
        if isVisitedPossible(num*2) {
            queue.pushLast((num*2, depth+1))
        }
    }
}

findSister(N)
print(count)

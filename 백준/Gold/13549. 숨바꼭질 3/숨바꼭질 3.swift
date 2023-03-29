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
    
    //queue가 비었을 때까지
    while !queue.isEmpty {
        let (num, cost) = queue.popFirst()
        // 큐에서 pop할 때 방문처리
        visited[num] = true
        
        // 동생을 찾은 경우 cost 출력
        if num == K {
            print(cost)
            return
        }
        
        //방문할 수 있는 위치인지 확인하고 X-1, X+1 이동시 cost+1, Xx2 이동시 cost를 넣어준다
        if isVisitedPossible(num*2) {
            queue.pushLast((num*2, cost))
        }
        if isVisitedPossible(num-1) {
            queue.pushLast((num-1, cost+1))
        }
        if isVisitedPossible(num+1) {
            queue.pushLast((num+1, cost+1))
        }
    }
}
findSister(N)

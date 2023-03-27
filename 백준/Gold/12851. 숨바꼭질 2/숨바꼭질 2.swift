class Dequeue<T> {
    var enQueue: [T]
    var deQueue: [T] = []
    
    var count: Int {
        return enQueue.count + deQueue.count
    }
    
    var isEmpty: Bool {
        return enQueue.isEmpty && deQueue.isEmpty
    }
    
    init(_ queue: [T]) {
        enQueue = queue
    }
    
    func pushLast(_ element: T) {
        enQueue.append(element)
    }
    
    func pushFirst(_ element: T) {
        deQueue.append(element)
    }
    
    func popLast() -> T {
        if enQueue.isEmpty {
            enQueue = deQueue.reversed()
            deQueue.removeAll()
        }
        return enQueue.popLast()!
    }
    
    func popFirst() -> T {
        if deQueue.isEmpty {
            deQueue = enQueue.reversed()
            enQueue.removeAll()
        }
        return deQueue.popLast()!
    }
}
let input = readLine()!.split(separator: " ").map{Int(String($0))!}
let (N, K) = (input[0], input[1])
var visited = Array(repeating: false, count: 100001)

var resultArr: [Int] = []
func bfs(_ startX: Int) {
    let queue = Dequeue([(startX, 0)])
    visited[startX] = true // 첫 시작 true로 초기화.
    
    while !queue.isEmpty {
        let (num, depth) = queue.popFirst()
        visited[num] = true // 큐에서 pop할 때 방문처리
        
        if num == K { // 탈출 가능인데,
            if resultArr.isEmpty { // 처음이면,
                resultArr.append(depth)
            }
            else {
                // 같은 가장 빠른 시간 내에 또 다른 동생을 찾는 방법
                if depth == resultArr[0] {
                    resultArr.append(depth)
                }
            }
        }
        
        if ((0...100000) ~= num+1) && !visited[num + 1] { // (x+1) - 유효 범위고, 방문할 수 있는 곳이고, 방문한 적 없으면,
            queue.pushLast((num+1, depth+1))
        }
        if ((0...100000) ~= num-1) && !visited[num - 1] { // (x-1) - 유효 범위고, 방문할 수 있는 곳이고, 방문한 적 없으면,
            queue.pushLast((num-1, depth+1))
        }
        if ((0...100000) ~= num*2) && !visited[num * 2 ] { // (2*x) - 유효 범위고, 방문할 수 있는 곳이고, 방문한 적 없으면,
            queue.pushLast((2*num, depth+1))
        }
    }
    
}

bfs(N)
print(resultArr.first!)
print(resultArr.count)
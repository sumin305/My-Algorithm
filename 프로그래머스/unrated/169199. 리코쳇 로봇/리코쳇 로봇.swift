import Foundation
extension String {
    subscript(idx: Int) -> String {
        let target = index(self.startIndex, offsetBy: idx)
        return String(self[target])
    }
}
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

func solution(_ board:[String]) -> Int {
    func canGo(_ x: Int, _ y: Int) -> Bool {
        if 0..<board.count ~= x && 0..<board[0].count ~= y && board[x][y] != "D" {
            return true
        }
        else {
            return false
        }
    }
    var visited = Array(repeating: Array(repeating: false, count: board[0].count), count: board.count)
    var queue = Queue([(0,0,0)])
    var goal = (0,0)
    var first = (0,0)
    for i in 0..<board.count {
        for j in 0..<board[0].count {
            if board[i][j] == "R" {
                queue = Queue([(i, j, 0)])
                first = (i,j)
                visited[i][j] = true
            }
            if board[i][j] == "G" {
                goal = (i,j)
            }
        }
    }
    let dx = [1,-1,0,0]
    let dy = [0,0,1,-1]
    // 최소 움직임을 구해야하므로 BFS로 그래프 탐색
    while !queue.isEmpty {
        let (x,y,z) = queue.popFirst()
        if x == goal.0 && y == goal.1 {
            return z
        }

        for i in 0...3 {
            // 이동할 수 있는 방향인지 확인하고 계속 이동
            var nx = x + dx[i]
            var ny = y + dy[i]
            if canGo(nx, ny) {
                while true {
                    nx = nx + dx[i]
                    ny = ny + dy[i]
                    if !canGo(nx, ny) {
                        nx = nx - dx[i]
                        ny = ny - dy[i]
                        break
                    }
                 }
            } else {
                continue
            }
            print(nx,ny)
            if !visited[nx][ny] {
               queue.pushLast((nx, ny, z+1))
                visited[nx][ny] = true
                
            }

            }
 
    }
    return -1
}
// ["...D..R", 
//  ".D.G...", 
//  "....D.D", 
//  "D....D.", 
//  "..D...."]
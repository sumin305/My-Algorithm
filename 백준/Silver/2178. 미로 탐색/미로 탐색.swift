import Foundation
let n = readLine()!.split(separator: " ").map{Int(String($0))!}
let (N,M) = (n[0], n[1])
var arr : [[Int]] = []
var visited = Array(repeating: Array(repeating: false, count: M), count: N)
for _ in 0..<N {
    arr.append(Array(readLine()!).map{Int(String($0))!})
}

var dx = [1,-1,0,0]
var dy = [0,0,1,-1]

// 깊이 탐색으로 하면 최단 거리를 구할 수 없으므로 너비 탐색으로 진행한다
func BFS() -> Int{
    arr[0][0] = 1
    var queue: [(x: Int, y: Int, count: Int)] = [(0,0,1)]
    //큐가 비어있지않으면 popleft
    while !queue.isEmpty {
        let target = queue.removeFirst()
        if target.x == N-1 && target.y == M-1{
            return target.count
        }
        //popleft한 좌표의 동서남북에 이동할 수 있는지 확인
        for i in 0...3 {
            let nx = target.x + dx[i]
            let ny = target.y + dy[i]
            
            // 그래프를 벗어날 경우 넘어간다
            if nx < 0 || ny < 0 || nx >= N || ny >= M {
                continue
            }
            // 아직 방문하지 않았고 이동할 수 있는 경우, queue에 넣고, visited = true로 한다
            // count를 방문전 값에 +1 해준다
            if !visited[nx][ny] && arr[nx][ny] == 1 {
                arr[nx][ny] = 0
                queue.append((nx,ny, target.count+1))
            }
        }
    }
    return queue.count
}

print(BFS())
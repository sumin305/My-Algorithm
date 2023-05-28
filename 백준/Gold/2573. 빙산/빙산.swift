// 양의 정수로 표현
// 빙산 이외의 바다에 해당되는 칸에는 0 저장
// 동서남북 네 방향에 0 이 저장된 칸의 개수만큼 줄어든다 (0보다는 줄어들지 않는다)
// 빙산이 두 덩어리 이상으로 분리되는 최초의 시간 구하기
// 만약 전부 다 녹을때까지 두 덩어리 이상으로 분리되지 않으면 0 출력

import Foundation

let n = readLine()!.split(separator: " ").map{Int(String($0))!}
let (N, M) = (n[0], n[1])
var icebergs: [[Int]] = Array(repeating: [], count: N)
var zeroiceBergs: [[Int]] = Array(repeating: Array(repeating: 1, count: M), count: N)
for i in 0..<N {
    icebergs[i] = readLine()!.split(separator: " ").map{Int(String($0))!}
    for j in 0..<M {
        if icebergs[i][j] == 0 {
            zeroiceBergs[i][j] = 0
        }
    }
}

func canGo(_ x: Int, _ y: Int) -> Bool {
    if 0..<N ~= x && 0..<M ~= y {
        return true
    } else {
        return false
    }
}

let dx = [-1, 1, 0, 0]
let dy = [0, 0, -1, 1]


func shortenHeight() {
    var zeroIdx: [(Int, Int)] = []
    for i in 0..<N {
        for j in 0..<M {
            if icebergs[i][j] != 0 {
                var zeroCount = 0
                for k in 0...3 {
                    let nx = i + dx[k]
                    let ny = j + dy[k]
                    
                    if canGo(nx, ny) && zeroiceBergs[nx][ny] == 0 {
                        zeroCount += 1
                    }
                }
                if icebergs[i][j] - zeroCount <= 0 {
                    icebergs[i][j] = 0
                    zeroIdx.append((i,j))
                } else {
                    icebergs[i][j] -= zeroCount
                }
            }
        }
    }
    for idx in zeroIdx {
        zeroiceBergs[idx.0][idx.1] = 0
    }
}

func countRegion() -> Int {
    var visited: [[Bool]] = Array(repeating: Array(repeating: false, count: M), count: N)
    
    var count = 0
    for i in 0..<N {
        for j in 0..<M {
            if !visited[i][j] && icebergs[i][j] != 0 {
                visited[i][j] = true
                DFS(i,j)
                count += 1
            }
        }
    }
    func DFS(_ x: Int, _ y: Int) {
        for i in 0...3 {
            let nx = x + dx[i]
            let ny = y + dy[i]
        
            if canGo(nx, ny) && !visited[nx][ny] && icebergs[nx][ny] != 0 {
                visited[nx][ny] = true
                DFS(nx, ny)
            }
        }
    }
    return count
}
func findMinTime() -> Int {
    var time = 0
    while !icebergs.flatMap{$0}.allSatisfy{ $0 == 0} {
        if countRegion() >= 2 {
            return time
        }
        time += 1
        shortenHeight()
        
    }
    return 0
}

print(findMinTime())

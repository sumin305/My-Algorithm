import Foundation

let N = Int(readLine()!)!

var arr: [[Int]] = Array(repeating: [], count:  N)
var visited: [[Bool]] = Array(repeating: Array(repeating: false, count: N), count: N)
var minHeight: Int = 101
var maxHeight: Int = 0

// 주어진 영역중 가장 낮은 높이와 높은 높이 검색
for i in 0..<N {
    arr[i] = readLine()!.split(separator: " ").map{Int(String($0))!}
    if let m = arr[i].max() {
        if m > maxHeight {
            maxHeight = m
        }
    }
    if let n = arr[i].min() {
        if n < minHeight {
            minHeight = n
        }
    }
}

var maxCount = 1

let dx = [-1, 1, 0, 0]
let dy = [0, 0, -1, 1]

// 다음 이동할 공간의 영역안의 공간인지 확인
func canGo(_ i: Int, _ j: Int) -> Bool {
    if 0..<N ~= i && 0..<N ~= j {
        return true
    } else {
        return false
    }
}

func DFS(_ i: Int, _ j: Int, _ height: Int) {
    for k in 0..<4 {
        let nx = i + dx[k]
        let ny = j + dy[k]
        
        if !canGo(nx, ny) || visited[nx][ny] || arr[nx][ny] <= height {
            continue
        }
        
        visited[nx][ny] = true
        DFS(nx, ny, height)
    }
}

// 가장 높은 높이부터 가장 높은 높이까지 돌며 안전한 영역 개수 count
for k in minHeight...maxHeight {
    var count = 0
    visited = Array(repeating: Array(repeating: false, count: N), count: N)
    for i in 0..<N {
        for j in 0..<N {
            if arr[i][j] > k && !visited[i][j] {
                visited[i][j] = true
                DFS(i,j,k)
                count += 1
            }
        }
        if maxCount < count {
            maxCount = count
        }
    }
    
}
print(maxCount)

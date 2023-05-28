import Foundation

let N = Int(readLine()!)!

var arr: [[Int]] = Array(repeating: [], count:  N)
var safeArea: [[[Int]]] = []
var visited: [[Bool]] = Array(repeating: Array(repeating: false, count: N), count: N)
var minHeight: Int = 101
var maxHeight: Int = 0
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

func canGo(_ i: Int, _ j: Int) -> Bool {
    if 0..<N ~= i && 0..<N ~= j {
        return true
    } else {
        return false
    }
}
func DFS(_ i: Int, _ j: Int, _ tempArr: inout [[Int]], _ height: Int) {
    for k in 0..<4 {
        let nx = i + dx[k]
        let ny = j + dy[k]
        
        if !canGo(nx, ny) || visited[nx][ny] || arr[nx][ny] <= height {
            continue
        }
        
        visited[nx][ny] = true
        tempArr.append([nx, ny])
        DFS(nx, ny, &tempArr, height)
    }
}

for k in minHeight...maxHeight {
    safeArea = []
    visited = Array(repeating: Array(repeating: false, count: N), count: N)
    for i in 0..<N {
        for j in 0..<N {
            if arr[i][j] > k && !visited[i][j] {
                var tempArr: [[Int]] = [[i,j]]
                visited[i][j] = true
                DFS(i,j,&tempArr,k)
                safeArea.append(tempArr)
            }
        }
    }
    if maxCount < safeArea.count {
        maxCount = safeArea.count
    }
}
print(maxCount)
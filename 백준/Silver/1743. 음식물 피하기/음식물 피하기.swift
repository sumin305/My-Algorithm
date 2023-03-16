import Foundation

let n = readLine()!.split(separator: " ").map{Int(String($0))!}
let (N,M,K) = (n[0], n[1], n[2])
var arr: [[Character]] = Array(repeating: Array(repeating: ".", count: M), count: N)
var visited: [[Bool]] = Array(repeating: Array(repeating: false, count: M), count: N)
for i in 0..<K {
    let a = readLine()!.split(separator: " ").map{Int(String($0))!}
    arr[a[0]-1][a[1]-1] = "#"
}
let dx = [1,-1,0,0]
let dy = [0,0,1,-1]
var maxCount = 0
func DFS(_ x: Int, _ y: Int, _ count: inout Int) {
    count += 1
    visited[x][y] = true
    if count >= maxCount {
        maxCount = count
    }
    for i in 0...3 {
        let nx = x + dx[i]
        let ny = y + dy[i]
        if !((0..<N) ~= nx) || !((0..<M) ~= ny) {
           continue
        }
        if !visited[nx][ny] && arr[nx][ny] == "#" {
            DFS(nx,ny,&count)
        }
    }
}

for i in 0..<N {
    for j in 0..<M {
        if arr[i][j] == "#" {
            var count = 0
            DFS(i,j,&count)
        }
    }
}
print(maxCount)
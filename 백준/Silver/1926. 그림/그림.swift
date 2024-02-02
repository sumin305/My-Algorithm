import Foundation

let temp: [Int] = readLine()!.split(separator: " ").map{Int(String($0))!}
let n = temp[0]
let m = temp[1]
var max_width = 0
var count = 0
var map: [[Int]] = Array(repeating: Array(repeating: 0, count: m), count: n)
var visited: [[Bool]] = Array(repeating: Array(repeating: false, count: m), count: n)
var width = 0
let dx = [-1, 1, 0, 0]
let dy = [0, 0, -1, 1]
for i in 0..<n {
  let temp: [Int] = readLine()!.split(separator: " ").map{Int(String($0))!}
  for j in 0..<m {
    map[i][j] = temp[j]
  }
}

for i in 0..<n {
  for j in 0..<m {
    if !visited[i][j] && map[i][j] == 1 {
      count += 1
      width = 1
      visited[i][j] = true
      search(i, j)
      max_width = max(max_width, width)
    }
  }
}
print(count)
print(max_width)

func search(_ x: Int, _ y: Int) {
  for i in 0..<4 {
    let nx = x + dx[i]
    let ny = y + dy[i]
    if onMap(nx, ny) && !visited[nx][ny] && map[nx][ny] == 1 {
      visited[nx][ny] = true
      width += 1
      search(nx, ny)
    }
  }
}

func onMap(_ x: Int, _ y: Int) -> Bool {
  return 0..<n ~= x && 0..<m ~= y
}

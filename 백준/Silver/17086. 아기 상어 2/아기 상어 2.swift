import Foundation

let n = readLine()!.split(separator: " ").map{Int(String($0))!}
let (N,M) = (n[0], n[1])
var shark: [[Int]] = []
for _ in 0..<N {
    let str = readLine()!.split(separator: " ").map{Int(String($0))!}
    shark.append(str)
}
var maxDistance = 0

let dx = [-1, -1, -1, 0, 0, 1, 1, 1]
let dy = [0, 1, -1, -1, 1, 0, 1, -1]

var queue: [(x: Int, y: Int)] = []

for i in 0..<N {
    for j in 0..<M {
        if shark[i][j] == 1 {
            queue.append((i,j))
        }
    }
}

func isCanGo(_ x: Int, _ y: Int) -> Bool {
    if 0..<N ~= x && 0..<M ~= y {
        return true
    } else {
        return false
    }
}

while !queue.isEmpty {
    let target = queue.removeFirst()
    for i in 0...7 {
        let nx = target.x + dx[i]
        let ny = target.y + dy[i]
        if isCanGo(nx, ny) && shark[nx][ny] == 0 {
            queue.append((nx, ny))
            shark[nx][ny] = shark[target.x][target.y]+1
        }
    }
}

print(shark.flatMap{$0}.max()!-1)
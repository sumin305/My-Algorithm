//11559 Puyo Puyo

import Foundation

var count = 0
var field: [[Character]] = []
for _ in 0..<12 {
    field.append(Array(readLine()!))
}
func canGo(_ x:Int, _ y:Int) -> Bool {
    if 0..<12 ~= x && 0..<6 ~= y {
        return true
    } else {
        return false
    }
}
// 연쇄 시작 터지는 그룹 count
func bubble() -> Bool {
    var visited: [[Bool]] = Array(repeating: Array(repeating: false, count: 6), count: 12)
    var isBubble = false
    func BFS(_ x: Int, _ y: Int) -> Bool {
        var count = 1
        var queue: [(Int, Int)] = [(x, y)]
        var arr: [(Int, Int)] = [(x, y)]
        visited[x][y] = true
        let dx = [-1,1,0,0]
        let dy = [0,0,-1,1]
        
        while !queue.isEmpty {
            let target = queue.removeFirst()
            for k in 0...3 {
                let nx = target.0 + dx[k]
                let ny = target.1 + dy[k]
                
                if canGo(nx, ny) && !visited[nx][ny] && field[nx][ny] == field[x][y] {
                    queue.append((nx, ny))
                    arr.append((nx, ny))
                    visited[nx][ny] = true
                    count += 1
                }
            }
        }
        if count >= 4 {
            for a in arr {
                field[a.0][a.1] = "."
            }
            return true
        }
        return false
    }
    for i in 0..<12 {
        for j in 0..<6 {
            if !visited[i][j] && field[i][j] != "." {
                if BFS(i, j) {
                    isBubble = true
                }
            }
        }
    }
    return isBubble
}

// 아래로 떨어지기
func felldown() {
    for i in 0..<6 {
        var temp: [Character] = []
        
        for j in stride(from: 11, to: -1, by: -1) {
            temp.append(field[j][i])
        }
        let lastIdx = temp.lastIndex(where: {$0 == "R" || $0 == "G" || $0 == "B" || $0 == "Y" || $0 == "P"})
        if let idx = lastIdx {
            temp.append(contentsOf: Array(repeating: ".", count: temp[0...idx].filter{$0 == "."}.count))
            temp[0...idx].removeAll(where: {$0 == "."})
            
            
        }
        temp.reverse()
        
        for j in 0..<12 {
            field[j][i] = temp[j]
        }
    }
}
while bubble() {
    // 1연쇄 
    count += 1

    
    felldown()
//    print("------")
//    for f in field {
//        print(f)
//    }
//    break
}
print(count)


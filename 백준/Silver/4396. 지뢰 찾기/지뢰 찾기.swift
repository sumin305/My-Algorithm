import Foundation

let n = Int(readLine()!)!
var landMine: [[Character]] = []
var arr: [[Character]] = []
var result: [[Character]] = Array(repeating: Array(repeating: ".", count: n), count: n)
for i in 0..<n {
    landMine.append(Array(readLine()!))
}
for i in 0..<n {
    arr.append(Array(readLine()!))
}

let dx = [-1, -1, -1, 0, 0, 1, 1, 1]
let dy = [-1, 1, 0, -1, 1, -1, 1, 0]

func canGo(_ x: Int, _ y: Int) -> Bool {
    if 0..<n ~= x && 0..<n ~= y {
        return true
    } else {
        return false
    }
}
func steppingOnMine() {
    for i in 0..<n {
        for j in 0..<n {
            if landMine[i][j] == "*" {
                result[i][j] = "*"
            }
        }
    }
}
func gameLandMine() {
    for i in 0..<n {
        for j in 0..<n {
            if arr[i][j] == "x" {
                var count = 0
                for k in 0..<8 {
                    let nx = i + dx[k]
                    let ny = j + dy[k]
                    
                    if canGo(nx, ny) {
                        if landMine[nx][ny] == "*" {
                            count += 1
                        }
                    }
                }
                result[i][j] = Character(String(count))
                if landMine[i][j] == "*" {
                    steppingOnMine()
                }

            }
        }
    }

}
gameLandMine()
for r in result {
    print(r.map{String($0)}.joined())
}
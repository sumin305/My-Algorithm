import Foundation

let N = Int(readLine()!)!

var satisfaction = 0
var favorites: [[Int]] = Array(repeating: [], count: N*N+1)
var arr : [[(Int, Int)]] = Array(repeating: Array(repeating: (0,0), count: N), count: N)
var seats: [[Int]] = Array(repeating: Array(repeating: 0, count: N), count: N)
let dx = [-1,1,0,0]
let dy = [0,0,-1,1]

for _ in 0..<(N*N) {
    
    let n = readLine()!.split(separator: " ").map{Int(String($0))!}
    
    favorites[n[0]] = Array(n[1...])
    arr = Array(repeating: Array(repeating: (0,0), count: N), count: N)
    updateArr(n[0])
    
    let (x, y) = findSeat(n[0])
    seats[x][y] = n[0]
    
}

func canGo(_ x: Int, _ y: Int) -> Bool {
    if 0..<N ~= x && 0..<N ~= y {
        return true
    } else {
        return false
    }
}

func findSeat(_ num: Int) -> (Int, Int) {
    var maxValue : (Int, Int) = (Int.min, Int.min)
    for i in 0..<N {
        for j in 0..<N {
            if arr[i][j].0 > maxValue.0 {
                maxValue = (arr[i][j].0, arr[i][j].1)
            } else if arr[i][j].0 == maxValue.0 {
                if arr[i][j].1 > maxValue.1 {
                    maxValue = (arr[i][j].0, arr[i][j].1)
                }
            }
        }
    }
    
    for i in 0..<N {
        for j in 0..<N {
            if seats[i][j] == 0 && arr[i][j].0 == maxValue.0 && arr[i][j].1 == maxValue.1 {
                return (i,j)
            }
        }
    }
    return (0,0)
}

func updateArr(_ num: Int) {
    for i in 0..<N {
        for j in 0..<N {
            if seats[i][j] == 0 {
                var adjCount = 0
                var emptyCount = 0
                for k in 0...3 {
                    let nx = i + dx[k]
                    let ny = j + dy[k]
                    
                    if canGo(nx, ny) {
                        if favorites[num].contains(seats[nx][ny]) {
                            adjCount += 1
                        } else if seats[nx][ny] == 0 {
                            emptyCount += 1
                        }
                    }
                }
                arr[i][j] = (adjCount, emptyCount)
            }
        }
    }
}

func countsatisfaction() {
    for i in 0..<N {
        for j in 0..<N {
            var count = 0
            for k in 0...3 {
                let nx = i + dx[k]
                let ny = j + dy[k]
                
                if canGo(nx, ny) {
                    if favorites[seats[i][j]].contains(seats[nx][ny]) {
                        count += 1
                    }
                }
            }
            if count == 1 {
                satisfaction += 1
            } else if count >= 2 {
                satisfaction += Int("1" + String(repeating: "0", count: count-1))!
            }
            
        }
    }
}
countsatisfaction()
print(satisfaction)
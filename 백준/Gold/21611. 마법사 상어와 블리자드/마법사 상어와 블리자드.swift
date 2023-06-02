import Foundation

let n = readLine()!.split(separator: " ").map{Int(String($0))!}
let (N, M) = (n[0], n[1])
let dx = [-1, 1, 0, 0]
let dy = [0, 0, -1, 1]
let shark = (N/2, N/2)
var totalCount = [0, 0, 0]
var arr: [[Int]] = []
var oneDimensionalArr: [Int] = []

for _ in 0..<N {
    arr.append(readLine()!.split(separator: " ").map{Int(String($0))!})
}
for _ in 0..<M {
    let m = readLine()!.split(separator: " ").map{Int(String($0))!}
    let (d, s) = (m[0], m[1])
    destroyMarbles(d, s)
    TwoToOne()
    moveMarbles()
    while removeMarbles() {
        moveMarbles()
    }
    grouping()
    OneToTwo()

}
var result = 0
for i in 0...2 {
    result += totalCount[i] * (i+1)
}
print(result)
func destroyMarbles(_ d: Int, _ s: Int) {
    var nx = shark.0
    var ny = shark.1
    for _ in 0..<s {
        nx += dx[d-1]
        ny += dy[d-1]
        
        if canGo(nx, ny) && arr[nx][ny] != 0 {
            arr[nx][ny] = 0
        }
        
    }
}
func canGo(_ x: Int, _ y: Int) -> Bool {
    if 0..<N ~= x && 0..<N ~= y {
        return true
    } else {
        return false
    }
}

func removeMarbles() -> Bool {
    var oneMoreRemove = false
        var target = (oneDimensionalArr[1], 1)
    for i in 0..<oneDimensionalArr.count {
        if oneDimensionalArr[i] == target.0 {
            target.1 += 1
        } else {
            if target.1 >= 4 {
                oneMoreRemove = true
                totalCount[target.0 - 1] += target.1
                for j in stride(from: i-1, to: i-1-target.1, by: -1) {
                    oneDimensionalArr[j] = 0
                }
            }
            target = (oneDimensionalArr[i], 1)
        }
    }
    return oneMoreRemove
}

func moveMarbles() {
    var tempArr = oneDimensionalArr[1...].filter({$0 != 0})
    oneDimensionalArr = [0] + tempArr + Array(repeating: 0, count: N*N - tempArr.count)
}
func grouping() {
    var tempArr: [Int] = []
    var count = 1
    for i in 2..<oneDimensionalArr.count {
        if tempArr.count >= N*N {
            break
        }
        else if oneDimensionalArr[i] != oneDimensionalArr[i-1] {
            if tempArr.count + 1 <= N*N {
                tempArr.append(count)
            }
            if tempArr.count + 1 <= N*N {
                tempArr.append(oneDimensionalArr[i-1])
            }
            
            if oneDimensionalArr[i] == 0 {
                tempArr.append(contentsOf: Array(repeating: 0, count: N*N - tempArr.count))
                break
            }
            count = 1
        }
        else {
            count += 1
        }
    }
    oneDimensionalArr = tempArr
}
func TwoToOne() {
    oneDimensionalArr = []
    let dx = [1, 0, -1, 0]
    let dy = [0, 1, 0, -1]
    var k = 2
    var (x, y) = (shark.0, shark.1)
    oneDimensionalArr.append(arr[x][y])
    while k < N {
        if canGo(x, y-1) {
            y -= 1
            oneDimensionalArr.append(arr[x][y])
        } else {
            break
        }
        for i in 0..<4 {
            for j in 0..<k {
                if i == 0 && j == 0 {
                    continue
                }
                x = x + dx[i]
                y = y + dy[i]
                
                if canGo(x, y) {
                    oneDimensionalArr.append(arr[x][y])
                }
            }
        }
        k += 2
    }
}

func OneToTwo() {
    var tempArr: [[Int]] = Array(repeating: Array(repeating: 0, count: N), count: N)
    let dx = [1, 0, -1, 0]
    let dy = [0, 1, 0, -1]
    
    var (x, y) = (shark.0, shark.1)
    
    tempArr[x][y] = 0
    var vector = 0
    var K = 2
    var k = 0
    
    for t in oneDimensionalArr {
        
        if vector == 0 && k == 0 {
            y -= 1
            if canGo(x, y) {
                tempArr[x][y] = t
                k += 1
            }
            continue
        }
        x += dx[vector]
        y += dy[vector]
        
        if canGo(x,y) {
            tempArr[x][y] = t
            k += 1
            
        }
        if k == K {
            vector += 1
            k = 0
        }
        if vector == 4 {
            K += 2
            vector = 0
            k = 0
            if K > N {
                break
            }
        }
    }
    arr = tempArr
    
}

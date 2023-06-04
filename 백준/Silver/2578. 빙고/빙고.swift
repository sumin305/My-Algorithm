import Foundation

var bingo: [[Int]] = []
var host: [Int] = []
for i in 0..<5 {
    bingo.append(readLine()!.split(separator: " ").map{Int(String($0))!})
}

for i in 0..<5 {
    let hst = readLine()!.split(separator: " ").map{Int(String($0))!}
    for h in hst {
        host.append(h)
    }
}
func doBingo() {
    for idx in 0..<host.count {
        goZero(host[idx])
        if checkBingo() >= 3 {
            print(idx+1)
            return
        }
    }
}

func goZero(_ target: Int) {
    for i in 0..<5 {
        for j in 0..<5 {
            if bingo[i][j] == target {
                bingo[i][j] = 0
            }
        }
    }
}
func checkBingo() -> Int {
    let dx = [-1, 1, 0, 0]
    let dy = [0, 0, -1, 1]
    
    var count = 0
    
    for i in 0..<5 {
        if bingo[i].allSatisfy{$0 == 0} {
            count += 1
        }
    }
    
    for i in 0..<5 {
        for j in 0..<5 {
            if bingo[j][i] != 0 {
                break
            }
            if j == 4 {
                count += 1
            }
        }
    }
    
    for i in 0..<5 {
        if bingo[i][i] != 0 {
            break
        }
        if i == 4 {
            count += 1
        }
    }
    
    for i in 0..<5 {
        if bingo[i][4-i] != 0 {
            break
        }
        if i == 4 {
            count += 1
        }
    }
    return count
}

doBingo()

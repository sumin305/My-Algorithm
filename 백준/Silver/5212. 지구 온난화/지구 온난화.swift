import Foundation
// r*c크기 x는 땅, .은 바다
// 인접한 세 칸 또는 네 칸이 바다로 둘러싸인 공간은 모두 잠김

// 지도 입력받기
let n = readLine()!.split(separator: " ").map{Int(String($0))!}
let (R, C) = (n[0], n[1])
var map: [[Character]] = []
var sea: [(Int, Int)] = []
for _ in 0..<R {
    map.append(Array(readLine()!))
}
// 50년 후 잠기기

let dx = [-1,1,0,0]
let dy = [0,0,-1,1]

for i in 0..<R {
    for j in 0..<C {
        if map[i][j] == "X" {
            var count = 0
            for k in 0...3 {
                let nx = i + dx[k]
                let ny = j + dy[k]
                if !(0..<R ~= nx) || !(0..<C ~= ny) {
                    count += 1
                    continue
                }
                if map[nx][ny] == "." { count += 1 }
            }
            if count >= 3 {
                sea.append((i, j))
            }
        }
    }
}
for s in sea {
    map[s.0][s.1] = "."
}

// 지도 갱신
var (minR, maxR) = (R-1, 0)
var (minC, maxC) = (C-1, 0)

for i in 0..<R {
    for j in 0..<C {
        if map[i][j] == "X" {
            minR = min(minR, i)
            maxR = max(maxR, i)
            minC = min(minC, j)
            maxC = max(maxC, j)
        }
    }
}
for i in minR...maxR {
    for j in minC...maxC {
        print(map[i][j], terminator: "")
    }
    print("")
}
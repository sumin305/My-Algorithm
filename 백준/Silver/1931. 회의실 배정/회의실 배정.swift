import Foundation

let N = Int(readLine()!)!
var meetings: [(Int, Int)] = []
for i in 0..<N {
    let n = readLine()!.split(separator: " ").map{Int(String($0))!}
    meetings.append((n[0], n[1]))
}

// 끝나는 시간이 가장 빠른 시간 순으로, 끝나는 시간이 같을 경우 시작 시간이 빠른 경우부터 정렬
meetings.sort{$0.1 == $1.1 ? $0.0 > $1.0 : $0.1 > $1.1}

var time = 0
var count = 0
while !meetings.isEmpty {
    let target = meetings.removeLast()
    if target.0 >= time {
        time = target.1
        count += 1
    }
}
print(count)

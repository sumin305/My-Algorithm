import Foundation

let N = Int(readLine()!)!
var queue: [Int] = readLine()!.split(separator: " ").map{Int(String($0))!}
var waitTime = 0
var result = 0
queue.sort{$0 > $1}
while !queue.isEmpty {
    let target = queue.removeLast()
    result = result + waitTime + target
    waitTime += target
}
print(result)
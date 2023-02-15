import Foundation

let n = readLine()!.split(separator: " ").map{Int(String($0))!}
let (N, M) = (n[0], n[1])
var arr: [[Int]] = Array(repeating: [], count: N+1)
var inDegree = Array(repeating: 0, count: N+1)
var queue = Array<Int>()
var result = Array<Int>()
for i in 0..<M{
    let m = readLine()!.split(separator: " ").map{Int(String($0))!}
    let (A, B) = (m[0], m[1])
    arr[A].append(B)
    inDegree[B] += 1
}

for i in 1...N{
    if inDegree[i] == 0{
        queue.append(i)
    }
}

while !queue.isEmpty{
    var current = queue.remove(at: 0)
    result.append(current)
    
    for i in arr[current]{
        inDegree[i] -= 1
        
        if inDegree[i] == 0{
            queue.append(i)
        }
    }
}
for i in result{
    print(i, terminator: " ")
}
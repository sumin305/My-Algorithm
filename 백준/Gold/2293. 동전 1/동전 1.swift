import Foundation

let N = readLine()!.split(separator: " ").map{Int(String($0))!}
let (n,k) = (N[0], N[1])
var numArray : [[Int]] = Array(repeating: Array(repeating: 0, count: k+1), count: n+1)
var coin : [Int] = []
for i in 0..<n {
    coin.append(Int(readLine()!)!)
}

for i in 0..<n {
    numArray[i+1][0] = 1
}

for i in 1...n {
    for j in 1...k {
        if j >= coin[i-1] {
            if numArray[i-1][j] + numArray[i][j-coin[i-1]] >= Int(pow(2.0, 31.0)){
                numArray[i][j] = 0
            }else{
                numArray[i][j] = numArray[i-1][j] + numArray[i][j-coin[i-1]]
            }
        }
        else {
            numArray[i][j] = numArray[i-1][j]
        }
       
    }
}

print(numArray[n][numArray[n].endIndex-1])

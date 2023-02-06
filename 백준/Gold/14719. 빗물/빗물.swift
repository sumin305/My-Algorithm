import Foundation
let n = readLine()!.split(separator: " ").map{Int(String($0))!}
let (H,W) = (n[0], n[1])
var array: [[Int]] = Array(repeating: Array(repeating: 0, count: W), count: H)
let m = readLine()!.split(separator: " ").map{Int(String($0))!}
for i in 0...W-1{
    for j in stride(from: H-1, to: H-1-m[i], by: -1){
        array[j][i] = 1
    }
}
var bool = false
var count: Int = 0
var temp: Int = 0

for i in 0...H-1{
    for j in 0...W-2 {
        if array[i][j] == 1{
            if bool{
                count += temp
                temp = 0
                
            }
            if array[i][j+1] == 0{
                bool = true
            }else{
                bool = false
            }
        }
        else{
            if bool{
                temp += 1
            }
        }
    }
    if array[i][array[i].endIndex-1] == 1{
        count += temp
    }
    temp = 0
    bool = false
}
print(count)
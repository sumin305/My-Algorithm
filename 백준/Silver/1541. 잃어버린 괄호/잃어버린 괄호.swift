import Foundation

let N = readLine()!
var arr: [String] = []
var temp = ""
for n in N {
    if n != "+" && n != "-" {
        temp += String(n)
    } else {
        arr.append(temp)
        arr.append(String(n))
        temp = ""
    }
}
arr.append(temp)


var result = Int(arr[0])!
var tempNum = 0

for idx in stride(from: 2, to: arr.count, by: 2) {
    if tempNum == 0 {
        if arr[idx - 1] == "+" {
            result += Int(arr[idx])!
        } else {
            tempNum = Int(arr[idx])!
        }
    } else {
        if arr[idx - 1] == "+" {
            tempNum += Int(arr[idx])!
        } else {
            result -= tempNum
            tempNum = Int(arr[idx])!
        }
    }
}
result -= tempNum
print(result)
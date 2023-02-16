import Foundation

var S = Int(readLine()!)!
var N = 0
var temp = 0
for i in 1...S{
    temp += i
    N += 1
    if temp > S{
        N -= 1
        break
    }
}
print(N)
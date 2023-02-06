import Foundation

var lst: [Character] = Array(readLine()!)
var stack: [Character] = []
var result: Int = 0
var temp: Int = 1

for i in 0...lst.count-1 {
    if lst[i] == "(" || lst[i] == "[" {
        stack.append(lst[i])
        if lst[i] == "(" {
            temp *= 2
        }else{
            temp *= 3
        }
    }
    else{
        if let target = stack.popLast() {
            if lst[i] == ")" {
                if target != "(" {
                    result = 0
                    break
                }else{
                    if lst[i-1] == "(" {
                        result += temp
                    }
                }
                temp /= 2
            }else{
                if target != "[" {
                    result = 0
                    break
                }
                else{
                    if lst[i-1] == "[" {
                        result += temp
                    }
                }
                temp /= 3
            }
        }else{
            result = 0
            break
        }
    }
}
if stack.isEmpty{
    print(result)
}
else{
    print(0)
}
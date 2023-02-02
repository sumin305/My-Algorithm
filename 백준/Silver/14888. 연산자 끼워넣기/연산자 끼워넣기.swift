import Foundation

func getCalculate(_ result : Int, _ idx : Int) {
    if idx == N {
        result_max = max(result, result_max)
        result_min = min(result, result_min)
        return
    }
    for i in 0...3 {
        if op[i] > 0 {
            op[i] -= 1
            switch i {
                case 0 :
                    getCalculate(result + lst[idx], idx + 1)
                    op[i] += 1
                case 1 :
                    getCalculate(result - lst[idx], idx + 1)
                    op[i] += 1
                case 2 :
                    getCalculate(result * lst[idx], idx + 1)
                    op[i] += 1
                default:
                    if result < 0 {
                        getCalculate((abs(result) / lst[idx])*(-1), idx + 1)
                        op[i] += 1
                    }else{
                        getCalculate(result / lst[idx], idx + 1)
                        op[i] += 1
                    }
            }
        }
    }
}
let N = Int(readLine()!)! 
let lst : [Int] = readLine()!.split(separator: " ").map{Int(String($0))!} 
var op : [Int] = readLine()!.split(separator: " ").map{Int(String($0))!} 
var opList : [Character] = [Character]()

var result_max = -1000000001
var result_min = 1000000001
getCalculate(lst[0], 1)
print(result_max)
print(result_min)
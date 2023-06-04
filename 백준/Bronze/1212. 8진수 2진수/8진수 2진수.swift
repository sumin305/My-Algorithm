import Foundation

let n = Array(readLine()!)
var str : [String] = []

func eightToBin(_ num: [Character]) -> String {
   
    
    for idx in stride(from: num.count-1, to: -1, by: -1) {
        tenToBin(Int(String(num[idx]))!)
    }

    let result = str.reversed().joined()
    if result == "000" {
        return "0"
    }
    return String(result[result.firstIndex(of: "1")!...])
    
}
func tenToBin(_ num: Int) {
    let bin = [0: "000", 1: "001", 2: "010", 3: "011", 4: "100", 5: "101", 6: "110", 7: "111"]
    
    str.append(bin[num, default: "000"])
}

print(eightToBin(n))
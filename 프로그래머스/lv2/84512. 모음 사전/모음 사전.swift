import Foundation

func solution(_ word:String) -> Int {
    var resultArr: [String] = []
    let candidates: [String] = ["A", "E", "I", "O", "U"]
    func select(_ str: String) {
        if str.count == 5 {
            return
        }
        for candidate in candidates {
            let newStr = str + candidate
            resultArr.append(newStr)
            select(newStr)
        }
    }
    select("")
    resultArr.sort{$0 < $1}
    return resultArr.firstIndex(of: word)!+1
}
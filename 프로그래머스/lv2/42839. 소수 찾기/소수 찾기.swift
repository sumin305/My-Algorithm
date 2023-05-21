import Foundation

// extension String {
//     subscript(idx: Int) -> Character {
//         return self[self.index(self.startIndex, offsetBy: idx)]
//     }
// }
func isPrimeNumber(_ num: Int) -> Bool {
    guard num >= 2 else { return false }
    var i = 2
    while i < num {
        if num % i == 0 {
            return false
        }
        i += 1
    }
    return true
} 

func solution(_ numbers:String) -> Int {
    var result: Set<Int> = []
    let numbers = numbers.map{Int(String($0))!}
    
    func select(_ nums: String, _ arr: [Int]) {
        var nums = nums
        var arr = arr
        if arr.isEmpty {
            return
        }
        for idx in 0..<arr.count {
            var tempArr = arr
            let str = nums + String(arr[idx])
            print(str)
            result.insert(Int(str)!)
            tempArr.remove(at: idx)
            select(str, tempArr)
        }
    }
    select("", numbers)
    return result.filter{isPrimeNumber($0)}.count
}
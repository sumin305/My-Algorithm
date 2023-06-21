import Foundation

func solution(_ users:[[Int]], _ emoticons:[Int]) -> [Int] {
    let discountRate = [10, 20, 30, 40]
    var candidateRates: [[Int]] = []
    var result: [Int] = [0, 0]
    
    func makeCases(_ arr: [Int]) {
        if arr.count == emoticons.count {
            candidateRates.append(arr)
            return
        }
        for i in 0..<4 {
            var tempArr = arr
            tempArr.append(discountRate[i])
            makeCases(tempArr)
        }
    }
    
    makeCases([])
    
    for candidateRate in candidateRates {
        var plusServiceUsers = 0
        var totalSales = 0
        for user in users {
            var tempSales = 0
            for idx in 0..<candidateRate.count {
                if candidateRate[idx] >= user[0] {
                    tempSales += emoticons[idx] - (emoticons[idx] * candidateRate[idx] / 100)
                }
            }
            if tempSales >= user[1] {
                plusServiceUsers += 1
            } else {
                totalSales += tempSales
            }
        }
        if result[0] == plusServiceUsers {
            if result[1] < totalSales {
                result = [plusServiceUsers, totalSales]
            }
        } else if result[0] < plusServiceUsers {
            result = [plusServiceUsers, totalSales]
        }
    }
    return result
}
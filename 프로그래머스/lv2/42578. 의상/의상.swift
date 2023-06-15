import Foundation

func solution(_ clothes:[[String]]) -> Int {
    var category: [String:Int] = [:]
    var result = 1
    // 의상의 종류마다 의상의 개수 count
    for cloth in clothes {
            category[cloth[1], default:0] += 1
    }
    // 한 개의 이상으로 조합한 개수 세서 출력 
    for cate in category {
        result = result * (cate.value+1)
    }
    return result-1
}
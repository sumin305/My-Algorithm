import Foundation

func solution(_ genres:[String], _ plays:[Int]) -> [Int] {
    var dict: [String: (first: Int, second: Int)] = [:]
    var dictCount: [String:Int] = [:]
    var plays = plays
    var result: [Int] = []
    plays.append(0)
    print(plays)
    for i in 0..<genres.count {
        // 딕셔너리에 해당 값이 있으면 값 비교하여 first, second 값 갱신
        if let (f, s) = dict[genres[i]] {
            if plays[i] > plays[f] {
                dict[genres[i]] = (i, f)
            } else if plays[i] <= plays[f] && plays[i] > plays[s] {
                dict[genres[i]] = (f, i)
            } 
        } 
        // 딕셔너리에 값 없을 경우 first에 해당 값의 idx, second에 0의 idx 넣음
        else {
            dict[genres[i]] = (i, genres.count)
        }
     dictCount[genres[i], default: 0] += plays[i]
    }
    print(dict)
    var dictArray = dict.sorted{ dictCount[$0.key]! > dictCount[$1.key]!}
     print(dict)
    for elem in dictArray {
        result.append(elem.value.first)
        if elem.value.second == genres.count {
             continue
        } 
        result.append(elem.value.second)
    }
    print(result)
    return result
}
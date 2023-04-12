import Foundation
func dateDif(_ bighhmm:String, _ hhmm:String) -> Int {
    let a = hhmm.split(separator: ":").map{Int(String($0))!}
    let b = bighhmm.split(separator: ":").map{Int(String($0))!}
    let min = b[1] - a[1]
    if a[0] == b[0] {
        return min
    } 
    let hour = b[0] - a[0]
    return hour*60 + min
}
func dateAdd(_ hhmm:String, _ add:Int) -> String {
    let date = hhmm.split(separator: ":").map{Int(String($0))!}
    if date[1] + add < 60 {
        return String(date[0]) + ":" + String(date[1] + add)
    }
    let hour = (date[1] + add) / 60 + date[0]
    let min = (date[1] + add) % 60
    if min < 10 {
        return String(hour) + ":" + "0" + String(min) 
    }
    return String(hour) + ":" + String(min)
}
func solution(_ plans:[[String]]) -> [String] {
    print(dateAdd("12:56", 23))
    print(dateDif("14:28", "11:56"))
    var waitStack: [(String, Int)] = []
    var result: [String] = []
    // 과제 시작하는 시점으로 오름차순 정렬
    let sortedPlans = plans.sorted{ ($0[1].split(separator: ":")[0], $0[1].split(separator: ":")[1]) < ($1[1].split(separator: ":")[0] , $1[1].split(separator: ":")[1])}
    // 과제를 모두 수행 못한 과제는 waitStack에 넣는다 (과제명, 남은 시간) waitTime < 0
    // 과제를 다음 과제 시작 시간에 딱 맞게 수행한 과제는 result에 넣는다 waitTime == 0
    // 과제를 다음 과제 시작 시간보다 시간을 남게 수행한 과제는 result에 넣고 남은 시간동안 다른 과제들을 수행한다!
    // 마지막 과제인 경우, 남은 시간동안 처리해주고 자신의 과제 result에 넣는다!
    for i in 1..<plans.count {
        var waitTime = dateDif(sortedPlans[i][1], dateAdd(sortedPlans[i-1][1],Int(sortedPlans[i-1][2])!))
        // print(waitTime)
        // 과제를 수행한 경우
        if waitTime >= 0 {
            result.append(sortedPlans[i-1][0])
            while waitTime > 0 && !waitStack.isEmpty {
                let assign = waitStack.removeLast()
                // 임시 과제를 다 못 끝내는 경우 다시 스택에 넣는다
                if waitTime < assign.1 {
                    waitStack.append((assign.0, assign.1 - waitTime))
                    waitTime = 0
                } 
                // 임시 과제를 다 끝내는 경우, waitTime 갱신해주고 다시 while문
                else {
                    waitTime = waitTime - assign.1
                    result.append(assign.0)
                }
            }
        }
        // 과제를 수행하지 못한 경우
        else {
            waitStack.append((sortedPlans[i-1][0], abs(waitTime)))
        }
         if i == plans.count - 1 {
           result.append(sortedPlans[i][0])
         }
    }
    while !waitStack.isEmpty {
        let assign = waitStack.removeLast().0
        result.append(assign)
    }
    return result
}
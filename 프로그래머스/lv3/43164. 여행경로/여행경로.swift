func solution(_ tickets:[[String]]) -> [String] {
    var result: [[String]] = []
    func DFS(_ target: String, _ arr: [String], _ tickets: [[String]]) {

        if tickets.isEmpty {
            result.append(arr)
        }
        guard tickets.map({$0[0]}).contains(target) else { return }
        for idx in 0..<tickets.count {
            if tickets[idx][0] == target {
                var tempArr = arr
                tempArr.append(tickets[idx][1])
                var tempTickets = tickets
                tempTickets.remove(at: idx)
                DFS(tickets[idx][1], tempArr, tempTickets)
            }
        }
    }
    DFS("ICN", ["ICN"], tickets)
    return result.sorted{String($0.flatMap{$0}) < String($1.flatMap{$0})}[0]
}
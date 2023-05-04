import Foundation
var N: Array<Substring> = readLine()!.dropLast(1).split(separator: " ").map{
    var a = String($0)
    if a[a.index(before: a.endIndex)] == "," {
        return Substring(a[a.startIndex..<a.index(before: a.endIndex)])
    } else {
        return Substring($0)
    }
}
                         
let names = ["*", "&", "["]
for i in 1..<N.count {
    var bool = false
    var result = N[0]
    var idx = N[i].startIndex
    for char in N[i] {
        if names.contains(String(char)) {
            idx = N[i].firstIndex(of: char)!
            bool = true
            break
        }
    }
    if bool {
        let rev = N[i][idx...].reversed()
        for i in rev {
            if i == "]" {
                result += "[]"
            } else if i == "[" {
                continue
            } else {
                result += String(i)
            }
        }
        print(result, N[i][N[i].startIndex...N[i].index(before: idx)]+";")
    } else {
        print(result, N[i]+";")
        
    }
}
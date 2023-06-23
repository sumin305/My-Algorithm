import Foundation

let N = readLine()!
var HTML: [[String]] = []
var isParagraph = false
var temp = ""
var paraTemp = ""
var i = -1
for n in N {
    if n == "<" {
        paraTemp += temp
        temp = ""
    }
    else if n == ">" {
        if temp.count >= 10 && temp.contains("div title=") && !isParagraph {
            i += 1
            let str = temp.split(separator: "\"")[1]
            print("title :",str)
        } else if temp == "p" {
            isParagraph = true
        } else if temp == "/p" {
            let target = paraTemp.trimmingCharacters(in: [" "])
            let result = target.split(separator: " ").joined(separator: " ")
            print(result)
            paraTemp = ""
            isParagraph = false
        }
        temp = ""
    }
    else {
        temp += String(n)
    }
}
import Foundation

// 태그는 <로 시작해서 >로 끝남 길이가 3이상 사이에 알파벳 소문자와 공백만 가능
let S = readLine()!
var temp = ""
var result = ""
var tag = false
for s in S {
    if s == " " && !tag {
        result += (String(Array(temp).reversed()) + " ")
        temp = ""
    }
    else if s == "<" {
        result += (String(Array(temp).reversed()))
        temp = ""
        tag = true
    }
    else if s == ">" {
        result += ("<"+temp+">")
        temp = ""
        tag = false
    }
    else {
        temp += String(s)
    }
}
result += (String(Array(temp).reversed()) + " ")
print(result)
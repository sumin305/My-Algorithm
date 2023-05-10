import Foundation

let N = readLine()!.split(separator: " ")
let (s, n) = (Int(N[0])!, N[1])

for i in 1...2*s+3 {
    for num in n {
        let num = Int(String(num))!
        if i == 1 {
            switch num {
                case 1, 4:
                    print(String(repeating: " ", count: s+2), terminator: "")
                default:
                    print(" "+String(repeating: "-", count: s)+" ", terminator: "")
            }
        }
        else if i < (2*s+4)/2 {
            switch num {
                case 1,2,3,7:
                    print(String(repeating: " ", count: s+1)+"|", terminator: "")
                case 5,6:
                    print("|"+String(repeating: " " , count: s+1), terminator: "")
                default:
                    print("|"+String(repeating: " ", count: s)+"|", terminator: "")
            }
        }
        else if i == (2*s+4)/2 {
            switch num {
                case 1,7,0:
                    print(String(repeating: " ", count: s+2), terminator: "")
                default:
                    print(" "+String(repeating: "-", count: s)+" ", terminator: "")
            }
        }
        else if i < 2*s+3 {
            switch num {
                case 2:
                    print("|"+String(repeating: " ", count: s+1), terminator: "")
                case 6,8,0:
                    print("|"+String(repeating: " ", count: s)+"|", terminator: "")
                default:
                    print(String(repeating: " ", count: s+1)+"|", terminator: "")
            }
        }
        else if i == 2*s+3 {
            switch num {
                case 1,4,7:
                    print(String(repeating: " ", count: s+2), terminator: "")
                default:
                    print(" "+String(repeating: "-", count: s)+" ", terminator: "")
            }
        }
    print(" ", terminator: "")
    }
    print()
}

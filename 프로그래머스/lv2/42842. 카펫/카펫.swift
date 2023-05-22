import Foundation
func makeSizes(_ yellow: Int) -> [[Int]] {
         var sizes: [[Int]] = []
         var i = 1
            while i <= yellow {
                if yellow % i == 0 {
                    if !sizes.isEmpty && (sizes[sizes.count-1] == [i, yellow/i] || sizes[sizes.count-1][0] == sizes[sizes.count-1][1]) {
                        break
                    }
                    sizes.append([yellow/i,i])
                }
                i += 1
            }
        return sizes
    }

func solution(_ brown:Int, _ yellow:Int) -> [Int] {
   var sizes: [[Int]] = []
    sizes = makeSizes(yellow)
    for size in sizes {
        if size[0] * 2 + size[1] * 2 + 4 == brown {
            return [size[0]+2, size[1]+2]
        }
    }
    return []
}
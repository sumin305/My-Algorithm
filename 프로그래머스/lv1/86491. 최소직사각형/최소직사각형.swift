import Foundation

func solution(_ sizes:[[Int]]) -> Int {
    var width = 0
    var height = 0
    for size in sizes {
        width = max(width, max(size[0], size[1]))
        height = max(height, min(size[0], size[1]))
    }
    return width * height
}
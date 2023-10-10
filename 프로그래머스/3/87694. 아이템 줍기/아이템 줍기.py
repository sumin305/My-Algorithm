from collections import deque

def solution(rectangle, characterX, characterY, itemX, itemY):
    answer = 0
    queue = deque([(characterX, characterY, 0)])
    rectangle_interior = set()
    rectangle_line = set()
    for x1, y1, x2, y2 in rectangle:
        for i in range(x1, x2):
            rectangle_line.add((i, y1, 1))
            rectangle_line.add((i, y2, 1))
        for j in range(y1, y2):
            rectangle_line.add((x1, j, 0))
            rectangle_line.add((x2, j, 0))
        for i in range(x1+1, x2):
            for j in range(y1+1, y2):
                rectangle_interior.add((i, j, 0))
                rectangle_interior.add((i, j, 1))
        for i in range(x1+1, x2):
            rectangle_interior.add((i, y1, 0))    
        for i in range(y1+1, y2):
            rectangle_interior.add((x1, i, 1))    
        

    
    rectangle_line = rectangle_line - rectangle_interior

    while queue:
        x, y, count = queue.popleft()
        if (x, y) == (itemX, itemY):
            return count
        for tuple in [(x-1, y, 1), (x, y-1, 0), (x, y, 1), (x, y, 0)]:
            if tuple in rectangle_line and tuple not in rectangle_interior:
                rectangle_line.remove(tuple)
                
                if tuple == (x-1, y, 1): queue.append((x-1, y, count+1))
                elif tuple == (x, y-1, 0): queue.append((x, y-1, count+1))
                elif tuple == (x, y, 1): queue.append((x+1, y, count+1))
                elif tuple == (x, y, 0): queue.append((x, y+1, count+1))
                
    return answer
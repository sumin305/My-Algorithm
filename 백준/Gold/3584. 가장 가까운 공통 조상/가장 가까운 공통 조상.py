import sys
input = sys.stdin.readline 

T = int(input())

def make_path(num):
    path = [num]
    while tree[num]:
        path.append(tree[num])
        num = tree[num]
    return path

for _ in range(T):
    N = int(input())
    tree = [0 for i in range(N+1)]

    for r in range(N-1):

        a, b = map(int, input().split())
        tree[b] = a

    target1, target2 = map(int, input().split())
    path1, path2 = make_path(target1), make_path(target2)

    i, j = 0, 0

    if len(path1) > len(path2):
        i += (len(path1) - len(path2))
    else:
        j += (len(path2) - len(path1))
    
    while path1[i] != path2[j]:
        i += 1
        j += 1

    print(path1[i])
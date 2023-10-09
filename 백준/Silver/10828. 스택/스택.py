import sys
input = sys.stdin.readline
N = int(input())
array = []
for _ in range(N):
    command = input().strip()
    if command[:4] == "push":
        array.append(command[5:])
    elif command == "pop":
        if array: print(array.pop())
        else: print(-1)
    elif command == "size":
        print(len(array))
    elif command == "empty":
        if array: print(0)
        else: print(1)
    elif command == "top":
        if array: print(array[-1])
        else: print(-1)
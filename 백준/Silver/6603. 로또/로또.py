from itertools import combinations

while True:
    N = list(map(int, input().split()))
    if len(N) == 1 and N == [0]: break
    k = N.pop(0)
    for s in list(combinations(N, 6)):
        print(*s)
    print()
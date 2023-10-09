from functools import reduce
def solution(picks, minerals):
    global answer
    answer = 1e9
    minerals_array = []
    for i in range(int(len(minerals) / 5)):
        minerals_array.append(minerals[i*5:i*5+5])
        temp = minerals[int(len(minerals) / 5) * 5:]
    if temp: minerals_array.append(temp)

    def dfs(picks_idx, minerals_idx, tired, picks):
        global answer
        if minerals_idx == len(minerals_array) :
            answer = min(answer, tired)
            return
        sum = 0
        for mineral in minerals_array[minerals_idx]:
            if picks_idx == 1 and mineral == "diamond": sum += 5
            elif picks_idx == 2:
                if mineral == "diamond": sum += 25
                elif mineral == "iron": sum += 5 
                else: sum += 1
            else: sum += 1
        print(sum)
        for i in range(3):
            if reduce(lambda x, y: x+y, picks) == 0:
                answer = min(answer, tired + sum)
                return
            if picks[i] != 0:
                picks[i] -= 1
                dfs(i, minerals_idx + 1, tired + sum, picks)
                picks[i] += 1

    for i in range(3):
        if picks[i] != 0:
            picks[i] -= 1
            dfs(i, 0, 0, picks)
            picks[i] += 1
    return answer
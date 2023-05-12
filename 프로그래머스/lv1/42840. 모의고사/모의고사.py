def solution(answers):
    answer = []
    math_give_up_1 = [1,2,3,4,5]
    math_give_up_2 = [2,1,2,3,2,4,2,5]
    math_give_up_3 = [3,3,1,1,2,2,4,4,5,5]
    count_1 = count_2 = count_3 = 0
    idx_1 = idx_2 = idx_3 = 0
    for ans in answers:
        if ans == math_give_up_1[idx_1]:
            count_1 += 1
        if ans == math_give_up_2[idx_2]:
            count_2 += 1
        if ans == math_give_up_3[idx_3]:
            count_3 += 1
        idx_1 += 1
        idx_2 += 1
        idx_3 += 1
        if idx_1 == 5:
            idx_1 = 0
        if idx_2 == 8:
            idx_2 = 0
        if idx_3 == 10:
            idx_3 = 0
    max_math = max(count_1, count_2, count_3)
    for (idx, math) in enumerate([count_1, count_2, count_3]):
        if max_math == math:
            answer.append(idx+1)
    
    return sorted(answer)
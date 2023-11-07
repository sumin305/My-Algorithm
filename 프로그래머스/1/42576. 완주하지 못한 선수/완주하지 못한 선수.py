def solution(participant, completion):
    answer = ""
    part_dic = {}
    comp_dic = {}
    for p in participant:
        if p in part_dic:
            part_dic[p] += 1
        else:
            part_dic[p] = 1
            
    for c in completion:
        if c in comp_dic:
            comp_dic[c] += 1
        else:
            comp_dic[c] = 1
    
    for key in part_dic:
        if key not in comp_dic:
            return key
        elif part_dic[key] != comp_dic[key]:
            return key
    return answer
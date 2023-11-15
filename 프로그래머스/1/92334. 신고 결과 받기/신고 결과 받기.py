def solution(id_list, report, k):
    
    report_dic = {}
    answer_dic = {}
    
    for id in id_list:
        report_dic[id] = set()
        answer_dic[id] = 0

    for r in report:
        a, b = r.split(" ")
        report_dic[b].add(a)
        
    for id in id_list:
        if len(report_dic[id]) >= k:
            for reporter in report_dic[id]:
                answer_dic[reporter] += 1

    return list(answer_dic.values())
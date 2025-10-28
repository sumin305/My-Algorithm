import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        
        // key: 차량번호 value: 총 주차 시간
        HashMap<Integer, Integer> totalFeeMap = new HashMap<>();
        
        // key: 차량번호 value: 최근 입차 시간
        HashMap<Integer, String> inTimeMap = new HashMap<>();

        for (String record: records) {
            String[] recordArr = record.split(" ");
            String time = recordArr[0];
            int carNum = Integer.parseInt(recordArr[1]);
            boolean isIn = recordArr[2].equals("IN");
            
            // 입차 시
            if (isIn) {
                inTimeMap.put(carNum, time);
            } 
            // 츨차 시
            else {
                totalFeeMap.put(carNum, totalFeeMap.getOrDefault(carNum, 0) + calculateTime(inTimeMap.get(carNum), time));
                inTimeMap.remove(carNum);
            }
        }
        
        // 23:59까지 출차 안한 차들 정리
        List<Integer> noOutCars = new ArrayList<Integer>(inTimeMap.keySet());
        
        for (Integer noOutCar: noOutCars) {
            System.out.println(noOutCar);
            totalFeeMap.put(noOutCar, totalFeeMap.getOrDefault(noOutCar, 0) + calculateTime(inTimeMap.get(noOutCar), "23:59"));
        }
        
        Collections.sort(noOutCars);
        
        List<Integer> totalFeeCars = new ArrayList<Integer>(totalFeeMap.keySet());
        Collections.sort(totalFeeCars);

        return totalFeeCars.stream().mapToInt(o -> calculateFee(totalFeeMap.get(o), fees)).toArray();
    }
    
    public int calculateFee(int time, int[] fees) {
        if (time <= fees[0]) return fees[1];
        int lastTime = time - fees[0];
        int lastFee = lastTime % fees[2] == 0 ? lastTime / fees[2] : (lastTime + fees[2]) / fees[2];
        return lastFee * fees[3] + fees[1];
    }
    
    public int calculateTime(String inTime, String outTime) {
        String[] inTimes = inTime.split(":");
        String[] outTimes = outTime.split(":");
        
        int inTimeHour = Integer.parseInt(inTimes[0]);
        int inTimeMinute = Integer.parseInt(inTimes[1]);
        int outTimeHour = Integer.parseInt(outTimes[0]);
        int outTimeMinute = Integer.parseInt(outTimes[1]);
        
        boolean okay = outTimeMinute >= inTimeMinute;
        return (okay ? outTimeHour - inTimeHour : outTimeHour - inTimeHour - 1) * 60 + (okay ? outTimeMinute - inTimeMinute : 60 - inTimeMinute + outTimeMinute);
    }
}
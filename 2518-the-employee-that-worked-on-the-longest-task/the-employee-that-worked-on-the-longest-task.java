class Solution {
    public int hardestWorker(int n, int[][] logs) {
        int result = logs[0][0];
        int maxTime = logs[0][1];
        int prevTime = 0;
        
        for (int[] log : logs) {
            int id = log[0];
            int leaveTime = log[1];
            int duration = leaveTime - prevTime;

            if (duration > maxTime || (duration == maxTime && id < result)) {
                maxTime = duration;
                result = id;
            } 
            prevTime = leaveTime;
        }   
        return result;
    }
}
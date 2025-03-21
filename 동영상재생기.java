import java.io.*;



class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        String[] cSplited = pos.split(":");
        int cMin = Integer.parseInt(cSplited[0]);
        int cSec = Integer.parseInt(cSplited[1]);
        
        String[] lSplited = video_len.split(":");
        int lMin = Integer.parseInt(lSplited[0]);
        int lSec = Integer.parseInt(lSplited[1]);
        
        String[] sSplited = op_start.split(":");
        int sMin = Integer.parseInt(sSplited[0]);
        int sSec = Integer.parseInt(sSplited[1]);
        
        String[] eSplited = op_end.split(":");
        int eMin = Integer.parseInt(eSplited[0]);
        int eSec = Integer.parseInt(eSplited[1]);
        
        for (String command : commands) {
            // 오프닝 건너뛰기
            if (sMin * 60 + sSec <= cMin * 60 + cSec && eMin * 60 + eSec >= cMin * 60 + cSec) {
                cMin = eMin;
                cSec = eSec;
            }
            
            // prev
            if (command.equals("prev")) {
                if (cSec >= 10) {
                    cSec -= 10;
                }
                else {
                    if (cMin == 0) {
                        cSec = 0;
                    }
                    else {
                        cMin -= 1;
                        cSec = cSec + 50;
                    }
                }
            }
            // next
            else {
                cSec += 10;
                if (cSec >= 60) {
                    cMin += 1;
                    cSec -= 60;
                }
                if (cMin * 60 + cSec > lMin * 60 + lSec) {
                    cMin = lMin;
                    cSec = lSec;
                }
            }
        }
        // 오프닝 건너뛰기
        if (sMin * 60 + sSec <= cMin * 60 + cSec && eMin * 60 + eSec >= cMin * 60 + cSec) {
            cMin = eMin;
            cSec = eSec;
        }
        
        String aMin = String.valueOf(cMin);
        String aSec = String.valueOf(cSec);
        if (aMin.length() == 1) {
            aMin = "0" + aMin;
        }
        if (aSec.length() == 1) {
            aSec = "0" + aSec;
        }
        
        return aMin + ":" + aSec;
    }
}
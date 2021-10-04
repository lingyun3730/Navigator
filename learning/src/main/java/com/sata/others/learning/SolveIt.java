package com.sata.others.learning;

public class SolveIt {
    public String largestTimeFromDigits(int[] arr) {
        int hour = 0;
        int lastHour = 0;
        int min = 0;
        if(arr[0] == 0 && arr[1] == 0 && arr[2] == 0 && arr[3] == 0) return "00:00";
        boolean changed = false;
        for(int i=0; i<arr.length; i++){
            for(int j=i+1; j<arr.length; j++){
                if(j != i){
                    lastHour = hour;
                    int temp1 = arr[i] * 10 + arr[j];
                    int temp2 = arr[j] * 10 + arr[i];
                    boolean tag = 3-i != i && 3-i != j && 3-j != i && 3-j != j;
                    int temp3 = tag? arr[3-i] * 10 + arr[3-j] : (i==0 ? arr[i+1] * 10 + arr[j-1] : arr[i-1] * 10 + arr[j+1]);
                    int temp4 = tag? arr[3-j] * 10 + arr[3-i] : (i==0 ? arr[j-1] * 10 + arr[i+1] : arr[j+1] * 10 + arr[i-1]);
                    if(23-temp1>=0 && 23-temp1<23-hour){
                        hour = temp1;
                        changed = true;
                    }
                    if(23-temp2>=0 && 23-temp2<23-hour){
                        hour = temp2;
                        changed = true;
                    }
                    if(hour != lastHour){
                        if(59-temp3<0 && 59-temp4<0) {
                            hour = lastHour;
                            changed = false;
                        }else if(59-temp3>=0 && 59-temp4<0){
                            min = temp3;
                            changed = true;
                        }else if(59-temp4>=0 && 59-temp3<0){
                            min = temp4;
                            changed = true;
                        }else{
                            min = 59-temp4 > 59-temp3? temp3 : temp4;
                            changed = true;
                        }
                    }
                }
            }
        }
        if(changed) return String.format("%02d", hour) + ":" + String.format("%02d", min);
        return "";
    }

    public static void main(String[] args) {
        SolveIt it = new SolveIt();
        int[] l = {1,2,3,4};
        String str = it.largestTimeFromDigits(l);
        System.out.println(str);
    }
}
import java.time.LocalDate;
import java.time.LocalTime;

public  abstract class Clock {
    private  int h,m,s;

    public void setCurrentTime(){
     this.h=  LocalTime.now().getHour();
        this.m=  LocalTime.now().getMinute();
        this.s=LocalTime.now().getSecond();
    }
    public void setTime(int h,int m, int s){
        if(h<0 || h>=24){
            throw new IllegalArgumentException("godzina nie miesci sie w zakresie :)");
        }
        if(m<0 || m>=60){
            throw new IllegalArgumentException("minuta nie miesci sie w zakresie :)");
        }
        if(s<0 || s>=60){
            throw new IllegalArgumentException("sekunda nie miesci sie w zakresie :)");
        }
        this.h=h;
        this.m=m;
        this.s=s;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d",h,m,s);
    }
}

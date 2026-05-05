public class DigitalClock extends Clock{
    public enum Type {
        H12, H24
    }
    private Type clockType;

    public DigitalClock(Type clockType, City city) {
        super(city);
        this.clockType = clockType;
    }

    @Override
    public String toString() {
        if(this.clockType == Type.H24) {
            return super.toString();
        }
        else{
            if(h<12) {
                if(h==0){
                    return String.format("%2d:%02d:%02d AM", 12, m, s);
                }
                return String.format("%2d:%02d:%02d AM", h, m, s);
            }
            else {
                int hour = h - 12;
                if(hour==0){
                    return String.format("%2d:%02d:%02d PM", 12, m, s);
                }
                return String.format("%2d:%02d:%02d PM", hour, m, s);
            }
        }
    }

}

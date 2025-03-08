package kachow.api_invocations.dto;

public class Ratio {
    private String stat;
    private int percent;

    public Ratio() {}

    public Ratio(String stat, int percent) {
        this.stat = stat;
        this.percent = percent;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    // toString pour faciliter le débogage
    @Override
    public String toString() {
        return "Ratio{" +
            "stat='" + stat + '\'' +
            ", percent=" + percent +
            '}';
    }
}

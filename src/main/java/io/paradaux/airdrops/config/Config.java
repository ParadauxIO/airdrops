package io.paradaux.airdrops.config;

public class Config {

    private int startX;
    private int startZ;

    private int endX;
    private int endZ;

    private int interval;

    public Config() {

    }

    public Config(int startX, int startZ, int endX, int endZ, int interval) {
        this.startX = startX;
        this.startZ = startZ;
        this.endX = endX;
        this.endZ = endZ;
        this.interval = interval;
    }

    public int getStartX() {
        return startX;
    }

    public Config setStartX(int startX) {
        this.startX = startX;
        return this;
    }

    public int getStartZ() {
        return startZ;
    }

    public Config setStartZ(int startZ) {
        this.startZ = startZ;
        return this;
    }

    public int getEndX() {
        return endX;
    }

    public Config setEndX(int endX) {
        this.endX = endX;
        return this;
    }

    public int getEndZ() {
        return endZ;
    }

    public Config setEndZ(int endZ) {
        this.endZ = endZ;
        return this;
    }

    public int getInterval() {
        return interval;
    }

    public Config setInterval(int interval) {
        this.interval = interval;
        return this;
    }
}

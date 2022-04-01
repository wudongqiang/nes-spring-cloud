package com.nes.data;

public class ScPage {
    private long total;
    private int size;

    public ScPage() {
    }

    ScPage(long total, int size) {
        this.total = total;
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
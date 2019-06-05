package com.example.s.zmood.entity;

public class weather {
    private String local;
    private String tmp;
    private String fl;
    private String cond;

    public weather(){

    }
    public weather(String local, String tmp, String fl, String cond) {
        this.local = local;
        this.tmp = tmp;
        this.fl = fl;
        this.cond = cond;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getCond() {
        return cond;
    }

    public void setCond(String cond) {
        this.cond = cond;
    }


    @Override
    public String toString() {
        return "当前位于"+this.local+
                " 天气 "+this.cond+
                " 最高温度为"+this.tmp+
                " 体感温度为"+this.fl;
    }
}

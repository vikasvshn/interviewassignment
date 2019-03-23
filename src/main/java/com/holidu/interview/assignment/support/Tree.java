package com.holidu.interview.assignment.support;


public class Tree {

    private Double x_sp;
    private Double y_sp;
    private String spc_common;

    public Tree() {
    }

    public Tree(String spc_common, Double x_sp, Double y_sp) {
        this.spc_common = spc_common;
        this.x_sp = x_sp;
        this.y_sp = y_sp;
    }


    public Double getX_sp() {
        return x_sp;
    }

    public void setX_sp(Double x_sp) {
        this.x_sp = x_sp;
    }

    public Double getY_sp() {
        return y_sp;
    }

    public void setY_sp(Double y_sp) {
        this.y_sp = y_sp;
    }

    public String getSpc_common() {
        return spc_common;
    }

    public void setSpc_common(String spc_common) {
        this.spc_common = spc_common;
    }
}

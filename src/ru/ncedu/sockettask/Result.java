package ru.ncedu.sockettask;

import java.io.Serializable;

/**
 * Created by Gorbatovskiy on 05.04.2016.
 */
public class Result implements Serializable{
    private double sumResult;
    private double difResult;
    private double mulResult;
    private double divResult;

    public double getSumResult() {
        return sumResult;
    }

    public void setSumResult(double sumResult) {
        this.sumResult = sumResult;
    }

    public double getDifResult() {
        return difResult;
    }

    public void setDifResult(double difResult) {
        this.difResult = difResult;
    }

    public double getMulResult() {
        return mulResult;
    }

    public void setMulResult(double mulResult) {
        this.mulResult = mulResult;
    }

    public double getDivResult() {
        return divResult;
    }

    public void setDivResult(double divResult) {
        this.divResult = divResult;
    }

    @Override
    public String toString() {
        return "Result{" +
                "sumResult=" + sumResult +
                ", difResult=" + difResult +
                ", mulResult=" + mulResult +
                ", divResult=" + divResult +
                '}';
    }
}

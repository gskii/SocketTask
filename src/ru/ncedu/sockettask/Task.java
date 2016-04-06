package ru.ncedu.sockettask;

/**
 * Created by Gorbatovskiy on 05.04.2016.
 */
public class Task {
    private double value1;
    private double value2;

    public Task(Double value1, Double value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public Result execute() {
        Result result = new Result();
        result.setSumResult(value1 + value2);
        result.setDifResult(value1 - value2);
        result.setMulResult(value1 * value2);
        result.setDivResult(value1 / value2);
        return result;
    }
}

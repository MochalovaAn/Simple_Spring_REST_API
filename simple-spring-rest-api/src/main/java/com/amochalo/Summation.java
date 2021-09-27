package com.amochalo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Summation implements Action{
    @Override
    public double returnActionResult(ArrayList<Integer> userValueList) {
        double res = 0;
        for (Integer value: userValueList) {
                res +=value;
        }
        return res;
    }


    @Override
    public String getKey() {
        return "sum";
    }
}


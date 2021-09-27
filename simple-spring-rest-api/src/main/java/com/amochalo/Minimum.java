package com.amochalo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Minimum implements Action{

    @Override
    public double returnActionResult(ArrayList<Integer> userValueList) {
        double res = 1.7976931348623157E308;
        for (Integer value: userValueList) {
                if (res > value)
                    res = value;
        }
        return res;
    }

    @Override
    public String getKey() {
        return "min";
    }
}

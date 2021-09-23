package com.amochalo;

import java.util.ArrayList;

public class Summation implements Action{
    @Override
    public double returnActionResult(ArrayList<Integer> userValueList) {
        double res = 0;
        for (Integer value: userValueList) {
                res +=value;
        }
        return res;
    }
}


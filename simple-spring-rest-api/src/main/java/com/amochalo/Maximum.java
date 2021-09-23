package com.amochalo;

import java.util.ArrayList;

public class Maximum implements Action{

    @Override
    public double returnActionResult(ArrayList<Integer> userValueList) {
        double res = -1.7976931348623157E308;
        for (Integer value: userValueList) {
            if (res < value)
                res = value;

        }
        return res;
    }
}

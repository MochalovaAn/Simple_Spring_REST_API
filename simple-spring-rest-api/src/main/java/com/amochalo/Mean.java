package com.amochalo;

import java.util.ArrayList;

public class Mean implements Action{

    @Override

    public double returnActionResult(ArrayList<Integer> userValueList) {
        return (new Summation()).returnActionResult(userValueList)/userValueList.size();
    }
}

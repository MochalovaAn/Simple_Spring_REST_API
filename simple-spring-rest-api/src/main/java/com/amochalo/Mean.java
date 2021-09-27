package com.amochalo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Mean implements Action{

    @Override

    public double returnActionResult(ArrayList<Integer> userValueList) {
        return (new Summation()).returnActionResult(userValueList)/userValueList.size();
    }

    @Override
    public String getKey() {
        return "mean";
    }
}

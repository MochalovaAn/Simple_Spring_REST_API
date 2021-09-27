package com.amochalo;

import java.util.ArrayList;

public interface Action {
    double returnActionResult(ArrayList<Integer> userValueList);
    String getKey();

}
package com.mitrais.unittesting.business;

import java.util.Arrays;

public class SomeBusinessImpl {

    private SomeDataService someDataService;

    public int calculateSum(int[] numbers){
        return Arrays.stream(numbers).reduce(Integer::sum).orElse(0);
    }

    public int calculateSumUsingDataService(){
        int[] numbers = someDataService.retrieveAllData();
        return Arrays.stream(numbers).reduce(Integer::sum).orElse(0);
    }

    public void setSomeDataService(SomeDataService someDataService) {
        this.someDataService = someDataService;
    }
}

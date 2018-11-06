package com.mitrais.unittesting.business;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

class SomeDataServiceStub implements SomeDataService {
    @Override
    public int[] retrieveAllData() {
        return new int[] {1, 2, 3};
    }
}

class SomeDataServiceStubEmpty implements SomeDataService {
    @Override
    public int[] retrieveAllData() {
        return new int[] {};
    }
}

class SomeDataServiceStubOneValue implements SomeDataService {
    @Override
    public int[] retrieveAllData() {
        return new int[] {2};
    }
}

public class SomeBusinessImplStubTest {

    @Test
    public void calculateSumUsingDataService_Basic(){
        SomeBusinessImpl someBusiness = new SomeBusinessImpl();
        someBusiness.setSomeDataService(new SomeDataServiceStub());
        int result = someBusiness.calculateSumUsingDataService();
        assertEquals(6, result);
    }

    @Test
    public void calculateSum_Empty(){
        SomeBusinessImpl someBusiness = new SomeBusinessImpl();
        someBusiness.setSomeDataService(new SomeDataServiceStubEmpty());
        int result = someBusiness.calculateSumUsingDataService();
        assertEquals(0, result);
    }

    @Test
    public void calculateSum_OneValue(){
        SomeBusinessImpl someBusiness = new SomeBusinessImpl();
        someBusiness.setSomeDataService(new SomeDataServiceStubOneValue());
        int result = someBusiness.calculateSumUsingDataService();
        assertEquals(2, result);
    }

}
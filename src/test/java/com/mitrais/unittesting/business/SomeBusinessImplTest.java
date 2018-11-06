package com.mitrais.unittesting.business;

import org.junit.Test;

import static org.junit.Assert.*;

public class SomeBusinessImplTest {

    @Test
    public void calculateSum_Basic(){
        SomeBusinessImpl someBusiness = new SomeBusinessImpl();
        int result = someBusiness.calculateSum(new int[]{1, 2, 3});
        assertEquals(6, result);
    }

    @Test
    public void calculateSum_Empty(){
        SomeBusinessImpl someBusiness = new SomeBusinessImpl();
        int result = someBusiness.calculateSum(new int[] {});
        assertEquals(0, result);
    }

    @Test
    public void calculateSum_OneValue(){
        SomeBusinessImpl someBusiness = new SomeBusinessImpl();
        int result = someBusiness.calculateSum(new int[] {2});
        assertEquals(2, result);
    }

}
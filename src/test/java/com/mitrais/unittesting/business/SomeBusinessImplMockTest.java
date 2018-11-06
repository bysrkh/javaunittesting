package com.mitrais.unittesting.business;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SomeBusinessImplMockTest {

    private SomeDataService dataServiceMock = mock(SomeDataService.class);;
    private SomeBusinessImpl someBusiness = new SomeBusinessImpl();

    @Before
    public void setUp() throws Exception {
        someBusiness.setSomeDataService(dataServiceMock);    }

    @Test
    public void calculateSumUsingDataService_Basic(){
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {1, 2, 3});
        int result = someBusiness.calculateSumUsingDataService();
        assertEquals(6, result);
    }

    @Test
    public void calculateSum_Empty(){
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {});
        int result = someBusiness.calculateSumUsingDataService();
        assertEquals(0, result);
    }

    @Test
    public void calculateSum_OneValue(){
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {2});
        int result = someBusiness.calculateSumUsingDataService();
        assertEquals(2, result);
    }

}
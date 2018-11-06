package com.mitrais.unittesting.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SomeBusinessImplMockTest {

    @InjectMocks
    private SomeBusinessImpl someBusiness;
    @Mock
    private SomeDataService dataServiceMock;

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
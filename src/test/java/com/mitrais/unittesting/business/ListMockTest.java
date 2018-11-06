package com.mitrais.unittesting.business;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ListMockTest {

    List<String> mock = mock(List.class);

    @Test
    public void size_Basic(){
        when(mock.size()).thenReturn(5);
        assertEquals(mock.size(), 5);
    }

    @Test
    public void returnDifferentValues(){

        when(mock.size()).thenReturn(5).thenReturn(10);
        assertEquals(5, mock.size());
        assertEquals(10, mock.size());
    }

    @Test
    public void returnWithParameters(){
        when(mock.get(0)).thenReturn("javaBootcamp");
        assertEquals("javaBootcamp", mock.get(0));
        assertEquals(null, mock.get(1));
    }

    @Test
    public void returnWithGenericParameters(){
        when(mock.get(anyInt())).thenReturn("javaBootcamp");
        assertEquals("javaBootcamp", mock.get(0));
        assertEquals("javaBootcamp", mock.get(1));
    }

    @Test
    public void verificationBasics(){
        // System Under Test (SUT)
        String value1 = mock.get(0);
        String value2 = mock.get(1);

        // verify
        // important for not returning method
        verify(mock).get(0);
        verify(mock, times(2)).get(anyInt());
        verify(mock, atLeast(1)).get(anyInt());
        verify(mock, atLeastOnce()).get(anyInt());
        verify(mock, atMost(2)).get(anyInt());
        verify(mock, never()).get(2);
    }

    @Test
    public void argumentCaptoring(){
        // SUT
        mock.add("someString");

        // verification
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mock).add(captor.capture());

        assertEquals("someString", captor.getValue());
    }

    @Test
    public void multipleArgumentCaptoring(){
        // SUT
        mock.add("someString1");
        mock.add("someString2");

        // verification
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mock, times(2)).add(captor.capture());

        List<String> allValues = captor.getAllValues();
        assertEquals("someString1", allValues.get(0));
        assertEquals("someString2", allValues.get(1));
    }

}

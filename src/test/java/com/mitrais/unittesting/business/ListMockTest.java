package com.mitrais.unittesting.business;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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

    @Test
    public void mocking(){
        ArrayList arrayListMock = mock(ArrayList.class);
        assertNull(arrayListMock.get(0)); // returning default (null)
        assertEquals(0, arrayListMock.size()); // returning default (0)
        arrayListMock.add("Here 1"); // did not take effect since it is mock
        arrayListMock.add("Here 2"); // did not take effect since it is mock
        assertEquals(0, arrayListMock.size()); // 0
        when(arrayListMock.size()).thenReturn(5);
        assertEquals(5, arrayListMock.size()); // 5
    }

    @Test
    public void spying(){
        ArrayList<String> arrayListSpy = spy(ArrayList.class);
        arrayListSpy.add("Here 0"); // did not take effect since it is mock
        assertEquals("Here 0", arrayListSpy.get(0)); // returning default (null)
        assertEquals(1, arrayListSpy.size()); // returning default (0)
        arrayListSpy.add("Here 1"); // did not take effect since it is mock
        arrayListSpy.add("Here 2"); // did not take effect since it is mock
        assertEquals(3, arrayListSpy.size()); // 2

        when(arrayListSpy.size()).thenReturn(5);
        assertEquals(5, arrayListSpy.size()); // 5

        arrayListSpy.add("Here 4"); // did not take effect since it is mock
        assertEquals(5, arrayListSpy.size()); // Still 5 because when happend

        verify(arrayListSpy).add("Here 4");
    }

}

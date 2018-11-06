package com.mitrais.unittesting.business;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListMockTest {

    List mock = mock(List.class);

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
}

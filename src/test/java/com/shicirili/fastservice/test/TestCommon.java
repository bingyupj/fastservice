package com.shicirili.fastservice.test;

import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class TestCommon {


    @Test
    public void testMockitoTest() {
        List mockedList = mock(List.class);
        mockedList.add("one");
        verify(mockedList).add("one");
        when(mockedList.get(0)).thenReturn("first");
        System.out.println(mockedList.get(0));
        System.out.println(mockedList.get(999));
    }


    public static void main(String[] args) {

    }

}

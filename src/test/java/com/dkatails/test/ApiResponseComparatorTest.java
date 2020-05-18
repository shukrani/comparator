package com.dkatails.test;

import com.dkatails.comparator.imp.ApiResponseComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApiResponseComparatorTest {

    @Test
    public void testValidCompare(){
        ApiResponseComparator comparator = new ApiResponseComparator();
        boolean actual = comparator.compare("https://reqres.in/api/users/2", "https://reqres.in/api/users/2");
        Assertions.assertTrue(actual);
    }

    @Test
    public void testInvalidCompare(){
        ApiResponseComparator comparator = new ApiResponseComparator();
        boolean actual = comparator.compare("https://reqres.in/api/users/2", "https://reqres.in/api/users/3");
        Assertions.assertFalse(actual);
    }

    @Test
    public void testErrorCompare(){
        ApiResponseComparator comparator = new ApiResponseComparator();
        boolean actual = comparator.compare("https://reqres.in/api", "https://reqres.in/api/users/3");
        Assertions.assertFalse(actual);
    }


}

package com.nod.demotest.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoJunitTest {

    @Test
    void shouldCreateNewTodo(){
        var test = new Todo("TEST", false);

        assertEquals("TEST", test.name(), "Todo name is not equal to TEST");
        assertFalse(test.complete());

    }
}

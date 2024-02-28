package com.nod.demotest.todo;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TodoHamcrestTest {

    @Test
    void shouldCreateNewTodo(){
        var test = new Todo("TEST", false);
        var test2 = new Todo("TEST", false);

        assertThat(test, equalTo(test2));
    }
}

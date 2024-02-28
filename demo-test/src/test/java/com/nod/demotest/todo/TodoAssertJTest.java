package com.nod.demotest.todo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class TodoAssertJTest {

    @Test
    void shouldCreateNewTodo(){
        var test = new Todo("TEST", false);

        assertThat(test.name())
                .startsWith("T")
                .endsWith("T")
                .contains("ES")
                .isEqualToIgnoringCase("test");

    }
}

package org.nod.jdbcblog.post;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDate;

public record Posts(
        String id,
        String title,
        String slug,
        LocalDate date,
        int timeToRead,
        String tags,
        int version
) {
}

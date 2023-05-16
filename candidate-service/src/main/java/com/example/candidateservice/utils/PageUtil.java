package com.example.candidateservice.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.JpaSort;

@UtilityClass
public class PageUtil {

    public static Pageable getPage(String sortDir, String sortField, int page, int size) {
        if (page <= 0) page = 1;
        if (size <= 0) size = 1;
        if (size > 1000) size = 1000;

        if (Constant.ASC.equalsIgnoreCase(sortDir)) {
            return PageRequest.of(page - 1, size, Direction.ASC, sortField);
        }

        return PageRequest.of(page - 1, size, Direction.DESC, sortField);
    }

    public static Pageable getPageWithJpaSortUnsafe(String sortDir, String sortField, int page, int size) {
        if (page <= 0) page = 1;
        if (size <= 0) size = 1;
        if (size > 1000) size = 1000;
        Sort sort;
        if (Constant.ASC.equalsIgnoreCase(sortDir)) {
            sort = JpaSort.unsafe(Direction.ASC, "(" + sortField + ")");
        } else {
            sort = JpaSort.unsafe(Direction.DESC, "(" + sortField + ")");
        }
        return PageRequest.of(page - 1, size, sort);
    }

}

package com.gate.planner.gate.dao.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class CommonPage extends PageRequest {
    public CommonPage(int page) {
        super(page, 5, Sort.by("id").descending());
    }
}
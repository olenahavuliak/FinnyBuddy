package com.example.finnybuddy.core.utils;

import lombok.Data;

import java.util.List;

@Data
public class PageDTO<D> {
    private Integer limit;
    private Long totalItems;
    private Integer page;
    private Integer totalPages;
    private List<D> data;
}
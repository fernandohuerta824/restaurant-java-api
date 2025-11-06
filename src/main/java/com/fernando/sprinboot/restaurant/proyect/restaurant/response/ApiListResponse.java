package com.fernando.sprinboot.restaurant.proyect.restaurant.response;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;

public class ApiListResponse<T> extends ApiResponse<List<T>> {

    private long numberOfElements;
    private long totalPages;
    private long totalElements;
    private long pageSize;
    private boolean hasNext;
    private boolean hasPrevious;



    public ApiListResponse(String message, Page<T> data, int status, Instant timestamp) {
        super(message, data.getContent(), status, timestamp);
        this.numberOfElements = data.getNumberOfElements();
        this.totalPages = data.getTotalPages();
        this.totalElements = data.getTotalElements();
        this.pageSize = data.getSize();
        this.hasNext = data.hasNext();
        this.hasPrevious = data.hasPrevious();
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(long numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

}

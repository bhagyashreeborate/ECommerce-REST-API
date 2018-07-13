/* Created by: Bhagyashree Borate
 *  Date: 8th June 2018
 * */

/* POJO Class for Getting Search Response to send over to user */

package com.stacklineapi.stacklineapi.model;

import lombok.Data;


import java.util.List;

@Data
public class SearchResponse {

    private List<Products> conditions;
    private Pagination pagination;

    public List<Products> getConditions() {
        return conditions;
    }

    public void setConditions(List<Products> conditions) {
        this.conditions = conditions;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}

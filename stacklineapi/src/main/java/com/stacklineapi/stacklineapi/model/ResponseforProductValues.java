/* Created by: Bhagyashree Borate
 *  Date: 8th June 2018
 * */

/* POJO Class for Product Values array */


package com.stacklineapi.stacklineapi.model;

import java.util.List;

public class ResponseforProductValues {

    List<ProdcutValues> prodcutValues;
    Pagination pagination;

    public List<ProdcutValues> getProdcutValues() {
        return prodcutValues;
    }

    public List<ResponseforProductValues> setProdcutValues(List<ProdcutValues> prodcutValues) {
        this.prodcutValues = prodcutValues;
        return null;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}

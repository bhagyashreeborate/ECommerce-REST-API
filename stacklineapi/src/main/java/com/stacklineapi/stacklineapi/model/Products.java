
/* Created by: Bhagyashree Borate
 *  Date: 8th June 2018
 * */

/* POJO Class for Products */

package com.stacklineapi.stacklineapi.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Products {
    private String fieldName;
    private List<String> Values = new ArrayList<String>();

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public List<String> getValues() {
        return Values;
    }

    public void setValues(List<String> values) {
        Values = values;
    }

}

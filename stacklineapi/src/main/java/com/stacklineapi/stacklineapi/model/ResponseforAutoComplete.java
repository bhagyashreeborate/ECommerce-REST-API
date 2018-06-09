/* Created by: Bhagyashree Borate
 *  Date: 8th June 2018
 * */

/* POJO Class for AutoComplete Values */


package com.stacklineapi.stacklineapi.model;

import java.util.List;

public class ResponseforAutoComplete {
    private String type;
    private List<Suggestions> suggestions;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Suggestions> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<Suggestions> suggestions) {
        this.suggestions = suggestions;
    }
}

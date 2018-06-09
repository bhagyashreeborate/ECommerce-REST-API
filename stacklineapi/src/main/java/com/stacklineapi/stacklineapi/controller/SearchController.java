/* Created by: Bhagyashree Borate
*  Date: 8th June 2018
* */

/* This file contains the code for mapping of REST API to respective Services. */

package com.stacklineapi.stacklineapi.controller;
import com.stacklineapi.stacklineapi.model.ResponseforAutoComplete;
import com.stacklineapi.stacklineapi.model.Products;
import com.stacklineapi.stacklineapi.model.ResponseforProductValues;
import com.stacklineapi.stacklineapi.model.SearchResponse;
import com.stacklineapi.stacklineapi.service.AutoCompleteSearchService;
import com.stacklineapi.stacklineapi.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/product")         //Main Endpoint

public class SearchController {
    @Autowired
    private SearchService searchService;                        //Search service object
    @Autowired
    private AutoCompleteSearchService autoCompleteSearchService;            //AutoComplete service object

    @Autowired
    SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    //This fucntion returns the Product details after doing Search
    @PostMapping("/search")
    public ResponseforProductValues search(@RequestBody SearchResponse sampleproductData){

        List<String> fieldName = new ArrayList<String>();

        for(Iterator iterator = sampleproductData.getConditions().iterator(); iterator.hasNext();){
            Products product = (Products) iterator.next();
            fieldName.add(product.getFieldName());
            System.out.println(fieldName);
        }

        return searchService.search(fieldName, sampleproductData.getPagination());
    }

    //This fucntion returns the Brand Details for AutoComplete functionality
    @GetMapping(value = "/autocomplete")
    public ResponseforAutoComplete autoCompleteValues(@RequestParam("type") String type, @RequestParam("searchTermPrefix") String searchTermPrefix){
        return autoCompleteSearchService.autoCompleteSearchService(type);
    }



}

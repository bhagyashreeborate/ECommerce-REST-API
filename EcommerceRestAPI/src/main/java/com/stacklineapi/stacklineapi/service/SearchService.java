/* Created by: Bhagyashree Borate
 *  Date: 8th June 2018
 * */

/* this is a Service for Search API to search the product details. */


package com.stacklineapi.stacklineapi.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.stacklineapi.stacklineapi.model.Pagination;
import com.stacklineapi.stacklineapi.model.ProdcutValues;
import com.stacklineapi.stacklineapi.model.ResponseforProductValues;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class SearchService {

    /* Connections to AWS S3 */
    private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;
    @Value("${amazonProperties.keyName}")
    private String keyName;

    /* AWS S3 connections section close */


    public ResponseforProductValues search(List<String> fieldName, Pagination pagination) {

        StringBuilder sb = new StringBuilder();

        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        com.amazonaws.services.s3.AmazonS3 s3Client = new AmazonS3Client(credentials);

        S3Object s3object = s3Client.getObject(bucketName, keyName);

        InputStream is = s3object.getObjectContent();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        ResponseforProductValues responseforProductValues = new ResponseforProductValues();
        List<ProdcutValues> prodcutValuesList = new ArrayList<ProdcutValues>();
        String line;

        try {
            //Here it checks whether the file is empty or not. If not it searches for the input values and returns the response in ProductValues array.
            while((line = br.readLine()) != null)
            {
                for(Iterator iterator = fieldName.iterator(); iterator.hasNext();){
                    String fieldValue = (String) iterator.next();
                    if(line.contains(fieldValue)){
                        String [] dataArray;
                        String delimiter = "\t";
                        dataArray = line.split(delimiter);
                        if(dataArray[0].equals(fieldValue) || dataArray[1].equals(fieldValue) || dataArray[2].equals(fieldValue) || dataArray[3].equals(fieldValue) || dataArray[4].equals(fieldValue) || dataArray[5].equals(fieldValue)) {
                            ProdcutValues prodcutValues = new ProdcutValues();
                            prodcutValues.setProductId(dataArray[0]);
                            prodcutValues.setTitle(dataArray[1]);
                            prodcutValues.setBrandId(dataArray[2]);
                            prodcutValues.setBrandName(dataArray[3]);
                            prodcutValues.setCategoryId(dataArray[4]);
                            prodcutValues.setCategoryName(dataArray[5]);
                            prodcutValuesList.add(prodcutValues);
                            break;
                        }
                    }

                }

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        responseforProductValues.setProdcutValues(prodcutValuesList);
        responseforProductValues.setPagination(pagination);
        return responseforProductValues;

    }
}

/* Created by: Bhagyashree Borate
 *  Date: 8th June 2018
 * */

/* this is a Service for AutoComplete Search Operation. This Service takes Input and search the value from S3 bucket file. */


package com.stacklineapi.stacklineapi.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.stacklineapi.stacklineapi.model.ResponseforAutoComplete;
import com.stacklineapi.stacklineapi.model.ResponseforProductValues;
import com.stacklineapi.stacklineapi.model.Suggestions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class AutoCompleteSearchService {


    /* Connections to AWS S3 */

    private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")        //get the endpoint url (define in application.yml)
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")         //get the bucket name (define in application.yml)
    private String bucketName;
    @Value("${amazonProperties.accessKey}")         //get the access key (define in application.yml)
    private String accessKey;
    @Value("${amazonProperties.secretKey}")         //get the secret key (define in application.yml)
    private String secretKey;
    @Value("${amazonProperties.keyName}")           //get the File name (i.e. key name) (define in application.yml)
    private String keyName;


    /* AWS Connection section close */

    public ResponseforAutoComplete autoCompleteSearchService(String type) {

        StringBuilder sb = new StringBuilder();

        //This is to connect to AWS with Access Key and Secret Key
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        //It creates the s3client object connection with credentials.
        com.amazonaws.services.s3.AmazonS3 s3Client = new AmazonS3Client(credentials);

        S3Object s3object = s3Client.getObject(bucketName, keyName);        //get the s3object

        InputStream is = s3object.getObjectContent();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        ResponseforProductValues responseforProductValues = new ResponseforProductValues();
        List<Suggestions> suggestionsList = new ArrayList<Suggestions>();
        ResponseforAutoComplete responseforAutoComplete = new ResponseforAutoComplete();

        String line;
        try {


            //Here it checks whether the file is empty or not. If not it searches for the input values and returns the response in suggestions array.
            while((line = br.readLine()) != null)
            {
                if(line.contains(type)){
                    System.out.println(line);
                    String [] dataArray;
                    String delimiter = "\t";
                    dataArray = line.split(delimiter);
                    if(dataArray[3].equals(type)) {
                        responseforAutoComplete.setType(type);
                        Suggestions suggestions = new Suggestions();
                        suggestions.setId(dataArray[2]);
                        suggestions.setName(dataArray[3]);
                        suggestionsList.add(suggestions);

                        break;

                    }

                }
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        responseforAutoComplete.setSuggestions(suggestionsList);        //The suggestions array is sent to the AutoComplete Response.

        return responseforAutoComplete;

    }
}

package com.crud.springbootmysql.demo.controller;

import com.crud.springbootmysql.demo.dataclass.ContactDTO;
import com.crud.springbootmysql.demo.model.Contact;
import com.crud.springbootmysql.demo.helper.CustomApiResponse;
import com.crud.springbootmysql.demo.helper.CustomApiResponseBody;
import com.crud.springbootmysql.demo.service.ContactService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(path="/Contacts") // This means URL's start with /demo (after Application path)

public class ContactController {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
    @Autowired
    ContactService service;
    CustomApiResponse customResponse;

    CustomApiResponseBody responseBody;

    public  ContactController(){

        customResponse= new CustomApiResponse();
        responseBody= new CustomApiResponseBody();
    }

    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping
    public CustomApiResponse create(@RequestBody ContactDTO contactDTO) {
        if (service.DuplicateEmailAddress(contactDTO.getEmail()))
        {
            responseBody.setStatusCode(HttpStatus.CONFLICT);
            responseBody.setStatusMessage("Email address must be unique");
            customResponse.responseBody = responseBody;
            return customResponse;
        }
        ContactDTO savedContact= service.createContact(contactDTO);
        responseBody.statusCode = HttpStatus.CREATED;

        if (savedContact == null)
        {
            responseBody.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            responseBody.setStatusMessage("Data not saved");
            customResponse.responseBody = responseBody;
            return customResponse;
        }

        responseBody.setStatusMessage("Data saved Successfully");
        responseBody.setObjectVal(savedContact);
        customResponse.setResponseBody(responseBody);
        return customResponse;
    }
    @ResponseStatus(HttpStatus.OK) // 200
    @GetMapping
    public CustomApiResponse getContacts() {
        // This returns a JSON or XML with the users
        var contacts=service.getContacts();
        if(contacts==null){
            responseBody.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            responseBody.setStatusMessage("Data can not be retrived");
            responseBody.setObjectVal(contacts);
            customResponse.setResponseBody(responseBody);
            return customResponse;
        }
        responseBody.setStatusCode(HttpStatus.OK);
        responseBody.setStatusMessage("Data retrived Successfully");
        responseBody.setObjectVal(contacts);
        customResponse.setResponseBody(responseBody);
        return customResponse;
    }

    @ResponseStatus(HttpStatus.OK) // 200
    @GetMapping("/{id}")
    public CustomApiResponse getContactById(@PathVariable Long id) {
        logger.info("getContactById called");
        // This returns a JSON or XML with the users
        ContactDTO contactDTO=service.getContactById(id);
        if(contactDTO==null){
            responseBody.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            responseBody.setStatusMessage("Data can not be retrived");
            responseBody.setObjectVal(contactDTO);
            customResponse.setResponseBody(responseBody);
            return customResponse;
        }
        if(Objects.nonNull(contactDTO)&&contactDTO.getId()==null){
            responseBody.setStatusCode(HttpStatus.NOT_FOUND);
            responseBody.setStatusMessage("Data can not be retrived");
            responseBody.setObjectVal(contactDTO);
            customResponse.setResponseBody(responseBody);
            return customResponse;
        }

        responseBody.setStatusCode(HttpStatus.OK);
        responseBody.setStatusMessage("Data Retrive Successfully");
        responseBody.setObjectVal(contactDTO);
        customResponse.setResponseBody(responseBody);
        return customResponse;
    }

    @ResponseStatus(HttpStatus.OK) // 200
    @PutMapping("/{id}")
    public CustomApiResponse updateContactById(@PathVariable(value = "id") Long id, @RequestBody ContactDTO contactDTO) {
        logger.info("update contacts");
        if(!service.ContactExists(id)){
            responseBody.setStatusCode(HttpStatus.NOT_FOUND);
            responseBody.setStatusMessage("Data can not be retrived");
            responseBody.setObjectVal(null);
            customResponse.setResponseBody(responseBody);
            return customResponse;
        }
        if(id!=contactDTO.getId()){
            responseBody.setStatusCode(HttpStatus.BAD_REQUEST);
            responseBody.setStatusMessage("Id not match");
            responseBody.setObjectVal(null);
            customResponse.setResponseBody(responseBody);
            return customResponse;
        }
        if (service.DuplicateEmailAddressForUpdate(contactDTO.getEmail(), id))
        {
            responseBody.setStatusCode(HttpStatus.CONFLICT);
            responseBody.setStatusMessage("Email address must be unique");
            customResponse.responseBody = responseBody;
            return customResponse;
        }
        // This returns a JSON or XML with the users
        contactDTO=service.updateContactById(contactDTO,id);
        if(contactDTO==null){
            responseBody.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            responseBody.setStatusMessage("Data can not be updated");
            responseBody.setObjectVal(contactDTO);
            customResponse.setResponseBody(responseBody);
            return customResponse;
        }

        responseBody.setStatusCode(HttpStatus.OK);
        responseBody.setStatusMessage("Data Updated Successfully");
        responseBody.setObjectVal(contactDTO);
        customResponse.setResponseBody(responseBody);
        return customResponse;
    }


}
package com.crud.springbootmysql.demo.service;

import com.crud.springbootmysql.demo.dataclass.ContactDTO;
import com.crud.springbootmysql.demo.model.Contact;

import java.util.List;

public interface ContactService {

    public ContactDTO createContact(ContactDTO contact);

    // updated it with UserDto
    public List<ContactDTO> getContacts();

    public ContactDTO getContactById(Long id);
    public boolean ContactExists(Long id);

    public ContactDTO updateContactById(ContactDTO contactDTO, Long id);

    public boolean DuplicateEmailAddress(String email);

    public boolean DuplicateEmailAddressForUpdate(String email, Long id);
}

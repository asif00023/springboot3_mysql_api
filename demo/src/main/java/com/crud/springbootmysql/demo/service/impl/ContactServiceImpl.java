package com.crud.springbootmysql.demo.service.impl;


import com.crud.springbootmysql.demo.dataclass.ContactDTO;
import com.crud.springbootmysql.demo.model.Contact;
import com.crud.springbootmysql.demo.repository.ContactRepository;
import com.crud.springbootmysql.demo.service.ContactService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    private static final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ContactDTO createContact(ContactDTO contactDTO){
        Contact contact = this.modelMapper.map(contactDTO, Contact.class);
        if (contactDTO.getDisplayName()==null || contactDTO.getDisplayName().isEmpty())
        {
//            contact.displayName = GenerateDisplayName(contact.salutation,contact.firstName,contact.lastName);
            contact.setDisplayName(GenerateDisplayName(contactDTO.getSalutation(),contactDTO.getFirstName(),contactDTO.getLastName()));
        }

        contact.creationTimestamp = new Date();
        try
        {
            contact=contactRepository.save(contact);
        }
        catch (Exception r)
        {

            logger.error(r.getMessage());
            return null;
        }
        contactDTO=this.modelMapper.map(contact, ContactDTO.class);
        Long bRemainDays=BirthdayRemainingDays(contactDTO.getBirthDate());
        if (bRemainDays <= 14)
            contactDTO.setnotificationHasBirthdaySoon("Birthday will be in " + bRemainDays + " days");
        else
            contactDTO.setnotificationHasBirthdaySoon("");
        return contactDTO;
    }


    @Override
    public ArrayList<ContactDTO> getContacts() {
        Iterable<Contact> contactList =new ArrayList<>();
        try{
           contactList= this.contactRepository.findAll();
        }
        catch (Exception r){
            contactList=null;
            logger.error(r.getMessage());
            return null;
        }
        ArrayList<ContactDTO>contactDTOS= new ArrayList<>();
        ContactDTO contactDTO;
        for (Contact contact : contactList) {
            contactDTO= new ContactDTO();
            try{
                contactDTO=this.modelMapper.map(contact, ContactDTO.class);
                Long bRemainDays=BirthdayRemainingDays(contactDTO.getBirthDate());
                if (bRemainDays <= 14)
                    contactDTO.setnotificationHasBirthdaySoon("Birthday will be in " + bRemainDays + " days");
                else
                    contactDTO.setnotificationHasBirthdaySoon("");
                contactDTOS.add(contactDTO);
            }
            catch (Exception r){
                logger.error(r.getMessage());
            }

        }

        return contactDTOS;
    }
    @Override
    public ContactDTO getContactById(Long id){
        Optional<Contact> contact= Optional.of(new Contact());
        try{
            contact=this.contactRepository.findById(id);
        }
        catch (Exception r){
            logger.error(r.getMessage());
            return null;
        }
        ContactDTO contactDTO= new ContactDTO();
        if(Objects.nonNull(contact)&&!contact.isEmpty())
                contactDTO=this.modelMapper.map(contact,ContactDTO.class);
        Long bRemainDays=BirthdayRemainingDays(contactDTO.getBirthDate());
        if (bRemainDays <= 14)
            contactDTO.setnotificationHasBirthdaySoon("Birthday will be in " + bRemainDays + " days");
        else
            contactDTO.setnotificationHasBirthdaySoon("");
        return contactDTO;
    }
    @Override
    public ContactDTO updateContactById(ContactDTO contactDTO,Long id){
        if (contactDTO.getDisplayName()==null || contactDTO.getDisplayName().isEmpty())
        {
            contactDTO.setDisplayName(GenerateDisplayName(contactDTO.getSalutation(),contactDTO.getFirstName(),contactDTO.getLastName()));
        }
        Contact contact= new Contact();
        contact=this.modelMapper.map(contactDTO,Contact.class);

        try{
            contact=this.contactRepository.save(contact);
        }
        catch (Exception r){
            logger.error(r.getMessage());
            return null;
        }
        contactDTO=this.modelMapper.map(contact,ContactDTO.class);
        Long bRemainDays=BirthdayRemainingDays(contactDTO.getBirthDate());
        if (bRemainDays <= 14)
            contactDTO.setnotificationHasBirthdaySoon("Birthday will be in " + bRemainDays + " days");
        else
            contactDTO.setnotificationHasBirthdaySoon("");
        return contactDTO;
    }
    @Override
    public boolean DuplicateEmailAddress(String email){
        boolean isExist=this.contactRepository.existsByEmail(email);
        return isExist;
    }

    @Override
    public boolean DuplicateEmailAddressForUpdate(String email, Long id){
        int count=this.contactRepository.existsDuplicateForUpdateByEmail(email,id);
        if(count>0)
            return true;
        else
            return false;

    }
    public String GenerateDisplayName(String salutation, String firstName, String lastName)
    {
        return salutation + " " + firstName + " " + lastName;
    }

    @Override
    public boolean ContactExists(Long id){
        boolean isExists=this.contactRepository.existsById(id);
        return isExists;
    }
    public long BirthdayRemainingDays(Date dt)
    {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//        Date date= new Date();
//
//        final String strBDay = "1990/04/07";//"1990/04/07"; // Next Birtday date
//
//        Date dt = null;
//        try
//        {
//            dt = sdf.parse(strBDay);
//        }
//        catch (final java.text.ParseException e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

        final Calendar BDay = Calendar.getInstance();
        BDay.setTime(dt);

        final Calendar today = Calendar.getInstance();

        // Take your DOB Month and compare it to current month
        final int BMonth = BDay.get(Calendar.MONTH);
        final int CMonth = today.get(Calendar.MONTH);
        BDay.set(Calendar.YEAR, today.get(Calendar.YEAR));
        if(BMonth <= CMonth)
        {
            BDay.set(Calendar.YEAR, today.get(Calendar.YEAR) + 1);
        }

        // Result in millis
        final long millis = BDay.getTimeInMillis() - today.getTimeInMillis();

        // Convert to days
        long days = millis / 86400000; // Precalculated (24 * 60 * 60 * 1000)
        if(days>=365)
            days=days-365;
        return days;
    }

}
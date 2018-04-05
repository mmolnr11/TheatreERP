package com.project.service;

import com.project.dao.UserDao;
import com.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

@Service
public class UserValidation {
    @Autowired
    UserDao userDao;
    public List<String> validateRegistrationDatas(User user, String confirm) {
        List<String> errorMessages = new ArrayList<>();

        if (user.getFirstName().length() < 2){
            errorMessages.add("First name must be at least 2 character long.");
        }
        if (dataContainsNumber(user.getFirstName())){
            errorMessages.add("First name shall be free of numbers.");
        }
        if (dataContainsSigns(user.getFirstName())){
            errorMessages.add("First name shall be free of signs.");
        }
        System.out.println("kaka");
        if (user.getFirstName().length() > 0 && dataNotStartsWithUpperCaseLetter(user.getFirstName())){
            System.out.println("kaka2");
            errorMessages.add("First name must start with upper case letter.");
        }
        System.out.println(user.getLastName().length() + "husszu");
        if (user.getLastName().length() < 2){
            errorMessages.add("Last name must be at least 2 character long.");
        }
        if (dataContainsNumber(user.getLastName())){
            errorMessages.add("Last name shall be free of numbers.");
        }
        if (dataContainsSigns(user.getLastName())){
            errorMessages.add("Last name shall be free of signs.");
        }
        if (user.getLastName().length() > 0 && dataNotStartsWithUpperCaseLetter(user.getLastName())){
            errorMessages.add("Last name must start with upper case letter.");
        }

        Pattern compiledPattern = Pattern.compile(
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = compiledPattern.matcher(user.getEmailAddress());
        boolean emailIsCorrect = matcher.matches();
        if (!emailIsCorrect){
            errorMessages.add("Enter a format like: hasi.tasi@citromail.com");
        }

        if (emailExists(user.getEmailAddress())){
            errorMessages.add("This email is already exists in our database. Give another one.");
        }

//        if (user.ge().length() < 3){
//            errorMessages.add("Country must be at least 3 character long.");
//        }
//
//        if (dataContainsNumber(user.getCountry())){
//            errorMessages.add("Country shall be free of numbers.");
//        }
//
//        if (user.getCity().length() < 2){
//            errorMessages.add("City must be at least 2 character long.");
//        }
//
//        if (dataContainsNumber(user.getCity())){
//            errorMessages.add("City shall be free of numbers.");
//        }
//
//        if (user.getPostalCode().length() < 2 || user.getPostalCode().length() > 10){
//            errorMessages.add("Postal Code's length must be between 2 and 10 characters.");
//        }
//
//        if (user.getAddress().length() < 5){
//            errorMessages.add("Address must be at least 5 character long.");
//        }
//
//        if (user.getPassword().length() < 5){
//            errorMessages.add("Password must be at least 5 character long.");
//        }
//
//        if (!user.getPassword().equals(confirm)){
//            errorMessages.add("Password confirmation failed. Type the same password in Password and Confirm fields.");
//        }

        return errorMessages;
    }
    private boolean dataContainsNumber(String name) {
        String numbers = "1234567890";
        for (int index = 0; index < name.length(); index++){
            if (numbers.contains(Character.toString(name.charAt(index)))){
                return true;
            }
        }
        return false;
    }
    private boolean dataContainsSigns(String name) {
        String signs = ".,;/=*-";
        for (int index = 0; index < name.length(); index++){
            if (signs.contains(Character.toString(name.charAt(index)))){
                return true;
            }
        }
        return false;
    }
    private boolean emailExists(String email) {
        boolean emailExists = false;
        User user = userDao.getUserByEmailAddress(email);
        if (user != null){
            emailExists = true;
        }
        return emailExists;
    }

    private boolean dataNotStartsWithUpperCaseLetter(String word) {
        return Character.isLowerCase(word.charAt(0));
    }
}

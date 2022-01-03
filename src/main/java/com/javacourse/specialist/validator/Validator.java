package com.javacourse.specialist.validator;

public class Validator {
    private static final String NUMBER = "\\d{1,3}";
    private static final String PHONE_REGEX = "(\\+375)(29|25|33|44)[\\d]{7}";
    private static final String ID_REGEX = "\\d{1,3}";
    private static final String PASSWORD_REGEX = "[a-zA-zА-Яа-я\\d]{5,20}";
    private static final String EMAIL_REGEX = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}&";
    private static final String NAME_REGEX = "[a-zA-Zа-яА-Я]{2,20}";
    private static final String SURNAME_REGEX = "[a-zA-Zа-яА-Я]{2,20}";
    private static final String DATE_REGEX ="^((19|20)\\d\\d).(0?[1-9]|1[012]).(0?[1-9]|[12][0-9]|3[01])$"; //"^\d{4}.\d{2}.\d{2}$";
      //String regex = "^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";  // подходит ли? или сделать отдельный класс для валидации даты?

    private Validator(){}
    public static boolean isValid(String number){
        return number.matches(NUMBER);
    }
    public static boolean isValidEmail(String email){
        return email.matches(EMAIL_REGEX);
    }
    public static boolean isValidPhone(String phone){
        return phone.matches(PHONE_REGEX);
    }
    public static boolean isValidId(String id){
        return id.matches(ID_REGEX);
    }
    public static boolean isIdenticalPasswords(String password1, String password2){
        return password1.equals(password2);
    }
    public static boolean isValidPassword(String password){
        if(password == null){return false;}
        boolean flag = false;
        if(!password.isEmpty() & !password.trim().isEmpty()){
            flag = password.matches(PASSWORD_REGEX);
        }
        return flag;
    }
    public static boolean isValidName(String name){
        return name.matches(NAME_REGEX);
    }
    public static boolean isValidSurname(String lastName){
        return lastName.matches(SURNAME_REGEX);
    }
    public static boolean isValidDate(String date){return date.matches(DATE_REGEX);}
}

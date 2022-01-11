package com.javacourse.specialist.validator;

public class Validator {
    private static Validator instance;

    private Validator(){}

    public static Validator getInstance(){
        if(instance == null){
            instance = new Validator();
        }
        return instance;
    }

    private final String NUMBER = "\\d{1,3}";
    private final String PHONE_REGEX = "(\\+375)(29|25|33|44)[\\d]{7}";
    private final String ID_REGEX = "\\d{1,3}";
    private final String PASSWORD_REGEX = "[a-zA-zА-Яа-я\\d]{5,20}";
    private final String EMAIL_REGEX = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}&";
    private final String NAME_REGEX = "[a-zA-Zа-яА-Я]{2,20}";
    private final String SURNAME_REGEX = "[a-zA-Zа-яА-Я]{2,20}";
    private final String DATE_REGEX ="^((19|20)\\d\\d).(0?[1-9]|1[012]).(0?[1-9]|[12][0-9]|3[01])$"; //"^\d{4}.\d{2}.\d{2}$";
      //String regex = "^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";  // подходит ли? или сделать отдельный класс для валидации даты?

    public boolean isValid(String number){
        return number.matches(NUMBER);
    }
    public boolean isValidEmail(String email){
        return email.matches(EMAIL_REGEX);
    }
    public boolean isValidPhone(String phone){
        return phone.matches(PHONE_REGEX);
    }
    public boolean isValidId(String id){
        return id.matches(ID_REGEX);
    }
    public boolean isIdenticalPasswords(String password1, String password2){
        return password1.equals(password2);
    }
    public boolean isValidPassword(String password){
        if(password == null){return false;}
        boolean flag = false;
        if(!password.isEmpty() & !password.trim().isEmpty()){
            flag = password.matches(PASSWORD_REGEX);
        }
        return flag;
    }
    public boolean isValidName(String name){
        return name.matches(NAME_REGEX);
    }
    public boolean isValidSurname(String lastName){
        return lastName.matches(SURNAME_REGEX);
    }
    public boolean isValidDate(String date){return date.matches(DATE_REGEX);}
}

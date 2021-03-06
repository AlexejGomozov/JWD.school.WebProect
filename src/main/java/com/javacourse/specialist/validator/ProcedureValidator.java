package com.javacourse.specialist.validator;

public class ProcedureValidator {
    private static ProcedureValidator instance;

    private static final String ID_REGEX = "\\d{1,3}";
    private static final String NAME_REGEX = "[A-Za-zА-Яа-я-, ]{2,200}";
    private static final String DURATION_REGEX = "\\d{1,3}";     ///////////////????????????
    private static final String PRICE_REGEX = "^[0-9]{1,3}(\\.[0-9]{1,2})?$";
   // private static final String DISCOUNT_REGEX = "^[0-9]{1,3}(\\.[0-9]{1,2})?$";

    private ProcedureValidator(){}

    public static ProcedureValidator getInstance(){
        if(instance == null){
            instance = new ProcedureValidator();
        }
        return instance;
    }

    public boolean isValidID(String id){
        return id.matches(ID_REGEX);
    }
    public boolean isValidName(String name){
        return name.matches(NAME_REGEX);
    }
    public boolean isValidDuration (String duration){
        return duration.matches(DURATION_REGEX);
    }
    public boolean isValidPrice(String price){
        return price.matches(PRICE_REGEX);
    }
   // public boolean checkDiscount(String discount){return discount.matches(DISCOUNT_REGEX);}
}

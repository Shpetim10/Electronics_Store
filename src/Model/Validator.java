package Model;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class Validator {
    public static boolean validateName(String name){
        //Must start with capital letter and than has multiple lowercase letters
        return Pattern.matches("^[A-Z]{1}[a-z]+$",name);
    } //Sh
    public static boolean validateCreditCardName(String fullName){
        //Must start with capital Letter, must have 2 parst first Name and Last Name
        return Pattern.matches("^[A-Z]{1}[a-z]+ [A-Z]{1}[a-z]+$",fullName);
    } //Sh
    public static boolean validateCreditCardNumber(String number){
        //16 digits
        return Pattern.matches("^[0-9]{16}$",number);
    } //Sh
    public static boolean validateExpirationDateFormat(String date){
        // format 01/2025
        //Year greater or equal to 2025
        //2 regex->One for months 10,11,12 ond one for other
        if (date == null || date.length() != 7)
        {
            return false;
        }
        String yearPart = date.substring(3);
        boolean validFormat = Pattern.matches("^0[1-9]/[2-9][0-9]{3}$", date) || Pattern.matches("^1[0-2]/[2-9][0-9]{3}$", date);

        return validFormat && Integer.parseInt(yearPart)>=LocalDate.now().getYear();
    } //Sh
    public static boolean validateCreditCardCvv(String cvv){
        //3 digits
        return Pattern.matches("^[0-9]{3}$",cvv);
    } //Sh
    public static boolean validateCustomerId(String customerId){
        // Format: K00000000A
        return Pattern.matches("^[A-Z][0-9]{8}[A-Z]$",customerId);
    }

    //For products
    public static boolean validateProductName(String productName){
        return Pattern.matches("^[A-Z][a-z]+$",productName);
    }
    public static void main(String[] args) {

        System.out.println(validateExpirationDateFormat("00/2025"));
        System.out.println(validateExpirationDateFormat("01/2025"));
        System.out.println(validateExpirationDateFormat("05/2026"));
    }
}

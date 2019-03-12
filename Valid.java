package it.sovy.validateDocument;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Valid {
    public  boolean valid(String birthNumber, String date){
        Pattern pBN = Pattern.compile(String.format("[0-9]{2}[0156][0-9]{3}/([0-9]{3,4})"));
        Matcher mBN = pBN.matcher(birthNumber);
        boolean goodBN= mBN.matches();
        Pattern pDOB = Pattern.compile(String.format("(0?[1-9]|[1-2][0-9]|3[0-1])\\.(0[1-9]|1[0-2])\\.(19|20)[0-9]{2}"));
        Matcher mDOB = pDOB.matcher(date);
        boolean goodDOB= mDOB.matches();
        if (goodBN && goodDOB){
            return  true;
        }
        else {
             return false;
        }
    }

    public boolean validBirthNumberAsDOB(String birthNumber,String date){
        if(valid(birthNumber,date)){
            //rok
            if((date.charAt(date.length()-1) == birthNumber.charAt(1)) && (date.charAt(date.length()-2) == birthNumber.charAt(0))){
                //mesiac
                if(birthNumber.charAt(3) == date.charAt(4)){
                    //den
                    if ((birthNumber.charAt(4)== date.charAt(0))&&(birthNumber.charAt(5)== date.charAt(1))){
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }

            return false;



    }

    public boolean divisibility(String birthNumber){
        birthNumber= birthNumber.replaceAll("/","");
        long number = Long.parseLong(birthNumber);
        if(number%11 ==0){
            return true;
        }
        else{
            return false;
        }
    }
}

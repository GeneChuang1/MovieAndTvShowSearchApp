package com.sample.genechuang.movieandtvsearch.UserInputPages;

import android.app.Activity;
import android.widget.Toast;

//Created by GeneChuang on 10/16/2015.
//Gene Chuang owns the copyright to this code. Publication or distribution of this code is
//not allowed without Gene Chuang's written consent.
public class Formatting {
    private String mName= "", mYear= "";
    Activity currentActivity;

    public Formatting (String name, String year, Activity activity){
        mName=name;
        mYear= year;
        currentActivity= activity;
    }

    public String getFormattedName() {
        mName = mName.replaceAll("(\r\n|\n|\r|\\r|\\n)", ""); //Replace all newlines with +
        mName = mName.replaceAll(" ", "\\+"); //Replace all spaces with +
        mName = mName.replaceAll(":", "%3A");
        mName = mName.replaceAll("#", "%23");
        return mName;
    }

    public Boolean checkYearFormatting(){
        if (mName.equals("")){
            Toast toast = Toast.makeText(currentActivity.getApplicationContext(), "Please enter a movie name", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        } else if ( mYear.equals("")){  //User didn't input a year value
            return true;
        } else {  //User entered something
            Integer intYear= Integer.parseInt(mYear);
            int length = (int) Math.log10(intYear) + 1;
            if (length != 4) {
                Toast toast = Toast.makeText(currentActivity.getApplicationContext(), "The year must be a 4 digit number", Toast.LENGTH_SHORT);
                toast.show();
                return false;
            } else {
                return true;
            }
        }
    }

    public Boolean checkNameFormatting() {
        if (mName.charAt(mName.length() - 1) == ' ' || mName.charAt(0)==' '
            || mName.charAt(mName.length()-1)== '\n') {
            Toast toast = Toast.makeText(currentActivity.getApplicationContext(), "Please do not leave spaces at the beginning or end of the name.", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        } else
            return true;
    }
}

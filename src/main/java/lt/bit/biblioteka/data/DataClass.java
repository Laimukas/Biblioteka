package lt.bit.biblioteka.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataClass {

    public String getDataNow(){
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate= DateFor.format(date);
        System.out.println(stringDate);
        return stringDate;

    }

    public String formatData ( String data){
        Date date = new Date(data);
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = DateFor.format(date);
        System.out.println(stringDate);
        return stringDate;
    }


}

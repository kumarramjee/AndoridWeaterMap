package weathermap.mobimedia.com.andoridweatermap.bean;

/**
 * Created by sadhana on 10/6/15.
 */
public class Weatherbean {


    String mimageUrl;
    String mtext;
    String mtemp;
    String  mdate;




    public void setimageUrl(String imageUrl){

        mimageUrl=imageUrl;
    }

    public void setText(String text){

        mtext=text;

    }

    public void setTemp(String temp){

        mtemp=temp;

    }
    public void setDate(String date){

        mdate=date;

    }
    public String getImageUrl(){

        return  mimageUrl;

    }


}

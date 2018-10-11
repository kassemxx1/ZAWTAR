package kassem.com.zawtar;


import java.util.ArrayList;

public class CustomClass {
    String titlefb;
    String imagefb;
    String time;
    String detailsfb;
    ArrayList<String> pics;
    ArrayList<String> videos;
    CustomClass(String titlefb, String detailsfb, String time, String imagefb, ArrayList<String> pics, ArrayList<String> videos){
        this.titlefb=titlefb;
        this.detailsfb=detailsfb;
        this.time=time;
        this.imagefb=imagefb;
        this.pics= pics;
        this.videos= videos;

    }

}

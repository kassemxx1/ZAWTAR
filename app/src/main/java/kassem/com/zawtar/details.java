package kassem.com.zawtar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class details extends AppCompatActivity{
    ArrayList<String> picturess;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailscontroller);
        ImageView detailImage=(ImageView)findViewById(R.id.imageView2);
        TextView detailstitle=(TextView)findViewById(R.id.textView2);
        TextView detailsDetails=(TextView)findViewById(R.id.textView3);
        Bundle b=getIntent().getExtras();
        String title=b.getString("title");
        String details=b.getString("details");
        String imagename=b.getString("imagename");
        picturess=new ArrayList<>();
        ArrayList<String> ddd=b.getStringArrayList("pics");
        ArrayList<String> vvv=b.getStringArrayList("videos");
       // picturess=ddd;
        Log.d("ggggggg",""+ddd);
        detailstitle.setText(title);
        detailsDetails.setText(details);
        new DownloadImageTask((ImageView) detailImage)
                .execute(imagename);
        ListView listimage=findViewById(R.id.PICS);
        picturesadapter aaa=new picturesadapter();
       listimage.setAdapter(aaa);

    }

    class picturesadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return picturess.size();
        }

        @Override
        public Object getItem(int position) {
           return null;
        }


        public long getItemId(int position) {
            return 0;
        }

       @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=getLayoutInflater().inflate(R.layout.imagescell,null);
            ImageView pic=view.findViewById(R.id.pictures);
            new DownloadImageTask((ImageView) pic)
                    .execute(picturess.get(position));

            return null;
        }
    }
}

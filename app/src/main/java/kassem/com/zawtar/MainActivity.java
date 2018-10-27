package kassem.com.zawtar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<CustomClass> news = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retirirve();

    ////////////////////////////////////////////////////////////////
// Create default options which will be used for every
//  displayImage(...) call if no options will be passed to this method

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
           .cacheInMemory(true)
                .cacheOnDisk(true)
           .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
           .defaultDisplayImageOptions(defaultOptions)
           .build();
        ImageLoader.getInstance().init(config);


    }
    ////////////////////////////////////////////////////////////////




    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.aboutt) {
            Intent aboutintet = new Intent(MainActivity.this, about.class);
            startActivity(aboutintet);
        }
        if (id == R.id.phonebook) {
            Intent phonetintet = new Intent(MainActivity.this, phone.class);
            startActivity(phonetintet);
        }
        if (id == R.id.action_refresh){
            news.clear();
        retirirve();
    }
        return super.onOptionsItemSelected(item);
    }




  //////////////////////////////////////////////////////////////////////////////////////////////////
    void pass(int i) {
        Intent myintet = new Intent(MainActivity.this, details.class);
        Bundle mybundle = new Bundle();
        mybundle.putString("title", news.get(i).titlefb);
        mybundle.putString("details", news.get(i).detailsfb);
        mybundle.putString("time", news.get(i).time);
        mybundle.putString("imagename", news.get(i).imagefb);
        mybundle.putStringArrayList("pics", ( news.get(i).pics));
        mybundle.putStringArrayList("videos",(news.get(i).videos));

        myintet.putExtras(mybundle);
        startActivity(myintet);
    }




////////////////////////////////////////////////////////////////////////////////////////////////////
    class baseadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return news.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            View view = getLayoutInflater().inflate(R.layout.custom_cell, null);//3arafna l custom cell
            TextView title = view.findViewById(R.id.textView);
            title.setText(news.get(position).titlefb);
            TextView time=view.findViewById(R.id.date);
            time.setText(news.get(position).time);
            ImageView image = view.findViewById(R.id.imageView);

            ImageLoader.getInstance().displayImage(news.get(position).imagefb, image);
            return view;

        }
    }
 ///////////////////////////////////////////////////////////////////////////////////////////////////





 ///////////////////////////////////////////////////////////////////////////////////////////////////

void retirirve(){
    final ListView list = findViewById(R.id.listview);


    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    final KProgressHUD hud = KProgressHUD.create(MainActivity.this)
            .setStyle(KProgressHUD.Style.ANNULAR_DETERMINATE)
            .setLabel("Please wait")
            .setMaxProgress(100)
            .show();


    final Task<QuerySnapshot> querySnapshotTask = db.collection("news")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot document : task.getResult()) {


                            String title = document.getData().get("title").toString();

                            String details = document.getData().get("details").toString();

                            String time = document.getData().get("time").toString();

                            String imagename = document.getData().get("imagename").toString();

                            @SuppressWarnings("unchecked")
                            ArrayList<String> pics= (ArrayList<String>) document.get("pics") ;

                            @SuppressWarnings("unchecked")
                            ArrayList<String> videos= (ArrayList<String>) document.get("videos") ;

                                news.add(0, new CustomClass(title, details, time, imagename, pics, videos));


                            baseadapter adapter = new baseadapter();
                            list.setAdapter(adapter);
                            hud.dismiss();
                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    pass(position);
                                }
                            });
                        }
                    }

                }

            });

}
////////////////////////////////////////////////////////////////////////////////////////////////////

}


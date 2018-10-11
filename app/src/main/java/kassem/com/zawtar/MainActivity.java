package kassem.com.zawtar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<CustomClass> news = new ArrayList<>();

    //ImageLoader cachedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView list = findViewById(R.id.listview);

        // cachedImage=new ImageLoader(this);

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


                                news.add(0, new CustomClass(title, details, time, imagename,pics,videos));
                                String ss = "" + news.size();



                                String size = "" + news.size();

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

    void pass(int i) {
        Intent myintet = new Intent(MainActivity.this, details.class);
        Bundle mybundle = new Bundle();
        mybundle.putString("title", news.get(i).titlefb);
        mybundle.putString("details", news.get(i).detailsfb);
        mybundle.putString("time", news.get(i).time);
        mybundle.putString("imagename", news.get(i).imagefb);
        mybundle.putString("pics", String.valueOf(news.get(i).pics));
        mybundle.putString("videos", String.valueOf(news.get(i).videos));
        myintet.putExtras(mybundle);
        startActivity(myintet);
    }

//    void retrieve() {
//
//        final FirebaseFirestore db = FirebaseFirestore.getInstance();
//        final KProgressHUD hud = KProgressHUD.create(MainActivity.this)
//                .setStyle(KProgressHUD.Style.ANNULAR_DETERMINATE)
//                .setLabel("Please wait")
//                .setMaxProgress(100)
//                .show();
//
//
//        final Task<QuerySnapshot> querySnapshotTask = db.collection("news")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (DocumentSnapshot document : task.getResult()) {
//                                Log.d("asdasdasd", document.getId() + " => " + document.getData());
//
//
//                                String title = document.getData().get("title").toString();
//                                Log.d("tttiiiittttllleee", title);
//                                String details = document.getData().get("details").toString();
//
//                                String time = document.getData().get("time").toString();
//
//                                String imagename = document.getData().get("imagename").toString();
//
////                                @SuppressWarnings("unchecked")
////                                ArrayList<String> pics= (ArrayList<String>) document.get("pics") ;
////                                @SuppressWarnings("unchecked")
////                                ArrayList<String> videos= (ArrayList<String>) document.get("videos") ;
//
//
//                                news.add(new CustomClass(title, details, time, imagename));
//                                String ss = "" + news.size();
//                                Log.d("hhhhhhhhhh", ss);
//
//                                hud.dismiss();
//                            }
//                        } else {
//
//                        }
//                    }
//                });
//    }

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
                ImageView image = view.findViewById(R.id.imageView);
                new DownloadImageTask((ImageView) image)
                        .execute(news.get(position).imagefb);

                return view;

            }
        }
    }


package kassem.com.zawtar;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;


import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;


public class details extends AppCompatActivity {
    ArrayList<String> picturesss = new ArrayList<>();
    ArrayList<String> videoss = new ArrayList<>();
    ArrayList<String>  newsss = new ArrayList<>();
    String datee;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailscontroller);
        Bundle b = getIntent().getExtras();
        String title = b.getString("title");
        String detailss = b.getString("details");
        String imagename = b.getString("imagename");
        String time=b.getString("time");
        ArrayList<String> ddd = b.getStringArrayList("pics");
        ArrayList<String> vvv = b.getStringArrayList("videos");
        picturesss = ddd;
        videoss = vvv;

        datee=time;
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        newsss.add(0, imagename);
        newsss.add(1, title);
        newsss.add(2, detailss);


        if (ddd != null && vvv == null) {
            for (int i = 3, j = 0; i < 3 + ddd.size() && j < ddd.size(); i++, j++) {

                newsss.add(i, ddd.get(j));
            }
        }
        if (vvv != null && ddd == null) {
            for (int i = 3, j = 0; i < 3 + vvv.size() && j < vvv.size(); i++, j++) {

                newsss.add(i, vvv.get(j));
            }
        }
        if (vvv != null && ddd!=null) {
            int i ;
            int j ;
          for (i = 3 , j = 0; i < 3 + ddd.size() + vvv.size() && j < ddd.size(); i++, j++){
              newsss.add(i,ddd.get(j));
              }

            for (i = 3+ ddd.size(), j = 0;i < 3+ddd.size()+vvv.size()&& j < vvv.size();i++,j++){
              newsss.add(i,vvv.get(j));
            }

        }

        ///////////////////////////////////////////////////////////////////////////////////////////

        ListView listimage = (ListView) findViewById(R.id.PICS);

        picturesadapter aaa = new picturesadapter();
        listimage.setAdapter(aaa);

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    class picturesadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return newsss.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }


        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view;
            if (videoss == null && picturesss == null) {
                if (position == 0) {
                    view = getLayoutInflater().inflate(R.layout.imagescell, null);
                    ImageView pic = view.findViewById(R.id.pictures);
                    ImageLoader.getInstance().displayImage(newsss.get(position), pic);
                    return view;
                }
                if (position == 1) {
                    view = getLayoutInflater().inflate(R.layout.titletext, null);
                    TextView ttttt = view.findViewById(R.id.titleTexttt);
                    TextView date = view.findViewById(R.id.date);
                    date.setText(datee);
                    ttttt.setText(newsss.get(position));
                    return view;
                }
                if (position == 2) {
                    view = getLayoutInflater().inflate(R.layout.textcell, null);
                    TextView ttttt = view.findViewById(R.id.textdetailll);
                    ttttt.setText(newsss.get(position));
                    return view;
                }
            }
            if (picturesss != null && videoss == null) {
                if (position == 0) {
                    view = getLayoutInflater().inflate(R.layout.imagescell, null);
                    ImageView pic = view.findViewById(R.id.pictures);
                    ImageLoader.getInstance().displayImage(newsss.get(position), pic);
                    return view;
                }
                if (position == 1) {
                    view = getLayoutInflater().inflate(R.layout.titletext, null);
                    TextView ttttt = view.findViewById(R.id.titleTexttt);
                    TextView date = view.findViewById(R.id.date);
                    date.setText(datee);
                    ttttt.setText(newsss.get(position));
                    return view;
                }
                if (position == 2) {
                    view = getLayoutInflater().inflate(R.layout.textcell, null);
                    TextView ttttt = view.findViewById(R.id.textdetailll);
                    ttttt.setText(newsss.get(position));
                    return view;
                } else {

                    view = getLayoutInflater().inflate(R.layout.imagescell, null);
                    ImageView pic = view.findViewById(R.id.pictures);
                    ImageLoader.getInstance().displayImage(newsss.get(position), pic);
                    return view;
                }
            }
            if (videoss != null && picturesss == null) {
                if (position == 0) {
                    view = getLayoutInflater().inflate(R.layout.imagescell, null);
                    ImageView pic = view.findViewById(R.id.pictures);
                    ImageLoader.getInstance().displayImage(newsss.get(position), pic);
                    return view;
                }
                if (position == 1) {
                    view = getLayoutInflater().inflate(R.layout.titletext, null);
                    TextView ttttt = view.findViewById(R.id.titleTexttt);
                    TextView date = view.findViewById(R.id.date);
                    date.setText(datee);
                    ttttt.setText(newsss.get(position));
                    return view;
                }
                if (position == 2) {
                    view = getLayoutInflater().inflate(R.layout.textcell, null);
                    TextView ttttt = view.findViewById(R.id.textdetailll);
                    ttttt.setText(newsss.get(position));
                    return view;
                } else  {

                    view = getLayoutInflater().inflate(R.layout.videocell, null);

                    YouTubePlayerFragment youtubeFragment = (YouTubePlayerFragment)
                            getFragmentManager().findFragmentById(R.id.youtubePlayerView);
                    youtubeFragment.initialize("AIzaSyAyTNILV-nLX7W2pHcTl2pgR0cDIo1DMHs",
                            new YouTubePlayer.OnInitializedListener() {
                                @Override
                                public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                                    YouTubePlayer youTubePlayer, boolean b) {
                                    // do any work here to cue video, play video, etc.
                                    youTubePlayer.cueVideo(newsss.get(position));
                                }
                                @Override
                                public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                                    YouTubeInitializationResult youTubeInitializationResult) {

                                }
                            });

                    return view;
                }
            }
            else {
                if (position == 0) {
                    view = getLayoutInflater().inflate(R.layout.imagescell, null);
                    ImageView pic = view.findViewById(R.id.pictures);
                    ImageLoader.getInstance().displayImage(newsss.get(position), pic);
                    return view;
                }
                if (position == 1) {
                    view = getLayoutInflater().inflate(R.layout.titletext, null);
                    TextView ttttt = view.findViewById(R.id.titleTexttt);
                    TextView date = view.findViewById(R.id.date);
                    date.setText(datee);
                    ttttt.setText(newsss.get(position));
                    return view;
                }
                if (position == 2) {
                    view = getLayoutInflater().inflate(R.layout.textcell, null);
                    TextView ttttt = view.findViewById(R.id.textdetailll);
                    ttttt.setText(newsss.get(position));
                    return view;
                }
                if (position < 3 + picturesss.size()) {
                    view = getLayoutInflater().inflate(R.layout.imagescell, null);
                    ImageView pic = view.findViewById(R.id.pictures);
                    ImageLoader.getInstance().displayImage(newsss.get(position), pic);
                    return view;
                } else {
                    view = getLayoutInflater().inflate(R.layout.videocell, null);
//AIzaSyAyTNILV-nLX7W2pHcTl2pgR0cDIo1DMHs

                    YouTubePlayerFragment youtubeFragment = (YouTubePlayerFragment)
                            getFragmentManager().findFragmentById(R.id.youtubePlayerView);
                    youtubeFragment.initialize("AIzaSyAyTNILV-nLX7W2pHcTl2pgR0cDIo1DMHs",
                            new YouTubePlayer.OnInitializedListener() {
                                @Override
                                public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                                    YouTubePlayer youTubePlayer, boolean b) {
                                    // do any work here to cue video, play video, etc.
                                    youTubePlayer.cueVideo(newsss.get(position));
                                }
                                @Override
                                public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                                    YouTubeInitializationResult youTubeInitializationResult) {

                                }
                            });


                    return view;
                }


            }
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.backmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_back:
                finish();
                break;
            // action with ID action_settings was selected

            default:
                break;
        }

        return true;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
@Override
public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
}

    }
//void play(final String code,View ooo){
//    Button buttonPlay = ooo.findViewById(R.id.buttonPlay);
//    final YouTubePlayerView youtubePlayerView = ooo.findViewById(R.id.youtubePlayerView);
//    buttonPlay.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            String videoId = code;
//            playVideo(videoId, youtubePlayerView);
//        }
//    });
//
//}










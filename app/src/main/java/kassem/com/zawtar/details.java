package kassem.com.zawtar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class details extends AppCompatActivity {
    ArrayList<String> picturess = new ArrayList<>();
    ArrayList<String> videossss = new ArrayList<>();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailscontroller);
        TextView detailstitle = (TextView) findViewById(R.id.textView2);
        TextView detailsDetails = (TextView) findViewById(R.id.textView3);
        Bundle b = getIntent().getExtras();
        String title = b.getString("title");
        String details = b.getString("details");
        String imagename = b.getString("imagename");
        ArrayList<String> ddd = b.getStringArrayList("pics");
        ArrayList<String> vvv = b.getStringArrayList("videos");
        if (ddd == null) {
            picturess.add(imagename);
        } else {
            picturess = ddd;
        }

        detailstitle.setText(title);
        detailsDetails.setText(details);

        ListView listimage = (ListView) findViewById(R.id.PICS);
        listimage.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
        picturesadapter aaa = new picturesadapter();
        listimage.setAdapter(aaa);
        String frameVideo = "<html><body>Video From YouTube<br><iframe width=\"420\" height=\"315\" src=\"https://www.youtube.com/embed/47yJ2XCRLZs\" frameborder=\"0\" allowfullscreen></iframe></body></html>";

        WebView vp=(WebView)findViewById(R.id.videolayout);
        vp.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = vp.getSettings();
        webSettings.setJavaScriptEnabled(true);
        vp.loadData(frameVideo, "text/html", "utf-8");
        vp.setWebViewClient();

    }

    class picturesadapter extends BaseAdapter {

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
            View view = getLayoutInflater().inflate(R.layout.imagescell, null);
            ImageView pic = view.findViewById(R.id.pictures);
            new DownloadImageTask((ImageView) pic)
                    .execute(picturess.get(position));

            return view;
        }
    }


}

package kassem.com.zawtar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;

public class phone extends AppCompatActivity {
    ArrayList<PhoneClass> numberbook=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_layout);
        final ListView Phonelist=(ListView)findViewById(R.id.Phone_List);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final KProgressHUD hud = KProgressHUD.create(phone.this)
                .setStyle(KProgressHUD.Style.ANNULAR_DETERMINATE)
                .setLabel("Please wait")
                .setMaxProgress(100)
                .show();


        final Task<QuerySnapshot> querySnapshotTask = db.collection("TypeOfCompany")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {


                                String namess = document.getData().get("nameOfCompany").toString();

                                String numberss = document.getData().get("numbOfCompany").toString();
                                numberbook.add(new PhoneClass(namess, numberss));
                                phoneadapter adapter = new phoneadapter();
                                Phonelist.setAdapter(adapter);
                                hud.dismiss();
                            }
                        }
                    }
                });

    }
    class phoneadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return numberbook.size();
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
            View view=getLayoutInflater().inflate(R.layout.custom_phone,null);
            TextView names=view.findViewById(R.id.Text_name);
            TextView numbers=view.findViewById(R.id.text_number);
            names.setText(numberbook.get(position).name);
           numbers.setText(numberbook.get(position).number);
            return view;
        }
    }
}

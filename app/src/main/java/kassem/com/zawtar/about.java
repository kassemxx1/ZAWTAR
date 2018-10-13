package kassem.com.zawtar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class about extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutpage);
        TextView ttt=(TextView)findViewById(R.id.abouttext);
        ttt.setText("هل لديك اي استسفار عن التطبيق ؟ هل تريد ان ترسل اي ملاحظة ,فكرة او اعتراض ؟\n" +
                "راسلنا عبر البريد او الهاتف .\n" +
                "أراءكم و ملاحظاتكم مهمة بالنسبة لنا .\n" +
                "الهاتف:0096170385816\n" +
                "البريد الالكتروني : abboudkassem1@gmail.com");
    }
}

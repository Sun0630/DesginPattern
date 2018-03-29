package tech.sx.day10_factory.sample2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import tech.sx.day10_factory.R;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //这样写的问题：如果想要迁移数据就比较麻烦了，
        mTextView = findViewById(R.id.text_tv);
        PreferencesUtils
                .getInstance()
                .putString("Name", "Sunxin")
                .putString("Age", "22")
                .commit();
    }


    public void click(View view) {
        String name = PreferencesUtils.getInstance().getString("Name");
        String age = PreferencesUtils.getInstance().getString("Age");

        mTextView.setText("Name-> " + name + " age -> " + age);
    }


}

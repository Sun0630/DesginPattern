package tech.sx.day10_factory.sample1;

import android.content.SharedPreferences;
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

        mTextView = findViewById(R.id.text_tv);

        SharedPreferences preferences = getSharedPreferences("Cache", MODE_PRIVATE);
        preferences
                .edit()
                .putString("Name", "Sunxin")
                .putString("Age", "22")
                .commit();
    }


    public void click(View view) {
        SharedPreferences preferences = getSharedPreferences("Cache", MODE_PRIVATE);
        String name = preferences.getString("Name", "");
        String age = preferences.getString("Age", "");


        mTextView.setText("Name-> " + name + " age -> " + age);
    }


}

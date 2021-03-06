package tech.sx.day10_factory.sample6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import tech.sx.day10_factory.R;
import tech.sx.day10_factory.sample5.IOHandler;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.text_tv);
        IOHandler memoryIoHandler = IOHandlerFactory.getInstance().getMemroyIOHandler();
        memoryIoHandler.save("Name", "HHHHHHHHH");
        memoryIoHandler.save("Age", "18");
    }


    public void click(View view) {
        IOHandler memoryIoHandler = IOHandlerFactory.getInstance().getMemroyIOHandler();
        String name = memoryIoHandler.getString("Name");
        String age = memoryIoHandler.getString("Age");

        mTextView.setText("Name-> " + name + " age -> " + age);
    }

}

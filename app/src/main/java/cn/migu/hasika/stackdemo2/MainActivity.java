package cn.migu.hasika.stackdemo2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    private static String TAG = "Stack";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StackDemo demo = new StackDemo();
//        9+(3-1)*3+8/2
//        9 3 1 - 3 *+ 8  2 / +
        int result = demo.getResultFromStr("9+(3-1)*3+8/2");
        Log.d(TAG, "result: "+result);
    }
}

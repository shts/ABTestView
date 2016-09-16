package jp.shts.android.abtestview.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import jp.shts.android.library.abtestview.ABTestView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("", "onCreate: ");

        ABTestView abTestViewEach = (ABTestView) findViewById(R.id.each);
        View inflatedEachView = abTestViewEach.inflate();
        if (abTestViewEach.getWhich() == ABTestView.Which.A && inflatedEachView != null) {
            TextView textView = (TextView) inflatedEachView.findViewById(R.id.a_title);
            textView.setText("Each A !!!!");

        } else if (abTestViewEach.getWhich() == ABTestView.Which.B && inflatedEachView != null) {
            TextView textView = (TextView) inflatedEachView.findViewById(R.id.b_title);
            textView.setText("Each B !!!!");
        }

        ABTestView abTestViewFix = (ABTestView) findViewById(R.id.fix);
        View inflatedFixView = abTestViewFix.inflate();
        if (abTestViewFix.getWhich() == ABTestView.Which.A && inflatedFixView != null) {
            TextView textView = (TextView) inflatedFixView.findViewById(R.id.a_title);
            textView.setText("Fix A !!!!");

        } else if (abTestViewFix.getWhich() == ABTestView.Which.B && inflatedFixView != null) {
            TextView textView = (TextView) inflatedFixView.findViewById(R.id.b_title);
            textView.setText("Fix B !!!!");
        }
    }
}

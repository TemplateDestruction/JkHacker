package com.breakout.myapplication;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    TextView homeAddress;
    TextView homeLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaints_layout);

        homeAddress = findViewById(R.id.home_address);
        homeLink = findViewById(R.id.home_site_link);

        ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("txt", homeAddress.getText().toString());
        clipboard.setPrimaryClip(clip);

        //paste
//        if (clip.getDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
//            System.out.println("DIIIIIICH");
//            System.out.println(clipboard.getPrimaryClip().getItemAt(0).getText().toString());
//        }

    }

    public void onCopyClick(View view) {
        ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        String string = getString(R.string.home_info) + "\n"
                + getString(R.string.home_address) + "\n" + homeAddress.getText().toString()
                + "\n" + getString(R.string.home_link) + "\n" + homeLink.getText().toString();
        ClipData clip = ClipData.newPlainText("txt", string);
        clipboard.setPrimaryClip(clip);

    }

    public void onBtnClick(View view) {
        switch (view.getId()) {
            case R.id.button_live_insp:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.live_inspection_link))));
                break;
            case R.id.button_live_comitee:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.live_comitee_link))));
                break;
            case R.id.button_gis:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.gis_zhkh_link))));
                break;
            case R.id.button_prokur:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.prokuratura_link))));
                break;
        }
    }
}

package beam.com.search.utils;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import beam.com.search.R;

public class ToolbarHelper {

    public ToolbarHelper() {

    }


    public void prepareToolbar(final AppCompatActivity activity, Toolbar toolbar) {

        if (toolbar != null) {
            activity.setSupportActionBar(toolbar);
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            activity.getSupportActionBar().setHomeButtonEnabled(true);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }

            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
            toolbar.setNavigationOnClickListener(v1 -> {
                activity.onBackPressed();
            });
            toolbar.invalidate();
        }


    }





}

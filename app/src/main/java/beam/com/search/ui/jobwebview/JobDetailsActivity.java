package beam.com.search.ui.jobwebview;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import beam.com.search.R;
import beam.com.search.utils.ToolbarHelper;

public class JobDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private WebView webView;
    private ProgressDialog progressDialog;
    public static final String KEY_LINK = "LINK";
    public static final String KEY_NAME = "NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        toolbar = findViewById(R.id.toolbar);
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        ToolbarHelper helper = new ToolbarHelper();
        helper.prepareToolbar(this, toolbar);
        toolbar.setTitle(getIntent().getStringExtra(KEY_NAME));

        startLoading();
        webView.loadUrl(getIntent().getStringExtra(KEY_LINK));
        webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                stopLoading();
            }
        });

    }


    public void startLoading() {
        progressDialog = ProgressDialog.show(this, null, null, false, false);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setContentView(R.layout.progressdialog);
        progressDialog.show();
    }

    public void stopLoading() {
        if (!isFinishing()) {
            if (progressDialog != null) {
                progressDialog.dismiss();
                progressDialog.cancel();
                progressDialog = null;
            }
        }
    }
}

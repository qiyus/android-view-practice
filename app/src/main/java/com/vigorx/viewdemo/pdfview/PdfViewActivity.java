package com.vigorx.viewdemo.pdfview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.vigorx.viewdemo.R;

import static java.lang.String.format;

public class PDFViewActivity extends AppCompatActivity implements OnPageChangeListener{
    private static final String PDF_NAME = "Architecting on AWS.pdf";
    private TextView mPageText;
    private PDFView mPdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);
        mPageText = (TextView) findViewById(R.id.page_text);
        mPdfView = (PDFView) findViewById(R.id.pdfView);
        mPdfView.fromAsset(PDF_NAME)
                .defaultPage(1)
                .onPageChange(this)
                .load();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        mPageText.setText(format("%s / %s", page, pageCount));
    }
}

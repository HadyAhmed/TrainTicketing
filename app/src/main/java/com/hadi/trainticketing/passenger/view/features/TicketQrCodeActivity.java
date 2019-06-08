package com.hadi.trainticketing.passenger.view.features;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.zxing.BarcodeFormat;
import com.hadi.trainticketing.R;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class TicketQrCodeActivity extends AppCompatActivity {

    public static final String RESERVATION_ID_EXTRA = "reservation_id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        Toolbar toolbar = findViewById(R.id.qrCodeToolbar);
        toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        String reservationId = getIntent().getStringExtra(RESERVATION_ID_EXTRA);

        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(reservationId, BarcodeFormat.QR_CODE, 700, 700);
            ImageView imageViewQrCode = findViewById(R.id.ticketQrCodeIV);
            imageViewQrCode.setImageBitmap(bitmap);
        } catch (Exception e) {
            Toast.makeText(this, "couldn't load ticket qr code", Toast.LENGTH_SHORT).show();
        }
    }
}

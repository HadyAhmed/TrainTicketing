package com.hadi.trainticketing.passenger.home.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.zxing.BarcodeFormat;
import com.hadi.trainticketing.R;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class TicketQrCodeFragment extends Fragment {

    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.fragment_qr_code, container, false);
        if (getArguments() != null) {
            try {
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.encodeBitmap(TicketQrCodeFragmentArgs.fromBundle(getArguments()).getReservationId(), BarcodeFormat.QR_CODE, 700, 700);
                ImageView imageViewQrCode = rootView.findViewById(R.id.ticketQrCodeIV);
                imageViewQrCode.setImageBitmap(bitmap);
            } catch (Exception e) {
                Toast.makeText(context, "couldn't load ticket qr code", Toast.LENGTH_SHORT).show();
            }
        }
        return rootView;
    }

}

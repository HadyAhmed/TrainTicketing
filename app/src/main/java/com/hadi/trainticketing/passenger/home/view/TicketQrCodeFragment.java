package com.hadi.trainticketing.passenger.home.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.zxing.BarcodeFormat;
import com.hadi.trainticketing.databinding.FragmentQrCodeBinding;
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
        FragmentQrCodeBinding qrCodeBinding = FragmentQrCodeBinding.inflate(inflater, container, false);

        if (getArguments() != null) {
            qrCodeBinding.setTicket(TicketQrCodeFragmentArgs.fromBundle(getArguments()).getTicket());
            try {
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.encodeBitmap(TicketQrCodeFragmentArgs.fromBundle(getArguments()).getReservationId(), BarcodeFormat.QR_CODE, 400, 400);
                qrCodeBinding.ticketQrCodeIV.setImageBitmap(bitmap);
            } catch (Exception e) {
                Toast.makeText(context, "couldn't load ticket qr code", Toast.LENGTH_SHORT).show();
            }
        }
        return qrCodeBinding.getRoot();
    }

}

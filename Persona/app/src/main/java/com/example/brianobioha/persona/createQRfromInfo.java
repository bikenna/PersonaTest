package com.example.brianobioha.persona;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;


/**
 * Created by brianobioha on 7/24/17.
 */


import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class createQRfromInfo extends Activity {

    public static final int white = 0xFFFFFFFF;
    public static final int black = 0xFF000000;
    public final static int WIDTH = 500;
    private Bitmap bitmap;

    public createQRfromInfo() {
        super();
    }

    private String text;
    private ImageView imageView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);
        imageView = (ImageView) findViewById(R.id.imageArea);
        button = (Button) findViewById(R.id.button_id);
        QRtext();
//        imageView.setImageBitmap(bitmap);
    }

    public void TextToImageEncode(String str) throws WriterException {
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, WIDTH, WIDTH, null);

        } catch (IllegalArgumentException e) {

            e.printStackTrace();
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        black : white;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        if(bitmap == null){
            Toast.makeText(createQRfromInfo.this, "Could not create QR", Toast.LENGTH_SHORT).show();
        }
        this.bitmap = bitmap;
    }


    public void QRtext() {
        final EditText edittext = (EditText) findViewById(R.id.editText);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Assert")
            public void onClick(View v) {
                text = edittext.getText().toString();
                try {
                    if(text.isEmpty())
                        Toast.makeText(createQRfromInfo.this, "Cannot have empty", Toast.LENGTH_SHORT).show();
                    TextToImageEncode(text);
                    imageView.setImageBitmap(bitmap);
                    createQRfromPicture brian = new createQRfromPicture(bitmap);
                    brian.readBitMap();
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Bitmap getBitmap() { return this.bitmap; }
    public void setBitmap(Bitmap bitmap) { this.bitmap = bitmap; }
}
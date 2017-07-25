//package com.example.brianobioha.persona;
//
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//import com.google.zxing.*;
//
//import android.graphics.Bitmap;
//import android.widget.ImageView;
//import android.widget.EditText;
//import android.view.View;
//import android.view.KeyEvent;
//import android.view.View.OnKeyListener;
//import android.widget.Toast;
//
//
//import com.google.zxing.common.BitMatrix;
//
//public class ScanQR extends Activity {
//
//    private static int white = 0xFFFFFFFF;
//    private static int black = 0xFF000000;
//    private final static int WIDTH=500;
//
//    public ScanQR(){ super(); }
//
//    String text;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        //super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_scan_qr);
//        ImageView imageView = (ImageView) findViewById(R.id.imageArea);
//        try {
//            Bitmap bitmap = TextToImageEncode(this.QRtext());
//            imageView.setImageBitmap(bitmap);
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
//
//    }
//    Bitmap TextToImageEncode(String str) throws WriterException {
//        BitMatrix result;
//        Bitmap bitmap = null;
//        try
//        {
//            result = new MultiFormatWriter().encode(str,
//                    BarcodeFormat.QR_CODE, WIDTH, WIDTH, null);
//
//            int w = result.getWidth();
//            int h = result.getHeight();
//            int[] pixels = new int[w * h];
//            for (int y = 0; y < h; y++) {
//                int offset = y * w;
//                for (int x = 0; x < w; x++) {
//                    pixels[offset + x] = result.get(x, y) ? black:white;
//                }
//            }
//            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
//            bitmap.setPixels(pixels, 0, 500, 0, 0, w, h);
//        } catch (Exception iae) {
//            iae.printStackTrace();
//            return null;
//        }
//        return bitmap;
//    }
//    public String QRtext() {
//        final EditText edittext = (EditText) findViewById(R.id.editText);
//        edittext.setOnKeyListener(new OnKeyListener() {
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//
//                if ((event.getAction() == KeyEvent.ACTION_DOWN)
//                        && (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                    Toast.makeText(ScanQR.this,
//                            edittext.getText(), Toast.LENGTH_LONG).show();
//                    text = edittext.getText().toString();
//                    return true;
//
//                }
//                return false;
//            }
//        });
//        return this.text;
//    }
//}

package cl.maskin.barcodedetect;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

/**
 * Created by abarza on 12/6/17.
 */

public class MainActivity extends AppCompatActivity {

  private TextView txtView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    txtView = findViewById(R.id.txtContent);


    Button btn = findViewById(R.id.button);
    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
      }
    });

    ImageView myImageView = findViewById(R.id.imgview);
    Bitmap myBitmap = BitmapFactory.decodeResource(
        getApplicationContext().getResources(),
        R.drawable.puppy);
    myImageView.setImageBitmap(myBitmap);

    BarcodeDetector detector =
        new BarcodeDetector.Builder(getApplicationContext())
            .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE)
            .build();
    if(!detector.isOperational()){
      txtView.setText("Could not set up the detector!");
      return;
    }

    Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
    SparseArray<Barcode> barcodes = detector.detect(frame);

    Barcode thisCode = barcodes.valueAt(0);
    txtView.setText(thisCode.rawValue);


  }


}

package com.locatemystickers.qrcode;

import android.graphics.Color;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import android.graphics.*;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;


public class GenerateQRCode {
    public GenerateQRCode() {}

    public Bitmap createQRImage(String qrCodeText, int size) throws WriterException, IOException
    {
        Hashtable hintMap = new Hashtable();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
        int matrixWidth = byteMatrix.getWidth();
        Bitmap image = Bitmap.createBitmap(matrixWidth, matrixWidth, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(image);
        Paint p1 = new Paint();
        p1.setColor(Color.WHITE);
        canvas.drawRect(0,0,matrixWidth,matrixWidth,p1);
        Paint p2 = new Paint();
        p2.setColor(Color.BLACK);
        for (int i=0;i<matrixWidth;i++)
        {
            for (int j=0;j<matrixWidth;j++)
            {
                if (byteMatrix.get(i, j))
                    canvas.drawRect(new RectF(i, j, i+1, j+1), p2);
                else
                    canvas.drawRect(new RectF(i, j, i+1, j+1), p1);
            }
        }
        canvas.save();
        return image;
    }

    private File saveBitmap(Bitmap bmp) {
        String extStorageDirectory = Environment.getExternalStorageDirectory()
                .toString();
        OutputStream outStream = null;

        File file = new File(bmp + ".png");
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, bmp + ".png");
            Log.e("file exist", "" + file + ",Bitmap= " + bmp);
        }
        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("file", "" + file);
        return file;
    }
}

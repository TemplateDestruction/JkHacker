package com.breakout.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SplashActivity extends AppCompatActivity {

    private Context myContext;
    private File DB_PATH;
    private String DB_NAME = "house";
    private SQLiteDatabase myDataBase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, SearchActivity.class));
//        startActivity(new Intent(this, SearchActivityRest.class));
        finish();
//        try {
//            createDataBase();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void createDataBase() throws IOException {
        //получаем путь к SD-карте.
        File DB_PATH = myContext.getExternalCacheDir();
        //создаём каталог для нашей базы данных

        DB_PATH.mkdirs();
        System.out.println("PATH: " + DB_PATH.getCanonicalPath());
        System.out.println("PATH: " + DB_PATH.getAbsolutePath());
        //проверяем есть ли уже файл БД на карте
        File db = new File(DB_PATH, DB_NAME);
        System.out.println("AAAAAAAAAAAAAAAAAAAAA");
        if (db.exists()) {
            System.out.println("BBBBBBBBBBBBBB");
        }
        if (!db.exists()) {
            //если файла нет, то попытаемся его создать
            db.createNewFile();
            System.out.println("AAAAAAAAAAAAAAAAAAAAA");

            try {
                copyFromZipFile();
            } catch (IOException e) {
                throw new Error("Error copying database", e);
            }
        }
    }

    private void copyFromZipFile() throws IOException {
        InputStream is = myContext.getResources().openRawResource(R.raw.house);
        File outFile = new File(DB_PATH, DB_NAME);
        OutputStream myOutput = new FileOutputStream(outFile.getAbsolutePath());
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(is));
        try {
            System.out.println("AAAAAAAAAAAAAAAAAAAAA");

            ZipEntry ze;
            while ((ze = zis.getNextEntry()) != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int count;

                while ((count = zis.read(buffer)) != -1) {
                    baos.write(buffer, 0, count);
                }
                baos.writeTo(myOutput);
            }
        } finally {
            zis.close();
            myOutput.flush();
            myOutput.close();
            is.close();
        }
    }

    public SQLiteDatabase openDataBase() throws SQLException {
        System.out.println("AAAAAAAAAAAAAAAAAAAAA");

        File DB_PATH = myContext.getExternalCacheDir();
        File dbFile = new File(DB_PATH, DB_NAME);
        myDataBase = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        return myDataBase;
    }

}



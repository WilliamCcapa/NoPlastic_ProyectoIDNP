
package com.example.noplastic.Fragments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.noplastic.InformacionPlasticos.ListPlasticos;
import com.example.noplastic.R;
import com.example.noplastic.db.DbHelper;

import java.util.ArrayList;


public class FragmentEstadisticas extends  Fragment{

    ImageView imageView;
    ImageView imageView1;
    Context context;
    //private Button button_home;

    public FragmentEstadisticas(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_estadisticas, container, false);
        int [] datos = new int[] {5,10,8,2};

        createCrafico(datos,view);
        return view;
    }

    public void createCrafico(int [] datos, View view){
        int y;
        y=resp(datos);
        //Creacion del grafico barras
        Bitmap bitmap=Bitmap.createBitmap(300,300,Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        Paint paint =new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(8);
        paint.setAntiAlias(true);
        imageView = view.findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);
        rect1(canvas,paint, datos,y);
        //Creacion del grafico leyenda
        Bitmap bitmap1=Bitmap.createBitmap(300,300,Bitmap.Config.ARGB_8888);
        Canvas canvas1=new Canvas(bitmap1);
        canvas1.drawColor(Color.WHITE);
        Paint paint1 =new Paint();
        paint1.setColor(Color.RED);
        paint1.setStyle(Paint.Style.FILL);
        paint1.setStrokeWidth(8);
        paint1.setAntiAlias(true);
        imageView1 = view.findViewById(R.id.imageView2);
        imageView1.setImageBitmap(bitmap1);


        ArrayList<ListPlasticos> listaPlasticos = new ArrayList<>();
        //rect1(canvas,paint, datos,y);
        color(canvas1,paint1,datos );
    }

    public void rect1(Canvas canvas, Paint paint, int [] datos, int y){
        int padding= 5;
        int[] colores = new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
        float rec = (canvas.getWidth()- padding * datos.length+1) / datos.length;

        for (int i=0; i < datos.length; i++){
            paint.setColor(colores[i]);
            canvas.drawRect(padding + (padding * i) + (rec * i),canvas.getHeight() - y * datos[i],padding + (padding * i) + rec * (i+1), canvas.getHeight(), paint);
        }
    }
    public int mayor(int[] datos){
        int mayor=datos[0];
        for(int x=1;x< datos.length;x++){
            if(datos[x]>mayor){
                mayor=datos[x];
            }
        }
        return mayor;
    }
    public int resp(int [] datos){
        int y;
        int m=mayor(datos);
        if(m<=10){y=m*2;
        }else if(m<=20){y=m/2;
        }else if(m<=50){y=m/10;
        }else {y=m/30;
        }
        return y;
    }
    public void color(Canvas canvas,Paint pincel1, int [] datos){
        int[] colores = new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
        int ancho= canvas.getWidth();
        for (int i=0; i<colores.length;i++){
            pincel1.setColor(colores[i]);
            canvas.drawRect(10,10+50*i,ancho-10,40+50*i,pincel1);
        }

    }
}
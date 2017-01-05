package com.example.pr_idi.mydatabaseexample;


import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pr_idi.mydatabaseexample.R;

import java.util.List;

import static com.example.pr_idi.mydatabaseexample.R.id.any;
import static com.example.pr_idi.mydatabaseexample.R.id.categ;
import static com.example.pr_idi.mydatabaseexample.R.id.publi;


/**
 * Created by User on 02/01/2017.
 */


public class AndroidBaseDatos extends Activity {

    private EditText txttitol;
    private EditText txtautor;
    private EditText txtpubli;
    private EditText txtany;
    private EditText txtcateg;
    private EditText txtevalua;


    private Button btnInsertar;
   // private Button btnActualizar;
    //private Button btnEliminar;

    //private SQLiteDatabase db;
    private BookData bookData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);

        bookData = new BookData(this);
        bookData.open();

        //Obtenemos las referencias a los controles
        txttitol = (EditText)findViewById(R.id.txttit);
        txtautor = (EditText)findViewById(R.id.txtaut);
        txtpubli = (EditText)findViewById(R.id.txtpubli);
        txtany = (EditText)findViewById(R.id.txtany);
        txtcateg = (EditText)findViewById(R.id.txtcateg);
        txtevalua = (EditText)findViewById(R.id.txtevalua);

        btnInsertar = (Button)findViewById(R.id.btnInsertar);


        btnInsertar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

        //Recuperamos los valores de los campos de texto
        String tit = txttitol.getText().toString();
        String nom = txtautor.getText().toString();
        String publi = txtpubli.getText().toString();
        String any = txtany.getText().toString();
        String categ = txtcateg.getText().toString();
        String evalua = txtevalua.getText().toString();

        System.out.println("titol: "+tit+" autor: "+nom+" publi: "+publi+" any: "+any+" categoria: "+categ+" evalua: "+evalua);

        Boolean trobat = false;

        List<Book> lb = bookData.getAllBooks();
        if(lb.size() > 0) {
            for (int i = 0; i < lb.size(); i++) {
                String t = lb.get(i).getTitle();
                String a = lb.get(i).getAuthor();
                String b = lb.get(i).getPublisher();
                int c = lb.get(i).getYear();
                String g = String.valueOf(c);
                String d = lb.get(i).getCategory();
                String z = lb.get(i).getPersonal_evaluation();
                if (t.equalsIgnoreCase(tit)) {
                    if (a.equalsIgnoreCase(nom)) {
                        trobat = true;
                    }
                }
            }
        }

        //Book e = new Book();

        if(!trobat) {
            if (tit.length() > 0) {
                if (nom.length() > 0) {
                    if (publi.length() > 0) {
                        if (any.length() > 0) {
                            if (categ.length() > 0) {
                                if (evalua.length() > 0) {
                                    float rate = Float.parseFloat(evalua);
                                    if (rate <= 10){
                                        if(rate >= 0){
                                            bookData.createBook(tit, nom, publi, any, categ, evalua);
                                            System.out.println("LLibreeeeeeeeeeeeeee" + "-> titol: " + tit + " ,autor: " + nom + " publi: " + publi + " any: " + any + " categoria: " + categ + " evalua: " + evalua);
                                            Toast.makeText(AndroidBaseDatos.this, "Afegit amb exit ", Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(AndroidBaseDatos.this, "L'avaluació és entre 0 i 10 ", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                    else{
                                        Toast.makeText(AndroidBaseDatos.this, "L'avaluació és entre 0 i 10 ", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Toast.makeText(AndroidBaseDatos.this, "Falten camps per omplir ", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(AndroidBaseDatos.this, "Falten camps per omplir ", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(AndroidBaseDatos.this, "Falten camps per omplir ", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(AndroidBaseDatos.this, "Falten camps per omplir ", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(AndroidBaseDatos.this, "Falten camps per omplir ", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(AndroidBaseDatos.this, "Falten camps per omplir ", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(AndroidBaseDatos.this, "El llibre ja existeix", Toast.LENGTH_SHORT).show();
        }

        //db.insert("Libros", null, nuevoRegistro);
        //System.out.println("book e: "+e.getTitle()+" "+e.getAuthor()+" "+e.getPublisher()+" "+e.getYear()+"  "+e.getCategory()+" "+e.getPersonal_evaluation()+" "+e.getId());
        //db.close();

        System.out.println("Ara veurem tots els llibres");
        lb = bookData.getAllBooks();
        for (int i = 0; i < lb.size(); i++){
            String t = lb.get(i).getTitle();
            String a = lb.get(i).getAuthor();
            String b = lb.get(i).getPublisher();
            int c = lb.get(i).getYear();
            String d = lb.get(i).getCategory();
            String z = lb.get(i).getPersonal_evaluation();
            System.out.println("LLibre"+i+"-> titol: "+t+" ,autor: "+a+" publi: "+b+" any: "+c+" categoria: "+d+" evalua: "+z);
        }




            }
        });

        /*
        btnActualizar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                //Recuperamos los valores de los campos de texto
                String tit = txttitol.getText().toString();
                String nom = txtautor.getText().toString();

                //Alternativa 1: método sqlExec()
                //String sql = "UPDATE Libros SET autor='" + nom + "' WHERE codigo=" + cod;
                //db.execSQL(sql);

                //Alternativa 2: método update()
                ContentValues valores = new ContentValues();
                valores.put("autor", nom);
                db.update("Libros", valores, "titol=" + tit, null);
            }
        });

        btnEliminar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                //Recuperamos los valores de los campos de texto
                String tit = txttitol.getText().toString();

                //Alternativa 1: método sqlExec()
                //String sql = "DELETE FROM Libros WHERE codigo=" + cod;
                //db.execSQL(sql);

                //Alternativa 2: método delete()
                    db.delete("Libros", "titol=" + tit, null);
            }
        });
        */
    }

   // @Override
    /*
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menubd, menu);
        return true;
    }
    */

}
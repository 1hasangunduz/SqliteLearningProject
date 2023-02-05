package com.example.sqlitelearningproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {

            val dataBase = this.openOrCreateDatabase("Products", Context.MODE_PRIVATE, null)
            dataBase.execSQL("CREATE TABLE IF NOT EXISTS products (id INTEGER PRIMARY KEY,name VARCHAR, price INT ) ")
            //dataBase.execSQL("INSERT INTO products (name,price) VALUES ('Shoes',100)")
//            dataBase.execSQL("INSERT INTO products (name,price) VALUES ('Casual Sneakers',120)")
//            dataBase.execSQL("INSERT INTO products (name,price) VALUES ('Socks',10)")
//            dataBase.execSQL("INSERT INTO products (name,price) VALUES ('Chair',250)")
//            dataBase.execSQL("INSERT INTO products (name,price) VALUES ('Phone',150)")
//            dataBase.execSQL("INSERT INTO products (name,price) VALUES ('Charger',25)")

            //dataBase.execSQL("DELETE FROM products WHERE id =5 ")

            dataBase.execSQL("UPDATE products SET price = 269 WHERE name ='Shoes' ")

            val cursor = dataBase.rawQuery("SELECT * FROM products WHERE name LIKE 'S%'",null)

            //val cursor = dataBase.rawQuery("SELECT * FROM products WHERE name LIKE 'S%'",null)
            //val cursor = dataBase.rawQuery("SELECT * FROM products WHERE id = 3 ",null)
             //val cursor = dataBase.rawQuery("SELECT * FROM products WHERE name = 'Casual Sneakers' ",null)
            //row query en basit hali (WHERE : nerede demek , LIKE : şunun gibi olanları getir bana A% ile baslayacak sonu önemli değil, %s sonu s ile bitenleri getirir..)
            //val cursor = dataBase.rawQuery("SELECT * FROM products",null)

            val idColumnIndex = cursor.getColumnIndex("id")
            val nameColumnIndex = cursor.getColumnIndex("name")
            val  priceColumnIndex = cursor.getColumnIndex("price")

            //rowlar bitene kadar bu işlemleri yap demek.
            while (cursor.moveToNext()){
            println("ID: ${cursor.getInt(idColumnIndex)}")
            println("NAME : ${cursor.getString(nameColumnIndex)}")
            println("PRICE : ${cursor.getInt(priceColumnIndex)}")
            }
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

}
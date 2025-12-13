package iss.nus.edu.sg.workshop.imagethread

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.net.URL
import java.util.UUID

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val btn=findViewById<Button>(R.id.fetch)
        val imageView=findViewById<ImageView>(R.id.image_view)

        btn.setOnClickListener {
            val url=findViewById<EditText>(R.id.url).text.toString()
            val file= createFile(url)
            Thread{
                android.util.Log.d("IMAGE", "Thread started")
                if(downloadImage(url, file)){
                    runOnUiThread{
                        val bitmap= BitmapFactory.decodeFile(file.absolutePath)
                        if (bitmap == null) {
                            android.util.Log.e("IMAGE", "Bitmap decode failed")
                        } else {
                            imageView.setImageBitmap(bitmap)
                        }
                    }
                }else{
                    android.util.Log.e("IMAGE", "Download failed")
                }
            }.start()
        }
    }

    fun downloadImage(url: String, file: File): Boolean{
        try {
            URL(url).openStream().use { input ->
                file.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
            return true
        }catch(_: Exception){
            return false
        }
    }
/*
    fun createFile(url: String): File {
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val filename = UUID.randomUUID().toString() + ".jpeg"
        return File(dir, filename)
    }

 */
private fun createFile(url: String) : File {
    var ext = ""
    val pos = url.lastIndexOf(".")
    if (pos != -1) {
        ext = url.substring(pos)
    }

    val filename = UUID.randomUUID().toString() + ext
    val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

    return File(dir, filename)
}

}
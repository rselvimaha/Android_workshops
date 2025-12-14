package iss.nus.edu.sg.workshop.counttomax

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import iss.nus.edu.sg.workshop.counttomax.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var bgThread: Thread? = null
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        initbutton()
    }

    fun initbutton() {
        binding.btnStart.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_start) {
            if (bgThread == null) {
                bgThread = startCompute()
                bgThread?.start()
                updateUI("Computing", "Stop")
            } else {
                stopCompute()
                updateUI("", "Start")
            }
        }
    }

    fun startCompute(): Thread {
        return Thread {
            var sum = 0L

            for (i in 0 until 1000000000) {
                sum += i

                if (Thread.interrupted()) {
                    bgThread=null
                    break
                }
            }
            if(bgThread!=null){
                bgThread=null
                runOnUiThread {
                    updateUI(sum.toString(), "Start")
                }
            }
            else{
                runOnUiThread { updateUI("", "Start") }
            }
        }
    }

    fun updateUI(result_text: String, btn_text: String){
       binding.result.setText(result_text)

        binding.btnStart.text=btn_text
    }

    private fun stopCompute() {
        bgThread?.interrupt()
    }
}
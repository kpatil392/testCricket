package com.halil.ozel.unsplashexample.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cricket.test.model.CricketResp
import com.google.gson.Gson
import com.halil.ozel.unsplashexample.MatchDetailActivity
import com.halil.ozel.unsplashexample.databinding.ActivityMainBinding
import com.halil.ozel.unsplashexample.ui.viewmodel.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ImageViewModel by viewModels()
    lateinit var strTeamA:String
    lateinit var strTeamB:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView()
        getDataResp();

    }
    private fun setView() {

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvTeama.setOnClickListener {
            Toast.makeText(this@MainActivity,strTeamA, Toast.LENGTH_LONG).show()
            startActivity(Intent(this@MainActivity, MatchDetailActivity::class.java).putExtra("Team",strTeamA))
        }
        binding.tvTeamb.setOnClickListener {
            //Toast.makeText(this@MainActivity,strTeamB, Toast.LENGTH_LONG).show()
            startActivity(Intent(this@MainActivity, MatchDetailActivity::class.java).putExtra("Team",strTeamB))
        }
    }
    fun getDataResp() {
        viewModel.mCricketDataStr.observe(this) {
            val hashMap=(getPlayer(getData(it.string().toString(), "Teams")))

           /// val mdetail=Gson().toJson(it.string().toString(),CricketResp::class.java)
//binding.tvVanue.setText(mdetail.toString())
            hashMap.forEach {
                val keyByIndex0 = hashMap.keys.elementAt(0) // Get key.
                val valueOfElement0 = hashMap.getValue(keyByIndex0) // Get Value.
                val keyByIndex1 = hashMap.keys.elementAt(1) // Get key by index.
                val valueOfElement1 = hashMap.getValue(keyByIndex1) // Get value.

                val nf1 = JSONObject(valueOfElement0).getString("Name_Full")
                val ns1 = JSONObject(valueOfElement0).getString("Name_Short")
                val nf2 = JSONObject(valueOfElement1).getString("Name_Full")
                val ns2 = JSONObject(valueOfElement1).getString("Name_Short")

                binding.tvTeama.setText("${nf1} (${ns1})")
                binding.tvTeamb.setText("${nf2} (${ns2})")
                strTeamA=valueOfElement0
                strTeamB=valueOfElement1


            }


        }

    }
    private fun getData(str: String, ss: String): String {
        val obj = JSONObject(str)
        val keys: Iterator<*> = obj.keys()
        while (keys.hasNext()) {
            val key = keys.next() as String
            if (key.equals(ss)) {
                val value: String = obj.getString(key) //This is where the error comes
                return value
                break
            }
        }
        return ""

    }

    private fun getPlayer(str: String): HashMap<String, String> {
        val hmap: HashMap<String, String> = HashMap()
        val obj = JSONObject(str)
        val keys: Iterator<*> = obj.keys()
        while (keys.hasNext()) {
            val key = keys.next() as String
            val value: String = obj.getString(key) //This is where the error comes
            val str = getData(value, "Players")
            hmap.put(key, value)
        }
        return hmap
    }
}
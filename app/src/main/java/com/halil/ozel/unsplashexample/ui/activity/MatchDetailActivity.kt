package com.halil.ozel.unsplashexample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.halil.ozel.unsplashexample.model.model.PlayerInfo
import com.halil.ozel.unsplashexample.databinding.ActivityMatchDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject


@AndroidEntryPoint
class MatchDetailActivity : AppCompatActivity() {
    private lateinit var list: ArrayList<PlayerInfo>
    private lateinit var playerAdapter: PlayerAdapter
    private lateinit var binding: ActivityMatchDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView()
    }
    private fun setView() {
        binding = ActivityMatchDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val ss:String = intent.getStringExtra("Team").toString()
       binding.tvData.visibility=View.GONE

            list=setPlayerInner(getData(ss,"Players"))


        binding.rvData.layoutManager=LinearLayoutManager(this)
        playerAdapter= PlayerAdapter(list)
        binding.rvData.adapter=playerAdapter

    }

    private fun setPlayerInner(str: String):ArrayList<PlayerInfo> {
        list = ArrayList()
        val obj = JSONObject(str)
        val keys: Iterator<*> = obj.keys()
        while (keys.hasNext()) {
            val key = keys.next() as String
            val value: String = obj.getString(key)
            list.add(Gson().fromJson(value, PlayerInfo::class.java))
        }
        return list;
    }
    private fun getData(str: String, ss: String): String {
        val obj = JSONObject(str)
        val keys: Iterator<*> = obj.keys()
        while (keys.hasNext()) {
            val key = keys.next() as String
            if (key.equals(ss)) {
                val value: String = obj.getString(key) //This is where the error comes
                return value
            }
        }
        return ""

    }
}
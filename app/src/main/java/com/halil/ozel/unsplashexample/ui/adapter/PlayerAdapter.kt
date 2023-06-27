package com.halil.ozel.unsplashexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.halil.ozel.unsplashexample.databinding.NoteMainBinding
import com.halil.ozel.unsplashexample.model.model.PlayerInfo


class PlayerAdapter(private val paymentList: List<PlayerInfo>) : RecyclerView.Adapter<PlayerAdapter.PaymentHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentHolder {
        val itemBinding = NoteMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaymentHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PaymentHolder, position: Int) {
        val paymentBean: PlayerInfo = paymentList[position]
        holder.bind(paymentBean)
    }

    override fun getItemCount(): Int = paymentList.size

    class PaymentHolder(private val itemBinding: NoteMainBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(paymentBean: PlayerInfo) {
            if(paymentBean.Iscaptain && paymentBean.Iskeeper ){
                itemBinding.tvPlayerName.setText("${paymentBean.Name_Full} (c & wk)")

            } else if(paymentBean.Iskeeper){
                itemBinding.tvPlayerName.setText("${paymentBean.Name_Full} (c)")

            } else if(paymentBean.Iscaptain){
                itemBinding.tvPlayerName.setText("${paymentBean.Name_Full} (wk)")

            }else
            {
                itemBinding.tvPlayerName.setText("${paymentBean.Name_Full} ${paymentBean.Iscaptain}")

            }
            if(paymentBean.Bowling.Style.equals("OB"))
            {
                itemBinding.tvPlayerInfo.setText("${paymentBean.Batting.Style} and Run ${paymentBean.Batting.Runs} and Strike rate is ${paymentBean.Batting.Strikerate} and average ${paymentBean.Batting.Average}  ")

            }else  if(!paymentBean.Bowling.Style.equals("OB")){

                itemBinding.tvPlayerInfo.setText("${paymentBean.Bowling.Style} Wicket ${paymentBean.Bowling.Wickets} and average ${paymentBean.Bowling.Average}  ")

            }

            itemBinding.tvPlayerName.setText("${paymentBean.Name_Full} ${paymentBean.Iscaptain}")
            itemBinding.tvPlayerName.setOnClickListener {
                if(itemBinding.linTop.visibility==View.GONE)
                {
                    itemBinding.linTop.visibility=View.VISIBLE
                }else{
                    itemBinding.linTop.visibility=View.GONE

                }
            }
        }
    }
}
package sharlene.work.basicbankingapp.model

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sharlene.work.basicbankingapp.R

public class TransactionAdapter(private val context: Context, var historyData: List<Data>) :
    RecyclerView.Adapter<TransactionAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TransactionAdapter.MyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.history_activity, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = historyData[position].sender
        holder.reciever.text = historyData[position].reciever
        holder.transferAmt.text = historyData[position].balance.toString()
        holder.transferstatus.text = historyData[position].status.toString()
        if (historyData[position].status == "FAILED") {
            holder.transferstatus.setTextColor(Color.parseColor("#FF0000"))
        }
    }

    override fun getItemCount(): Int {
        return historyData.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView=itemView.findViewById(R.id.sender_name_edit)
        var reciever: TextView=itemView.findViewById(R.id.reciever_name_edit)
        var transferAmt: TextView=itemView.findViewById(R.id.transfer_text_edit)
        var transferstatus: TextView=itemView.findViewById(R.id.status_edit)
    }
}
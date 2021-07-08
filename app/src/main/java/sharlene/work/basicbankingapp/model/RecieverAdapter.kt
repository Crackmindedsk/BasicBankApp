package sharlene.work.basicbankingapp.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sharlene.work.basicbankingapp.R
import sharlene.work.basicbankingapp.RecieverActivity

class RecieverAdapter(
    var recieverlist:RecieverActivity,
    var modelList:List<Data>,
    private val listener:RecyclerviewClickListener
    ):RecyclerView.Adapter<RecieverAdapter.myViewHolder>(){
    private val context: Context?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.list_item_layout_activity,parent,false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.text.text=modelList.get(position).name.toString()
        holder.email.text=modelList.get(position).email.toString()
        holder.balance.text=modelList.get(position).balance.toString()
    }

    override fun getItemCount(): Int {
        return modelList.size
    }
    inner class myViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val text:TextView
        val email:TextView
        val balance:TextView
        init {
            text=itemView.findViewById(R.id.list_name_edit)
            email=itemView.findViewById(R.id.list_email_edit)
            balance=itemView.findViewById(R.id.list_balance_edit)
            itemView.setOnClickListener(View.OnClickListener {
                recieverlist.selectUser(adapterPosition)
            })
        }

    }
    interface RecyclerviewClickListener{
        fun onClick(view:View?,position:Int)
    }

    }
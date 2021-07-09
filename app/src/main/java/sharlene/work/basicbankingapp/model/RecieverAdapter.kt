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
    var recieverlist: RecieverActivity,
    var modelList: List<Data>,
    private val listener: RecyclerviewClickListener
) : RecyclerView.Adapter<RecieverAdapter.MyViewHolder>() {
    private val context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_layout_activity, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.text.text = modelList[position].name.toString()
        holder.email.text = modelList[position].email.toString()
        holder.balance.text = modelList[position].balance.toString()
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView
        val email: TextView
        val balance: TextView

        init {
            text = itemView.findViewById(R.id.list_name_edit) as TextView
            email = itemView.findViewById(R.id.list_email_edit) as TextView
            balance = itemView.findViewById(R.id.list_balance_edit) as TextView
            itemView.setOnClickListener(View.OnClickListener {
                recieverlist.selectUser(adapterPosition)
            })
        }

    }

    interface RecyclerviewClickListener {
        fun onClick(view: View?, position: Int)
    }
}
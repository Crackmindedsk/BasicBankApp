package sharlene.work.basicbankingapp.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sharlene.work.basicbankingapp.R

class BankAdapter(
    private val context: Context,
    var name: ArrayList<*>,
    var email: ArrayList<*>,
    var balance: ArrayList<*>,
    private val listener: RecyclerViewClickListener?
) : RecyclerView.Adapter<BankAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_layout_activity, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = name[position].toString()
        holder.sender.text = email[position].toString()
        holder.balance.text = balance[position].toString()
    }

    override fun getItemCount(): Int {
        return name.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var title: TextView
        var sender: TextView
        var balance: TextView
        override fun onClick(p0: View?) {
            listener?.onClick(itemView, adapterPosition)
        }

        init {
            title = itemView.findViewById<View>(R.id.list_name_edit) as TextView
            sender = itemView.findViewById<View>(R.id.list_email_edit) as TextView
            balance = itemView.findViewById<View>(R.id.list_balance_edit) as TextView
            itemView.setOnClickListener{
                v
            }
        }
    }

    interface RecyclerViewClickListener {
        fun onClick(v: View?, position: Int)
    }

}


package sharlene.work.basicbankingapp.model

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import sharlene.work.basicbankingapp.R

class BankAdapter(
    private val context:Context,
    var name:ArrayList<*>,
    var email:ArrayList<*>,
    var balance:ArrayList<*>,
    private val listener:RecyclerViewClickListener?
    ):RecyclerView.Adapter<Adapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.list_item_layout_activity)
        return My
    }

}


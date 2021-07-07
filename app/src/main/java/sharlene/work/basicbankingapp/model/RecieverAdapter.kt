package sharlene.work.basicbankingapp.model

import androidx.recyclerview.widget.RecyclerView
import sharlene.work.basicbankingapp.RecieverActivity

class RecieverAdapter(var recieverlist:RecieverActivity,
var modelList:List<Data>,
private val listener:RecyclerviewClickListener):RecyclerView.Adapter<RecieverAdapter.myViewHolder>{
}
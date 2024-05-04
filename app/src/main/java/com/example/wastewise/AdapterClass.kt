//package com.example.wastewise
//
//import android.content.Context
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import android.widget.Toast
//import androidx.recyclerview.widget.RecyclerView
//
//class AdapterClass(private val dataList: ArrayList<DataClass>) : RecyclerView.Adapter<AdapterClass.ViewHolderClass>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layer, parent, false)
//        return ViewHolderClass(itemView)
//    }
//
//    override fun getItemCount(): Int {
//        return dataList.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
//        val currentitem = dataList[position]
//        holder.rvImage.setImageResource(currentitem.dataImage)
//        holder.rvTitle.text = currentitem.datatitle
//
//        holder.itemView.setOnClickListener {
//            if (position != RecyclerView.NO_POSITION) {
//                // Get the context
//                val context: Context = holder.itemView.context
//
//                // Create an intent to start the NonDisposableActivity
//                val intent = Intent(context, NonDisposableActivity::class.java)
//
//                // Pass any necessary data to the NonDisposableActivity using extras in the intent
////                intent.putExtra("DATA_TITLE", currentitem.datatitle)
////                intent.putExtra("DATA_IMAGE", currentitem.dataImage)
//
//                // Start the NonDisposableActivity
//                context.startActivity(intent)
//            }
//        }
//    }
//
//    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val rvImage: ImageView = itemView.findViewById(R.id.itemImage)
//        val rvTitle: TextView = itemView.findViewById(R.id.title)
//    }
//}
package com.example.wastewise

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterClass(private val context: Context, private val routesList: List<Route>) : RecyclerView.Adapter<AdapterClass.ViewHolderClass>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layer, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun getItemCount(): Int {
        return routesList.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentRoute = routesList[position]
        holder.rvTitle.text = currentRoute.routeName

        holder.itemView.setOnClickListener {
            // Create an intent to start the NonDisposableActivity
            val intent = Intent(context, NonDisposableActivity::class.java)

            // Pass route ID or any other necessary data to the NonDisposableActivity using extras in the intent
            intent.putExtra("ROUTE_ID", currentRoute.id)

            // Start the NonDisposableActivity
            context.startActivity(intent)
        }
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvTitle: TextView = itemView.findViewById(R.id.title)
    }
}

package com.example.testing_bottom_sheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_dialog.*
import kotlinx.android.synthetic.main.bottom_sheet_dialog_item.view.*

const val ARG_TITLE = "title"

class ItemListDialogFragment : BottomSheetDialogFragment() {

    val names: List<String> =
        listOf("Finalizar Inspección", "Sincronizar Inspección", "Eliminar Inspección")

    val icons: List<Int> =
        listOf(R.drawable.ic_stop_24dp, R.drawable.ic_sync_24dp, R.drawable.ic_delete_24dp)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        inspectionNameTextView.text = arguments?.getString(ARG_TITLE)
        bottomSheetList.layoutManager = LinearLayoutManager(context)
        bottomSheetList.adapter = ItemAdapter(names.size)
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    private inner class ViewHolder internal constructor(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.bottom_sheet_dialog_item,
            parent,
            false
        )
    ) {

        internal val text: TextView = itemView.bottomSheetItemText
    }

    private inner class ItemAdapter internal constructor(private val mItemCount: Int) :
        RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context), parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            with(holder.text) {
                text = names[position]
                setCompoundDrawablesWithIntrinsicBounds(
                    icons[position], 0, 0, 0
                )
                compoundDrawablePadding = 16
                setOnClickListener {
                    Log.i("JAPM", names[position])
                }
            }
        }

        override fun getItemCount(): Int {
            return mItemCount
        }
    }

    companion object {
        fun newInstance(title: String): ItemListDialogFragment =
            ItemListDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                }
            }

    }
}

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

class ItemListDialogFragment : BottomSheetDialogFragment() {

    private lateinit var names: List<String>
    private lateinit var icons: List<Int>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        names = listOf(
            getString(R.string.finish_inspection_label),
            getString(R.string.sync_inspection_label),
            getString(R.string.delete_inspection_label)
        )

        icons = listOf(R.drawable.ic_stop_24dp, R.drawable.ic_sync_24dp, R.drawable.ic_delete_24dp)

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
                    Log.i(TAG, names[position])
                }
            }
        }

        override fun getItemCount(): Int {
            return mItemCount
        }
    }

    companion object {
        const val ARG_TITLE = "title"
        const val TAG = "ItemListDialogFragment"
        fun newInstance(title: String): ItemListDialogFragment =
            ItemListDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                }
            }

    }
}

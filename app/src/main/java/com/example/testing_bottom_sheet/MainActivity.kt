package com.example.testing_bottom_sheet

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottom_sheet.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomSheetBehavior =
            BottomSheetBehavior.from(bottom_sheet_layout)

        val btnBottomSheet = findViewById<Button>(R.id.btnBottomSheet)
        val btnBottomSheetDialog = findViewById<Button>(R.id.btnBottomSheetDialog)
        val btnBottomSheetDialogFragment = findViewById<Button>(R.id.btnBottomSheetDialogFragment)

        btnBottomSheet.setOnClickListener {
            if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
            }
        }

        btnBottomSheetDialog.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.fragment_bottom_sheet_dialog, null)
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(view)
            dialog.show()
        }

        btnBottomSheetDialogFragment.setOnClickListener {
            ItemListDialogFragment.newInstance(3)
                .show(supportFragmentManager, "dialog")
        }

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // React to state change
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        Log.i("JAPM", "STATE_EXPANDED")
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        Log.i("JAPM", "STATE_COLLAPSED")
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // React to dragging events
            }
        })

    }

}


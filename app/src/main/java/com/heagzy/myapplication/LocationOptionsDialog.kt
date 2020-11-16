package com.heagzy.myapplication

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog

object DepartmentsDialog {

    var dialog: BottomSheetDialog? = null
    var context: Context? = null
    var btnCloseDialog: View? = null
    var tvChooseHr: TextView? = null
    var tvChooseSoftware: TextView? = null
    var tvChooseFinance: TextView? = null

    private lateinit var mListener: LocationsOtionsListener

    fun showDialog(context: Context, listener: LocationsOtionsListener) {
        dialog = BottomSheetDialog(context)
        this.context = context
        dialog?.window?.setGravity(Gravity.CENTER)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(R.layout.locatons_options_dialog)
        dialog?.setCancelable(true)

        mListener = listener

        btnCloseDialog = dialog?.findViewById(R.id.close_dialog)
        tvChooseHr = dialog?.findViewById(R.id.tv_dept_hr)
        tvChooseSoftware = dialog?.findViewById(R.id.tv_dept_sw)
        tvChooseFinance = dialog?.findViewById(R.id.tv_dept_finance)

        btnCloseDialog?.setOnClickListener {
            dialog?.dismiss()
//            listener.onChooseFavsList()
        }

        tvChooseHr?.setOnClickListener {
            dialog?.dismiss()
            listener.onChooseDept(tvChooseHr?.text.toString())
        }

        tvChooseSoftware?.setOnClickListener {
            dialog?.dismiss()
            listener.onChooseDept(tvChooseSoftware?.text.toString())
        }

        tvChooseFinance?.setOnClickListener {
            dialog?.dismiss()
            listener.onChooseDept(tvChooseFinance?.text.toString())
        }

        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialog?.show()
    }


    interface LocationsOtionsListener {
        //        fun onChooseFavsList()
        fun onChooseDept(type: String)
    }
}
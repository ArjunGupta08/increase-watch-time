package com.okstore.increasewatchtimeto4000hr.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.okstore.increasewatchtimeto4000hr.R

    fun showProgressDialog(context: Context) : Dialog {

        val dialog = Dialog(context)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_progress_loader)

        val image = dialog.findViewById<ImageView>(R.id.imageview)

        Glide.with(context).load(R.drawable.animated_three_dot).into(image)
        dialog.show()
        return dialog
    }

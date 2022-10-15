package com.example.loginapplication.DialogAlert

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.lang.ClassCastException

class confimationAlertDialog : DialogFragment() {

    internal lateinit var listenerDialog : listenerAlertdialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder = AlertDialog.Builder(it)
            builder.setMessage("descripcion")
                .setPositiveButton("OK"
                ) { _, _ ->
                    listenerDialog.onDialogPositiveClick(this)
                }.setNegativeButton("Cancel"
                ) { _, _ ->
                    listenerDialog.ondialogNegativeclick(this)
                }
            builder.create()
        }?:throw IllegalStateException("Activity can not be null")
    }



    interface listenerAlertdialog{
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun ondialogNegativeclick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listenerDialog = context as listenerAlertdialog
        } catch (e: ClassCastException){
            throw ClassCastException((context.toString() + "implementar confirmationAlertDialog"))
        }
    }
}
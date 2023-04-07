package com.example.qihangcooperation.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.example.qihangcooperation.R
import com.example.qihangcooperation.dialog.callbacks.JoinProjectCallback

class JoinProjectDialog(private val context: Context, private val callback: JoinProjectCallback): Dialog(context) {
    private lateinit var joinProjectButton: Button
    private lateinit var projectInvitationCodeEV: EditText

    init {
        setContentView(R.layout.join_project_layout)
        setCanceledOnTouchOutside(true)
        setAnimationsBottomUp()
        initDialogSize()
    }

    private fun initDialogSize(){
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(window?.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.attributes = lp
    }

    private fun setAnimationsBottomUp() {
        window?.setWindowAnimations(R.style.dialogAnimBottomUp)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        joinProjectButton = findViewById(R.id.join_into_project_button)
        projectInvitationCodeEV = findViewById(R.id.join_project_invitation_code)

        initButtonClickListener()
    }

    private fun initButtonClickListener(){
        joinProjectButton.setOnClickListener {
            callback.joinProject(projectInvitationCodeEV.text.toString())
        }
    }
}
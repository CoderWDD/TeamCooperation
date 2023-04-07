package com.example.qihangcooperation.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.qihangcooperation.R
import com.example.qihangcooperation.dialog.callbacks.JoinProjectCallback

class JoinProjectDialog(private val context: Context, private val callback: JoinProjectCallback): Dialog(context) {
    private lateinit var joinProjectButton: Button
    private lateinit var projectInvitationCodeEV: EditText

    init {
        setContentView(R.layout.add_project_dialog)
        setCanceledOnTouchOutside(true)
        setAnimationsBottomUp()
    }

    private fun setAnimationsBottomUp() {
        window?.setWindowAnimations(R.style.dialogAnimBottomUp)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        joinProjectButton = findViewById(R.id.join_to_project_button)
        projectInvitationCodeEV = findViewById(R.id.join_project_invitation_code)

        initButtonClickListener()
    }

    private fun initButtonClickListener(){
        joinProjectButton.setOnClickListener {
            callback.joinProject(projectInvitationCodeEV.text.toString())
        }
    }
}
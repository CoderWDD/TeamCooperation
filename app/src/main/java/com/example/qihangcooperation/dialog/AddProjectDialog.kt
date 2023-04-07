package com.example.qihangcooperation.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.example.qihangcooperation.R
import com.example.qihangcooperation.dialog.callbacks.AddProjectCallback

class AddProjectDialog(private val context: Context, private val callback: AddProjectCallback): Dialog(context) {
    private lateinit var createProjectButton: Button
    private lateinit var projectNameEV: EditText
    private lateinit var projectDescEV: EditText

    init {
        setContentView(R.layout.add_project_dialog)
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
        createProjectButton = findViewById(R.id.add_project_btn_create)
        projectNameEV = findViewById(R.id.add_project_et_project_name)
        projectDescEV = findViewById(R.id.add_project_et_project_description)

        initButtonClickListener()
    }

    private fun initButtonClickListener(){
        createProjectButton.setOnClickListener {
            callback.addProject(projectNameEV.text.toString(), projectDescEV.text.toString())
        }
    }
}
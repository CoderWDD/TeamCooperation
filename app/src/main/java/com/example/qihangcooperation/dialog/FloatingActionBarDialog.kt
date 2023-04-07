package com.example.qihangcooperation.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import com.example.qihangcooperation.R
import com.example.qihangcooperation.dialog.callbacks.AddProjectCallback
import com.example.qihangcooperation.dialog.callbacks.JoinProjectCallback

class FloatingActionBarDialog(private val context: Context, private val addProjectCallback: AddProjectCallback, private val joinProjectCallback: JoinProjectCallback): Dialog(context) {
    private lateinit var addProjectButton: Button
    private lateinit var joinProjectButton: Button
    init {
        setContentView(R.layout.floating_button_dialog_layout)
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
        addProjectButton = findViewById(R.id.add_project_button)
        joinProjectButton = findViewById(R.id.join_to_project_button)

        initButtonClickListener()
    }

    private fun initButtonClickListener(){
        addProjectButton.setOnClickListener {
            // jump to the add project page
            AddProjectDialog(context, addProjectCallback).show()
        }

        joinProjectButton.setOnClickListener {
            JoinProjectDialog(context, joinProjectCallback).show()
        }
    }
}
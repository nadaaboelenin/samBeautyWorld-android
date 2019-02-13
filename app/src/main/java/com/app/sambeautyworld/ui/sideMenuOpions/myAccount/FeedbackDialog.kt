package com.app.sambeautyworld.ui.sideMenuOpions.myAccount

import android.content.DialogInterface
import android.support.design.widget.TextInputEditText
import android.view.View
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.design.textInputEditText
import org.jetbrains.anko.design.textInputLayout

class FeedbackDialog(ui: AnkoContext<View>) {

    lateinit var dialog: DialogInterface
    lateinit var feedbackText: TextInputEditText
    lateinit var cancelButton: TextView
    lateinit var okButton: TextView

    init {
        with(ui) {
            dialog = alert {
                customView {
                    verticalLayout {
                        padding = dip(16)
                        textView("Enter text") { }
                        textView("Long message here") { }
                        okButton {

                        }
                        cancelButton {

                        }

                        textInputLayout {
                            hint = "hint here"
                            feedbackText = textInputEditText {
                                textSize = 16f
                            }
                        }
                    }
                }
            }.show()
        }
    }
}
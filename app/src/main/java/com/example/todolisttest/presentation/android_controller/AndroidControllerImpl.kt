package com.example.todolisttest.presentation.android_controller

import android.os.Process

class AndroidControllerImpl : IAndroidController {
    override fun exitApp() {
        val pid = Process.myPid()
        Process.killProcess(pid)
    }
}

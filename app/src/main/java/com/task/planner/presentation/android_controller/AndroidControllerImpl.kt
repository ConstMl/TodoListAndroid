package com.task.planner.presentation.android_controller

import android.os.Process

class AndroidControllerImpl : IAndroidController {
    override fun exitApp() {
        val pid = Process.myPid()
        Process.killProcess(pid)
    }
}

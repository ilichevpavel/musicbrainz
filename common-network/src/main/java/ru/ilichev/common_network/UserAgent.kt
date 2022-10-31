package ru.ilichev.common_network

import android.os.Build

internal object UserAgent {

    private const val FORMAT = "%s/%s"
    private const val APP_NAME = "dice_fm_test"
    private val VERSION = Build.VERSION.RELEASE

    val value: String = FORMAT.format(APP_NAME, VERSION)
}
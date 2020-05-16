package com.snooper.www.mars.util

import java.io.File

object FileUtil {
    fun readFile(fileName: String): List<String>
            = File(fileName).useLines { it.toList() }
}
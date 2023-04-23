package com.droidista.app

import com.droidista.katalyst.environment.Environment
import com.droidista.katalyst.environment.clean
import com.droidista.katalyst.environment.copyStaticAssets
import java.io.File

fun main(args: Array<String>) {
    val workingDirectory = System.getProperty("user.dir")
    println("Working Dir: $workingDirectory")
    val env = Environment(
        baseDir = File(workingDirectory,"static"),
        outputDir = File(workingDirectory,"dist"),
    )
    clean(env)
    copyStaticAssets(env)
    buildIndexPage(env)
}
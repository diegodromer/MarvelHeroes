package com.diegolima.marvelheroes.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*

fun Date.getCurrentDate(pattern: String) =
    SimpleDateFormat(pattern, Locale.getDefault()).format(this)

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}
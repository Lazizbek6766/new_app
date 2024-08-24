package com.megasoft.possystem.utils

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.OpenableColumns
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import okio.IOException
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

//fun downloadFile(context: Context, url: String, fileName: String) {
//    val client = OkHttpClient()
//    val request = Request.Builder().url(url).build()
//
//    client.newCall(request).enqueue(object : okhttp3.Callback {
//        override fun onFailure(call: okhttp3.Call, e: IOException) {
//            e.printStackTrace()
//        }
//
//        override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
//            if (response.isSuccessful) {
//                val body = response.body?.byteStream()
//                body?.let {
//                    saveFileToStorage(context, it, fileName)
//                }
//            }
//        }
//    })
//}
//
//private fun saveFileToStorage(context: Context, inputStream: InputStream, fileName: String) {
//    val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), fileName)
//    FileOutputStream(file).use { outputStream ->
//        inputStream.copyTo(outputStream)
//    }
//    // Foydalanuvchiga bildirishnoma yuboring yoki UI-ni yangilang
//}

fun formatDate(inputDate: String): String {
    // Sana formatlash uchun kerakli format
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMMM, yyyy", Locale("uz"))

    return try {
        // Sanani parsing qilish
        val date = inputFormat.parse(inputDate)
        // Sanani kerakli formatda qaytarish
        outputFormat.format(date)
    } catch (e: Exception) {
        // Xatolik yuz berganda, default xabar
        "Invalid date format"
    }
}

fun formattedNumber(number: Int):String {
    val numberFormat = NumberFormat.getInstance(Locale.FRANCE)
    numberFormat.isGroupingUsed = true
    val formattedNumber = numberFormat.format(number)
    return formattedNumber
}
fun formattedNumber(number: Long):String {
    val numberFormat = NumberFormat.getInstance(Locale.FRANCE)
    numberFormat.isGroupingUsed = true
    val formattedNumber = numberFormat.format(number)
    return formattedNumber
}

fun formatDouble(value: Double): String {
    return if (value % 1.0 == 0.0) {
        value.toInt().toString()  // Butun son
    } else {
        String.format("%.2f", value).replace(",", ".")  // Kasr qismi bilan
    }
}

fun getFilePathFromUri(context: Context, uri: Uri): String {
    var filePath: String? = null
    context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
        val columnIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        cursor.moveToFirst()
        val fileName = cursor.getString(columnIndex)
        val file = File(context.cacheDir, fileName)
        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            file.outputStream().use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }
        filePath = file.absolutePath
    }
    return filePath ?: throw IllegalArgumentException("File path could not be found")
}

fun createMultipartBody(file: File): MultipartBody.Part {
    val requestFile = file.asRequestBody("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData("file", file.name, requestFile)
}



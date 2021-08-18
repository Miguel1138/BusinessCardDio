package com.miguelsantos.businesscard.utils

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.content.FileProvider.getUriForFile
import com.miguelsantos.businesscard.R
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class Image {
    companion object {

        fun share(context: Context, card: View) {
            val bitmap = getCardScreenshot(card)

            bitmap?.let { saveInMediaStore(context, it) }
        }

        private fun getCardScreenshot(view: View): Bitmap? {
            var screenshot: Bitmap? = null
            try {
                screenshot = Bitmap.createBitmap(
                    view.measuredWidth,
                    view.measuredHeight,
                    Bitmap.Config.ARGB_8888
                )
                val canvas = Canvas(screenshot)
                view.draw(canvas)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return screenshot
        }

        private fun saveInMediaStore(context: Context, bitmap: Bitmap) {
            val filename = "${System.currentTimeMillis()}.jpg"
            var fos: OutputStream? = null

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                context.contentResolver?.also { resolver ->
                    val contentValues = ContentValues().apply {
                        put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                        // Tipo de arquivo
                        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                        // Caminho para salvar a imagem
                        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                    }

                    var imageUri: Uri? =
                        resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                    fos = imageUri?.let {
                        shareIntent(context, imageUri)
                        resolver.openOutputStream(it)
                    }
                }
            } else {
                val imageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                val image = File(imageDir, filename)
                val imageUri: Uri =
                    getUriForFile(context, "com.miguelsantos.businesscard.fileprovider", image)
                shareIntent(context, imageUri)
                fos = FileOutputStream(image)
            }

            fos?.use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                Toast.makeText(context, "Imagem capturada com sucesso!", Toast.LENGTH_SHORT).show()
            }

        }

        private fun shareIntent(context: Context, imageUri: Uri) {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, imageUri)
                type = "image/jpeg"
            }

            context.startActivity(
                Intent.createChooser(
                    shareIntent,
                    context.resources.getText(R.string.label_share)
                )
            )
        }

    }
}
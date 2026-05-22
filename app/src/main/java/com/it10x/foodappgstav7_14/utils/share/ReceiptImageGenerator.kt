package com.it10x.foodappgstav7_14.utils.share

import android.content.Context
import android.graphics.*
import android.net.Uri
import androidx.core.content.FileProvider
import com.it10x.foodappgstav7_14.data.pos.entities.PosOrderMasterEntity
import java.io.File
import java.io.FileOutputStream

object ReceiptImageGenerator {

    fun generateReceiptImage(
        context: Context,
        order: PosOrderMasterEntity
    ): Uri {

        val bitmap = Bitmap.createBitmap(
            600,
            800,
            Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bitmap)

        canvas.drawColor(Color.WHITE)

        val paint = Paint().apply {
            color = Color.BLACK
            textSize = 32f
            isAntiAlias = true
        }

        var y = 60f

        canvas.drawText("POS RECEIPT", 180f, y, paint)

        y += 80f
        canvas.drawText("Order #${order.srno}", 40f, y, paint)

        y += 50f
        canvas.drawText("Type: ${order.orderType}", 40f, y, paint)

        y += 50f
        canvas.drawText("Total: ₹${order.grandTotal}", 40f, y, paint)

        y += 50f
        canvas.drawText("Thank You!", 40f, y, paint)

        val file = File(
            context.cacheDir,
            "receipt_${order.id}.png"
        )

        FileOutputStream(file).use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }

        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
    }
}
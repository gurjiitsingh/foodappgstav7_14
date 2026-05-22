package com.it10x.foodappgstav7_14.utils.share

import android.content.Context
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.net.Uri
import androidx.core.content.FileProvider
import com.it10x.foodappgstav7_14.data.pos.entities.PosOrderMasterEntity
import java.io.File
import java.io.FileOutputStream

object ReceiptPdfGenerator {

    fun generatePdf(
        context: Context,
        order: PosOrderMasterEntity
    ): Uri {

        val pdfDocument = PdfDocument()

        val pageInfo = PdfDocument.PageInfo.Builder(
            600,
            900,
            1
        ).create()

        val page = pdfDocument.startPage(pageInfo)

        val canvas = page.canvas

        val paint = Paint().apply {
            textSize = 24f
            isAntiAlias = true
        }

        var y = 60

        canvas.drawText("POS RECEIPT", 200f, y.toFloat(), paint)

        y += 60
        canvas.drawText(
            "Order #${order.srno}",
            40f,
            y.toFloat(),
            paint
        )

        y += 40
        canvas.drawText(
            "Type: ${order.orderType}",
            40f,
            y.toFloat(),
            paint
        )

        y += 40
        canvas.drawText(
            "Total: ₹${order.grandTotal}",
            40f,
            y.toFloat(),
            paint
        )

        y += 40
        canvas.drawText(
            "Thank You!",
            40f,
            y.toFloat(),
            paint
        )

        pdfDocument.finishPage(page)

        val file = File(
            context.cacheDir,
            "receipt_${order.id}.pdf"
        )

        FileOutputStream(file).use {
            pdfDocument.writeTo(it)
        }

        pdfDocument.close()

        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
    }
}
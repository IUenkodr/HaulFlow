package com.yocam.haulflow.domain.usecase

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import com.opencsv.CSVWriter
import com.yocam.haulflow.domain.model.Transaction
import java.io.File
import java.io.FileWriter

class ExportUseCase(private val context: Context) {
    fun exportToCsv(transactions: List<Transaction>): File {
        val file = File(context.getExternalFilesDir(null), "HaulFlow_Ledger.csv")
        val writer = CSVWriter(FileWriter(file))
        writer.writeNext(arrayOf("ID", "Amount", "Type", "Category", "Note", "Date"))
        transactions.forEach { tx ->
            writer.writeNext(arrayOf(tx.id, tx.amount.toString(), tx.type.name, tx.category.name, tx.note, tx.timestamp.toString()))
        }
        writer.close()
        return file
    }

    fun exportToPdf(transactions: List<Transaction>): File {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas
        val paint = Paint()
        paint.textSize = 18f
        paint.isFakeBoldText = true
        canvas.drawText("TEAM YOCAM - FINANCIAL LEDGER", 50f, 50f, paint)
        paint.textSize = 12f
        paint.isFakeBoldText = false
        var yPosition = 80f
        transactions.forEach { tx ->
            canvas.drawText("${tx.timestamp} | ${tx.category} | ${tx.amount} | ${tx.type}", 50f, yPosition, paint)
            yPosition += 20f
        }
        pdfDocument.finishPage(page)
        val file = File(context.getExternalFilesDir(null), "HaulFlow_Ledger.pdf")
        pdfDocument.writeTo(java.io.FileOutputStream(file))
        pdfDocument.close()
        return file
    }
}

package com.it10x.foodappgstav7_14.ui.settings

import com.it10x.foodappgstav7_14.data.PrinterConfig
import com.it10x.foodappgstav7_14.data.PrinterRole

data class PrinterSettingsState(
    val printers: Map<PrinterRole, PrinterConfig> = emptyMap()
)

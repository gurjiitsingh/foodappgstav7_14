package com.it10x.foodappgstav7_14.fiscal

import com.it10x.foodappgstav7_14.fiskaly.GermanyFiscalService
import com.it10x.foodappgstav7_14.fiskaly.IndiaFiscalService
import com.it10x.foodappgstav7_14.fiskaly.SpainFiscalService
import com.it10x.foodappgstav7_14.fiskaly.FiskalyRepository

fun getFiscalService(
    country: String,
    fiskalyRepository: FiskalyRepository? = null
): FiscalService {

    return when (country) {
        "DE" -> GermanyFiscalService(fiskalyRepository!!)
        "IN" -> IndiaFiscalService()
        "ES" -> SpainFiscalService()
        else -> IndiaFiscalService()
    }
}
package com.vama.utils

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.vama.R

object FontFamilyUtils {
    val sfProDisplayFamily = FontFamily(
        Font(R.font.sf_pro_display_bold, FontWeight.Bold),
        Font(R.font.sf_pro_display_medium, FontWeight.Medium),
        Font(R.font.sf_pro_display_semibold, FontWeight.SemiBold),
        Font(R.font.sf_pro_display_regular, FontWeight.Normal)
    )
    val sfProTextFamily = FontFamily(
        Font(R.font.sf_pro_text_medium, FontWeight.Medium),
        Font(R.font.sf_pro_text_semibold, FontWeight.SemiBold),
    )
}
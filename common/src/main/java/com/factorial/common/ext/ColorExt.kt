package com.factorial.common.ext

import android.graphics.Color
import android.graphics.Color.parseColor

class ColorExt {

    companion object {

        // "color": "8D6B94"
        fun String.toColorOrBlack(): Int =
            runCatching { parseColor("#$this") }.getOrElse { Color.BLACK }

    }

}

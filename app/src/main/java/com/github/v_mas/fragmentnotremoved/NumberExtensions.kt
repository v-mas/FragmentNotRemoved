package com.github.v_mas.fragmentnotremoved

import android.content.Context
import android.content.res.Resources

/*
 * Created by Sławomir Golonka on 02-01-2019.
 */

inline fun Number.dp(context: Context) = dp(context.resources)
fun Number.dp(resources: Resources) = this.toFloat() * resources.displayMetrics.density
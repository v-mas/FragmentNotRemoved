package com.github.v_mas.fragmentnotremoved

/*
 * Created by Sławomir Golonka on 02-01-2019.
 */
val Any.identity get() = System.identityHashCode(this).toString(16)
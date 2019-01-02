package com.github.v_mas.fragmentnotremoved

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlin.random.Random
import kotlin.random.nextInt

/**
 * Created by Sławomir Golonka on 02-01-2019.
 */
class SomeFragment : Fragment() {
    companion object {
        private const val TAG = "v_bughunt_frag"
        private const val ARG_WIDTH = "V_W"
        private const val ARG_HEIGHT = "V_H"
        private const val ARG_COLOR = "V_C"

        fun newInstance(width: Int, height: Int, color: Int) = SomeFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_WIDTH, width)
                putInt(ARG_HEIGHT, height)
                putInt(ARG_COLOR, color)
            }
        }
    }

    private var activity: MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("create")
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity = context as? MainActivity
        log("attach")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        log("create view")
        return View(context).apply {
            val width = 100.dp(context).toInt()
            val height = 30.dp(context).toInt()
            val color = Color.rgb(Random.nextInt(0..255), Random.nextInt(0..255), Random.nextInt(0..255))

            layoutParams = ViewGroup.LayoutParams(
                arguments?.getInt(ARG_WIDTH, width) ?: width,
                arguments?.getInt(ARG_HEIGHT, height) ?: height
            )
            setBackgroundColor(arguments?.getInt(ARG_COLOR, color) ?: color)
        }
    }

    override fun onDestroyView() {
        log("destroy view")
        super.onDestroyView()
    }

    override fun onDetach() {
        log("detach")
        activity = null
        super.onDetach()
    }

    override fun onDestroy() {
        log("destroy")
        super.onDestroy()
    }

    private fun log(action: String) {
        val log = "$identity $action"
        Log.d(TAG, log)
        activity?.logAction(log)
    }
}
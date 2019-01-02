package com.github.v_mas.fragmentnotremoved

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random
import kotlin.random.nextInt

private const val TAG = "v_bughunt"
private const val FRAGMENT_CONTAINER = R.id.frag_container

/**
 * Created by Sławomir Golonka on 02-01-2019.
 */
class MainActivity : AppCompatActivity() {

    private val addClickListener: View.OnClickListener = View.OnClickListener {
        logAction("clicked add fragment")
        logAction("container have ${frag_container.childCount} views")
        logPreviousFragment()
        val prevFrag = supportFragmentManager.findFragmentById(FRAGMENT_CONTAINER)
        if (prevFrag != null) {
            logAction("\n!!! unable to add next fragment, container already have fragment!!!\n")
            return@OnClickListener
        }
        val width = Random.nextInt(100.dp(it.context).toInt()..300.dp(it.context).toInt())
        val height = Random.nextInt(10.dp(it.context).toInt()..40.dp(it.context).toInt())
        val color = Color.rgb(Random.nextInt(0..255), Random.nextInt(0..255), Random.nextInt(0..255))
        val nextFrag = SomeFragment.newInstance(width, height, color)
        logAction("created new fragment instance(${nextFrag.identity}) with size ${width}x${height} px and color 0x${color.toString(16)}")
        supportFragmentManager.beginTransaction().addAnim().replace(FRAGMENT_CONTAINER, nextFrag).commit()

        (it as TextView).setText(R.string.remove)
        it.setOnClickListener(removeClickListener)
    }

    private val removeClickListener: View.OnClickListener = View.OnClickListener {
        logAction("clicked remove fragment")
        logPreviousFragment()
        val prevFrag = supportFragmentManager.findFragmentById(FRAGMENT_CONTAINER)
        if (prevFrag != null) {
            logAction("  removing fragment ${prevFrag.identity}")
            supportFragmentManager.beginTransaction().addAnim().remove(prevFrag).commit()
        } else {
            logAction("  no previous fragment, nothing to do")
        }

        (it as TextView).setText(R.string.add)
        it.setOnClickListener(addClickListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setText(R.string.add)
        button1.setOnClickListener(addClickListener)
    }

    private fun logPreviousFragment() {
        val prevFrag = supportFragmentManager.findFragmentById(FRAGMENT_CONTAINER)
        logAction("  do container have fragment: ${prevFrag != null}")
        if (prevFrag != null) {
            logAction("    prev frag: identity: ${prevFrag.identity}")
            logAction("    prev frag: added: ${prevFrag.isAdded}")
            logAction("    prev frag: attached: ${!prevFrag.isDetached}")
            logAction("    prev frag: visible: ${prevFrag.isVisible}")
            logAction("    prev frag: removing: ${prevFrag.isRemoving}")
        }
    }

    @SuppressLint("SetTextI18n")
    fun logAction(action: String) {
        tv_log?.apply {
            text = "$text\n$action"
            post {
                (parent as ScrollView).smoothScrollTo(0, height)
            }
        }
        Log.d(TAG, action)
    }

    private fun FragmentTransaction.addAnim() =
        setCustomAnimations(R.anim.right_in, R.anim.left_out, R.anim.left_in, R.anim.right_out)
}

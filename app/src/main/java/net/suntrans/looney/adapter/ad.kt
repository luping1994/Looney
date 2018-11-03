package net.suntrans.looney.adapter

import android.view.View

/**
 * Created by Looney on 2018/2/2.
 * Des:
 */

class ad {
    private fun test() {
        val view: View? = null
        view!!.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(v: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
                v.removeOnLayoutChangeListener(this)
            }
        })
    }
}

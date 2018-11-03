package net.suntrans.looney.vedio

import android.support.v4.app.Fragment


/**
 * Created by Looney on 2018/2/6.
 * Des:
 */

open class BasedFragment : Fragment() {

    override fun onStop() {
        super.onStop()
        // clear all the subscription
    }
}

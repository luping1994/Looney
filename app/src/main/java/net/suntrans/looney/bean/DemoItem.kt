package net.suntrans.looney.bean

import android.app.Activity
import kotlin.reflect.KClass

/**
 * Created by Looney on 2018/2/1.
 * Des:
 */

class DemoItem(name:String,clazz: KClass<out Activity>) {
    var name: String? = name
    var clazz: KClass<out Activity>? = clazz
}

package net.suntrans.looney.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import net.suntrans.looney.R
import net.suntrans.looney.bean.DemoItem

/**
 * Created by Looney on 2018/2/1.
 * Des:
 */
class MainAdapter (layoutResId: Int, data: List<DemoItem>?) : BaseQuickAdapter<DemoItem, BaseViewHolder>(layoutResId, data){


    override fun convert(helper: BaseViewHolder?, item: DemoItem?) {
        helper!!.setText(R.id.name,item!!.name)
    }

}
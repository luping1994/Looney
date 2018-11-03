package net.suntrans.looney

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import net.suntrans.looney.adapter.MainAdapter
import net.suntrans.looney.bean.DemoItem
import net.suntrans.looney.cavans_demo.CanvasActivity
import net.suntrans.looney.custom_view.CoordinatorLayoutActivity
import net.suntrans.looney.custom_view.Demo1Activity
import net.suntrans.looney.custom_view.RefreshLayoutActivity
import net.suntrans.looney.databinding.ActivityMainBinding
import net.suntrans.looney.utils_activity.AndroidUtilsActivity
import net.suntrans.looney.vedio.AudioRecord
import net.suntrans.looney.vedio.CameraDemo
import net.suntrans.looney.window_manager.WindowManagerDemo1

/**
 * Created by Looney on 2018/1/26.
 * Des:
 */

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var datas: MutableList<DemoItem>? = null
    private var adapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        resources.displayMetrics.density=3.0f
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        datas = ArrayList()
        adapter = MainAdapter(R.layout.item_demo, datas)
        binding!!.recyclerView.adapter = adapter
        binding!!.recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        adapter!!.setOnItemClickListener { adapter, view, position ->
            var intent = Intent(this@MainActivity, datas!![position].clazz!!.java)
            startActivity(intent)
        }
    }


    override fun onResume() {
        super.onResume()
        initData()
    }


    fun initData() {


        datas!!.clear()
        val item = DemoItem("CoordinatorLayout", MainActivity::class)
        datas!!.add(item)

        val item2 = DemoItem("Coscos2d", MainActivity::class)
        datas!!.add(item2)

        val item3 = DemoItem("AndroidUtils", AndroidUtilsActivity::class)
        datas!!.add(item3)


        val item4 = DemoItem("自定义ViewDemo1", Demo1Activity::class)
        datas!!.add(item4)

        val item5 = DemoItem("WindowManager Use Demo", WindowManagerDemo1::class)
        datas!!.add(item5)


        val item6 = DemoItem("Cavans Use Demo", CanvasActivity::class)
        datas!!.add(item6)


        val item7 = DemoItem("USB Demo", CanvasActivity::class)
        datas!!.add(item7)

        val item8 = DemoItem("Camara Demo", CameraDemo::class)
        datas!!.add(item8)

        val item9= DemoItem("AudioRecord", AudioRecord::class)
        datas!!.add(item9)

        val item10= DemoItem("CoordinatorLayout", CoordinatorLayoutActivity::class)
        datas!!.add(item10)

        val item11= DemoItem("RefreshLayoutActivity", RefreshLayoutActivity::class)
        datas!!.add(item11)
        adapter!!.notifyDataSetChanged()


    }

}

package net.suntrans.looney.vedio;
import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import net.suntrans.looney.R;

/**
 * Created by Looney on 2018/8/21.
 * Des:
 * 采集工作很简单，我们只需要构造一个AudioRecord对象，然后传入各种不同配置的参数即可。一般情况下录音实现的简单流程如下：

 1. 音频源:我们可以使用麦克风作为采集音频的数据源。

 2. 采样率:一秒钟对声音数据的采样次数，采样率越高，音质越好。

 3.音频通道：单声道，双声道等，

 4.音频格式:一般选用PCM格式，即原始的音频样本。

 5.缓冲区大小:音频数据写入缓冲区的总数，可以通过AudioRecord.getMinBufferSize获取最小的缓冲区。（将音频采集到缓冲区中然后再从缓冲区中读取）。

 作者：DreamFish
 链接：https://www.jianshu.com/p/90c4071c7768
 來源：简书
 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
 */
public class AudioRecord extends AppCompatActivity {

    private String[] permissions = {Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions,101);
//            checkSelfPermission()
        }
    }
























    public void startRecord(View view) {

        System.out.println("startRecord");
    }

    public void play(View view) {
        System.out.println("play");

    }
}

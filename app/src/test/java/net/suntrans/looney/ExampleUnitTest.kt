package net.suntrans.looney

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    //获取完整命令 包头+order+crc+包尾
    fun getOrderWithCrc(order: String): String {
        var order = order
        order = order.replace(" ", "")
        val bytes = Converts.HexString2Bytes(order)
        val crc = Converts.GetCRC(bytes, 0, bytes.size)
        order = "ab68" + order + crc + "0d0a"
        return order
    }

    @Test
    fun testHostA() {


        val s = "http://ota.suntrans-cloud.com/ST-SLC-10_QQ_2.0.0.5.bin"
        var order = "41000000000003030003000000 006A"
        val length = getStringFormat(Integer.toHexString(s.length), 4)
        var urlHex = strToASCII(s)
        urlHex = toHexStringSetp2(urlHex)
        order = getOrderWithCrc(order + length + urlHex)

        println(order)
        //        AB 68 41 00 00 00 00 00 03 09 00 0D 31 39 32 2E 31 36 38 2E 31 2E 31 39 31 00 00 23 8D F4 4E 0D 0A

        //AB 68 41 00 00 00 00 00 03 09 00 0D 31 39 32 2E 31 36 38 2E 31 39 31 2E 31 00 00 23 8D B7 83 0D 0A

        //AB 68 41 00 00 00 00 00 03 09 00 0D 49 57 50 46 49 54 56 46 49 57 49 46 49 00 00 23 8D 76 96 0D 0A
        //        String ipHex = "";
        //        String portHex = "";
        //        String ipLength = "";
        //        String ip = "192.168.191.1";
        //        portHex = Integer.toHexString(9101);
        //
        //        portHex = getStringFormat(portHex, 8);
        //        ipHex = stringToAscii(ip);
        //
        //        System.out.println("ipHex:" + ipHex);
        //
        //        ipLength = getStringFormat(Integer.toHexString(ip.length()), 2);

        //        System.out.println("ip:" + ipHex);
        //        System.out.println("port:" + portHex);
        //        System.out.println("ipLength:" + ipLength);

        //        String order = "AB 68 41 00 00 00 00 00 03";
        //        order = order + "09 00";//09 00  socket1   // 0a 00  socket2
        //        order += ipLength;//域名长度
        //        order += ipHex;
        //        order += portHex;
        //
        //        order = order.replace(" ", "");
        //
        //        System.out.println(order.substring(4, order.length()));
        //        byte[] bytes = Converts.HexString2Bytes(order.substring(4, order.length()));
        //        String crc = Converts.GetCRC(bytes, 0, bytes.length);
        //
        //        order = order + crc + "0d0a";
        //
        //        order = order.toUpperCase();
        //
        //
        //        System.out.println(order);
        //        String  ipS = "31 39 32 2E 31 36 38 2E 31 2E 31 39 31";
        //        ipS = ipS.toLowerCase();

    }

    fun toHexStringSetp2(s: String): String {
        var str = ""
        var i = 0
        while (i < s.length - 2) {
            val sub = s.substring(i, i + 2)
            val ch = Integer.valueOf(sub)!!
            val s4 = Integer.toHexString(ch)
            str = str + s4
            i += 2
        }
        return str
    }


    private fun getStringFormat(str: String, targetLength: Int): String {
        val sb = StringBuilder()
        for (i in 0 until targetLength - str.length) {
            sb.append("0")
        }
        sb.append(str)
        return sb.toString()
    }
}

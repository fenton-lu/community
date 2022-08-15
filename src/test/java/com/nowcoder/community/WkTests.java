package com.nowcoder.community;

import java.io.IOException;

public class WkTests {

    public static void main(String[] args) {
        String cmd = "d:/work/wkhtmltopdf/bin/wkhtmltoimage --quality 75  https://www.nowcoder.com d:/CowCoderCommunity/data/wk-images/1.png";
        try {
            Runtime.getRuntime().exec(cmd); //操作系统执行命令与当前的程序是并发的（异步的）
            // 会先输出ok，再生成图片
            System.out.println("ok.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

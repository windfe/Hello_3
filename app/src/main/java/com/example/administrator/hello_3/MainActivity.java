package com.example.administrator.hello_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    HashMap<String,String> dictionary;//一次读取所有文件内容

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readAlltext();
    }

    private void readAlltext() {
        Scanner scan=new Scanner(getResources().openRawResource(R.raw.dortry));
        if (this.dictionary==null){
            this.dictionary=new HashMap<>();
            while(scan.hasNext()){
                String line=scan.nextLine();
                String[] piece = line.split(",");//split()返回的是数组，\t 为下标分隔标志
                this.dictionary.put(piece[0],piece[1]);
            }
        }
    }

    public void btOnclick(View view) {
        EditText textword=findViewById(R.id.text);
        String text=textword.getText().toString();//getText()方法返回的值不是String类型，需toString()转型
        if (this.dictionary==null){
            readDefinition(text);
        }
        String def=this.dictionary.get(text);//readDefinition(text);

        TextView tw=findViewById(R.id.textout);
        if (def==null){
            tw.setText("word not found");
        }else {
            tw.setText(def);
        }
    }

    private String readDefinition(String text) {
        //读取文件,及其内容,以下方式，文件需要放在特定的位置raw
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.dortry));

        while (scan.hasNext()) {//hasNext()每次读一行
                                //每次读一行并输出一个数组,有效率问题
                                //用到HashMap<key,values>读取全部内容
            String line = scan.nextLine();//打印出一行的内容
            String[] piece = line.split(",");//split()返回的是数组，\t 为下标分隔标志
            if (piece[0].equalsIgnoreCase(text)) {
                return piece[1];

            }

        }return null;

    }
}

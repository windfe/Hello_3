package com.example.administrator.hello_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    HashMap<String,String> dictionary;//һ�ζ�ȡ�����ļ�����

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
                String[] piece = line.split(",");//split()���ص������飬\t Ϊ�±�ָ���־
                this.dictionary.put(piece[0],piece[1]);
            }
        }
    }

    public void btOnclick(View view) {
        EditText textword=findViewById(R.id.text);
        String text=textword.getText().toString();//getText()�������ص�ֵ����String���ͣ���toString()ת��
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
        //��ȡ�ļ�,��������,���·�ʽ���ļ���Ҫ�����ض���λ��raw
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.dortry));

        while (scan.hasNext()) {//hasNext()ÿ�ζ�һ��
                                //ÿ�ζ�һ�в����һ������,��Ч������
                                //�õ�HashMap<key,values>��ȡȫ������
            String line = scan.nextLine();//��ӡ��һ�е�����
            String[] piece = line.split(",");//split()���ص������飬\t Ϊ�±�ָ���־
            if (piece[0].equalsIgnoreCase(text)) {
                return piece[1];

            }

        }return null;

    }
}

package com.chenggoi.dududu;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by chenggoi on 16-8-5.
 */

public class DududuActivity extends Activity implements Runnable {
    private Socket socket = null;
    private Button sendButton;
    private TextView msgReceive;
    private EditText msgSend;
    private EditText host;
    private EditText port;
    private Button connect;
    private LinearLayout connectLayout;
    private String HOST = "";
    private int PORT = 0;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String content = "";
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            msgReceive.setText(msgReceive.getText().toString() + content);
        }
    };
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            Log.e("Dududu", e.toString());
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dududu_activity);
        msgReceive = (TextView) findViewById(R.id.msg_text);
        msgSend = (EditText) findViewById(R.id.msg_input);
        sendButton = (Button) findViewById(R.id.send_button);

        host = (EditText) findViewById(R.id.host_edit);
        port = (EditText) findViewById(R.id.port_edit);
        connect = (Button) findViewById(R.id.connect_button);
        connectLayout = (LinearLayout) findViewById(R.id.connect_layout);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final boolean isRemember = sharedPreferences.getBoolean("remember", false);
        if (isRemember) {
            host.setText(sharedPreferences.getString("host", ""));
            port.setText(sharedPreferences.getString("port", ""));
        }

        msgReceive.setMovementMethod(new ScrollingMovementMethod());

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = msgSend.getText().toString();
                if (socket.isConnected() && !socket.isClosed()) {
                    if (!socket.isOutputShutdown()) {
                        out.println(msg);
                        msgSend.setText("");
                    }
                }
            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((HOST = host.getText().toString()) != null && port.getText().toString() != null) {
                    PORT = Integer.parseInt(port.getText().toString());
                    new Thread(DududuActivity.this).start();
                    connectLayout.setVisibility(View.GONE);
                    if (!isRemember) {
                        editor = sharedPreferences.edit();
                        editor.putString("host", HOST);
                        editor.putString("port", port.getText().toString());
                        editor.putBoolean("remember", true);
                        editor.commit();
                    }
                }
            }
        });

    }

    @Override
    public void run() {

        if (socket == null) {
            try {
                socket = new Socket(HOST, PORT);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Dududu", e.toString());
            }
        }

        try {
            while (true) {
                if (socket.isConnected() && !socket.isClosed()) {
                    if (!socket.isInputShutdown()) {
                        if ((content = in.readLine()) != null) {
                            content += "\n";
                            mHandler.sendMessage(mHandler.obtainMessage());
                        }
                    }
                }
            }
        } catch (Exception e) {

            Log.e("Dududu", e.toString());
        }
    }
}

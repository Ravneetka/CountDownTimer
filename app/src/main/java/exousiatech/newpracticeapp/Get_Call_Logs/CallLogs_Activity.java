package exousiatech.newpracticeapp.Get_Call_Logs;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.provider.CallLog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import exousiatech.newpracticeapp.Get_Call_Logs.SMTP_send_email.GMailSender;
import exousiatech.newpracticeapp.R;

public class CallLogs_Activity extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<CallLogsPojo> list;
    Call_Logs_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_logs_);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        list = new ArrayList<>();
        list = getUserCallLogs();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(CallLogs_Activity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new Call_Logs_Adapter(CallLogs_Activity.this,list);
        recyclerView.setAdapter(adapter);
        if (isOnline()){
            Log.e(TAG,"bnda connected h");
            sendEmailUsingSMTP();
        }else {
            Log.e(TAG,"Net nhi chal rha h");
        }
    }

    public List<CallLogsPojo> getUserCallLogs(){
        List<CallLogsPojo> list = new ArrayList<>();
        Uri allCalls = Uri.parse("content://call_log/calls");
        Cursor cursor = managedQuery(allCalls,null,null,null,null);
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        while (cursor.moveToNext()){
            CallLogsPojo callLogsPojo = new CallLogsPojo();
            callLogsPojo.setPhoneNumber(cursor.getString(number));
            callLogsPojo.setType(cursor.getString(type));
            callLogsPojo.setDate(cursor.getString(date));
            callLogsPojo.setDuration(cursor.getString(duration));
            list.add(callLogsPojo);
        }
        cursor.close();
        return list;
    }

    public boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnectedOrConnecting()){
            return true;
        }
        return false;
    }

    public void sendEmailUsingSMTP(){
        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender(
                            "applancer.exousia@gmail.com",
                            "applancer121");
//                    sender.addAttachment("C:\\Users\\Admin\\Pictures\\appicon.png");
                    sender.sendMail("Test mail", "This mail has been sent from android app along with attachment",
                            "applancer.exousia@gmail.com",
                            "jai.exousia@gmail.com");
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                }
            }
        },1000);
//        new Thread(new Runnable() {
//            public void run() {
//
//            }
//        }).start();
//        Session session = createSession();
//        try {
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("applancer.exousia@gmail.com"));
//            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("jai.exousia@gmail.com"));
//            message.setSubject("Testing Mail using SMTP");
//            message.setText("Dear Varsha ,"+"\n\n No spam to my email, please!");
//            MimeBodyPart mimeBodyPart = new MimeBodyPart();
//            Multipart multipart = new MimeMultipart();
//            mimeBodyPart = new MimeBodyPart();
//            String file = "path/of/file/to/be/attached";
//            String filename = "attachmentName";
//            DataSource dataSource =new FileDataSource(file);
//            mimeBodyPart.setDataHandler(new DataHandler(dataSource));
//            mimeBodyPart.setFileName(filename);
//            multipart.addBodyPart(mimeBodyPart);
//            message.setContent(multipart);
//            Transport.send(message);
//            Log.e(TAG,"DOne .....Mail send ho gyi h ");
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }

    }

    public Session createSession(){
        Log.e(TAG,"Session create hone shuru ho gya h");
        final String username = "applancer.exousia@gmail.com";
        final String password = "applancer121";
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return super.getPasswordAuthentication();
            }
        });
    }
}

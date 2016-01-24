materiali per un corso Android

AM001_Intent ******************************************

Activity
  onCreate()
Button
  setOnClickListener
    onClick
Toast
Intent

AM002_Intent ******************************************

Activity
  finish()
  onActivityResult()
  setResult()
Bundle
Intent
  putInt()
  putString()
  putExtra()

AM003_Receiver ****************************************

PendingIntent
getSystemService()
BroadcastReceiver
  onReceive()

AM004_Receiver ****************************************

<receiver android:name="MyReceiver" >
  <intent-filter>
    <action android:name="MY_ACTION" />
  </intent-filter>
</receiver>

Intent i = new Intent(MY_ACTION)

AM005_Map **********************************************

Intent i = new Intent(android.content.Intent.ACTION_VIEW);
i.setData(Uri.parse("geo:45.495403,12.257509"));

AM006_Activity *****************************************

Notification ... "Activity LifeCycle"

AM007_Browser ******************************************

<activity android:name=".Browser">
            <intent-filter>
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <data android:scheme="http" />
            </intent-filter>
        </activity>







package com.tcl.download.test;

import com.tcl.download.DownloadManager;
import com.tcl.download.DownloadManager.Request;
import com.tcl.download.Downloads;
import com.tcl.download.R;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

//ContentValues values = new ContentValues();
//values.put(Downloads.URI, url);//指定下载地址
//values.put(Downloads.COOKIE_DATA, cookie);//如果下载Server需要cookie,设置cookie
//values.put(Downloads.VISIBILITY,Downloads.VISIBILITY_HIDDEN);//设置下载提示是否在屏幕顶部显示 
//values.put(Downloads.NOTIFICATION_PACKAGE, getPackageName());//设置下载完成之后回调的包名 
//values.put(Downloads.NOTIFICATION_CLASS, DownloadCompleteReceiver.class.getName());//设置下载完成之后负责接收的Receiver，这个类要继承BroadcastReceiver      
//values.put(Downloads.DESTINATION,save_path);//设置下载到的路径，这个需要在Receiver里自行处理
//values.put(Downloads.TITLE,title);//设置下载任务的名称
//this.getContentResolver().insert(Downloads.CONTENT_URI, values);//将其插入到DownloadManager的数据库中，数据库会触发修改事件，启动下载任务
//
//如何为DownloadManager设置代理，比如Wap
//values.put(Downloads.PROXY_HOST,"10.0.0.172");
//values.put(Downloads.PROXY_PORT,"80");

public class TestActivity extends Activity {

	DownloadManager downloadManager;
	DownLoadCompleteReceiver receiver;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if ( 0 == msg.what) {
				Log.i("kevint", "all="+msg.arg2+",curr="+msg.arg1+",status="+msg.obj);
			}
		}
	};
//	protected = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		reg();
		setContentView(R.layout.test_layout);
		
		downloadManager = new DownloadManager(getContentResolver(), getPackageName());
		findViewById(R.id.click_download).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// String url = "http://10.0.8.131:8080/files/xxxx.png";
				String url = "http://10.0.8.131:8080/files/2.zip";

				Request request = new Request(Uri.parse(url));

				// 设置在什么网络情况下进行下载
				request.setAllowedNetworkTypes(Request.NETWORK_WIFI);
				// 设置通知栏标题
				request.setNotificationVisibility(Request.VISIBILITY_VISIBLE);
				request.setTitle("下载");
				request.setDescription("今日头条正在下载");
				request.setAllowedOverRoaming(false);
				// 设置文件存放目录
				request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "2.zip");

				long mDownloadId  = downloadManager.enqueue(request);
				regCr(mDownloadId);
				Log.i("kevint", "click for download");
			}
		});
		
		findViewById(R.id.click_download1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// String url = "http://10.0.8.131:8080/files/xxxx.png";
				String url = "http://10.0.8.131:8080/files/3.zip";

				Request request = new Request(Uri.parse(url));

				// 设置在什么网络情况下进行下载
				request.setAllowedNetworkTypes(Request.NETWORK_WIFI);
				// 设置通知栏标题
				request.setNotificationVisibility(Request.VISIBILITY_VISIBLE);
				request.setTitle("下载3");
				request.setDescription("3今日头条正在下载");
				request.setAllowedOverRoaming(false);
				// 设置文件存放目录
				request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "3.zip");

				long mDownloadId  = downloadManager.enqueue(request);
				regCr(mDownloadId);
				Log.i("kevint", "click for download");
			}
		});
		
		findViewById(R.id.click_download2).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// String url = "http://10.0.8.131:8080/files/xxxx.png";
				String url = "http://10.0.8.131:8080/files/4.zip";

				Request request = new Request(Uri.parse(url));

				// 设置在什么网络情况下进行下载
				request.setAllowedNetworkTypes(Request.NETWORK_WIFI);
				// 设置通知栏标题
				request.setNotificationVisibility(Request.VISIBILITY_VISIBLE);
				request.setTitle("下载4");
				request.setDescription("4今日头条正在下载");
				request.setAllowedOverRoaming(false);
				// 设置文件存放目录
				request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "4.zip");

				long mDownloadId  = downloadManager.enqueue(request);
				regCr(mDownloadId);
				Log.i("kevint", "click for download");
			}
		});
	}

	private class DownLoadCompleteReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
				long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
				// Toast.makeText(TestActivity.this, "编号："+id+"的下载任务已经完成！",
				// Toast.LENGTH_SHORT).show();
				Log.i("kevint", "=======编号：" + id + "的下载任务已经完成！");
			} else if (intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)) {
				// Toast.makeText(TestActivity.this, "别瞎点！！！",
				// Toast.LENGTH_SHORT).show();
				Log.i("kevint", "别瞎点！！！");
			}
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		ureg();
		super.onDestroy();
	}

	class DownloadChangeObserver extends ContentObserver {
		private Handler handler;
		private long downloadId;

		public DownloadChangeObserver(Handler handler, long downloadId) {
			super(handler);
			this.handler = handler;
			this.downloadId = downloadId;
		}

		@Override
		public void onChange(boolean selfChange) {
			updateView(handler, downloadId);
		}
	}

	// 注册ContentResolver
	void regCr(long id) {
		getContentResolver().registerContentObserver(Uri.parse("content://kdownloads/my_downloads"), true,
				new DownloadChangeObserver(mHandler, id));
	}
	
	void unRegCr() {
//		getContentResolver().unregisterContentObserver(observer);
	}

	// updateView()方法，获取进度，通过handle发送消息，更新UI
	public void updateView(Handler handler, long downloadId) {
		// 获取状态和字节
		int[] bytesAndStatus = getBytesAndStatus(downloadId);
		//
		handler.sendMessage(handler.obtainMessage(0, bytesAndStatus[0], bytesAndStatus[1], bytesAndStatus[2]));
	}

	public int[] getBytesAndStatus(long downloadId) {
		int[] bytesAndStatus = new int[] { -1, -1, 0 };
		DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
		Cursor c = null;
		try {
			c = downloadManager.query(query);
			if (c != null && c.moveToFirst()) {
				// 当前下载的字节
				bytesAndStatus[0] = c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
				// 总字节数
				bytesAndStatus[1] = c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
				// 状态
				bytesAndStatus[2] = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return bytesAndStatus;
	}

	void reg() {
		if (receiver == null) {
			receiver = new DownLoadCompleteReceiver();
			IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
			filter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
			registerReceiver(receiver, filter);
		}
	}

	void ureg() {
		if (receiver != null) {
			unregisterReceiver(receiver);
		}
	}
	
	
//	上面这种做法可能对性能有些损耗，因为会不断触发onChange
//	推荐使用ScheduledExecutorService
//	public static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
//	Runnable command = new Runnable() {
//
//	    @Override
//	    public void run() {
//	        updateView();
//	    }
//	};
//	scheduledExecutorService.scheduleAtFixedRate(command, 0, 3, TimeUnit.SECONDS);
}

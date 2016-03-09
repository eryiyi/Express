package com.seeker.liuhe.avos;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;

import com.eln.lib.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;


public class UploadTestActivity extends Activity {
	
	final static String APP_ID="wjm04batbnzn0rigyo6zzwcz5rzntlyvgv3l6y7bsy2bncy9";
	final static String APP_KEY="3d437nbjhrwd6ys610km914mowl5puz2zit2f7t7815rhpcv";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
			        
			AssetManager am = this.getAssets();
			InputStream inputStream;
			try {
//				inputStream =am.open("html/js/equipt.js");
//				Reader reader=new InputStreamReader(inputStream);
//			
////				Map<String, JsonElement> map = GsonUtil.fromJson2MapJson(streamStr);
//				ArrayList<Equip> dataList=new ArrayList<Equip>();
//				Gson gson=new Gson();
//				Map<String, JsonElement> map = gson.fromJson(reader,
//						new TypeToken<Map<String, JsonElement>>() {
//						}.getType());
//				for (String key : map.keySet()) {
//						Equip hero=new Equip();
//						hero.item_id=key;
//						JsonElement element=map.get(key);
//						Map<String, JsonElement> detail=gson.fromJson(element, new TypeToken<Map<String, JsonElement>>() {
//						}.getType());
//						hero.name=detail.get("name").getAsString();
//						hero.pic=detail.get("pic").getAsString();
//						hero.sx=detail.get("sx").getAsString();
//						hero.fdjn=detail.get("fdjn").toString();
//						hero.ms=detail.get("ms").getAsString();
//						dataList.add(hero);
////						 Log.e("parse ", hero.name);
//					  }				 
//				
//				AVOSCloud.initialize(getApplication(), APP_ID, APP_KEY);
//				for (Equip bean : dataList) {
//					AVObject gameScore = new AVObject("yh_equip");
//					gameScore.put("name", bean.name);
//					gameScore.put("item_id", bean.item_id);
//					gameScore.put("pic", bean.pic);
//					gameScore.put("sx", bean.sx);
//					gameScore.put("fdjn", bean.fdjn);
//					gameScore.put("ms", bean.ms);
//					gameScore.saveInBackground();
//
//				}
				
//				AVOSCloud.initialize(getApplication(), APP_ID, APP_KEY);
//				File parent=new File(Environment.getExternalStorageDirectory() + "/pic/");
//
//				String filePath[]=parent.list();
//				List<AVFile> fileList = new LinkedList<AVFile>();
//				int i=0;
//				for (String string : filePath) {
//					i++;
//					if(i<73) continue;
//					Log.e("parse", string);
//					File localFile=new File(Environment.getExternalStorageDirectory() + "/pic/"+string);
//					AVFile file = AVFile.withFile(string, localFile);
//					fileList.add(file);
//					AVObject parseObject = new AVObject("ls_pic");
//					 parseObject.put("pic", file);
//				    parseObject.saveInBackground();
//				    Thread.sleep(10000);
//				}
			    
//				 AVObject parseObject = new AVObject("ls_pic");
//				    parseObject.addAll("file_array", fileList);
//				    parseObject.saveInBackground();

//				File localFile=new File(Environment.getExternalStorageDirectory() + "/pic/"+"Abomination.jpg");
////				localFile.length()canRead()isFile()exists();
//				AVFile file = AVFile.withAbsoluteLocalPath("newfest.jpg", Environment.getExternalStorageDirectory() + "/test.jpg");
//				
//				file.saveInBackground();
//						AVFile.withFile("Abomination.jpg", localFile);
//				 AVObject parseObject = new AVObject("ls_pic");
//				 parseObject.put("ms", file);
//			    parseObject.saveInBackground();
//				File file=new File(Environment.getExternalStorageDirectory() + "/test.jpg");
//		        byte[] data = readFile(file);
//		        if (data == null) {
//		            Toast.makeText(this, "File is too big to upload.", Toast.LENGTH_LONG).show();
//		            return;
//		        }
//		         AVFile avFile = new AVFile(file.getName(), data);
//		        avFile.saveInBackground();
		        
			}catch(Exception e){
				e.printStackTrace();
//				 Log.e("parse ", e.printStackTrace());
			}

	}	
	
	public static String inputStream2String(InputStream is) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int i = -1;
			while ((i = is.read()) != -1) {
				baos.write(i);
			}
			return baos.toString();

		} catch (Exception e) {
			return null ;
		}
	}


    private byte[] readFile(File file) {
        RandomAccessFile rf = null;
        byte[] data = null;
        try {
            rf = new RandomAccessFile(file, "r");
            int length = (int)rf.length();
            if (length >= 5 * 1024 * 1024) {
                return null;
            }
            data = new byte[length];
            rf.readFully(data);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
//            closeQuietly(rf);
        }
        return data;
    }
	
	
    public void onClickEvent(View view) {
//    	startActivity(view.getId());
//    	
//		File file=new File(Environment.getExternalStorageDirectory() + "/test.jpg");
//        byte[] data = readFile(file);
//        if (data == null) {
//            Toast.makeText(this, "File is too big to upload.", Toast.LENGTH_LONG).show();
//            return;
//        }
//         AVFile avFile = new AVFile(file.getName(), data);
//        avFile.saveInBackground();
    }
    

}

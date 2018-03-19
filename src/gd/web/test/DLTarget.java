package gd.web.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class DLTarget {

	public DLTarget(){}
	public DLTarget(String val, int number){
		rootPath = "http://www.bdsdh99.com/article-show-id-"+ val +".html";
		targetPath = "D:\\Tomcat 8.0\\webapps\\gd\\sources\\images\\pic\\pic_col0\\pic"+number+"\\";
		specificPath = rootPath;
	}
	//631390, 631387, 630665, 630666, 623718-623721
	private String targetPath = "";
	private String rootPath = "http://www.bdsdh99.com/article-show-id-"+ 630666 +".html";//dsssd ereretersd
	private String specificPath = rootPath;//"https://www.csdn.net/";//s
	private boolean IKnowImgStartWithHttps = false;
	
	public void start() throws IOException{
		String url = rootPath;
		int count = 1;
		
		String pageContent = getContentFromUrl(url);
		LinkedList<String> imgUrlList = getImgUrlFromContent(pageContent, url);
		System.out.println("downloadImg start!");
		File dir = new File(targetPath);
		if(dir.exists()){
			deleteDir(dir);
		}
		while(!imgUrlList.isEmpty()){
			System.out.println("Thread ID--"+Thread.currentThread().getId());
			//String imgUrl = imgUrlList.removeFirst();
			downloadImg(targetPath, imgUrlList.removeFirst(),count);
			System.out.println(imgUrlList.size()+" url remained");
			count++;
		}
		System.out.println("downloadImg end!");
	}
	
	
	private String getContentFromUrl(String url) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null ;
        try {
        	//addElemToVisited(url);
        	InputStream inS; 
        	if(url.startsWith("https://")){
				//new
				HttpClient httpClient = new DefaultHttpClient();
				httpClient = HttpsClient.getNewHttpsClient(httpClient);
				HttpGet httpGet = new HttpGet(url);
				HttpResponse response = httpClient.execute(httpGet);
				HttpEntity entity = response.getEntity();
				inS = entity.getContent();
			}else{
				URL uri = new URL(url);
				//start
				URLConnection urlcon = uri.openConnection();
				
				//将爬虫连接伪装成浏览器连接
				urlcon.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
				//HttpURLConnection httpc = (HttpURLConnection)urlcon;
				urlcon.connect();
				
				inS = urlcon.getInputStream();
			}

            in = new BufferedReader(new InputStreamReader(inS,FindLink.charset));  
            String line = "";  
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
 
        } catch (MalformedURLException e) {
            System.out.print("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
 
        return result.toString();
}  

	public LinkedList<String> getImgUrlFromContent(String content,String oriUrl){
		LinkedList<String> finalUrlList = new LinkedList<String>();
		String title = getTitle(content);
		//check the img url is surrounding by """ or "'" .
		boolean isSingleQuoteMark = false;
        String[] contents = content.split("<img"); 
        for(int i = 0; i < contents.length; i++){
			int length = contents[i].indexOf(">");
			contents[i] = contents[i].substring(0,length);
		}
        String sepStr = "src";
        for(String url : contents){
        	//int start = url.indexOf("src=");
        	
        	if(url.indexOf("data-original=") >=0 || url.indexOf("data-original =") >= 0){
        		sepStr = "data-original";
        	}
        	int start = url.indexOf(sepStr+"=");
        	if(start<0){
        		start = url.indexOf(sepStr+" =");
        		//start = url.indexOf("src =");
        		if(start < 0 ){
        			continue;
        		}
        	}
        	if(start<0){
        		continue;
        	}
        	url = url.substring(start, url.length());
        	int startOfDoubleQuote = url.indexOf("\"");
        	int startOfSingleQuote = url.indexOf("'");
        	if((startOfDoubleQuote==startOfSingleQuote)&&startOfSingleQuote==-1){
        		continue;
        	}
        	if(startOfDoubleQuote<0){
        		isSingleQuoteMark = true;
        		start = startOfSingleQuote;
        	}else if(startOfSingleQuote<0){
        		isSingleQuoteMark = false;
        		start = startOfDoubleQuote;
        	}else if(startOfDoubleQuote > startOfSingleQuote){
        		isSingleQuoteMark = true;
        		start = startOfSingleQuote;
        	}else{
        		isSingleQuoteMark = false;
        		start = startOfDoubleQuote;
        	}
        	url = url.substring(start+1, url.length());
        	int end = isSingleQuoteMark ? url.indexOf("'") : url.indexOf("\"");
        	String finalUrl = title + "?" + getImgFinalURL(url.substring(0,end),oriUrl);
        	
        	finalUrlList.add(finalUrl);
        }
        return finalUrlList;
	}
	
	private String getImgFinalURL(String href, String requestUrl) {  
		
        /* 内外部链接最终转化为完整的链接格式 */
        String finalUrl = "";
  
        //check if outside url and match specificPath.
        if (href.startsWith("http://") || href.startsWith("https://")) {
        	finalUrl = href;
        } else {
        	//supplement url according to oriUrl
			String tempURL = "";
			String domain = "";
			int protocolLength = 0;
			if(requestUrl.startsWith("http://")){
				tempURL = requestUrl.replace("http://","");
				protocolLength = 7;
			}
			if(requestUrl.startsWith("https://")){
				tempURL = requestUrl.replace("https://","");
				protocolLength = 8;
			}
			
            if (href.startsWith("/")) {
				int splittoken = tempURL.indexOf("/");
				if(splittoken<0){
					domain = requestUrl;
				}else{
					domain = requestUrl.substring(0, splittoken + protocolLength);
				}
				finalUrl = domain + href;
            }else{
				int splittoken = tempURL.lastIndexOf("/");
				if(splittoken<0){
					domain = requestUrl;
				}else{
					domain = requestUrl.substring(0, splittoken + 1 + protocolLength);
				}
				finalUrl = domain + href;  
            }
        }
        return finalUrl;
    }	
	private String getTitle(String content){
		int start = content.indexOf("<title");
		if(start < 0){
			return "ntitle";
		}
		content = content.substring(start, content.length());
		content = content.replace("<title", "");
		start = content.indexOf(">");
		content = content.substring(start+1, content.length());
		start = content.indexOf("<");
		content = content.substring(0,start);
		return content;
	}

	public void downloadImg(String path, String imgUrl,int count){
		File dir = new File(path);
		
		if(!dir.exists()){
			if(dir.mkdirs()){
				System.out.println("dir create success. --" + path);
			}else{
				System.out.println("dir create failed! --" + path);
				return;
			}
		}
		int splitPosition = imgUrl.indexOf("?http");
		if(splitPosition >= 0 ){
			imgUrl = imgUrl.substring(splitPosition+1, imgUrl.length());
		}
		try{
				System.out.println(imgUrl);
				String imgName = imgUrl.substring(imgUrl.lastIndexOf("/")+1, imgUrl.length());
				if(count < 10){
					imgName = "000"+count+".jpg";
				}else if(count < 100){
					imgName = "00"+count+".jpg";
				}else{
					imgName = "0"+count+".jpg";
				}
				
				InputStream in = null;
				//some image url are wrong
				if(imgUrl.startsWith("http://") && Temp.IKnowImgStartWithHttps){
					//if you know img have "https"
					imgUrl = imgUrl.replace("http://", "https://");
				}
				
				if(imgUrl.startsWith("https://")){
					//new
					HttpClient httpClient = new DefaultHttpClient();
					httpClient = HttpsClient.getNewHttpsClient(httpClient);
					HttpGet httpGet = new HttpGet(imgUrl);
					HttpResponse response = httpClient.execute(httpGet);
					HttpEntity entity = response.getEntity();
					in = entity.getContent();
				}
				else{
					URL uri = new URL(imgUrl);
					//start
					URLConnection urlcon = uri.openConnection();
					
					//将爬虫连接伪装成浏览器连接
					urlcon.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
					//HttpURLConnection httpc = (HttpURLConnection)urlcon;
					urlcon.connect();
					
					in = urlcon.getInputStream();
					
					//end
					
					//in = uri.openStream();
				}
				FileOutputStream fo = new FileOutputStream(new File(path + File.separator + imgName));
	            byte[] buf = new byte[1024];  
	            int length = 0;  
	            while ((length = in.read(buf, 0, buf.length)) != -1) {
	            	fo.write(buf, 0, length);
	            }  
	            in.close();
	            fo.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
		}
	}
	private boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
	}

}

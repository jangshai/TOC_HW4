import java.io.*;
import java.net.*;
import java.util.ArrayList;

import org.json.*;
public class TOC_HW4 {

	public static void main(String[] argv) throws IOException, JSONException , NullPointerException {
		try{
		// TODO Auto-generated method stub
		    String url=new String(argv[0]);
			URL myurl = new URL(url);
			URLConnection myconnection = myurl.openConnection();
			myconnection.connect();
			BufferedReader BR = new BufferedReader(new InputStreamReader(myurl.openStream(),"UTF-8"));
			String temp;
			StringBuilder builder = new StringBuilder();
			while ((temp = BR.readLine()) != null)
			{
				builder.append(temp);
				//System.out.println(temp);
			}
	        String json = builder.toString();
			JSONTokener jsontokener = new JSONTokener(json);
			JSONArray jsonarray = new JSONArray(jsontokener);
			ArrayList<String> roadarray = new ArrayList<String>();
			ArrayList<Integer> numinroad = new ArrayList<Integer>();
			for(int i=0;i<jsonarray.length();i++)
			{
				
				JSONObject jsonob = jsonarray.getJSONObject(i);
				String temp1 = jsonob.getString("土地區段位置或建物區門牌");
				int k=0;
				
				if(temp1.contains("路"))
				{
					temp1=temp1.substring(0,temp1.indexOf("路")+1);
					k=1;
					//System.out.println(temp1);
				}
				else if(temp1.contains("街"))
				{
					temp1=temp1.substring(0,temp1.indexOf("街")+1);
					k=1;
					//System.out.println(temp1);
				}
				else if(temp1.contains("大道"))
				{
					temp1=temp1.substring(0,temp1.indexOf("大道")+2);
					k=1;
					//System.out.println(temp1);
				}
				else if(temp1.contains("巷"))
				{
					temp1=temp1.substring(0,temp1.indexOf("巷")+1);
					k=1;
					//System.out.println(temp1);
				}
				if(k==1)
				{
					if(roadarray.contains(temp1))
					{
						int temp3=roadarray.indexOf(temp1);
						ArrayList<Integer> months = new ArrayList<Integer>();
						
						for(int j=0;j<jsonarray.length();j++)
						{
							
							JSONObject aaa = jsonarray.getJSONObject(j);
								int compare=aaa.getInt("交易年月");
								String temp5 = aaa.getString("土地區段位置或建物區門牌");
								//System.out.println(compare);
								
									if(temp5.contains(temp1))
									{
										if(months.contains(compare))
										{}
										else
										months.add(compare);
									//int temp4=numinroad.get(temp3);
									//temp4=temp4+1;
									//numinroad.set(temp3, temp4);
								
									}
						
						}
						numinroad.set(temp3,months.size());
											
					}
					else 
					{
						roadarray.add(temp1);
						int temp3=roadarray.indexOf(temp1);
						numinroad.add(temp3, 0);
					}
					

				}
			}
			//for(int i=0;i<roadarray.size();i++)
			//{
			//	System.out.println(i+":"+roadarray.get(i)+"  "+numinroad.get(i));
			//}
			ArrayList<String> aa = new ArrayList<String>();
			int maxnum=0;
			for(int i=0;i<roadarray.size();i++)
			{
				if(maxnum<numinroad.get(i))
				{
					maxnum=numinroad.get(i);
				}
			}
			//System.out.println("max:"+maxnum);
			for(int i=0;i<roadarray.size();i++)
			{
				if(numinroad.get(i)==maxnum)
				{
					aa.add(roadarray.get(i));
					//System.out.println(roadarray.get(i));
				}
			}
			for(int i=0;i<aa.size();i++)
			{
				int max=0;
				int min=0;
				for(int j=0;j<jsonarray.length();j++)
				{
					JSONObject jsonob = jsonarray.getJSONObject(j);
					String temp1 = jsonob.getString("土地區段位置或建物區門牌");
					int temp7 = jsonob.getInt("總價元");
					//System.out.println(temp1);
					if(temp1.contains(aa.get(i)))
					{
						//System.out.println(j+"  "+temp7);
						if(max<temp7)
						max=temp7;
						if(min==0)
						min=temp7;
						if(min>temp7)
						min=temp7;
							
						
					}
				}
				System.out.println(aa.get(i)+",最高成交價"+max+",最低成交價"+min);
			}
				
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}

}

